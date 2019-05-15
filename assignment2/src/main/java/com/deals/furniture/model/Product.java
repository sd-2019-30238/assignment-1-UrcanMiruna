package com.deals.furniture.model;


import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "product")
public class Product {



    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "idproduct")
    private Integer id;

    @NotNull
    @Column(name = "name")
    private String name;

    @NotNull
    @Column(name = "description")
    private String description;

    @NotNull
    @Column(name = "amount")
    private Integer amount;

    private Float price;

    @NotNull
    @Column(name = "type")
    private String type;

    public Product(@NotNull String name, @NotNull String description, @NotNull Integer amount, Float price, @NotNull String type) {
        this.name = name;
        this.description = description;
        this.amount = amount;
        this.price = price;
        this.type = type;
    }

    public Product() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Integer getAmount() {
        return amount;
    }

    public void setAmount(Integer amount) {
        this.amount = amount;
    }

    public Float getPrice() {
        return price;
    }

    public void setPrice(Float price) {
        this.price = price;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    @Override
    public String toString() {
        return "Product{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", amount=" + amount +
                ", price=" + price +
                ", type='" + type + '\'' +
                '}';
    }
}
