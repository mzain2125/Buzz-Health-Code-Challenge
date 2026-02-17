package com.BuzzRx.Drug.Management.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class BaseResponse <T> {
    private boolean success;
    private String message;
    private T data;
    private int code;

    public static BaseResponse<?> successResponse(boolean success, String message,int code, Object data){
        BaseResponse<Object> response=new BaseResponse<>();
        response.setCode(code);
        response.setSuccess(success);
        response.setData(data);
        response.setMessage(message);
        return response;
    }

    public static BaseResponse<?> failureResponse(String message,int code){
        BaseResponse<Object> response=new BaseResponse<>();
        response.setCode(code);
        response.setData(null);
        response.setMessage(message);
        response.setSuccess(false);
        return response;
    }
}
