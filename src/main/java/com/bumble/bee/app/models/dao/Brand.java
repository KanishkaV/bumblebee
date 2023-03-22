package com.bumble.bee.app.models.dao;

import jakarta.persistence.*;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.*;

@Getter
@Setter
@ToString(exclude = {"products"})
@EqualsAndHashCode(of = {"id"})
@jakarta.persistence.Entity
public class Brand implements Entity<Long> {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(nullable = false, length = 64)
    private String name;

    @Column(length = 512)
    private String description;

    @OneToMany(mappedBy = "brand", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private Collection<Product> products;

    @Override
    public String getEntityName() {
        return "Brand";
    }
}
