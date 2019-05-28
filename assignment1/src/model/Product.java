package model;

import javax.swing.table.TableColumn;
import java.util.Objects;

public class Product {
    private int id;
    private String name;
    private String description;
    private int amount;
    private Float price;
    private String type;
    //private static int increment=1;



    public Product(String name, String description, int amount, Float price, String type) {
        //this.setId();
        this.name = name;
        this.description = description;
        this.amount = amount;
        this.price = price;
        this.type = type;
    }

    public Product(int id, String name, String description, int amount, Float price, String type) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.amount = amount;
        this.price = price;
        this.type = type;
    }

    public Product(){};
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id=id;
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

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
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
        return "Product: " +
                "  name" + name +
                "  description='" + description +
                "  amount=" + amount +
                "  price "  + price +
                "  type: " + type;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Product)) return false;
        Product product = (Product) o;
        return
                getAmount() == product.getAmount() &&
                Objects.equals(getName(), product.getName()) &&
                Objects.equals(getDescription(), product.getDescription()) &&
                Objects.equals(getPrice(), product.getPrice()) &&
                Objects.equals(getType(), product.getType());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getName(), getDescription(), getAmount(), getPrice(), getType());
    }
}
