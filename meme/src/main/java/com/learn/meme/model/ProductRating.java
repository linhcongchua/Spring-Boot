package com.learn.meme.model;

import com.learn.meme.model.compositeKey.ProductRatingKey;

import javax.persistence.*;

@Table
@Entity
public class ProductRating {
    @EmbeddedId
    ProductRatingKey id;

    @ManyToOne
    @MapsId("productId")
    @JoinColumn(name = "product_id")
    Product product;

    @ManyToOne
    @MapsId("customerId")
    @JoinColumn(name = "customer_id")
    Customer customer;

    int rating;
}
