package com.ey.evaluation.demo.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.util.Date;
import java.util.UUID;

@Data
public class UserResponse {

    private UUID id;

    private Date created;

    private Date modified;

    @JsonProperty("last_login")
    private Date lastLogin;

    private String token;

    private Boolean isactive;

}
