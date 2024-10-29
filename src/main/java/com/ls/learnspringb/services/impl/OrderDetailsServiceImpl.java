package com.ls.learnspringb.services.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ls.learnspringb.entities.OrderDetails;
import com.ls.learnspringb.repositories.OrderDetailsRepository;
import com.ls.learnspringb.services.OrderDetailsService;

@Service
public class OrderDetailsServiceImpl implements OrderDetailsService {

    @Autowired
    OrderDetailsRepository orderDetailsRepository;

    @Override
    public List<OrderDetails> getAllOrderDetails() {
        return orderDetailsRepository.getAllOrderDetails();
    }

    @Override
    public OrderDetails saveOrderDetails(OrderDetails orderDetails) {
        return orderDetailsRepository.save(orderDetails);
    }

}
