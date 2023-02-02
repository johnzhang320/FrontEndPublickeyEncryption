package com.front.end.pk.encrypt.demo.exception;

import lombok.Data;

@Data
public class MyPasswordException extends RuntimeException {  // RuntimeException do not need handled
    public MyPasswordException(String message)   {
      super(message);
    }

}
