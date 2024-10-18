package com.ls.learnspringb.services;

import java.util.List;

import com.ls.learnspringb.entities.Variant;

public interface VariantService {

    List<Variant> getAllVariants();
    Variant saveVariant(Variant variant);

}
