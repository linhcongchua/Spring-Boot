package com.learn.meme.model.compositeKey;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.io.Serializable;
import java.util.Objects;

@Embeddable
public class ProductRatingKey implements Serializable {
    @Column(name = "product_id")
    Integer productId;

    @Column(name = "customer_id")
    Integer customer_id;

    public Integer getProductId() {
        return productId;
    }

    public void setProductId(Integer productId) {
        this.productId = productId;
    }

    public Integer getCustomer_id() {
        return customer_id;
    }

    public void setCustomer_id(Integer customer_id) {
        this.customer_id = customer_id;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ProductRatingKey that = (ProductRatingKey) o;
        return Objects.equals(productId, that.productId) && Objects.equals(customer_id, that.customer_id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(productId, customer_id);
    }
}
