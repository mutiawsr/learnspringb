package com.ls.learnspringb.services;

import java.util.List;

import com.ls.learnspringb.entities.OrderDetails;

public interface OrderDetailsService {
    
    List<OrderDetails> getAllOrderDetails();

    OrderDetails saveOrderDetails(OrderDetails orderDetails);

}
