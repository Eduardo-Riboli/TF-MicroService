package com.t1.assinaturas.application.useCases;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import com.t1.assinaturas.application.dto.ClientResponseDTO;
import com.t1.assinaturas.domain.entityModels.ClientModel;
import com.t1.assinaturas.domain.services.ClientService;

@Component
public class FindAllClientsUC {
    private ClientService clientService;

    @Autowired
    public FindAllClientsUC(ClientService clientService) {
        this.clientService = clientService;
    }

    public ResponseEntity<List<ClientResponseDTO>> run() {
        List<ClientModel> listClients = clientService.findAll();

        List<ClientResponseDTO> formattedClients = listClients.stream().map(client -> {
            ClientResponseDTO dto = new ClientResponseDTO();
            dto.setId(client.getId());
            dto.setName(client.getName());
            dto.setEmail(client.getEmail());
            return dto;

        }).collect(Collectors.toList());

        return ResponseEntity.status(200).body(formattedClients);
    }
}
