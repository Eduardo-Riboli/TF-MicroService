package com.t1.assinaturas.interfaceAdapter.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.t1.assinaturas.application.dto.ClientResponseDTO;
import com.t1.assinaturas.application.useCases.FindAllClientsUC;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/servcad")
@RequiredArgsConstructor
public class ClientController {

    private FindAllClientsUC clientsUC;

    @Autowired
    public ClientController(FindAllClientsUC clientUC) {
        this.clientsUC = clientUC;
    }

    @GetMapping("/clientes")
    public ResponseEntity<List<ClientResponseDTO>> getAllClients() {
        return clientsUC.run();
    }
}
