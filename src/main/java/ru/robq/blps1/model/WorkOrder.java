package ru.robq.blps1.model;

import java.time.LocalDate;
import java.util.Date;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "work_order")
public class WorkOrder {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", nullable = false)
    private Long id;
    private String description;
    private OrderStatus status;
    private LocalDate deadline;
    @ManyToOne
    @JoinColumn(name = "worker_id")
    private Worker worker;

    public WorkOrder(String description) {
        super();
        this.description = description;
        this.status = OrderStatus.UNPAID;
    }
}
