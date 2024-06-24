package com.t1.assinaturas.interfaceAdapter.persistency.entitysJPA;

import com.t1.assinaturas.domain.entityModels.ClientModel;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.*;

@Entity
@Builder
@Getter
@Setter
@AllArgsConstructor
public class Client {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String name;
    private String email;

    public Client() {}


    public static Client fromClientModel(ClientModel clientModel) {
        return new Client(clientModel.getId(), clientModel.getName(), clientModel.getEmail());
    }

    public static ClientModel toClientModel(Client client) {
        return new ClientModel(client.getId(), client.getName(), client.getEmail());
    }
}
