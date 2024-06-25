package com.t1.assinaturas.interfaceAdapter.persistency.ITFRepository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.t1.assinaturas.interfaceAdapter.persistency.entitysJPA.App;

public interface AppITFRepository extends JpaRepository<App, Long> {
}
