package com.learn.meme.model;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "customer")
public class Customer extends User{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToMany(cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    @JoinTable(
            name="favorite_table",
            joinColumns = @JoinColumn(name = "customer_id"),
            inverseJoinColumns = @JoinColumn(name = "product_id")
    )
    private Set<Product> favoriteProducts =new HashSet<>();

    @OneToMany(mappedBy = "customer")
    Set<ProductRating> ratings;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Set<Product> getFavoriteProducts() {
        return favoriteProducts;
    }

    public void setFavoriteProducts(Set<Product> favoriteProducts) {
        this.favoriteProducts = favoriteProducts;
    }
}
