package com.ls.learnspringb.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.ls.learnspringb.entities.Variant;

@Repository
public interface VariantRepository extends JpaRepository<Variant, Long> {
    
    @Query(value = "select * from variants where is_deleted = false", nativeQuery = true)
    List<Variant> getAllVariants();

}
