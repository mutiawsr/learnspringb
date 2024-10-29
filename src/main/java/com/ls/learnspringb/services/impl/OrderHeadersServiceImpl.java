package com.ls.learnspringb.services.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ls.learnspringb.entities.OrderHeaders;
import com.ls.learnspringb.repositories.OrderHeadersRepository;
import com.ls.learnspringb.services.OrderHeadersService;

@Service
public class OrderHeadersServiceImpl implements OrderHeadersService {

    @Autowired
    OrderHeadersRepository orderHeadersRepository;

    @Override
    public List<OrderHeaders> getAllOrderHeaders() {
        return orderHeadersRepository.getAllOrderHeaders();
    }

    @Override
    public Long getHeaderIdByReference(Long reference) {
        return orderHeadersRepository.getHeaderIdByReference(reference);
    }

    @Override
    public OrderHeaders getOrderHeadersByReference(Long reference) {
        return orderHeadersRepository.getOrderHeadersByReference(reference);
    }

    @Override
    public OrderHeaders saveOrderHeaders(OrderHeaders orderHeaders) {
        return orderHeadersRepository.save(orderHeaders);
    }

}
