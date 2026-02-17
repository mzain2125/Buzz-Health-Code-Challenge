package com.BuzzRx.Drug.Management.exceptions;

import com.BuzzRx.Drug.Management.response.BaseResponse;
import io.swagger.v3.oas.annotations.Hidden;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
@Hidden
@RestControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(DuplicateResourceException.class)
    public ResponseEntity<?> duplicateFound(DuplicateResourceException ex) {
        return ResponseEntity.status(HttpStatus.CONFLICT).body(BaseResponse.failureResponse(ex.getMessage(), HttpStatus.CONFLICT.value()));
    }
    @ExceptionHandler(Exception.class)
    public ResponseEntity<BaseResponse<?>> exceptionHandling(Exception ex) {
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(BaseResponse.failureResponse(ex.getMessage(),HttpStatus.INTERNAL_SERVER_ERROR.value()));
    }

    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<BaseResponse<?>>notFound(ResourceNotFoundException ex){
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(BaseResponse.failureResponse(ex.getMessage(), HttpStatus.NOT_FOUND.value()));
    }

    @ExceptionHandler(InactiveCouponException.class)
    public ResponseEntity<?>Inactive(InactiveCouponException ex){
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(BaseResponse.failureResponse(ex.getMessage(),HttpStatus.BAD_REQUEST.value()));
    }

}
