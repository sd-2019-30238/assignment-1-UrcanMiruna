package model;

import java.util.Objects;

public class UserAccount extends Person{
    private int id;
    private String username;
    private String password;
    private static int increment=1;

    public UserAccount( String name, int age, String address, String username, String password) {
        super( name, age, address);
        //this.setId();
        this.username = username;
        this.password = password;
    }
    public UserAccount(int id, String name, int age, String address, String username, String password) {
        super( name, age, address);
        this.id=id;
        this.username = username;
        this.password = password;
    }


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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
        return "User: \n" +
                "id: " + id +
                "\nUsername: " + username + "\nName: " + this.getName() +"\nAge:  " + this.getAge()+"\nAddress: "+this.getAddress();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof UserAccount)) return false;
        if (!super.equals(o)) return false;
        UserAccount that = (UserAccount) o;
        return
                Objects.equals(getUsername(), that.getUsername()) &&
                Objects.equals(getPassword(), that.getPassword());
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), getId(), getUsername(), getPassword());
    }
}
