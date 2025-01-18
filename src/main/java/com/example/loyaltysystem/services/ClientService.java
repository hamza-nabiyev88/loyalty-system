package com.example.loyaltysystem.services;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import com.example.loyaltysystem.models.Client;
import com.example.loyaltysystem.repositories.ClientRepository;
import java.util.List;

@Service
public class ClientService {
    private static final Logger log = LoggerFactory.getLogger(ClientService.class);

    private final ClientRepository clientRepository;

    public ClientService(ClientRepository clientRepository) {
        this.clientRepository = clientRepository;
    }

    public Client registerClient(Client client) {
        if (clientRepository.findByEmail(client.getEmail()) != null) {
            throw new RuntimeException("Client with this email already exists");
        }
        clientRepository.save(client);
        log.info("Client registered successfully: {}", client.getName());
        return client;
    }

    public Client updateBonus(Long id, float amount) {
        Client client = clientRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Client not found"));
        client.setBonusBalance(client.getBonusBalance() + amount);
        clientRepository.save(client);
        log.info("Bonus updated successfully for client: {}", client.getName());
        return client;
    }

    public Client deductBonus(Long id, float amount) {
        Client client = clientRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Client not found"));

        if (client.getBonusBalance() < amount) {
            throw new RuntimeException("Insufficient bonus balance");
        }

        client.setBonusBalance(client.getBonusBalance() - amount);
        clientRepository.save(client);
        log.info("Bonus deducted successfully for client: {}", client.getName());
        return client;
    }

    public List<Client> getAllClients() {
        return clientRepository.findAll();
    }

    public void deleteClient(Long id) {
        if (!clientRepository.existsById(id)) {
            throw new RuntimeException("Client not found");
        }
        clientRepository.deleteById(id);
        log.info("Client with ID: {} successfully deleted.", id);
    }

    public Client updateClient(Long id, Client updatedClient) {
        Client existingClient = clientRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Client not found"));

        existingClient.setName(updatedClient.getName());
        existingClient.setEmail(updatedClient.getEmail());
        existingClient.setPhone(updatedClient.getPhone());
        existingClient.setBonusBalance(updatedClient.getBonusBalance());

        clientRepository.save(existingClient);
        log.info("Client with ID: {} successfully updated.", id);
        return existingClient;
    }
}
