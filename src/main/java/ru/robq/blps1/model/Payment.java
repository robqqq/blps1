package ru.robq.blps1.model;

import jakarta.persistence.*;
import lombok.*;

@Getter
@Setter
@Builder
@Entity
@Table(name = "payment")
@NoArgsConstructor
@AllArgsConstructor
public class Payment {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", nullable = false)
    private Long id;
    private String cardNumber;
    private String cardExpiration;
    private String cardName;
    private Long orderId;
    private double sum;
    private boolean resolved;
}
