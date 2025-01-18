package com.example.loyaltysystem.dto;

public class CustomResponse {
    private String message; // Сообщение о результате операции
    private Object data; // Данные (ID клиента, бонусный баланс, список клиентов/транзакций)
    private String status; // Статус операции (например, SUCCESS или ERROR)

    // Конструктор с тремя аргументами
    public CustomResponse(String message, Object data, String status) {
        this.message = message;
        this.data = data;
        this.status = status;
    }

    // Геттер для поля message
    public String getMessage() {
        return message;
    }

    // Сеттер для поля message
    public void setMessage(String message) {
        this.message = message;
    }

    // Геттер для поля data
    public Object getData() {
        return data;
    }

    // Сеттер для поля data
    public void setData(Object data) {
        this.data = data;
    }

    // Геттер для поля status
    public String getStatus() {
        return status;
    }

    // Сеттер для поля status
    public void setStatus(String status) {
        this.status = status;
    }

    // Переопределение метода toString для удобного вывода объекта в логах
    @Override
    public String toString() {
        return "CustomResponse{" +
                "message='" + message + '\'' +
                ", data=" + data +
                ", status='" + status + '\'' +
                '}';
    }
}
