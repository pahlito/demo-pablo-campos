package com.ey.evaluation.demo.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.util.Date;

@Data
public class UserResponse {

    private Long id;

    private Date created;

    private Date modified;

    @JsonProperty("last_login")
    private Date lastLogin;

    private String token;

    private Boolean isactive;

}
