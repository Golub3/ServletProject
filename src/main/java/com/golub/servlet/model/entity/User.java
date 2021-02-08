package com.golub.servlet.model.entity;


import java.math.BigDecimal;
import java.util.List;
import java.util.Objects;

public class User {

    private long id;
    private String firstName;
    private String lastName;
    private String email;
    private String password;
    private BigDecimal balance;
    private ROLE role;

//    default constructor
    public User() {
    }

    public User(long id, String firstName, String lastName, String email, String password, BigDecimal balance, ROLE role) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.password = password;
        this.balance = balance;
        this.role = role;
    }

    public User(long id) {
        this.id = id;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public BigDecimal getBalance() {
        return balance;
    }

    public void setBalance(BigDecimal balance) {
        this.balance = balance;
    }

    public ROLE getRole() {
        return role;
    }

    public void setRole(ROLE role) {
        this.role = role;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }


    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return id == user.id &&
                Objects.equals(firstName, user.firstName) &&
                Objects.equals(lastName, user.lastName) &&
                Objects.equals(email, user.email) &&
                Objects.equals(password, user.password) &&
                Objects.equals(balance, user.balance) &&
                role == user.role;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, firstName, lastName, email, password, balance, role);
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", email='" + email + '\'' +
                ", password='" + password + '\'' +
                ", balance='" + balance + '\'' +
                ", role=" + role +
                '}';
    }


    public enum ROLE {
        ADMIN, USER, UNKNOWN;

        public int getRoleID(){
            return ordinal() + 1;
        }

        public static ROLE getRoleById(int id){
            int index = id - 1;
            return values()[index];
        }

        public static ROLE getRoleByString(String str){
            return ROLE.valueOf(str);
        }

    }
}