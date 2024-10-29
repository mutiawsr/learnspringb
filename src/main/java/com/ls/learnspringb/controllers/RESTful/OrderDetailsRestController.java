package com.ls.learnspringb.controllers.restful;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ls.learnspringb.dtos.responses.OrderDetailsResponseDto;
import com.ls.learnspringb.entities.OrderDetails;
import com.ls.learnspringb.services.OrderDetailsService;
import com.ls.learnspringb.services.OrderHeadersService;

@RestController
@RequestMapping("/api/orderdetails")
@CrossOrigin("http://localhost:9002")
public class OrderDetailsRestController {

    @Autowired
    OrderDetailsService orderDetailsService;

    @Autowired
    OrderHeadersService orderHeadersService;

    @GetMapping("")
    public ResponseEntity<?> getAllOrderDetails() {
        LinkedHashMap<String, Object> resultMap = new LinkedHashMap<>();
        ModelMapper modelMapper = new ModelMapper();
        try {
            List<OrderDetails> orderDetailsList = orderDetailsService.getAllOrderDetails();
            List<OrderDetailsResponseDto> orderDetailsResponseDtos = new ArrayList<>();
            for (OrderDetails orderDetails : orderDetailsList) {
                OrderDetailsResponseDto orderDetailsResponseDto = modelMapper.map(orderDetails, OrderDetailsResponseDto.class);
                orderDetailsResponseDtos.add(orderDetailsResponseDto);
            }
            resultMap.put("status", 200);
            resultMap.put("message", "success");
            resultMap.put("data", orderDetailsResponseDtos);
            return new ResponseEntity<>(resultMap, HttpStatus.OK);
        } catch (Exception e) {
            resultMap.put("status", 500);
            resultMap.put("message", "failed");
            resultMap.put("error", e);
            return new ResponseEntity<>(resultMap, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping("/{reference}/all")
    public ResponseEntity<?> saveAllOrderDetails(@PathVariable Long reference, @RequestBody List<Map<String, Object>> orderDetailsList) {
        LinkedHashMap<String, Object> resultMap = new LinkedHashMap<>();
        try {
            List<OrderDetails> orderDetailsResult = new ArrayList<>();
            Long headerId = orderHeadersService.getHeaderIdByReference(reference);
            for (Map<String, Object> order : orderDetailsList) {
                OrderDetails orderDetails = new OrderDetails();
                orderDetails.setHeaderId(headerId);
                orderDetails.setVariantId(((Number) order.get("id")).longValue());
                orderDetails.setPrice(new BigDecimal(order.get("price").toString()));
                orderDetails.setQuantity(new BigDecimal(order.get("quantity").toString()));
                orderDetails.setIsDeleted(false);
                orderDetailsService.saveOrderDetails(orderDetails);
                orderDetailsResult.add(orderDetails);
            }
            resultMap.put("status", 200);
            resultMap.put("message", "success");
            resultMap.put("data", orderDetailsResult);
            return new ResponseEntity<>(resultMap, HttpStatus.OK);
        } catch (Exception e) {
            resultMap.put("status", 500);
            resultMap.put("message", "failed");
            resultMap.put("error", e);
            return new ResponseEntity<>(resultMap, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

}
