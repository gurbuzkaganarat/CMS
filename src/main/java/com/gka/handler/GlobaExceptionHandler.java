package com.gka.handler;

import java.net.Inet4Address;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

import com.gka.exception.BaseException;

@ControllerAdvice
public class GlobaExceptionHandler {
    
    @ExceptionHandler(value = {BaseException.class})
    public ResponseEntity<ApiError<?>> handleBaseException(BaseException ex, WebRequest request) {
        
        
        int httpCode = ex.getHttpCode();
        HttpStatus status = HttpStatus.resolve(httpCode); 
        if (status == null) {
            
            status = HttpStatus.INTERNAL_SERVER_ERROR;
        }

        ApiError<?> apiError = createApiError(ex.getMessage(), request, status.value()); // HTTP kodu burada set ediliyor

        return new ResponseEntity<>(apiError, status);
    }

    @ExceptionHandler(value = {MethodArgumentNotValidException.class})
    public ResponseEntity<ApiError<Map<String, List<String>>>> handleMethodArgumentNotValidException(MethodArgumentNotValidException ex, WebRequest request) {
        
        Map<String, List<String>> map = new HashMap<>();
        for (ObjectError objError : ex.getBindingResult().getAllErrors()) {
            
            String fieldName = ((FieldError) objError).getField();
            
            if (map.containsKey(fieldName)) {
                map.put(fieldName, addValue(map.get(fieldName), objError.getDefaultMessage()));
            } else {
                map.put(fieldName, addValue(new ArrayList<>(), objError.getDefaultMessage()));
            }
        }
        
        return ResponseEntity.status(HttpStatus.UNPROCESSABLE_ENTITY).body(createApiError(map, request, HttpStatus.UNPROCESSABLE_ENTITY.value()));  
    }

    private List<String> addValue(List<String> list, String newValue) {
        list.add(newValue);
        return list;
    }

    private String getHostName() {
        try {
            return Inet4Address.getLocalHost().getHostName();
        } catch (UnknownHostException e) {
            e.printStackTrace();
        }
        return "";
    }
    
    
   
    
    public <E> ApiError<E> createApiError(E message, WebRequest request, int httpCode) {
        ApiError<E> apiError = new ApiError<>();
        apiError.setStatus(httpCode);

        Exception<E> exception = new Exception<>();
        exception.setPath(request.getDescription(false).substring(4));
        exception.setCreateTime(new Date());
        exception.setMessage(message);
        exception.setHostName(getHostName());

        apiError.setException(exception);

        return apiError;
    }
}
