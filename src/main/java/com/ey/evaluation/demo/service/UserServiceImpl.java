package com.ey.evaluation.demo.service;

import com.ey.evaluation.demo.dto.Phone;
import com.ey.evaluation.demo.dto.User;
import com.ey.evaluation.demo.dto.UserResponse;
import com.ey.evaluation.demo.exception.UsedEmailException;
import com.ey.evaluation.demo.model.UserJPA;
import com.ey.evaluation.demo.repository.PhoneRepository;
import com.ey.evaluation.demo.repository.UserRepository;
import com.ey.evaluation.demo.security.JwtBuilder;
import com.ey.evaluation.demo.util.EntityUtils;
import com.ey.evaluation.demo.util.PasswordUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.Date;
import java.util.List;

@Slf4j
@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private PhoneRepository phoneRepository;

    @Autowired
    private JwtBuilder jwtBuilder;

    @Override
    public UserResponse addUser(User user) {

        PasswordUtils.validatePassword(user.getPassword());
        validateEmail(user.getEmail());

        final UserJPA entity = saveUserJPA(user);
        addUserToken(entity);
        savePhones(user.getPhones(), entity);

        return EntityUtils.buildResponse(entity);
    }

    private UserJPA saveUserJPA(User user) {
        return userRepository.save(EntityUtils.createUser(user));
    }

    private void addUserToken(UserJPA entity) {
        final String token = jwtBuilder.createToken(entity);
        entity.setToken(token);
        entity.setModified(new Date());
        userRepository.save(entity);
    }

    private void validateEmail(String email) {
        if (!CollectionUtils.isEmpty(userRepository.findByEmail(email))) {
            throw new UsedEmailException();
        }
    }

    private void savePhones(List<Phone> phones, UserJPA user) {
        if (!CollectionUtils.isEmpty(phones)) {
            phones.stream().map(phone -> EntityUtils.createPhone(phone, user))
                    .forEach(phoneRepository::save);
        }
    }

}
