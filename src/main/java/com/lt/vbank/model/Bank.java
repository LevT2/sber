package com.lt.vbank.model;

import org.hibernate.annotations.NaturalId;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name="BANK")
public class Bank {

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY) //
    @Column(name="ID",nullable = false, updatable = false)
    private int id;

    @NaturalId
    @Column(name="NAME")
    private String name;

    @OneToMany(
            mappedBy = "bank",
            cascade  = CascadeType.PERSIST
//            ,fetch   =  FetchType.LAZY
    )

    private Set<AccountType> accountTypes = new HashSet<>();

    public Bank() {
    }

    public Bank(String name) {
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public AccountType addAccountType(String name) {
        AccountType accountType = new AccountType(name);
        accountTypes.add(accountType);
        accountType.setBank(this);
        return accountType;
    }

    public Set<AccountType> getAccountTypes() {
        return accountTypes;
    }

    public void setAccountTypes(Set<AccountType> accountTypes) {
        this.accountTypes = accountTypes;
    }

    public String getName() {
        return name;
    }

//    public void setName(String name) {
//        this.name = name;
//    }

    @Override
    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("\nBank: ");
        stringBuilder.append(name);
        if (accountTypes != null) {
            accountTypes.forEach(stringBuilder::append);
        }
        return stringBuilder.toString();
    }

}
