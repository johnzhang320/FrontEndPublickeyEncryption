package com.front.end.pk.encrypt.demo.exception;

import lombok.Data;

@Data
public class PasswordException extends Exception {
    public PasswordException(String message)   {
      super(message);
    }
    public PasswordException(String message, Throwable cause) {
        super(message,cause);
    }
}
