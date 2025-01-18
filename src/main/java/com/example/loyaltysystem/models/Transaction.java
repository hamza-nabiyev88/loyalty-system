package com.example.loyaltysystem.models;

import jakarta.persistence.*;
import lombok.Data;
import java.util.Date;

@Data
@Entity
public class Transaction {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    private Client client;

    private String type; // "ACCRUAL" или "REDEMPTION"
    private float amount;

    @Temporal(TemporalType.TIMESTAMP)
    private Date date = new Date();
}
