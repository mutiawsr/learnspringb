package com.ls.learnspringb.entities;

import java.math.BigDecimal;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Entity
@EqualsAndHashCode(callSuper = true)
@Data
@Table(name = "order_details")
public class OrderDetails extends BaseEntity {

    public OrderDetails() {

    }

    public OrderDetails(BigDecimal quantity, BigDecimal price, Boolean isDeleted, Long headerId, Long variantId) {
        this.quantity = quantity;
        this.price = price;
        this.isDeleted = isDeleted;
        this.headerId = headerId;
        this.variantId = variantId;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "quantity", precision = 18, scale = 4)
    private BigDecimal quantity;

    @Column(name = "price", precision = 18, scale = 4)
    private BigDecimal price;

    @Column(name = "is_deleted")
    private Boolean isDeleted;

    @OneToOne
    @JoinColumn(name = "header_id", insertable = false, updatable = false)
    private OrderHeaders orderHeaders;

    @Column(name = "header_id")
    private Long headerId;

    @ManyToOne
    @JoinColumn(name = "variant_id", insertable = false, updatable = false)
    private Variant variant;

    @Column(name = "variant_id")
    private Long variantId;

}
