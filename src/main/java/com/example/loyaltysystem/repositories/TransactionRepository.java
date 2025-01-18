package com.example.loyaltysystem.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import com.example.loyaltysystem.models.Transaction;
import java.util.List;

public interface TransactionRepository extends JpaRepository<Transaction, Long> {
    List<Transaction> findByClientId(Long clientId);
}