package com.ey.evaluation.demo.controller;

import com.ey.evaluation.demo.dto.Phone;
import com.ey.evaluation.demo.dto.User;
import com.ey.evaluation.demo.dto.UserResponse;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class UserControllerTests {

    @LocalServerPort
    private int port;

    private RestTemplate restTemplate = new RestTemplate();

    @Test
    public void testAddUser(){
        final User user = getDummyUser("s1esV4lido");
        UserResponse response = restTemplate.postForObject(getUrl(), user, UserResponse.class);
        Assertions.assertNotNull(response);
        Assertions.assertNotNull(response.getId());
        Assertions.assertNotNull(response.getToken());
    }

    @Test
    public void testBadRequestPassword(){
        final User user = getDummyUser("noesvalido");
        Assertions.assertThrows(HttpClientErrorException.BadRequest.class,
                () -> restTemplate.postForObject(getUrl(), user, UserResponse.class));
    }

    @Test
    public void testBadRequest(){
        final User user = new User();
        Assertions.assertThrows(HttpClientErrorException.BadRequest.class,
                () -> restTemplate.postForObject(getUrl(), user, UserResponse.class));
    }

    private String getUrl() {
        return new StringBuilder().append("http://localhost:").append(port).append("/").toString();
    }


    private static User getDummyUser(String password) {
        Phone phone=new Phone();
        phone.setNumber("98754321");
        phone.setCityCode("9");
        phone.setCountryCode("56");
        User user = new User();
        user.setName("Test User");
        user.setEmail("prueba@email.com");
        user.setPassword(password);
        user.setPhones(List.of(phone));
        return user;
    }

}
