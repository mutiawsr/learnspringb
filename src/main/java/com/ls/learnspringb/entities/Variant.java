package com.ls.learnspringb.entities;

import java.math.BigDecimal;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Entity
@EqualsAndHashCode(callSuper = true)
@Data
@Table(name = "variants")
public class Variant extends BaseEntity {
    
    public Variant() {

    }

    public Variant(String name, String slug, String description, BigDecimal price, BigDecimal stock, Long productId, Boolean isDeleted) {
        this.name = name;
        this.slug = slug;
        this.description = description;
        this.price = price;
        this.stock = stock;
        this.productId = productId;
        this.isDeleted = isDeleted;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "name", length = 50)
    private String name;

    @Column(name = "slug", length = 20)
    private String slug;

    @Column(name = "description", length = 500, nullable = true)
    private String description;

    @Column(name = "price", precision = 18, scale = 2)
    private BigDecimal price;

    @Column(name = "stock", precision = 18, scale = 2)
    private BigDecimal stock;

    @Column(name = "is_deleted")
    private Boolean isDeleted;

    // Many Variant to One Product
    @ManyToOne
    @JoinColumn(name = "product_id", insertable = false, updatable = false)
    private Product product;

    @Column(name = "product_id")
    private Long productId;

}
