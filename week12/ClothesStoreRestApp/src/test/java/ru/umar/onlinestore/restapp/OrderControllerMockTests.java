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

import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import ru.umar.onlinestore.restapp.models.Buyer;
import ru.umar.onlinestore.restapp.models.T_Shirt;
import ru.umar.onlinestore.restapp.services.BuyerService;
import ru.umar.onlinestore.restapp.services.OrderService;
import ru.umar.onlinestore.restapp.services.T_ShirtService;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;;

@RunWith(SpringRunner.class)
@SpringBootTest
@WithMockUser(roles = "ADMIN")
@AutoConfigureMockMvc
public class OrderControllerMockTests {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private OrderService orderService;
    @Autowired
    private BuyerService buyerService;
    @Autowired
    private T_ShirtService t_shirtService;

    @Before
    public void addOrderInDb(){
        Buyer buyer = new Buyer(15645, "Max", "Pain", "max@pain.com", "+1 007 007 007");
        buyerService.save(buyer);
        T_Shirt t_shirt1 = new T_Shirt(4,"Armani man's t_shirt", "Black", 6999);
        t_shirtService.save(t_shirt1);
        orderService.save(1, buyer.getId(), t_shirt1.getId(), "03/03/2020");
    }

    @After
    public void deleteBuyerFromDb(){
        orderService.deleteAll();
        buyerService.delete(15645);
        t_shirtService.delete(4);
    }

    @Test
    public void postOrderTest() throws Exception {
        Buyer buyer = new Buyer(653, "Max", "Pain", "max@pain.com", "+1 007 007 007");
        buyerService.save(buyer);
        T_Shirt t_shirt1 = new T_Shirt(896,"Armani man's t_shirt", "Black", 6999);
        t_shirtService.save(t_shirt1);
        mockMvc.perform(post("/api/order?buyer_id=653&t_shirt_id=896&order_date=\"03/03/2020\""))
                .andExpect(status().isOk());
        orderService.deleteAll();
        buyerService.delete(653);
        t_shirtService.delete(896);
    }

    @Test
    public void getAllOrdersTest() throws Exception{
        mockMvc.perform( get("/api/order"))
                .andDo(print())
                .andExpect(status().isOk());
    }

    @Test
    public void getOneOrderTest() throws Exception{
        mockMvc.perform( get("/api/order/{id}", 1))
                .andDo(print())
                .andExpect(status().isOk());
    }

    @Test
    public void updateOrderTest() throws Exception{
        mockMvc.perform(put("/api/order/update/1?buyer_id=15645&t_shirt_id=4&order_date=\"03/03/2022\""))
                .andDo(print())
                .andExpect(status().isOk());
    }

    @Test
    public void deleteOrderTest() throws Exception{
        Buyer buyer = new Buyer(689, "Max", "Pain", "max@pain.com", "+1 007 007 007");
        buyerService.save(buyer);
        T_Shirt t_shirt1 = new T_Shirt(689,"Armani man's t_shirt", "Black", 6999);
        t_shirtService.save(t_shirt1);
        orderService.save(569, buyer.getId(), t_shirt1.getId(), "03/03/2020");
        mockMvc.perform( delete("/api/order/delete/{id}", 569))
                .andDo(print())
                .andExpect(status().isOk());
        buyerService.delete(689);
        t_shirtService.delete(689);
    }
}

