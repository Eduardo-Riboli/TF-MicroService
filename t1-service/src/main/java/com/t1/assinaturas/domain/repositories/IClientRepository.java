package com.t1.assinaturas.domain.repositories;

import java.util.List;
import java.util.Optional;

import com.t1.assinaturas.domain.entityModels.ClientModel;

public interface IClientRepository {
    List<ClientModel> findAll();

    ClientModel save(ClientModel clientModel);

    Optional<ClientModel> findById(Long clientId);
}