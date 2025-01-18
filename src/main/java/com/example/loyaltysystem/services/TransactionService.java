package com.example.loyaltysystem.services;

import com.example.loyaltysystem.models.Client;
import com.example.loyaltysystem.models.Transaction;
import com.example.loyaltysystem.repositories.ClientRepository;
import com.example.loyaltysystem.repositories.TransactionRepository;
import org.springframework.stereotype.Service;
import java.util.List;


@Service
public class TransactionService {
    private final ClientRepository clientRepository;
    private final TransactionRepository transactionRepository;

    public TransactionService(ClientRepository clientRepository, TransactionRepository transactionRepository) {
        this.clientRepository = clientRepository;
        this.transactionRepository = transactionRepository;
    }

    public Transaction accrueBonus(Long clientId, float amount) {
        Client client = clientRepository.findById(clientId)
                .orElseThrow(() -> new RuntimeException("Client not found"));

        client.setBonusBalance(client.getBonusBalance() + amount);
        clientRepository.save(client);

        Transaction transaction = new Transaction();
        transaction.setClient(client);
        transaction.setType("ACCRUAL");
        transaction.setAmount(amount);

        return transactionRepository.save(transaction);
    }

    public List<Transaction> getTransactionsByClientId(Long clientId) {
        return transactionRepository.findByClientId(clientId);
    }
}
