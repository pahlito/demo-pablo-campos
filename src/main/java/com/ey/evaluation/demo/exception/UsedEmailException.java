package com.ey.evaluation.demo.exception;

public class UsedEmailException extends RuntimeException {

    public UsedEmailException() {
        super("El correo ya registrado");
    }
}
