package com.ey.evaluation.demo.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity(name = "demo_phone")
@Getter
@Setter
public class PhoneJPA {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long phoneId;

    private String number;

    private String cityCode;

    private String countryCode;

    @ManyToOne(fetch = FetchType.LAZY)
    private UserJPA user;

}
