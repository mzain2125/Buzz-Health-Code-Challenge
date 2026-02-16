package com.BuzzRx.Drug.Management.exceptions;

import com.BuzzRx.Drug.Management.response.BaseResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(DuplicateResourceException.class)
    public ResponseEntity<BaseResponse<?>> duplicateFound(DuplicateResourceException ex) {

        BaseResponse<?> response = new BaseResponse<>();
        response.setSuccess(false);
        response.setMessage(ex.getMessage());
        response.setData(null);
        response.setCode(HttpStatus.BAD_REQUEST.value());

        return ResponseEntity.ok(response);
    }
    @ExceptionHandler(Exception.class)
    public ResponseEntity<BaseResponse<?>> exceptionHandling(DuplicateResourceException ex) {

        BaseResponse<?> response = new BaseResponse<>();
        response.setSuccess(false);
        response.setMessage(ex.getMessage());
        response.setData(null);
        response.setCode(HttpStatus.INTERNAL_SERVER_ERROR.value());

        return ResponseEntity.ok(response);
    }

    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<BaseResponse<?>>notFound(ResourceNotFoundException ex){
        BaseResponse<?> response=new BaseResponse<>();
        response.setSuccess(false);
        response.setData(null);
        response.setMessage(ex.getMessage());
        response.setCode(HttpStatus.NOT_FOUND.value());
        return ResponseEntity.ok(response);
    }

}
