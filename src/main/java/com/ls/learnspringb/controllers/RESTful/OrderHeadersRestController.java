package com.ls.learnspringb.controllers.restful;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;

import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ls.learnspringb.dtos.requests.OrderHeadersRequestDto;
import com.ls.learnspringb.dtos.responses.OrderHeadersResponseDto;
import com.ls.learnspringb.entities.OrderHeaders;
import com.ls.learnspringb.services.OrderHeadersService;

@RestController
@RequestMapping("/api/orderheaders")
@CrossOrigin("http://localhost:9002")
public class OrderHeadersRestController {
    
    @Autowired
    OrderHeadersService orderHeadersService;
    
    @GetMapping("")
    public ResponseEntity<?> getAllOrderHeaders() {
        LinkedHashMap<String, Object> resultMap = new LinkedHashMap<>();
        ModelMapper modelMapper = new ModelMapper();
        try {
            List<OrderHeaders> orderHeaders = orderHeadersService.getAllOrderHeaders();
            List<OrderHeadersResponseDto> orderHeadersResponseDtos = new ArrayList<>();
            for (OrderHeaders orderHeader : orderHeaders) {
                OrderHeadersResponseDto orderHeadersResponseDto = modelMapper.map(orderHeader, OrderHeadersResponseDto.class);
                orderHeadersResponseDtos.add(orderHeadersResponseDto);
            }
            resultMap.put("status", 200);
            resultMap.put("message", "success");
            resultMap.put("data", orderHeadersResponseDtos);
            return new ResponseEntity<>(resultMap, HttpStatus.OK);
        } catch (Exception e) {
            resultMap.put("status", 500);
            resultMap.put("message", "failed");
            resultMap.put("error", e);
            return new ResponseEntity<>(resultMap, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/{reference}")
    public ResponseEntity<?> getHeaderIdByReference(@PathVariable Long reference) {
        LinkedHashMap<String, Object> resultMap = new LinkedHashMap<>();
        try {
            Long headerId = orderHeadersService.getHeaderIdByReference(reference);
            resultMap.put("status", 200);
            resultMap.put("message", "success");
            resultMap.put("data", headerId);
            return new ResponseEntity<>(resultMap, HttpStatus.OK);
        } catch (Exception e) {
            resultMap.put("status", 500);
            resultMap.put("message", "failed");
            resultMap.put("error", e);
            return new ResponseEntity<>(resultMap, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    
    @PostMapping("")
    public ResponseEntity<?> saveNewOrderHeaders(@RequestBody OrderHeadersRequestDto orderHeadersRequestDto) {
        LinkedHashMap<String, Object> resultMap = new LinkedHashMap<>();
        ModelMapper modelMapper = new ModelMapper();
        modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);
        try {
            OrderHeaders orderHeaders = new OrderHeaders();
            modelMapper.map(orderHeadersRequestDto, orderHeaders);
            orderHeaders = orderHeadersService.saveOrderHeaders(orderHeaders);
            resultMap.put("status", 200);
            resultMap.put("message", "success");
            resultMap.put("data", orderHeaders);
            return new ResponseEntity<>(resultMap, HttpStatus.OK);
        } catch (Exception e) {
            resultMap.put("status", 500);
            resultMap.put("message", "failed");
            resultMap.put("error", e);
            return new ResponseEntity<>(resultMap, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PatchMapping("/{reference}")
    public ResponseEntity<?> updateOrderHeaders(@PathVariable Long reference, @RequestBody OrderHeadersRequestDto orderHeadersRequestDto) {
        LinkedHashMap<String, Object> resultMap = new LinkedHashMap<>();
        ModelMapper modelMapper = new ModelMapper();
        modelMapper.getConfiguration().setSkipNullEnabled(true);
        try {
            OrderHeaders orderHeaders = orderHeadersService.getOrderHeadersByReference(reference);
            modelMapper.map(orderHeadersRequestDto, orderHeaders);
            orderHeaders = orderHeadersService.saveOrderHeaders(orderHeaders);
            resultMap.put("status", 200);
            resultMap.put("message", "success");
            resultMap.put("data", orderHeaders);
            return new ResponseEntity<>(resultMap, HttpStatus.OK);
        } catch (Exception e) {
            resultMap.put("status", 500);
            resultMap.put("message", "failed");
            resultMap.put("error", e);
            return new ResponseEntity<>(resultMap, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

}
