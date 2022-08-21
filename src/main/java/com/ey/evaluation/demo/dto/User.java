package com.ey.evaluation.demo.dto;

import lombok.Data;

import javax.validation.Valid;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import java.util.List;

@Data
public class User {

    @NotBlank(message = "Se debe indicar un nombre")
    private String name;

    @NotBlank(message = "Se debe indicar un correo electrónico")
    @Email(message = "Correo electrónico informado no es válido. Ej: aaaaaaa@dominio.cl")
    private String email;

    @NotBlank(message = "Se debe indicar una clave")
    private String password;

    @Valid
    @NotEmpty(message = "Se debe indicar al menos 1 teléfono")
    private List<Phone> phones;

}
