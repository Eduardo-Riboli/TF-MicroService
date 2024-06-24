package com.t1.assinaturas.interfaceAdapter.persistency.ITFRepository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.t1.assinaturas.interfaceAdapter.persistency.entitysJPA.Client;

public interface ClientITFRepository extends JpaRepository<Client, Long> {
}