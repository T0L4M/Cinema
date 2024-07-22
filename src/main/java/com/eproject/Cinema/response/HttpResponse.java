package com.eproject.Cinema.response;

import java.util.List;

import com.eproject.Cinema.dto.*;

import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.ErrorResponse;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Component
public class HttpResponse<T> {
    private int code;
    private String message;
    private T data;
    private T errors;

    /**
     * Return response success
     *
     * @param message
     * @return
     */
    public ResponseEntity<ResponseDTO<?>> success() {
        ResponseDTO<T> response = new ResponseDTO<>("Succcess", null, null, HttpStatus.OK.value());
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    /**
     * Return response success
     *
     * @param message
     * @return
     */
    public ResponseEntity<ResponseDTO<?>> success(String message) {
        ResponseDTO<T> response = new ResponseDTO<>(message, null, null, HttpStatus.OK.value());
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    public ResponseEntity<ResponseDTO<?>> success(T data) {
        ResponseDTO<T> response = new ResponseDTO<>("OK", data, null, HttpStatus.OK.value());
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    /**
     * Return response success
     *
     * @param message
     * @return
     */
    public ResponseEntity<ResponseDTO<?>> success(String message, T data) {
        ResponseDTO<T> response = new ResponseDTO<>(message, data, null, HttpStatus.OK.value());
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    public ResponseEntity<ResponseDTO<?>> unprocessable(T errors) {
        ResponseDTO<T> response = new ResponseDTO<>("UNPROCESSABLE ENTITY", null, errors,
                HttpStatus.UNPROCESSABLE_ENTITY.value());

        return ResponseEntity.status(HttpStatus.UNPROCESSABLE_ENTITY).body(response);
    }

    /**
     * Return response failure
     *
     * @param message
     * @return
     */
    public ResponseEntity<ResponseDTO<?>> failure() {
        ResponseDTO<T> response = new ResponseDTO<>("Bad request", null, null, HttpStatus.BAD_REQUEST.value());
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
    }

    /**
     * Return response failure
     *
     * @param message
     * @return
     */
    public ResponseEntity<ResponseDTO<?>> failure(String message) {
        ResponseDTO<T> response = new ResponseDTO<>(message, null, null, HttpStatus.BAD_REQUEST.value());
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
    }

    /**
     * Return response failure
     *
     * @param message
     * @return
     */
    public ResponseEntity<ResponseDTO<?>> failure(String message, T data) {
        ResponseDTO<T> response = new ResponseDTO<>(message, null, data, HttpStatus.BAD_REQUEST.value());
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
    }

    /**
     * Return response failure
     *
     * @param message
     * @return
     */
    public ResponseEntity<ResponseDTO<?>> failure(String message, T data, int code) {
        ResponseDTO<T> response = new ResponseDTO<>(message, null, data, code);
        HttpStatusCode statusCode = HttpStatus.BAD_REQUEST;
        if (code == HttpStatus.UNAUTHORIZED.value()) {
            statusCode = HttpStatus.UNAUTHORIZED;
        } else if (code == HttpStatus.FORBIDDEN.value()) {
            statusCode = HttpStatus.FORBIDDEN;
        } else if (code == HttpStatus.NOT_FOUND.value()) {
            statusCode = HttpStatus.NOT_FOUND;
        } else if (code == HttpStatus.METHOD_NOT_ALLOWED.value()) {
            statusCode = HttpStatus.METHOD_NOT_ALLOWED;
        } else if (code == HttpStatus.NOT_ACCEPTABLE.value()) {
            statusCode = HttpStatus.NOT_ACCEPTABLE;
        } else if (code == HttpStatus.REQUEST_TIMEOUT.value()) {
            statusCode = HttpStatus.REQUEST_TIMEOUT;
        } else if (code == HttpStatus.UNPROCESSABLE_ENTITY.value()) {
            statusCode = HttpStatus.UNPROCESSABLE_ENTITY;
        } else if (code == HttpStatus.INTERNAL_SERVER_ERROR.value()) {
            statusCode = HttpStatus.INTERNAL_SERVER_ERROR;
        } else if (code == HttpStatus.SERVICE_UNAVAILABLE.value()) {
            statusCode = HttpStatus.SERVICE_UNAVAILABLE;
        } else if (code == HttpStatus.BAD_GATEWAY.value()) {
            statusCode = HttpStatus.BAD_GATEWAY;
        } else {
            statusCode = HttpStatus.BAD_REQUEST;
        }

        return ResponseEntity.status(statusCode).body(response);
    }
}
