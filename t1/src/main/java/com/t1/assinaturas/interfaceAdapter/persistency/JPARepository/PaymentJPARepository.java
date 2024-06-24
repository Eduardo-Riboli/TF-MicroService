package com.t1.assinaturas.interfaceAdapter.persistency.JPARepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.t1.assinaturas.domain.entityModels.PaymentModel;
import com.t1.assinaturas.domain.repositories.IPaymentRepository;
import com.t1.assinaturas.interfaceAdapter.persistency.ITFRepository.PaymentITFRepository;
import com.t1.assinaturas.interfaceAdapter.persistency.entitysJPA.Payment;

@Repository
public class PaymentJPARepository implements IPaymentRepository {
    private PaymentITFRepository payment;

    @Autowired
    public PaymentJPARepository(PaymentITFRepository payment) {
        this.payment = payment;
    }

    @Override
    public PaymentModel save(PaymentModel paymentModel) {
        Payment newPayment = Payment.fromPaymentModel(paymentModel);
        payment.save(newPayment);

        return paymentModel;
    }
}
