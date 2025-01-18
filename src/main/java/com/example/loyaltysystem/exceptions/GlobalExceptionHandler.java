package com.example.loyaltysystem.exceptions;

import com.example.loyaltysystem.dto.CustomResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

@ControllerAdvice
public class GlobalExceptionHandler {

    // Обработка пользовательских исключений (CustomException)
    @ExceptionHandler(CustomException.class)
    public ResponseEntity<CustomResponse> handleCustomException(CustomException ex, WebRequest request) {
        CustomResponse response = new CustomResponse(
                ex.getMessage(), // Сообщение об ошибке
                null,           // Нет данных
                "ERROR"         // Статус ошибки
        );
        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
    }

    // Обработка всех остальных исключений
    @ExceptionHandler(Exception.class)
    public ResponseEntity<CustomResponse> handleGlobalException(Exception ex, WebRequest request) {
        CustomResponse response = new CustomResponse(
                "Внутренняя ошибка сервера: " + ex.getMessage(), // Сообщение об ошибке
                null,                                           // Нет данных
                "ERROR"                                         // Статус ошибки
        );
        return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
