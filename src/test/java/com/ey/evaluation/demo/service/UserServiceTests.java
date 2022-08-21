package com.ey.evaluation.demo.service;

import com.ey.evaluation.demo.dto.Phone;
import com.ey.evaluation.demo.dto.User;
import com.ey.evaluation.demo.dto.UserResponse;
import com.ey.evaluation.demo.exception.NotValidPasswordException;
import com.ey.evaluation.demo.exception.UsedEmailException;
import com.ey.evaluation.demo.model.UserJPA;
import com.ey.evaluation.demo.repository.PhoneRepository;
import com.ey.evaluation.demo.repository.UserRepository;
import com.ey.evaluation.demo.security.JwtBuilder;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import java.util.List;

public class UserServiceTests {
    
    @InjectMocks
    private UserServiceImpl userService;

    @Mock
    private UserRepository userRepository;
    
    @Mock
    private PhoneRepository phoneRepository;

    @Mock
    private JwtBuilder jwtBuilder;

    @BeforeEach
    public void init(){
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testAddUser(){
        String email = "test@mail.com";
        User user = getDummyUser(email);
        Mockito.when(userRepository.findByEmail(email)).thenReturn(null);
        UserJPA userJPA = new UserJPA();
        userJPA.setUserId(1L);
        Mockito.when(userRepository.save(Mockito.any())).thenReturn(userJPA);
        final UserResponse response=userService.addUser(user);
        Assertions.assertNotNull(response);
        Assertions.assertEquals(userJPA.getUserId(), response.getId());
    }

    @Test
    public void testUsedEmail(){
        User user = new User();
        String email = "test@mail.com";
        user.setEmail(email);
        user.setPassword("s1esV4lido");
        Mockito.when(userRepository.findByEmail(email)).thenReturn(List.of(new UserJPA()));
        Assertions.assertThrows(UsedEmailException.class,() -> userService.addUser(user));
    }

    @Test
    public void testNotValidPassword(){
        User user = new User();
        user.setPassword("noesvalido");
        Assertions.assertThrows(NotValidPasswordException.class,() -> userService.addUser(user));
    }

    private static User getDummyUser(String email) {
        Phone phone=new Phone();
        phone.setNumber("98754321");
        phone.setCityCode("9");
        phone.setCountryCode("56");
        User user = new User();
        user.setEmail(email);
        user.setPassword("s1esV4lido");
        user.setPhones(List.of(phone));
        return user;
    }
}
