package com.springboot.blog.dto;

import com.springboot.blog.entity.Order;
import com.springboot.blog.entity.Payment;
import lombok.Data;

@Data
public class OrderRequest {
 private Order order;
 private Payment payment;
}
