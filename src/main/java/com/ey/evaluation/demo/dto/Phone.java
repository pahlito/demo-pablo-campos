package com.ey.evaluation.demo.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import javax.validation.constraints.NotBlank;

@Data
public class Phone {

    @NotBlank(message = "Se debe indicar un número")
    private String number;

    @NotBlank(message = "Se debe indicar un código de ciudad")
    @JsonProperty("citycode")
    private String cityCode;

    @NotBlank(message = "Se debe indicar un código de país")
    @JsonProperty("contrycode")
    private String countryCode;

}
