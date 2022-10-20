package io.abhijtih.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

public class FibonacciOutOfRangeException extends Exception{

    public FibonacciOutOfRangeException(String message) {
        super(message);
    }

}
