package org.fasttrackit.onlineshop.domain;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

public class Review {

    @Id
    @GeneratedValue
    private Long id;

    @NotNull
    private String content;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "product_id")
    private Product product;


}
