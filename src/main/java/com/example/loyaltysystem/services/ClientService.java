package com.example.loyaltysystem.services;

import com.example.loyaltysystem.models.Client;
import com.example.loyaltysystem.repositories.ClientRepository;
import com.example.loyaltysystem.exceptions.CustomException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ClientService {
    private static final Logger log = LoggerFactory.getLogger(ClientService.class);
    private final ClientRepository clientRepository;

    public ClientService(ClientRepository clientRepository) {
        this.clientRepository = clientRepository;
    }

    public Client registerClient(Client client) {
        // Проверка на существующий email
        Client existingClient = clientRepository.findByEmail(client.getEmail());
        if (existingClient != null) {
            if (existingClient.getName().equals(client.getName()) &&
                    existingClient.getPhone().equals(client.getPhone())) {
                throw new CustomException("Пользователь с такими данными уже зарегистрирован", "ERROR");
            }
            throw new CustomException("Пользователь с таким email уже зарегистрирован", "ERROR");
        }

        // Проверка на дублирование имени
        if (clientRepository.findAll().stream().anyMatch(c -> c.getName().equals(client.getName()))) {
            throw new CustomException("Пользователь с таким именем уже зарегистрирован", "ERROR");
        }

        // Проверка на дублирование номера телефона
        if (clientRepository.findAll().stream().anyMatch(c -> c.getPhone().equals(client.getPhone()))) {
            throw new CustomException("Пользователь с таким номером телефона уже зарегистрирован", "ERROR");
        }

        // Сохранение клиента
        clientRepository.save(client);
        log.info("Клиент успешно зарегистрирован: {}", client.getName());
        return client;
    }

    public Client updateBonus(Long id, float amount) {
        Client client = clientRepository.findById(id)
                .orElseThrow(() -> new CustomException("Клиент с ID " + id + " не найден", "ERROR"));

        client.setBonusBalance(client.getBonusBalance() + amount);
        clientRepository.save(client);
        log.info("Бонус успешно добавлен клиенту: {}", client.getName());
        return client;
    }

    public Client deductBonus(Long id, float amount) {
        Client client = clientRepository.findById(id)
                .orElseThrow(() -> new CustomException("Клиент с ID " + id + " не найден", "ERROR"));

        if (client.getBonusBalance() < amount) {
            throw new CustomException("Недостаточно бонусов для списания", "ERROR");
        }

        client.setBonusBalance(client.getBonusBalance() - amount);
        clientRepository.save(client);
        log.info("Бонус успешно списан у клиента: {}", client.getName());
        return client;
    }

    public List<Client> getAllClients() {
        List<Client> clients = clientRepository.findAll();
        if (clients.isEmpty()) {
            throw new CustomException("Список клиентов пуст", "ERROR");
        }
        return clients;
    }

    public void deleteClient(Long id) {
        if (!clientRepository.existsById(id)) {
            throw new CustomException("Клиент с ID " + id + " не существует", "ERROR");
        }
        clientRepository.deleteById(id);
        log.info("Клиент с ID {} успешно удалён", id);
    }

    public Client updateClient(Long id, Client updatedClient) {
        Client existingClient = clientRepository.findById(id)
                .orElseThrow(() -> new CustomException("Клиент с ID " + id + " не найден", "ERROR"));

        // Проверка на совпадение данных
        if (existingClient.getName().equals(updatedClient.getName()) &&
                existingClient.getEmail().equals(updatedClient.getEmail()) &&
                existingClient.getPhone().equals(updatedClient.getPhone())) {
            throw new CustomException("Обновление данных не выполнено, так как данные идентичны существующим", "ERROR");
        }

        existingClient.setName(updatedClient.getName());
        existingClient.setEmail(updatedClient.getEmail());
        existingClient.setPhone(updatedClient.getPhone());
        existingClient.setBonusBalance(updatedClient.getBonusBalance());

        clientRepository.save(existingClient);
        log.info("Клиент с ID {} успешно обновлён", id);
        return existingClient;
    }
}
