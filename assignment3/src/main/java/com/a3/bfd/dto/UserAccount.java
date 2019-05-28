package com.a3.bfd.dto;

import java.util.Objects;

public class UserAccount {

    private Integer id;
    private String name;
    private Integer age;
    private String address;
    private String username;
    private String password;
    private String matchingPassword;

    public UserAccount(String name, Integer age, String address, String username, String password, String matchingPassword) {
        this.name = name;
        this.age = age;
        this.address = address;
        this.username = username;
        this.password = password;
        this.matchingPassword = matchingPassword;
    }

    public UserAccount(Integer id, String name, Integer age, String address, String username, String password, String matchingPassword) {
        this.id = id;
        this.name = name;
        this.age = age;
        this.address = address;
        this.username = username;
        this.password = password;
        this.matchingPassword = matchingPassword;
    }

    public UserAccount() {
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

    public String getMatchingPassword() {
        return matchingPassword;
    }

    public void setMatchingPassword(String matchingPassword) {
        this.matchingPassword = matchingPassword;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof UserAccount)) return false;
        UserAccount that = (UserAccount) o;
        return Objects.equals(getId(), that.getId()) &&
                Objects.equals(getName(), that.getName()) &&
                Objects.equals(getAge(), that.getAge()) &&
                Objects.equals(getAddress(), that.getAddress()) &&
                Objects.equals(getUsername(), that.getUsername()) &&
                Objects.equals(getPassword(), that.getPassword()) &&
                Objects.equals(getMatchingPassword(), that.getMatchingPassword());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getName(), getAge(), getAddress(), getUsername(), getPassword(), getMatchingPassword());
    }

    @Override
    public String toString() {
        return "UserAccount{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", age=" + age +
                ", address='" + address + '\'' +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", matchingPassword='" + matchingPassword + '\'' +
                '}';
    }
}
