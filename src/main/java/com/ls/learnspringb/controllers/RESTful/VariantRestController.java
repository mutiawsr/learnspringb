package com.ls.learnspringb.controllers.restful;

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

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PathVariable;

@RestController
@RequestMapping("/api/variant")
@CrossOrigin("http://localhost:9002")
public class VariantRestController {
    
    @Autowired
    VariantService variantService;

    @GetMapping("/all")
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

    @GetMapping("/active")
    public ResponseEntity<?> getAllActiveVariants() {
        LinkedHashMap<String, Object> resultMap = new LinkedHashMap<>();
        ModelMapper modelMapper = new ModelMapper();
        try {
            List<Variant> variants = variantService.getAllActiveVariants();
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

    @GetMapping("/{id}")
    public ResponseEntity<?> getVariantById(@PathVariable Long id) {
        LinkedHashMap<String, Object> resultMap = new LinkedHashMap<>();
        ModelMapper modelMapper = new ModelMapper();
        try {
            Variant variant = variantService.getVariantById(id);
            VariantResponseDto variantResponseDto = modelMapper.map(variant, VariantResponseDto.class);
            resultMap.put("status", 200);
            resultMap.put("message", "success");
            resultMap.put("data", variantResponseDto);
            return new ResponseEntity<>(resultMap, HttpStatus.OK);
        } catch (Exception e) {
            resultMap.put("status", 500);
            resultMap.put("message", "failed");
            resultMap.put("error", e);
            return new ResponseEntity<>(resultMap, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    
    @PostMapping("")
    public ResponseEntity<?> saveVariant(@RequestBody VariantRequestDto variantRequestDto) {
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

    // Update is_deleted = false data only
    @PatchMapping("/{id}")
    public ResponseEntity<?> updateVariantById(@PathVariable Long id, @RequestBody VariantRequestDto variantRequestDto) {
        LinkedHashMap<String, Object> resultMap = new LinkedHashMap<>();
        ModelMapper modelMapper = new ModelMapper();
        modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);
        modelMapper.getConfiguration().setSkipNullEnabled(true);
        try {
            Variant variant = variantService.getVariantById(id);
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

    @DeleteMapping("/{id}")
    public ResponseEntity<?> softDeleteVariantById(@PathVariable Long id) {
        LinkedHashMap<String, Object> resultMap = new LinkedHashMap<>();
        try {
            if (variantService.getActiveVariantById(id) == null) {
                throw new Exception();
            }
            variantService.softDeleteVariantById(id);
            resultMap.put("status", 200);
            resultMap.put("message", "success");
            return new ResponseEntity<>(resultMap, HttpStatus.OK);
        } catch (Exception e) {
            resultMap.put("status", 500);
            resultMap.put("message", "failed");
            resultMap.put("error", e);
            return new ResponseEntity<>(resultMap, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

}
