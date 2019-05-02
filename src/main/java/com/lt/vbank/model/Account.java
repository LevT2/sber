package com.lt.vbank.model;


import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;

@Entity
@Table(name="CLIENTS")
public class Account {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    @Column(name = "ID",nullable = false, updatable = false)
    private int id;

    //@NaturalId
    @Column(name = "NAME")
    private String name;

    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "ACCOUNT_TYPE_ID")
    private AccountType accountType;


    public Account() {
    }

    public Account(String name) {
        this.name = name;
    }
//
//    public Account(String name, AccountType accountType) {
//        this.name = name;
//        this.accountType = accountType;
//    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public AccountType getAccountType() {
        return accountType;
    }

    public void setAccountType(AccountType accountType) {
        this.accountType = accountType;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }


    @Override
    public String toString() {
        return "Account{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", accountType=" + accountType +
                '}';
    }
}
