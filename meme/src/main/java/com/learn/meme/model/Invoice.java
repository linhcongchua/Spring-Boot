package com.learn.meme.model;

import org.hibernate.annotations.Formula;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "Invoice")
//@NamedStoredProcedureQueries({
//    @NamedStoredProcedureQuery(
//            name = "test",
//            procedureName = "sp_meme_bonusSalary",
//            parameters = {
//                    @StoredProcedureParameter(mode = ParameterMode.IN,name = "i_staff_id", type = Integer.class),
//                    @StoredProcedureParameter(mode = ParameterMode.IN,name = "i_percent_bonus", type = Double.class)
//            }
//    ),
//        @NamedStoredProcedureQuery(
//                name = "troidu",
//                procedureName = "troidu",
//                parameters = {}
//        )
//})
public class Invoice {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private Date date;
    @Transient
    private Double price;
    private Integer tax;

    @Formula("price * (100- tax)/100")
    private Double total_price;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "staff_id", referencedColumnName = "id")
    private Staff staff;
    @OneToOne
    @JoinColumn(name = "customer_id", referencedColumnName = "id")
    private Customer customer;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public Integer getTax() {
        return tax;
    }

    public void setTax(Integer tax) {
        this.tax = tax;
    }

    public Double getTotal_price() {
        return total_price;
    }

    public void setTotal_price(Double total_price) {
        this.total_price = total_price;
    }

    public Staff getStaff() {
        return staff;
    }

    public void setStaff(Staff staff) {
        this.staff = staff;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }
}
