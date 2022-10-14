package ru.umar.onlinestore.restapp;


import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.*;
import org.springframework.test.context.junit4.SpringRunner;
import ru.umar.onlinestore.restapp.models.Buyer;
import ru.umar.onlinestore.restapp.models.Order;
import ru.umar.onlinestore.restapp.models.T_Shirt;
import ru.umar.onlinestore.restapp.services.BuyerService;
import ru.umar.onlinestore.restapp.services.OrderService;
import ru.umar.onlinestore.restapp.services.T_ShirtService;

import java.util.List;
import java.util.Objects;

import static org.hamcrest.CoreMatchers.*;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.hasSize;


@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class RestAppApplicationTests {
    @Autowired
    private TestRestTemplate restTemplate;
    @LocalServerPort
    private int port;
    @Autowired
    private T_ShirtService t_shirtService;
    @Autowired
    private BuyerService buyerService;

    @Autowired
    private OrderService orderService;
    HttpHeaders headers = new HttpHeaders();
    private void instantiateNewUser() {
        Buyer buyerNumberFour = new Buyer(4, "Max", "Pain", "max@pain.com", "+1 007 007 007");
        restTemplate.withBasicAuth("umar","916052")
                .postForEntity("/api/buyer", buyerNumberFour, Buyer.class);
    }


    private void instantiateNewT_Shirt() {
        T_Shirt t_shirtNumberFour = new T_Shirt(4, "Puma man's t_shirt", "Black", 5559);

        t_shirtService.save(t_shirtNumberFour);
    }

    private void instantiateNewOrder() {
        Buyer buyerNumberFour = new Buyer(4, "Max", "Pain", "max@pain.com", "+1 007 007 007");
        T_Shirt t_shirtNumberFour = new T_Shirt(3, "Puma man's t_shirt", "Black", 5559);
        buyerService.save(buyerNumberFour);
        t_shirtService.save(t_shirtNumberFour);
        orderService.save(4, buyerNumberFour.getId(), t_shirtNumberFour.getId(), "03/03/2020");
    }

    private String createURLWithPort(String uri) {
        return "http://localhost:" + port + uri;
    }

    @Test
    public void whenAuthUserGetHelloPage_thenGetAndResponseStatus200() {
        HttpEntity<String> httpEntity = new HttpEntity<>(null, headers);

        ResponseEntity<String> responseEntity = restTemplate.withBasicAuth("Umar","916052")
                .exchange(
                        createURLWithPort("/hello"),
                        HttpMethod.GET, httpEntity, String.class);

        assertThat(responseEntity.getStatusCode(), is(HttpStatus.OK));
    }

    @Test
    public void whenNoneAuthUserGetHelloPage_thenGetAndResponseStatusUNAUTHORIZED401() {
        HttpEntity<String> httpEntity = new HttpEntity<String>(null, headers);

        ResponseEntity<String> responseEntity = restTemplate.withBasicAuth("user","password")
                .exchange(
                        createURLWithPort("/hello"),
                        HttpMethod.GET, httpEntity, String.class);

        assertThat(responseEntity.getStatusCode(), is(HttpStatus.UNAUTHORIZED));
    }


    @Test
    public void getListBuyers_whenGetListBuyers_thenGetAllBuyersAndResponseStatus200()  {
        Buyer buyer1 = new Buyer(1,"Umar","Dzhumaza","umardzhumaza@mail.ru", "+996 703 361 322");
        Buyer buyer2 = new Buyer(2,"Mike","Vazovskyi","mike@vazovsky.com", "+7 996 12 45 75");
        buyerService.save(buyer1);
        buyerService.save(buyer2);

        HttpEntity<String> httpEntity = new HttpEntity<>(null, headers);

        ResponseEntity<List<Buyer>> responseEntity = restTemplate.withBasicAuth("umar","916052")
                .exchange(
                createURLWithPort("/api/buyer"),
                HttpMethod.GET, httpEntity,
                        new ParameterizedTypeReference<List<Buyer>>() {
                        });

        List<Buyer> buyers = responseEntity.getBody();
        assertThat(responseEntity.getStatusCode(), is(HttpStatus.OK));
        assertThat(buyers, hasSize(2));
        assertThat(buyers.get(1).getName(), is("Mike"));
        buyerService.deleteAll();
    }

    @Test
    public void getBuyer_whenGetBuyer_thenGetBuyerByIdAndResponseStatus200() {
        instantiateNewUser();
        HttpEntity<String> httpEntity = new HttpEntity<String>(null, headers);

        ResponseEntity<Buyer> responseEntity = restTemplate.withBasicAuth("umar","916052")
                .exchange(
                createURLWithPort("/api/buyer/4/"),
                HttpMethod.GET, httpEntity, Buyer.class);

        assertThat(responseEntity.getStatusCode(), is(HttpStatus.OK));
        assertThat(Objects.requireNonNull(responseEntity.getBody()).getId(), notNullValue());
        assertThat(responseEntity.getBody().getName(), is("Max"));
        buyerService.deleteAll();
    }

    @Test
    public void getNonExistentBuyer_whenGetNonExistentBuyer_thenGetNonExistentBuyerByIdAndResponseStatus404() {
        HttpEntity<String> httpEntity = new HttpEntity<>(null, headers);

        ResponseEntity<String> responseEntity = restTemplate.withBasicAuth("umar","916052")
                .exchange(
                        createURLWithPort("/api/buyer/1567"),
                        HttpMethod.GET, httpEntity, String.class);

        assertThat(responseEntity.getStatusCode(), is(HttpStatus.NOT_FOUND));
    }

    @Test
    public void postBuyer_whenPostBuyer_thenAddBuyerAndResponseStatusCodeCreated201() {
        Buyer buyerNumberFour = new Buyer(4, "Max", "Pain", "max@pain.com", "+1 007 007 007");

        ResponseEntity<Buyer> responseEntity = restTemplate.withBasicAuth("umar","916052")
                .postForEntity(createURLWithPort("/api/buyer"), buyerNumberFour, Buyer.class);

        assertThat(responseEntity.getStatusCode(), is(HttpStatus.CREATED));
        assertThat(Objects.requireNonNull(responseEntity.getBody()).getId(), notNullValue());
        assertThat(responseEntity.getBody().getName(), is("Max"));
        buyerService.deleteAll();
    }

    @Test
    public void whenRoleUserUseAdminMethPostBuyer_ThenResponseStatusCodeFORBIDDEN403() {
        Buyer buyerNumberFour = new Buyer(4, "Max", "Pain", "max@pain.com", "+1 007 007 007");

        ResponseEntity<Buyer> responseEntity = restTemplate.withBasicAuth("Max","916052")
                .postForEntity(createURLWithPort("/api/buyer"), buyerNumberFour, Buyer.class);

        assertThat(responseEntity.getStatusCode(), is(HttpStatus.FORBIDDEN));
    }

    @Test
    public void updateBuyer_whenUpdateBuyer_thenUpdateBuyerAndResponseStatusCodeOK200() throws JsonProcessingException {
        instantiateNewUser();

        Buyer updatedUser = new Buyer("Updated", "Pain", "updated@pain.com", "+1 007 007 007");

        ObjectMapper mapper = new ObjectMapper();
        String requestBody = mapper.writeValueAsString(updatedUser);

        headers.setContentType(MediaType.APPLICATION_JSON);

        HttpEntity<String> httpEntity = new HttpEntity<>(requestBody, headers);

        ResponseEntity<Buyer> responseEntity = restTemplate.withBasicAuth("umar","916052")
                .exchange(
                createURLWithPort("/api/buyer/update/4/"),
                HttpMethod.PUT, httpEntity, Buyer.class);

        assertThat(responseEntity.getStatusCode(), is(HttpStatus.OK));
        assertThat(Objects.requireNonNull(responseEntity.getBody()).getId(), notNullValue());
        assertThat(responseEntity.getBody().getName(), is("Updated"));
        buyerService.deleteAll();
    }

    @Test
    public void whenRoleUserUseAdminMethUpdateBuyer_thenResponseStatusFORBIDDEN403() throws JsonProcessingException {
        instantiateNewUser();

        Buyer updatedUser = new Buyer("Updated", "Pain", "updated@pain.com", "+1 007 007 007");

        ObjectMapper mapper = new ObjectMapper();
        String requestBody = mapper.writeValueAsString(updatedUser);

        headers.setContentType(MediaType.APPLICATION_JSON);

        HttpEntity<String> httpEntity = new HttpEntity<String>(requestBody, headers);

        ResponseEntity<Buyer> responseEntity = restTemplate.withBasicAuth("Max","916052")
                .exchange(
                        createURLWithPort("/api/buyer/update/4/"),
                        HttpMethod.PUT, httpEntity, Buyer.class);

        assertThat(responseEntity.getStatusCode(), is(HttpStatus.FORBIDDEN));
        buyerService.deleteAll();
    }

    @Test
    public void updateNonExistentBuyer_whenUpdateNonExistentBuyer_thenUpdateNonExistentBuyerAndResponseStatusCodeNotFound404() throws JsonProcessingException {

        Buyer updatedUser = new Buyer("Updated", "Pain", "updated@pain.com", "+1 007 007 007");

        ObjectMapper mapper = new ObjectMapper();
        String requestBody = mapper.writeValueAsString(updatedUser);

        headers.setContentType(MediaType.APPLICATION_JSON);

        HttpEntity<String> httpEntity = new HttpEntity<>(requestBody, headers);

        ResponseEntity<String> responseEntity = restTemplate.withBasicAuth("umar","916052")
                .exchange(
                        createURLWithPort("/api/buyer/update/1567/"),
                        HttpMethod.PUT, httpEntity, String.class);

        assertThat(responseEntity.getStatusCode(), is(HttpStatus.NOT_FOUND));
    }

    @Test
    public void deleteBuyer_whenDeleteBuyer_thenDeleteBuyerAndResponseStatusCodeOK200() {

        instantiateNewUser();
        HttpEntity<String> httpEntity = new HttpEntity<String>(null, headers);

        ResponseEntity<String> responseEntity = restTemplate.withBasicAuth("umar", "916052").exchange(
                createURLWithPort("/api/buyer/delete/4"),
                HttpMethod.DELETE, httpEntity, String.class);

        assertThat(responseEntity.getStatusCode(), is(HttpStatus.OK));
    }

    @Test
    public void whenRoleUserUseAdminMethDeleteBuyer_thenResponseStatusCodeFORBIDDEN403() {
        instantiateNewUser();
        HttpEntity<String> httpEntity = new HttpEntity<>(null, headers);

        ResponseEntity<String> responseEntity = restTemplate.withBasicAuth("Max", "916052").exchange(
                createURLWithPort("/api/buyer/delete/4"),
                HttpMethod.DELETE, httpEntity, String.class);

        assertThat(responseEntity.getStatusCode(), is(HttpStatus.FORBIDDEN));
        buyerService.deleteAll();
    }

    @Test
    public void deleteNonExistentBuyer_whenDeleteNonExistentBuyer_thenDeleteNonExistentBuyerAndResponseStatusCodeNotFound404() {

        HttpEntity<String> httpEntity = new HttpEntity<>(null, headers);

        ResponseEntity<String> responseEntity = restTemplate.withBasicAuth("umar", "916052").exchange(
                createURLWithPort("/api/buyer/delete/1567"),
                HttpMethod.DELETE, httpEntity, String.class);

        assertThat(responseEntity.getStatusCode(), is(HttpStatus.NOT_FOUND));
    }

    @Test
    public void getListT_Shirts_whenGetListT_Shirts_thenGetAllT_ShirtsAndResponseStatus200() {
        T_Shirt t_shirt1 = new T_Shirt(1, "Armani man's t_shirt", "Black", 6999);
        T_Shirt t_shirt2 = new T_Shirt(2, "Gucci woman's t_shirt", "White", 5999);
        t_shirtService.save(t_shirt1);
        t_shirtService.save(t_shirt2);

        HttpEntity<String> httpEntity = new HttpEntity<>(null, headers);

        ResponseEntity<List<T_Shirt>> responseEntity = restTemplate.withBasicAuth("umar", "916052")
                .exchange(
                        createURLWithPort("/api/t_shirt"),
                        HttpMethod.GET, httpEntity,
                        new ParameterizedTypeReference<List<T_Shirt>>() {
                        });

        List<T_Shirt> t_shirts = responseEntity.getBody();
        assertThat(responseEntity.getStatusCode(), is(HttpStatus.OK));
        assertThat(t_shirts, hasSize(2));
        assertThat(t_shirts.get(1).getName(), is("Gucci woman's t_shirt"));
        t_shirtService.deleteAll();
    }

    @Test
    public void getT_Shirt_whenGetT_Shirt_thenGetT_ShirtByIdAndResponseStatus200() {
        instantiateNewT_Shirt();
        HttpEntity<String> httpEntity = new HttpEntity<>(null, headers);

        ResponseEntity<T_Shirt> responseEntity = restTemplate.withBasicAuth("umar","916052")
                .exchange(
                        createURLWithPort("/api/t_shirt/4/"),
                        HttpMethod.GET, httpEntity, T_Shirt.class);

        assertThat(responseEntity.getStatusCode(), is(HttpStatus.OK));
        assertThat(Objects.requireNonNull(responseEntity.getBody()).getId(), notNullValue());
        assertThat(responseEntity.getBody().getName(), is("Puma man's t_shirt"));
        t_shirtService.deleteAll();
    }

    @Test
    public void postT_Shirt_whenPostT_Shirt_thenAddT_ShirtAndResponseStatusCodeCreated201() {
        T_Shirt t_shirt1 = new T_Shirt(4,"Armani man's t_shirt", "Black", 6999);

        ResponseEntity<T_Shirt> responseEntity = restTemplate.withBasicAuth("umar","916052")
                .postForEntity(createURLWithPort("/api/t_shirt"), t_shirt1, T_Shirt.class);

        assertThat(responseEntity.getStatusCode(), is(HttpStatus.CREATED));
        assertThat(Objects.requireNonNull(responseEntity.getBody()).getId(), notNullValue());
        assertThat(responseEntity.getBody().getName(), is("Armani man's t_shirt"));
        t_shirtService.deleteAll();
    }

    @Test
    public void whenRoleUserUseAdminMethPostT_Shirt_ThenResponseStatusCodeFORBIDDEN403() {
        T_Shirt t_shirt1 = new T_Shirt("Armani man's t_shirt", "Black", 6999);

        ResponseEntity<T_Shirt> responseEntity = restTemplate.withBasicAuth("Max","916052")
                .postForEntity(createURLWithPort("/api/buyer"), t_shirt1, T_Shirt.class);

        assertThat(responseEntity.getStatusCode(), is(HttpStatus.FORBIDDEN));
    }
//
    @Test
    public void updateT_Shirt_whenUpdateT_Shirt_thenUpdateT_ShirtAndResponseStatusCodeOK200() throws JsonProcessingException {
        instantiateNewT_Shirt();

        T_Shirt t_shirt1 = new T_Shirt("Armani man's t_shirt", "Black", 6999);

        ObjectMapper mapper = new ObjectMapper();
        String requestBody = mapper.writeValueAsString(t_shirt1);

        headers.setContentType(MediaType.APPLICATION_JSON);

        HttpEntity<String> httpEntity = new HttpEntity<>(requestBody, headers);

        ResponseEntity<T_Shirt> responseEntity = restTemplate.withBasicAuth("umar","916052")
                .exchange(
                        createURLWithPort("/api/t_shirt/update/4/"),
                        HttpMethod.PUT, httpEntity, T_Shirt.class);

        assertThat(responseEntity.getStatusCode(), is(HttpStatus.OK));
        assertThat(Objects.requireNonNull(responseEntity.getBody()).getId(), notNullValue());
        assertThat(responseEntity.getBody().getName(), is("Armani man's t_shirt"));
        t_shirtService.deleteAll();
    }

    @Test
    public void whenRoleUserUseAdminMethUpdateT_Shirt_thenResponseStatusFORBIDDEN403() throws JsonProcessingException {
        instantiateNewT_Shirt();

        T_Shirt t_shirt1 = new T_Shirt("Armani man's t_shirt", "Black", 6999);

        ObjectMapper mapper = new ObjectMapper();
        String requestBody = mapper.writeValueAsString(t_shirt1);

        headers.setContentType(MediaType.APPLICATION_JSON);

        HttpEntity<String> httpEntity = new HttpEntity<>(requestBody, headers);

        ResponseEntity<T_Shirt> responseEntity = restTemplate.withBasicAuth("Max","916052")
                .exchange(
                        createURLWithPort("/api/t_shirt/update/4/"),
                        HttpMethod.PUT, httpEntity, T_Shirt.class);

        assertThat(responseEntity.getStatusCode(), is(HttpStatus.FORBIDDEN));
        t_shirtService.deleteAll();
    }

    @Test
    public void updateNonExistentT_Shirt_whenUpdateNonExistentT_Shirt_thenAndResponseStatusCodeNotFound404() throws JsonProcessingException {

        T_Shirt t_shirt1 = new T_Shirt("Armani man's t_shirt", "Black", 6999);

        ObjectMapper mapper = new ObjectMapper();
        String requestBody = mapper.writeValueAsString(t_shirt1);

        headers.setContentType(MediaType.APPLICATION_JSON);

        HttpEntity<String> httpEntity = new HttpEntity<String>(requestBody, headers);

        ResponseEntity<T_Shirt> responseEntity = restTemplate.withBasicAuth("umar","916052")
                .exchange(
                        createURLWithPort("/api/t_shirt/update/1567/"),
                        HttpMethod.PUT, httpEntity, T_Shirt.class);

        assertThat(responseEntity.getStatusCode(), is(HttpStatus.NOT_FOUND));
    }

    @Test
    public void deleteT_Shirt_whenDeleteT_Shirt_thenDeleteT_ShirtAndResponseStatusCodeOK200() {

        instantiateNewT_Shirt();
        HttpEntity<String> httpEntity = new HttpEntity<String>(null, headers);

        ResponseEntity<String> responseEntity = restTemplate.withBasicAuth("umar", "916052").exchange(
                createURLWithPort("/api/t_shirt/delete/4"),
                HttpMethod.DELETE, httpEntity, String.class);

        assertThat(responseEntity.getStatusCode(), is(HttpStatus.OK));
    }

    @Test
    public void whenRoleUserUseAdminMethDeleteT_Shirt_thenResponseStatusCodeFORBIDDEN403() {
        instantiateNewT_Shirt();
        HttpEntity<String> httpEntity = new HttpEntity<String>(null, headers);

        ResponseEntity<String> responseEntity = restTemplate.withBasicAuth("Max", "916052").exchange(
                createURLWithPort("/api/t_shirt/delete/4"),
                HttpMethod.DELETE, httpEntity, String.class);

        assertThat(responseEntity.getStatusCode(), is(HttpStatus.FORBIDDEN));
        t_shirtService.deleteAll();
    }

    @Test
    public void deleteNonExistentT_Shirt_whenDeleteNonExistentT_Shirt_thenResponseStatusCodeNotFound404() {

        HttpEntity<String> httpEntity = new HttpEntity<String>(null, headers);

        ResponseEntity<String> responseEntity = restTemplate.withBasicAuth("umar", "916052").exchange(
                createURLWithPort("/api/t_shirt/delete/1567"),
                HttpMethod.DELETE, httpEntity, String.class);

        assertThat(responseEntity.getStatusCode(), is(HttpStatus.NOT_FOUND));
    }

    @Test
    public void getListOrders_whenGetListOrders_thenGetAllOrdersAndResponseStatus200()  {
        Buyer buyerNumberFour = new Buyer(4, "Max", "Pain", "max@pain.com", "+1 007 007 007");
        T_Shirt t_shirtNumberFour = new T_Shirt(4, "Puma man's t_shirt", "Black", 5559);
        T_Shirt t_shirtNumberOne = new T_Shirt(1, "Gucci man's t_shirt", "Black", 5559);
        buyerService.save(buyerNumberFour);
        t_shirtService.save(t_shirtNumberFour);
        t_shirtService.save(t_shirtNumberOne);

        orderService.save(buyerNumberFour.getId(), t_shirtNumberFour.getId(), "03/03/2020");
        orderService.save(buyerNumberFour.getId(), t_shirtNumberOne.getId(),"03/04/2020");

        HttpEntity<String> httpEntity = new HttpEntity<>(null, headers);

        ResponseEntity<List<Order>> responseEntity = restTemplate.withBasicAuth("umar","916052")
                .exchange(
                        createURLWithPort("/api/order"),
                        HttpMethod.GET, httpEntity,
                        new ParameterizedTypeReference<List<Order>>() {
                        });

        List<Order> orders = responseEntity.getBody();
        assertThat(responseEntity.getStatusCode(), is(HttpStatus.OK));
        assertThat(orders, hasSize(2));
        assertThat(orders.get(1).getOrder_date(), is("03/04/2020"));
        orderService.deleteAll();
        buyerService.deleteAll();
        t_shirtService.deleteAll();
    }

    @Test
    public void getOrder_whenGetOrder_thenGetOrderByIdAndResponseStatus200() {
        instantiateNewOrder();
        HttpEntity<String> httpEntity = new HttpEntity<>(null, headers);

        ResponseEntity<Order> responseEntity = restTemplate.withBasicAuth("umar","916052")
                .exchange(
                        createURLWithPort("/api/order/4/"),
                        HttpMethod.GET, httpEntity, Order.class);

        assertThat(responseEntity.getStatusCode(), is(HttpStatus.OK));
        assertThat(Objects.requireNonNull(responseEntity.getBody()).getId(), notNullValue());
        assertThat(responseEntity.getBody().getOrder_date(), is("03/03/2020"));
        orderService.deleteAll();
        buyerService.deleteAll();
        t_shirtService.deleteAll();
    }

    @Test
    public void getNonExistentOrder_whenGetNonExistentOrder_thenResponseStatus404() {
        HttpEntity<String> httpEntity = new HttpEntity<>(null, headers);

        ResponseEntity<String> responseEntity = restTemplate.withBasicAuth("umar","916052")
                .exchange(
                        createURLWithPort("/api/order/1567"),
                        HttpMethod.GET, httpEntity, String.class);

        assertThat(responseEntity.getStatusCode(), is(HttpStatus.NOT_FOUND));
    }

    @Test
    public void postOrder_whenPostOrder_thenAddOrderAndResponseStatusCodeCreated201() {
        Buyer buyerNumberFour = new Buyer(4, "Max", "Pain", "max@pain.com", "+1 007 007 007");
        T_Shirt t_shirtNumberFour = new T_Shirt(4, "Puma man's t_shirt", "Black", 5559);
        buyerService.save(buyerNumberFour);
        t_shirtService.save(t_shirtNumberFour);
        HttpEntity<String> httpEntity = new HttpEntity<>(null, headers);

        ResponseEntity<String> responseEntity = restTemplate.withBasicAuth("umar","916052")
                .exchange(
                        createURLWithPort("/api/order?buyer_id=4&t_shirt_id=4&order_date=\"03/03/2020\""),
                        HttpMethod.POST, httpEntity, String.class);

        assertThat(responseEntity.getStatusCode(), is(HttpStatus.OK));
        orderService.deleteAll();
        buyerService.deleteAll();
        t_shirtService.deleteAll();
    }

    @Test
    public void whenRoleUserUseAdminMethPostOrder_ThenResponseStatusCodeFORBIDDEN403() {
        HttpEntity<String> httpEntity = new HttpEntity<>(null, headers);

        ResponseEntity<String> responseEntity = restTemplate.withBasicAuth("Max","916052")
                .exchange(
                        createURLWithPort("/api/order/update/1563?buyer_id=4&t_shirt_id=4&order_date=\"03/03/2020\""),
                        HttpMethod.PUT, httpEntity, String.class);

        assertThat(responseEntity.getStatusCode(), is(HttpStatus.FORBIDDEN));
    }

    @Test
    public void updateOrder_whenUpdateOrder_thenUpdateOrderAndResponseStatusCodeOK200() {
        instantiateNewOrder();

        Buyer buyerNumberTen = new Buyer(10, "Max", "Pain", "max@pain.com", "+1 007 007 007");
        T_Shirt t_shirtNumberFive = new T_Shirt(5, "Puma man's t_shirt", "Black", 5559);
        buyerService.save(buyerNumberTen);
        t_shirtService.save(t_shirtNumberFive);
        HttpEntity<String> httpEntity = new HttpEntity<>(null, headers);

        ResponseEntity<String> responseEntity = restTemplate.withBasicAuth("umar","916052")
                .exchange(
                        createURLWithPort("/api/order/update/4?buyer_id=10&t_shirt_id=5&order_date=\"03/03/2020\""),
                        HttpMethod.PUT, httpEntity, String.class);

        assertThat(responseEntity.getStatusCode(), is(HttpStatus.OK));
        orderService.deleteAll();
        t_shirtService.deleteAll();
        buyerService.deleteAll();
    }

    @Test
    public void whenRoleUserUseAdminMethUpdateOrder_thenResponseStatusFORBIDDEN403() {
        instantiateNewOrder();

        Buyer buyerNumberTen = new Buyer(10, "Max", "Pain", "max@pain.com", "+1 007 007 007");
        T_Shirt t_shirtNumberFive = new T_Shirt(5, "Puma man's t_shirt", "Black", 5559);
        buyerService.save(buyerNumberTen);
        t_shirtService.save(t_shirtNumberFive);
        HttpEntity<String> httpEntity = new HttpEntity<>(null, headers);

        ResponseEntity<String> responseEntity = restTemplate.withBasicAuth("Max","916052")
                .exchange(
                        createURLWithPort("/api/order/update/4?buyer_id=10&t_shirt_id=5&order_date=\"03/03/2020\""),
                        HttpMethod.PUT, httpEntity, String.class);

        assertThat(responseEntity.getStatusCode(), is(HttpStatus.FORBIDDEN));
        orderService.deleteAll();
        t_shirtService.deleteAll();
        buyerService.deleteAll();
    }

    @Test
    public void updateNonExistentOrder_whenUpdateNonExistentOrder_thenResponseStatusCodeNotFound404() throws JsonProcessingException {

        HttpEntity<String> httpEntity = new HttpEntity<>(null, headers);

        ResponseEntity<String> responseEntity = restTemplate.withBasicAuth("umar","916052")
                .exchange(
                        createURLWithPort("/api/order/update/1567?buyer_id=10&t_shirt_id=5&order_date=\"03/03/2020\""),
                        HttpMethod.PUT, httpEntity, String.class);

        assertThat(responseEntity.getStatusCode(), is(HttpStatus.NOT_FOUND));
    }

    @Test
    public void deleteOrder_whenDeleteOrder_thenDeleteOrderAndResponseStatusCodeOK200() {
        instantiateNewOrder();
        HttpEntity<String> httpEntity = new HttpEntity<>(null, headers);

        ResponseEntity<String> responseEntity = restTemplate.withBasicAuth("umar", "916052").exchange(
                createURLWithPort("/api/order/delete/4"),
                HttpMethod.DELETE, httpEntity, String.class);

        assertThat(responseEntity.getStatusCode(), is(HttpStatus.OK));
        t_shirtService.deleteAll();
        buyerService.deleteAll();
    }

    @Test
    public void whenRoleUserUseAdminMethDeleteOrder_thenResponseStatusCodeFORBIDDEN403() {
        instantiateNewOrder();
        HttpEntity<String> httpEntity = new HttpEntity<>(null, headers);

        ResponseEntity<String> responseEntity = restTemplate.withBasicAuth("Max", "916052").exchange(
                createURLWithPort("/api/order/delete/4"),
                HttpMethod.DELETE, httpEntity, String.class);

        assertThat(responseEntity.getStatusCode(), is(HttpStatus.FORBIDDEN));
        orderService.deleteAll();
        t_shirtService.deleteAll();
        buyerService.deleteAll();
    }

    @Test
    public void deleteNonExistentOrder_whenDeleteNonExistentOrder_thenResponseStatusCodeNotFound404() {
        HttpEntity<String> httpEntity = new HttpEntity<>(null, headers);

        ResponseEntity<String> responseEntity = restTemplate.withBasicAuth("umar", "916052").exchange(
                createURLWithPort("/api/order/delete/1567"),
                HttpMethod.DELETE, httpEntity, String.class);

        assertThat(responseEntity.getStatusCode(), is(HttpStatus.NOT_FOUND));
    }
}
