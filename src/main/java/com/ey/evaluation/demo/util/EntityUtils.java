package com.ey.evaluation.demo.util;

import com.ey.evaluation.demo.dto.Phone;
import com.ey.evaluation.demo.dto.User;
import com.ey.evaluation.demo.dto.UserResponse;
import com.ey.evaluation.demo.model.PhoneJPA;
import com.ey.evaluation.demo.model.UserJPA;

import java.util.Date;
import java.util.UUID;

public final class EntityUtils {

    public static UserJPA createUser(User user) {

        final String email = user.getEmail().toLowerCase();
        final UserJPA entity = new UserJPA();
        entity.setUserId(UUID.randomUUID());
        entity.setUserName(user.getName());
        entity.setEmail(email);
        entity.setUserPassword(PasswordUtils.getHash(user.getPassword()));
        entity.setIsactive(true);
        Date now = new Date();
        entity.setCreated(now);
        entity.setModified(now);
        entity.setLastLogin(now);

        return entity;
    }

    public static PhoneJPA createPhone(Phone phone, UserJPA user) {
        final PhoneJPA entity = new PhoneJPA();
        entity.setUser(user);
        entity.setNumber(phone.getNumber());
        entity.setCityCode(phone.getCityCode());
        entity.setCountryCode(phone.getCountryCode());
        return entity;
    }

    public static UserResponse buildResponse(UserJPA entity) {

        final UserResponse response = new UserResponse();
        response.setId(entity.getUserId());
        response.setIsactive(entity.getIsactive());
        response.setCreated(entity.getCreated());
        response.setModified(entity.getModified());
        response.setLastLogin(entity.getLastLogin());
        response.setToken(new String(entity.getToken()));

        return response;
    }

    private EntityUtils() {
        //
    }
}
