package com.ls.learnspringb.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.ls.learnspringb.entities.Variant;

@Repository
public interface VariantRepository extends JpaRepository<Variant, Long> {
    
    // get all products including soft deleted products
    @Query(value = "select * from variants", nativeQuery = true)
    List<Variant> getAllVariants();

    // get all products where is_deleted = false
    @Query(value = "select * from variants where is_deleted = false", nativeQuery = true)
    List<Variant> getAllActiveVariants();

    @Query(value = "select * from variants where id = :id and is_deleted = false", nativeQuery = true)
    Variant getActiveVariantById(@Param("id") Long id);

}
