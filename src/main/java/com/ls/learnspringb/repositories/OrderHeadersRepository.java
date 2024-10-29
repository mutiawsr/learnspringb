package com.ls.learnspringb.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.ls.learnspringb.entities.OrderHeaders;

@Repository
public interface OrderHeadersRepository extends JpaRepository<OrderHeaders, Long> {
    
    @Query(value = "select * from order_headers", nativeQuery = true)
    List<OrderHeaders> getAllOrderHeaders();

    @Query(value = "select id from order_headers where reference = :reference", nativeQuery = true)
    Long getHeaderIdByReference(Long reference);

    @Query(value = "select * from order_headers where reference = :reference", nativeQuery = true)
    OrderHeaders getOrderHeadersByReference(@Param("reference") Long reference);

}
