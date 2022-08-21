package com.ey.evaluation.demo.exception;

public class NotValidPasswordException extends RuntimeException {

    public NotValidPasswordException() {
        super("La clave debe contener al menos una letra en minúscula, una en mayúscula y 2 dígitos");
    }
}
