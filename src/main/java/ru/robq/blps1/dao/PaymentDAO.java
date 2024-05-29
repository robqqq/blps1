package ru.robq.blps1.dao;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import ru.robq.blps1.model.Payment;

@Getter
@Setter
@AllArgsConstructor
public class PaymentDAO {
    private String cardNumber;
    private String cardExpiration;
    private String cardName;
    private Long orderId;
    private double sum;

    public PaymentDAO(Payment payment) {
        this(payment.getCardNumber(), payment.getCardExpiration(), payment.getCardName(), payment.getOrderId(), payment.getSum());
    }
}
