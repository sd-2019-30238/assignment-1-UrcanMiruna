package com.a3.bfd.model;


import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import java.util.Objects;

@Entity
@Table(name = "staffaccount")
public class StaffAccount {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "idstaffaccount")
    private Integer id;

    @NotNull
    @Column(name = "name")
    private String name;

    @NotNull
    @Column(name = "age")
    private Integer age;

    @NotNull
    @Column(name = "address")
    private String address;

    @NotNull
    @Pattern(regexp ="^[A-Za-z0-9+_.-]+@(.+)$")
    @Column(name = "username")
    private String username;

    @NotNull
    @Size(min=6)
    @Column(name = "password")
    private String  password;

    public StaffAccount(@NotNull String name, @NotNull Integer age, @NotNull String address, @NotNull String username, @NotNull String password) {
        this.name = name;
        this.age = age;
        this.address = address;
        this.username = username;
        this.password = password;
    }

    public StaffAccount() {
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

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
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
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof StaffAccount)) return false;
        StaffAccount that = (StaffAccount) o;
        return Objects.equals(getId(), that.getId()) &&
                Objects.equals(getName(), that.getName()) &&
                Objects.equals(getAge(), that.getAge()) &&
                Objects.equals(getAddress(), that.getAddress()) &&
                Objects.equals(getUsername(), that.getUsername()) &&
                Objects.equals(getPassword(), that.getPassword());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getName(), getAge(), getAddress(), getUsername(), getPassword());
    }

    @Override
    public String toString() {
        return "StaffAccount{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", age=" + age +
                ", address='" + address + '\'' +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                '}';
    }
}
