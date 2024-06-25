package com.t1.assinaturas.interfaceAdapter.persistency.JPARepository;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.t1.assinaturas.domain.entityModels.ClientModel;
import com.t1.assinaturas.domain.repositories.IClientRepository;
import com.t1.assinaturas.interfaceAdapter.persistency.ITFRepository.ClientITFRepository;
import com.t1.assinaturas.interfaceAdapter.persistency.entitysJPA.Client;

@Repository
public class ClientJPARepository implements IClientRepository {

    private ClientITFRepository client;

    @Autowired
    public ClientJPARepository(ClientITFRepository client) {
        this.client = client;
    }

    @Override
    public List<ClientModel> findAll() {
        List<Client> clients = client.findAll();

        return clients.stream()
                .map(it -> Client.toClientModel(it))
                .toList();
    }

    @Override
    public ClientModel save(ClientModel clientModel) {
        Client newClient = Client.fromClientModel(clientModel);
        client.save(newClient);

        return clientModel;
    }

    @Override
    public Optional<ClientModel> findById(Long clientId) {
        Optional<Client> newClient = client.findById(clientId);

        if (newClient.isPresent()) {
            return Optional.of(Client.toClientModel(newClient.get()));
        } else {
            return Optional.empty();
        }
    }
}
