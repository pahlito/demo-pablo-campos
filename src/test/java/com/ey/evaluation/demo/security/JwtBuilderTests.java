package com.ey.evaluation.demo.security;

import com.ey.evaluation.demo.model.UserJPA;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;
import org.springframework.test.util.ReflectionTestUtils;

public class JwtBuilderTests {

    private static final String SECRET="12345678901234567890123456789012345678901234";
    @InjectMocks
    private JwtBuilder jwtBuilder;

    @BeforeEach
    public void init(){
        MockitoAnnotations.openMocks(this);
        ReflectionTestUtils.setField(jwtBuilder, "secret", SECRET);
        ReflectionTestUtils.invokeMethod(jwtBuilder, "loadKey", new Object[]{});
    }

    @Test
    public void testCreateToken(){
        String token = jwtBuilder.createToken(new UserJPA());
        Assertions.assertNotNull(token);
    }

}
