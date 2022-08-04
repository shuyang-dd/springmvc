package com.springboot.blog.service.impl;

import com.springboot.blog.dto.OrderRequest;
import com.springboot.blog.dto.OrderResponse;
import com.springboot.blog.entity.Order;
import com.springboot.blog.entity.Payment;
import com.springboot.blog.exception.PaymentException;
import com.springboot.blog.repository.OrderRepository;
import com.springboot.blog.repository.PaymentRepository;
import com.springboot.blog.service.OrderService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;

@Service
public class OrderServiceImpl implements OrderService {
    private OrderRepository orderRepository;
    private PaymentRepository paymentRepository;

    public OrderServiceImpl(OrderRepository orderRepository, PaymentRepository paymentRepository) {
        this.orderRepository = orderRepository;
        this.paymentRepository = paymentRepository;
    }

    @Override
    @Transactional
    public OrderResponse placeOrder(OrderRequest orderRequest) {

        Order order=orderRequest.getOrder();
        order.setStatus("INPROGRESS");
        order.setOrderTrackingNumber((UUID.randomUUID().toString()));
        orderRepository.save(order);

        Payment payment=orderRequest.getPayment();

        if(!payment.getType().equals("DEBIT")){
            throw new PaymentException("payment card type");

        }
        payment.setOrderId(order.getId());
        paymentRepository.save(payment);

        OrderResponse orderResponse=new OrderResponse();
        orderResponse.setOrderTrackingNumber(order.getOrderTrackingNumber());
        orderResponse.setStatus(order.getStatus());
        orderResponse.setMessage("Success");

        return orderResponse;
    }
}
