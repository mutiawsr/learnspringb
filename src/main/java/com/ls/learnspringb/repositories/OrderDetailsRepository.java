package com.ls.learnspringb.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.ls.learnspringb.entities.OrderDetails;

@Repository
public interface OrderDetailsRepository extends JpaRepository<OrderDetails, Long> {
    
    @Query(value = "select * from order_details", nativeQuery = true)
    List<OrderDetails> getAllOrderDetails();

}
