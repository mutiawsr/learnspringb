package com.ls.learnspringb.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ls.learnspringb.entities.Variant;

@Repository
public interface VariantRepository extends JpaRepository<Variant, Long> {
    
}
