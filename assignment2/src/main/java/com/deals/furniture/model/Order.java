package com.deals.furniture.model;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.Objects;

@Entity
@Table(name = "orders")
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;


    @NotNull
    @Column(name  = "username")
    private String username;

    @NotNull
    @Column(name = "idProduct")
    private Integer idProduct;

    @NotNull
    @Column(name = "state")
    private String state;

    @NotNull
    @Column(name = "amountOrdered")
    private Integer amountOrdered;

    public Order(@NotNull String username, @NotNull Integer idProduct, @NotNull String state, @NotNull Integer amountOrdered) {
        this.username = username;
        this.idProduct = idProduct;
        this.state = state;
        this.amountOrdered = amountOrdered;
    }

    public Order() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public Integer getIdProduct() {
        return idProduct;
    }

    public void setIdProduct(Integer idProduct) {
        this.idProduct = idProduct;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public Integer getAmountOrdered() {
        return amountOrdered;
    }

    public void setAmountOrdered(Integer amountOrdered) {
        this.amountOrdered = amountOrdered;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Order)) return false;
        Order order = (Order) o;
        return Objects.equals(getId(), order.getId()) &&
                Objects.equals(getUsername(), order.getUsername()) &&
                Objects.equals(getIdProduct(), order.getIdProduct()) &&
                Objects.equals(getState(), order.getState()) &&
                Objects.equals(getAmountOrdered(), order.getAmountOrdered());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getUsername(), getIdProduct(), getState(), getAmountOrdered());
    }

    @Override
    public String toString() {
        return "Order{" +
                "id=" + id +
                ", username='" + username + '\'' +
                ", idProduct=" + idProduct +
                ", state='" + state + '\'' +
                ", amountOrdered=" + amountOrdered +
                '}';
    }
}
