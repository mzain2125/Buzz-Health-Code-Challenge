package com.BuzzRx.Drug.Management.exceptions;

public class InactiveCouponException extends RuntimeException {
    public InactiveCouponException (String message){
        super(message);
    }
}
