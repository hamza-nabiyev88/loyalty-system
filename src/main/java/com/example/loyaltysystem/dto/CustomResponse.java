package com.example.loyaltysystem.dto;

public class CustomResponse {
    private String message; // Сообщение о результате операции
    private float bonusBalance; // Текущий бонусный баланс клиента
    private String status; // Статус операции (например, SUCCESS или ERROR)

    // Конструктор с тремя аргументами
    public CustomResponse(String message, float bonusBalance, String status) {
        this.message = message;
        this.bonusBalance = bonusBalance;
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

    // Геттер для поля bonusBalance
    public float getBonusBalance() {
        return bonusBalance;
    }

    // Сеттер для поля bonusBalance
    public void setBonusBalance(float bonusBalance) {
        this.bonusBalance = bonusBalance;
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
                ", bonusBalance=" + bonusBalance +
                ", status='" + status + '\'' +
                '}';
    }
}
