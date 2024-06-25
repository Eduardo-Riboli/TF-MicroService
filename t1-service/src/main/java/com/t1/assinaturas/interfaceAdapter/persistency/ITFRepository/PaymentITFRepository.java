package com.t1.assinaturas.interfaceAdapter.persistency.ITFRepository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.t1.assinaturas.interfaceAdapter.persistency.entitysJPA.Payment;

public interface PaymentITFRepository extends JpaRepository<Payment, Long> {
}
