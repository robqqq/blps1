package ru.robq.blps1.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import ru.robq.blps1.model.OrderStatus;
import ru.robq.blps1.model.WorkOrder;

@Repository
public interface WorkOrderRepository extends JpaRepository<WorkOrder, Long> {
    List<WorkOrder> findAllByStatus(OrderStatus status);
}
