package com.t1.assinaturas.domain.services;

import lombok.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.t1.assinaturas.application.dto.PaymentResponseDTO;
import com.t1.assinaturas.domain.entityModels.PaymentModel;
import com.t1.assinaturas.domain.entityModels.SubscriptionModel;
import com.t1.assinaturas.domain.repositories.IPaymentRepository;
import com.t1.assinaturas.domain.repositories.ISubscriptionRepository;

import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.Calendar;
import java.util.Date;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class PaymentService {
    private ISubscriptionRepository subscriptionRepository;
    private IPaymentRepository paymentRepository;
    
    @Autowired
    public PaymentService(ISubscriptionRepository subscriptionRepository,
            IPaymentRepository paymentRepository) {
        this.subscriptionRepository = subscriptionRepository;
        this.paymentRepository = paymentRepository;
    }

    @Transactional
    public PaymentResponseDTO registerPayment(Date paymentDate, Long subscriptionId, BigDecimal paymentValue) {
        Optional<SubscriptionModel> optionalSubscription = subscriptionRepository.findById(subscriptionId);
        if (optionalSubscription.isEmpty()) {
            return PaymentResponseDTO.builder()
                    .status("ASSINATURA_INEXISTENTE")
                    .date(paymentDate)
                    .value(paymentValue)
                    .build();
        }

        SubscriptionModel subscription = optionalSubscription.get();
        BigDecimal monthlyCost = subscription.getApp().getMonthlyCost();

        if (paymentValue.compareTo(monthlyCost) != 0) {
            return PaymentResponseDTO.builder()
                    .status("VALOR_INCORRETO")
                    .date(subscription.getEndDate())
                    .value(paymentValue)
                    .build();
        }

        PaymentModel payment = new PaymentModel();
        payment.setPaymentDate(paymentDate);
        payment.setAmountPaid(paymentValue);
        payment.setSubscription(subscription);

        paymentRepository.save(payment);

        Calendar calendar = Calendar.getInstance();
        if (subscription.getEndDate().before(paymentDate)) {
            calendar.setTime(paymentDate);
        } else {
            calendar.setTime(subscription.getEndDate());
        }
        calendar.add(Calendar.DATE, 30);
        Date newEndDate = calendar.getTime();

        subscription.setEndDate(newEndDate);
        subscriptionRepository.save(subscription);

        if (!subscription.getStatus()) {
            subscription.setStatus(true);
            subscriptionRepository.save(subscription);
        }

        return PaymentResponseDTO.builder()
                .status("PAGAMENTO_OK")
                .date(newEndDate)
                .value(BigDecimal.ZERO)
                .build();
    }
}
