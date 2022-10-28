package RestApp.src.test.java.ru.umar.onlinestore.restapp;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import com.fasterxml.jackson.databind.SerializationFeature;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import static org.springframework.http.MediaType.APPLICATION_JSON_UTF8;

import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import ru.umar.onlinestore.restapp.models.T_Shirt;
import ru.umar.onlinestore.restapp.services.T_ShirtService;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;;

@RunWith(SpringRunner.class)
@SpringBootTest
@WithMockUser(roles = "ADMIN")
@AutoConfigureMockMvc
public class T_ShirtControllerMockTests {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private T_ShirtService t_shirtService;

    @Before
    public void addT_ShirtInDb(){
        T_Shirt t_shirt1 = new T_Shirt(15645, "Armani man's t_shirt", "Black", 6999);
        t_shirtService.save(t_shirt1);
    }

    @After
    public void deleteT_ShirtFromDb(){
        t_shirtService.deleteAll();
    }

    @Test
    public void postT_ShirtTest() throws Exception {
        T_Shirt t_shirt1 = new T_Shirt(1, "Armani man's t_shirt", "Black", 6999);
        ObjectMapper mapper = new ObjectMapper();
        mapper.configure(SerializationFeature.WRAP_ROOT_VALUE, false);
        ObjectWriter ow = mapper.writer().withDefaultPrettyPrinter();
        String requestJson=ow.writeValueAsString(t_shirt1);
        mockMvc.perform(post("/api/t_shirt").contentType(APPLICATION_JSON_UTF8)
                        .content(requestJson))
                .andExpect(status().isCreated());
    }

    @Test
    public void getAllT_ShirtsTest() throws Exception{
        mockMvc.perform( get("/api/t_shirt"))
                .andDo(print())
                .andExpect(status().isOk());
    }

    @Test
    public void getOneT_ShirtTest() throws Exception{
        mockMvc.perform( get("/api/t_shirt/{id}", 15645))
                .andDo(print())
                .andExpect(status().isOk());
    }


    @Test
    public void updateT_ShirtTest() throws Exception{
        T_Shirt updatedT_shirt1 = new T_Shirt( "Armani woman's t_shirt", "Black", 6999);
        ObjectMapper mapper = new ObjectMapper();
        String jsonRequest = mapper.writeValueAsString((updatedT_shirt1));

        mockMvc.perform(put("/api/t_shirt/update/{id}", 15645)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(jsonRequest))
                .andDo(print())
                .andExpect(status().isOk());
    }

    @Test
    public void deleteT_ShirtTest() throws Exception{
        T_Shirt t_shirt1 = new T_Shirt(1, "Armani man's t_shirt", "Black", 6999);
        t_shirtService.save(t_shirt1);
        mockMvc.perform( delete("/api/t_shirt/delete/{id}", 1))
                .andDo(print())
                .andExpect(status().isOk());
    }
}

