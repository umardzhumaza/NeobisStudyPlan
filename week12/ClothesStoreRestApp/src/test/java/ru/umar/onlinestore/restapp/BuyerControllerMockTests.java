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
import ru.umar.onlinestore.restapp.models.Buyer;
import ru.umar.onlinestore.restapp.services.BuyerService;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;;

@RunWith(SpringRunner.class)
@SpringBootTest
@WithMockUser(roles = "ADMIN")
@AutoConfigureMockMvc
public class BuyerControllerMockTests {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private BuyerService buyerService;

    @Before
    public void addBuyerInDb(){
        Buyer buyer = new Buyer(15645, "Max", "Pain", "max@pain.com", "+1 007 007 007");
        buyerService.save(buyer);
    }

    @After
    public void deleteBuyerFromDb(){
        buyerService.delete(15645);
    }

    @Test
    public void postBuyerTest() throws Exception {
        Buyer buyer = new Buyer(1, "Max", "Pain", "max@pain.com", "+1 007 007 007");
        ObjectMapper mapper = new ObjectMapper();
        mapper.configure(SerializationFeature.WRAP_ROOT_VALUE, false);
        ObjectWriter ow = mapper.writer().withDefaultPrettyPrinter();
        String requestJson=ow.writeValueAsString(buyer);
        mockMvc.perform(post("/api/buyer").contentType(APPLICATION_JSON_UTF8)
                        .content(requestJson))
                .andExpect(status().isCreated());
        buyerService.delete(1);
    }

    @Test
    public void getAllBuyersTest() throws Exception{
        mockMvc.perform( get("/api/buyer"))
                .andDo(print())
                .andExpect(status().isOk());
    }

    @Test
    public void getOneBuyerTest() throws Exception{
        mockMvc.perform( get("/api/buyer/{id}", 15645))
                .andDo(print())
                .andExpect(status().isOk());
    }


    @Test
    public void updateBuyerTest() throws Exception{
        Buyer updatedBuyer = new Buyer("Updated", "Pain", "max@pain.com", "+1 007 007 007");
        ObjectMapper mapper = new ObjectMapper();
        String jsonRequest = mapper.writeValueAsString((updatedBuyer));

        mockMvc.perform(put("/api/buyer/update/{id}", 15645)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(jsonRequest))
                .andDo(print())
                .andExpect(status().isOk());
    }

    @Test
    public void deleteBuyerTest() throws Exception{
        Buyer buyer = new Buyer(1, "Max", "Pain", "max@pain.com", "+1 007 007 007");
        buyerService.save(buyer);
        mockMvc.perform( delete("/api/buyer/delete/{id}", 1))
                .andDo(print())
                .andExpect(status().isOk());
    }
}
