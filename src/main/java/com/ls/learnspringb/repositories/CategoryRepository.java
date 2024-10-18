package com.ls.learnspringb.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.ls.learnspringb.entities.Category;

@Repository
public interface CategoryRepository extends JpaRepository<Category, Long> {
    
    @Query(value = "select * from categories", nativeQuery = true)
    List<Category> getAllCategories();

    @Query(value = "select * from categories where is_deleted = false", nativeQuery = true)
    List<Category> getAllActiveCategories();

    @Query(value = "select * from categories where id = :id and is_deleted = false", nativeQuery = true)
    Category getActiveCategoryById(@Param("id") Long id);

}
