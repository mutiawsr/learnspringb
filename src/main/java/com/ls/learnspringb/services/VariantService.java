package com.ls.learnspringb.services;

import java.util.List;

import com.ls.learnspringb.entities.Variant;

public interface VariantService {

    List<Variant> getAllVariants();

    List<Variant> getAllActiveVariants();

    // get all products including soft deleted products
    Variant getVariantById(Long id);

    // get all products where is_deleted = false
    Variant getActiveVariantById(Long id);

    Variant saveVariant(Variant variant);

    void softDeleteVariantById(Long id);

}
