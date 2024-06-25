package com.t1.assinaturas.domain.services;

import lombok.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.t1.assinaturas.domain.entityModels.ClientModel;
import com.t1.assinaturas.domain.repositories.IClientRepository;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ClientService {
    private IClientRepository clientRepository;
    
    @Autowired
    public ClientService(IClientRepository clientRepository) {
        this.clientRepository = clientRepository;
    }

    public List<ClientModel> findAll() {
        return clientRepository.findAll();
    }

    public ClientModel registerClient(ClientModel clientModel) {
        return clientRepository.save(clientModel);
    }

    public Optional<ClientModel> editClient(Long clientId, ClientModel clientDetails) {
        Optional<ClientModel> optionalClient = clientRepository.findById(clientId);
        if (optionalClient.isPresent()) {
            ClientModel client = optionalClient.get();
            client.setName(clientDetails.getName());
            client.setEmail(clientDetails.getEmail());
            return Optional.of(clientRepository.save(client));
        }
        return Optional.empty();
    }
}
