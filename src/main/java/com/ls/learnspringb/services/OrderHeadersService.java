package com.ls.learnspringb.services;

import java.util.List;

import com.ls.learnspringb.entities.OrderHeaders;

public interface OrderHeadersService {
    
    List<OrderHeaders> getAllOrderHeaders();

    Long getHeaderIdByReference(Long reference);

    OrderHeaders getOrderHeadersByReference(Long reference);

    OrderHeaders saveOrderHeaders(OrderHeaders orderHeaders);

}
