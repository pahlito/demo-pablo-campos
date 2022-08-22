package com.ey.evaluation.demo.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.OneToMany;
import java.util.Date;
import java.util.List;
import java.util.UUID;

@Entity(name = "demo_user")
@Getter
@Setter
public class UserJPA {

    @Id
    private UUID userId;

    private String userName;

    private String email;

    private String userPassword;

    @Lob
    private byte[] token;

    private Date created;

    private Date modified;

    private Date lastLogin;

    private Boolean isactive;

    @OneToMany(mappedBy = "user")
    private List<PhoneJPA> phones;

}
