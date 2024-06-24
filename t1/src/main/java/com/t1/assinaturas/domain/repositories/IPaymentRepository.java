package com.t1.assinaturas.domain.repositories;

import com.t1.assinaturas.domain.entityModels.PaymentModel;

public interface IPaymentRepository {

    PaymentModel save(PaymentModel payment);

}