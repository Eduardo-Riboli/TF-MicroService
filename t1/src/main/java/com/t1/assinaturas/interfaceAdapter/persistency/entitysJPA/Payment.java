package com.t1.assinaturas.interfaceAdapter.persistency.entitysJPA;

import java.math.BigDecimal;
import java.util.Date;

import com.t1.assinaturas.domain.entityModels.PaymentModel;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import lombok.*;

@Entity
@Builder
@Getter
@Setter
public class Payment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @ManyToOne
    private Subscription subscription;
    private BigDecimal amountPaid;
    private Date paymentDate;
    private String promotionCode;

    public Payment(long id, Subscription subscription, BigDecimal amountPaid, Date paymentDate, String promotionCode) {
        this.id = id;
        this.subscription = subscription;
        this.amountPaid = amountPaid;
        this.paymentDate = paymentDate;
        this.promotionCode = promotionCode;
    }

    public static Payment fromPaymentModel(PaymentModel paymentModel) {
        return new Payment(paymentModel.getId(), Subscription.fromSubscriptionModel(paymentModel.getSubscription()),
                paymentModel.getAmountPaid(), paymentModel.getPaymentDate(), paymentModel.getPromotionCode());
    }

    public static PaymentModel toPaymentModel(Payment payment) {
        return new PaymentModel(payment.getId(), Subscription.toSubscriptionModel(payment.getSubscription()),
                payment.getAmountPaid(), payment.getPaymentDate(), payment.getPromotionCode());
    }
}
