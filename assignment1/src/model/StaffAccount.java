package model;

import java.util.Objects;

public class StaffAccount extends Person{
    private int id;
    private String username;
    private String password;
    private static int increment=1;

    public StaffAccount( String name, int age, String address, String username, String password) {
        super( name, age, address);
       // this.setId();
        this.username = username;
        this.password = password;
    }
    public StaffAccount( int id, String name, int age, String address, String username, String password) {
        super( name, age, address);
        this.id=id;
        this.username = username;
        this.password = password;
    }


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id =id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public String toString() {

        return "Staff: " +
                "id: " + id +
                "\n Username: " + username + "\nName: " + this.getName() +"\nAge:  " + this.getAge()+"\nAddress: "+this.getAddress();

    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof StaffAccount)) return false;
        if (!super.equals(o)) return false;
        StaffAccount that = (StaffAccount) o;
        return
                Objects.equals(getName(), that.getName()) &&
                Objects.equals(getUsername(), that.getUsername()) &&
                Objects.equals(getPassword(), that.getPassword());
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), getId(), getUsername(), getPassword());
    }

}
