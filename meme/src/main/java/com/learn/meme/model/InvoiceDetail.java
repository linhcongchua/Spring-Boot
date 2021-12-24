package com.learn.meme.model;

import org.hibernate.annotations.Formula;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "invoice_detail")
@AssociationOverrides({
        @AssociationOverride(name = "pk.product", joinColumns = @JoinColumn(name = "product_id")),
        @AssociationOverride(name = "pk.invoice", joinColumns = @JoinColumn(name = "invoice_id"))
})
public class InvoiceDetail {
    @EmbeddedId
    private InvoiceDetailPK pk=new InvoiceDetailPK();
    private Double price;
    private Integer quantity;
    private Integer discount;
    @Formula("price * quantity * (100-discount)/100")
    private Double amount;

    public InvoiceDetailPK getPk() {
        return pk;
    }

    public void setPk(InvoiceDetailPK pk) {
        this.pk = pk;
    }

    @Transient
    public Product getProduct(){
        return getPk().getProduct();
    }

    public void setProduct(Product product){
        getPk().setProduct(product);
    }

    @Transient
    public Invoice getInvoice(){
        return getPk().getInvoice();
    }

    public void setInvoice(Invoice invoice){
        getPk().setInvoice(invoice);
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public Integer getDiscount() {
        return discount;
    }

    public void setDiscount(Integer discount) {
        this.discount = discount;
    }

    public Double getAmount() {
        return amount;
    }

    public void setAmount(Double amount) {
        this.amount = amount;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        InvoiceDetail that = (InvoiceDetail) o;
        return Objects.equals(pk, that.pk) && Objects.equals(price, that.price) && Objects.equals(quantity, that.quantity) && Objects.equals(discount, that.discount) && Objects.equals(amount, that.amount);
    }

    @Override
    public int hashCode() {
        return Objects.hash(pk, price, quantity, discount, amount);
    }
}
