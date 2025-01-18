package com.example.loyaltysystem.controllers;

import java.util.Map;
import java.util.List;
import com.example.loyaltysystem.models.Transaction;
import com.example.loyaltysystem.services.TransactionService;
import com.example.loyaltysystem.dto.CustomResponse;
import com.example.loyaltysystem.services.ClientService;
import com.example.loyaltysystem.models.Client;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/clients")
public class ClientController {
    private final ClientService clientService;
    private final TransactionService transactionService;

    public ClientController(ClientService clientService, TransactionService transactionService) {
        this.clientService = clientService;
        this.transactionService = transactionService;
    }

    @PutMapping("/{id}")
    public ResponseEntity<Client> updateClient(@PathVariable Long id, @RequestBody Client updatedClient) {
        Client client = clientService.updateClient(id, updatedClient);
        return ResponseEntity.ok(client);
    }

    @PostMapping("/register")
    public ResponseEntity<Client> registerClient(@RequestBody Client client) {
        Client savedClient = clientService.registerClient(client);
        return ResponseEntity.ok(savedClient);
    }

    @PutMapping("/{id}/bonus")
    public ResponseEntity<CustomResponse> updateBonus(@PathVariable Long id, @RequestBody Map<String, Float> request) {
        float amount = request.getOrDefault("amount", 0.0f);
        Client updatedClient = clientService.updateBonus(id, amount);

        CustomResponse response = new CustomResponse("Бонус успешно добавлен", updatedClient.getBonusBalance(), "SUCCESS");
        return ResponseEntity.ok(response);
    }

    @GetMapping
    public ResponseEntity<List<Client>> getAllClients() {
        List<Client> clients = clientService.getAllClients();
        return ResponseEntity.ok(clients);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteClient(@PathVariable Long id) {
        clientService.deleteClient(id);
        return ResponseEntity.noContent().build();
    }

    @PutMapping("/{id}/deduct-bonus")
    public ResponseEntity<CustomResponse> deductBonus(@PathVariable Long id, @RequestBody Map<String, Float> request) {
        float amount = request.getOrDefault("amount", 0.0f);
        Client updatedClient = clientService.deductBonus(id, amount);

        // Добавляем третий аргумент "SUCCESS" при создании CustomResponse
        CustomResponse response = new CustomResponse("Bonus successfully deducted", updatedClient.getBonusBalance(), "SUCCESS");
        return ResponseEntity.ok(response);
    }

    @GetMapping("/{id}/transactions")
    public ResponseEntity<List<Transaction>> getClientTransactions(@PathVariable Long id) {
        List<Transaction> transactions = transactionService.getTransactionsByClientId(id);
        return ResponseEntity.ok(transactions);
    }
}
