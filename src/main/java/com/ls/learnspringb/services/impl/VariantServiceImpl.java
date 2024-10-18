package com.ls.learnspringb.services.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ls.learnspringb.entities.Variant;
import com.ls.learnspringb.repositories.VariantRepository;
import com.ls.learnspringb.services.VariantService;

@Service
public class VariantServiceImpl implements VariantService {
    
    @Autowired
    VariantRepository variantRepository;

    @Override
    public List<Variant> getAllVariants() {
        return variantRepository.getAllVariants();
    }

    @Override
    public List<Variant> getAllActiveVariants() {
        return variantRepository.getAllActiveVariants();
    }

    @Override
    public Variant getVariantById(Long id) {
        return variantRepository.findById(id).orElse(null);
    }

    @Override
    public Variant getActiveVariantById(Long id) {
        return variantRepository.getActiveVariantById(id);
    }

    @Override
    public Variant saveVariant(Variant variant) {
        return variantRepository.save(variant);
    }

}
