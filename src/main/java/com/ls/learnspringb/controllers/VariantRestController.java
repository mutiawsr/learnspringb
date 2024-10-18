package com.ls.learnspringb.controllers;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;

import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ls.learnspringb.dtos.requests.VariantRequestDto;
import com.ls.learnspringb.dtos.responses.VariantResponseDto;
import com.ls.learnspringb.entities.Variant;
import com.ls.learnspringb.services.VariantService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;


@RestController
@RequestMapping("/api/variant")
public class VariantRestController {
    
    @Autowired
    VariantService variantService;

    @GetMapping("")
    public ResponseEntity<?> getAllVariants() {
        LinkedHashMap<String, Object> resultMap = new LinkedHashMap<>();
        ModelMapper modelMapper = new ModelMapper();
        try {
            List<Variant> variants = variantService.getAllVariants();
            List<VariantResponseDto> variantResponseDtos = new ArrayList<>();
            for (Variant variant : variants) {
                VariantResponseDto variantResponseDto = modelMapper.map(variant, VariantResponseDto.class);
                variantResponseDtos.add(variantResponseDto);
            }
            resultMap.put("status", 200);
            resultMap.put("message", "success");
            resultMap.put("data", variantResponseDtos);
            return new ResponseEntity<>(resultMap, HttpStatus.OK);
        } catch (Exception e) {
            resultMap.put("status", 500);
            resultMap.put("message", "failed");
            resultMap.put("error", e);
            return new ResponseEntity<>(resultMap, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    
    @PostMapping("")
    public ResponseEntity<?> postMethodName(@RequestBody VariantRequestDto variantRequestDto) {
        LinkedHashMap<String, Object> resultMap = new LinkedHashMap<>();
        ModelMapper modelMapper = new ModelMapper();
        modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);
        try {
            Variant variant = new Variant();
            modelMapper.map(variantRequestDto, variant);
            variant = variantService.saveVariant(variant);
            resultMap.put("status", 200);
            resultMap.put("message", "success");
            resultMap.put("data", variant);
            return new ResponseEntity<>(resultMap, HttpStatus.OK);
        } catch (Exception e) {
            resultMap.put("status", 500);
            resultMap.put("message", "failed");
            resultMap.put("error", e);
            return new ResponseEntity<>(resultMap, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

}
