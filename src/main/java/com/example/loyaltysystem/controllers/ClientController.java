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
    public ResponseEntity<CustomResponse> updateClient(@PathVariable Long id, @RequestBody Client updatedClient) {
        Client client = clientService.updateClient(id, updatedClient);

        CustomResponse response = new CustomResponse(
                "Клиент успешно обновлён",
                client.getId(),
                "SUCCESS"
        );
        return ResponseEntity.ok(response);
    }

    @PostMapping("/register")
    public ResponseEntity<CustomResponse> registerClient(@RequestBody Client client) {
        Client savedClient = clientService.registerClient(client);

        CustomResponse response = new CustomResponse(
                "Клиент успешно зарегистрирован",
                savedClient.getId(),
                "SUCCESS"
        );
        return ResponseEntity.ok(response);
    }

    @PutMapping("/{id}/bonus")
    public ResponseEntity<CustomResponse> updateBonus(@PathVariable Long id, @RequestBody Map<String, Float> request) {
        float amount = request.getOrDefault("amount", 0.0f);
        Client updatedClient = clientService.updateBonus(id, amount);

        CustomResponse response = new CustomResponse(
                "Бонус успешно добавлен",
                updatedClient.getBonusBalance(),
                "SUCCESS"
        );
        return ResponseEntity.ok(response);
    }

    @GetMapping
    public ResponseEntity<CustomResponse> getAllClients() {
        List<Client> clients = clientService.getAllClients();

        CustomResponse response = new CustomResponse(
                "Список всех клиентов успешно получен",
                clients,
                "SUCCESS"
        );
        return ResponseEntity.ok(response);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<CustomResponse> deleteClient(@PathVariable Long id) {
        clientService.deleteClient(id);

        CustomResponse response = new CustomResponse(
                "Клиент успешно удалён",
                id,
                "SUCCESS"
        );
        return ResponseEntity.ok(response);
    }

    @PutMapping("/{id}/deduct-bonus")
    public ResponseEntity<CustomResponse> deductBonus(@PathVariable Long id, @RequestBody Map<String, Float> request) {
        float amount = request.getOrDefault("amount", 0.0f);
        Client updatedClient = clientService.deductBonus(id, amount);

        CustomResponse response = new CustomResponse(
                "Бонус успешно списан",
                updatedClient.getBonusBalance(),
                "SUCCESS"
        );
        return ResponseEntity.ok(response);
    }

    @GetMapping("/{id}/transactions")
    public ResponseEntity<CustomResponse> getClientTransactions(@PathVariable Long id) {
        List<Transaction> transactions = transactionService.getTransactionsByClientId(id);

        CustomResponse response = new CustomResponse(
                "Список транзакций клиента успешно получен",
                transactions,
                "SUCCESS"
        );
        return ResponseEntity.ok(response);
    }
}
