package com.ls.learnspringb.entities;

import java.math.BigDecimal;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Entity
@EqualsAndHashCode(callSuper = true)
@Data
@Table(name = "order_headers")
public class OrderHeaders extends BaseEntity {
    
    public OrderHeaders() {

    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "reference", length = 15, unique = true, updatable = false)
    private Long reference;

    @Column(name = "amount", precision = 18, scale = 4)
    private BigDecimal amount;

    @Column(name = "is_deleted")
    private Boolean isDeleted;

}
