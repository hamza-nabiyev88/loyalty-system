package com.example.loyaltysystem.dto;

public class CustomResponse {
    private String message; // Сообщение о результате операции
    private Object data; // Данные (например, ID клиента, список клиентов, бонусный баланс)
    private String status; // Статус операции (например, SUCCESS или ERROR)

    public CustomResponse(String message, Object data, String status) {
        this.message = message;
        this.data = data;
        this.status = status;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "CustomResponse{" +
                "message='" + message + '\'' +
                ", data=" + data +
                ", status='" + status + '\'' +
                '}';
    }
}
