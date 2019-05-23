package com.deals.furniture.model;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import com.deals.furniture.model.Observer;

@Entity
@Table(name = "deal")
public class  Order {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name="iddeal")
    private Integer id;


    @NotNull
    @Column(name  = "username")
    private String username;

    @NotNull
    @Column(name = "product")
    private Integer idProduct;

    @NotNull
    @Column(name = "state")
    private String state;

    @NotNull
    @Column(name = "amount")
    private Integer amountOrdered;

    @NotNull
    @Column
    private String password;

    public Order(@NotNull String username, @NotNull Integer idProduct, @NotNull Integer amountOrdered, @NotNull String password) {
        this.username = username;
        this.idProduct = idProduct;
        this.amountOrdered = amountOrdered;
        this.password = password;
        this.state="delivering";
    }

    public Order() {
        this.state="delivering";

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

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
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
                Objects.equals(getAmountOrdered(), order.getAmountOrdered()) &&
                Objects.equals(getPassword(), order.getPassword());
    }


    @Override
    public int hashCode() {
        return Objects.hash(getId(), getUsername(), getIdProduct(), getState(), getAmountOrdered(), getPassword());
    }
//    private List<Observer> observers=new ArrayList<>();
//
//    public void attach(Observer observer){
//        observers.add(observer);
//    }
//
//    public void notifyAllObservers() {
//        for(Observer observer: observers){
//            observer.update();
//        }
//    }
//

}
