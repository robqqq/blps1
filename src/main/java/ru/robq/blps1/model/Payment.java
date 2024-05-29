package ru.robq.blps1.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
@Entity
@Table(name = "payment")
public class Payment {
    private String cardNumber;
    private String cardExpiration;
    private String cardName;
    private Long orderId;
    private double sum;
    private boolean resolved;
}
