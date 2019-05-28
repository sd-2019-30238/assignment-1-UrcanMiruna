package model;

import java.util.Objects;

public class Person {
    private String name;
    private int age;
    private String address;

    public Person( String name, int age, String address) {
        this.name = name;
        this.age = age;
        this.address = address;
    }
    public Person(){};


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    @Override
    public String toString() {
        return "Person{" +
                ", name='" + name + '\'' +
                ", age=" + age +
                ", address='" + address + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Person)) return false;
        Person person = (Person) o;
        return getAge() == person.getAge() &&
                getName().equals(person.getName()) &&
                getAddress().equals(person.getAddress());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getName(), getAge(), getAddress());
    }
}
