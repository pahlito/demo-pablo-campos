package com.ey.evaluation.demo.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Entity(name = "demo_user")
@Getter
@Setter
public class UserJPA {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long userId;

    private String userName;

    private String email;

    private String userPassword;

    private String token;

    private Date created;

    private Date modified;

    private Date lastLogin;

    private Boolean isactive;

    @OneToMany(mappedBy = "user")
    private List<PhoneJPA> phones;

}
