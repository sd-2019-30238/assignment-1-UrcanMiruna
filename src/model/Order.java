package model;

import java.util.Objects;

public class Order {
    private int id;
    private UserAccount user;  /// same for people who orderred more products.
    private StaffAccount staff;
    private Product product;
    private String state;
    private int amountOrdered;
    private static int incremnet=1;

    public Order(UserAccount user, StaffAccount staff, Product product, String state, int amountOrdered) {
        this.setId();
        this.user = user;
        this.staff = staff;
        this.product = product;
        this.state = state;
        this.amountOrdered = amountOrdered;
    }
    public Order(int id, UserAccount user, StaffAccount staff, Product product, String state, int amountOrdered) {
        this.id=id;
        this.user = user;
        this.staff = staff;
        this.product = product;
        this.state = state;
        this.amountOrdered = amountOrdered;
    }

    public int getId() {
        return id;
    }

    public void setId() {
        this.id = incremnet++;
    }

    public UserAccount getUser() {
        return user;
    }

    public void setUser(UserAccount user) {
        this.user = user;
    }

    public StaffAccount getStaff() {
        return staff;
    }

    public void setStaff(StaffAccount staff) {
        this.staff = staff;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public int getAmountOrdered() {
        return amountOrdered;
    }

    public void setAmountOrdered(int amountOrdered) {
        this.amountOrdered = amountOrdered;
    }

    @Override
    public String toString() {
        return "Order{" +
                "id=" + id +
                ", user=" + user +
                ", staff=" + staff +
                ", product=" + product +
                ", state='" + state + '\'' +
                ", amountOrdered=" +  amountOrdered +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Order)) return false;
        Order order = (Order) o;
        return getId() == order.getId() &&
                Objects.equals(getUser(), order.getUser()) &&
                Objects.equals(getStaff(), order.getStaff()) &&
                Objects.equals(getProduct(), order.getProduct()) &&
                Objects.equals(getState(), order.getState()) &&
                Objects.equals(getAmountOrdered(), order.getAmountOrdered());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getUser(), getStaff(), getProduct(), getState(), getAmountOrdered());
    }
}
