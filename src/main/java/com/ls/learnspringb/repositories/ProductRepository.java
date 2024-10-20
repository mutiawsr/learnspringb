package com.ls.learnspringb.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.ls.learnspringb.entities.Product;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {

    @Query(value = "select * from products", nativeQuery = true)
    List<Product> getAllProducts();

    @Query(value = "select * from products where is_deleted = false", nativeQuery = true)
    List<Product> getAllActiveProducts();

    @Query(value = "select * from products where category_id = :category_id", nativeQuery = true)
    List<Product> getProductsByCategoryId(@Param("category_id") Long id);

    @Query(value = "select * from products where id = :id and is_deleted = false", nativeQuery = true)
    Product getActiveProductById(@Param("id") Long id);

}
