package ru.robq.blps1.controller;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import ru.robq.blps1.dao.PaymentDAO;
import ru.robq.blps1.exception.PaymentNotFoundException;
import ru.robq.blps1.exception.WorkOrderNotFoundException;
import ru.robq.blps1.model.OrderStatus;
import ru.robq.blps1.model.Payment;
import ru.robq.blps1.model.WorkOrder;
import ru.robq.blps1.model.Worker;
import ru.robq.blps1.repository.PaymentRepository;
import ru.robq.blps1.repository.WorkOrderRepository;
import ru.robq.blps1.repository.WorkerRepository;

import java.time.LocalDate;
import java.util.List;
import java.util.Random;

@Controller
@AllArgsConstructor
public class OrderController {
    private WorkOrderRepository workOrderRepository;
    private PaymentRepository paymentRepository;
    private WorkerRepository workerRepository;

    @PostMapping("/order/new")
    public @ResponseBody WorkOrder makeOrder(@RequestBody String description) {
        return workOrderRepository.save(new WorkOrder(description));
    }

    @PostMapping("/payment")
    public @ResponseBody Payment payment(@RequestBody PaymentDAO paymentDAO) {
        return paymentRepository.save(Payment.builder()
                .cardNumber(paymentDAO.getCardNumber())
                .cardExpiration(paymentDAO.getCardExpiration())
                .cardName(paymentDAO.getCardName())
                .orderId(paymentDAO.getOrderId())
                .sum(paymentDAO.getSum())
                .resolved(false)
                .build());
    }

    @PutMapping("/payment/accept")
    public @ResponseBody WorkOrder paymentAccept(@RequestParam Long paymentId) throws PaymentNotFoundException, WorkOrderNotFoundException {
        Payment payment = paymentRepository.findById(paymentId).orElseThrow(() -> new PaymentNotFoundException("Payment not found."));
        WorkOrder order = workOrderRepository.findById(payment.getOrderId()).orElseThrow(() -> new WorkOrderNotFoundException("Order not found."));
        payment.setResolved(true);
        List<Worker> freeWorkers = workerRepository.findAllByIsBusy(false);
        if (freeWorkers.isEmpty()) {
            order.setStatus(OrderStatus.PENDING);
        } else {
            Worker worker = freeWorkers.get(new Random().nextInt(freeWorkers.size()));
            worker.setBusy(true);
            order.setStatus(OrderStatus.AT_WORK);
            order.setWorker(worker);
            order.setDeadline(LocalDate.now().plusDays(3));
            workerRepository.save(worker);
        }
        paymentRepository.save(payment);
        return workOrderRepository.save(order);
    }

    @PutMapping("/payment/decline")
    public @ResponseBody WorkOrder paymentDecline(@RequestParam Long paymentId) throws PaymentNotFoundException, WorkOrderNotFoundException {
        Payment payment = paymentRepository.findById(paymentId).orElseThrow(() -> new PaymentNotFoundException("Payment not found."));
        WorkOrder order = workOrderRepository.findById(payment.getOrderId()).orElseThrow(() -> new WorkOrderNotFoundException("Order not found."));
        payment.setResolved(true);
        order.setStatus(OrderStatus.DECLINED);
        paymentRepository.save(payment);
        return workOrderRepository.save(order);
    }

    @GetMapping("/payment/{id}")
    public @ResponseBody Payment paymentInfo(@PathVariable Long id) throws PaymentNotFoundException{
        return paymentRepository.findById(id).orElseThrow(() -> new PaymentNotFoundException("Payment not found."));
    }

    @GetMapping("/order/{id}")
    public @ResponseBody WorkOrder orderInfo(@PathVariable Long id) throws WorkOrderNotFoundException {
        return workOrderRepository.findById(id).orElseThrow(() -> new WorkOrderNotFoundException("Order not found."));
    }

    @PutMapping("/order/ready")
    public @ResponseBody WorkOrder orderReady(@RequestParam Long orderId) throws WorkOrderNotFoundException {
        WorkOrder order = workOrderRepository.findById(orderId).orElseThrow(() -> new WorkOrderNotFoundException("Order not found."));
        order.setStatus(OrderStatus.READY);
        order.getWorker().setBusy(false);
        workerRepository.save(order.getWorker());
        return workOrderRepository.save(order);
    }

}
