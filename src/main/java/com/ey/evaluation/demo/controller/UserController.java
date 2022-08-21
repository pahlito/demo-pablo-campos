package com.ey.evaluation.demo.controller;

import com.ey.evaluation.demo.dto.User;
import com.ey.evaluation.demo.dto.UserResponse;
import com.ey.evaluation.demo.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.util.MimeTypeUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@Slf4j
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping(
            value = "/",
            consumes = MimeTypeUtils.APPLICATION_JSON_VALUE,
            produces = MimeTypeUtils.APPLICATION_JSON_VALUE
    )
    public ResponseEntity<UserResponse> addUser(@RequestBody @Valid User user) {
        final UserResponse response = userService.addUser(user);
        return ResponseEntity.ok(response);
    }

}
