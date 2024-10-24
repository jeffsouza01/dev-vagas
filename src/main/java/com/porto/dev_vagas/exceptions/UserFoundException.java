package com.porto.dev_vagas.exceptions;

public class UserFoundException extends RuntimeException{

    public UserFoundException() {
        super("User already exists");
    }
}
