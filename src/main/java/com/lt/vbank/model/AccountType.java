package com.lt.vbank.model;


import com.fasterxml.jackson.annotation.JsonIgnore;
import org.hibernate.annotations.NaturalId;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "ACCOUNT_TYPE")
public class AccountType {

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    @Column(name = "ID",nullable = false, updatable = false)
    private int id;

    @NaturalId
    @Column(name="NAME")
    private String name;

    public AccountType() {
    }


    @JsonIgnore
    @ManyToOne
//    @JoinColumn(name="BANK_ID")
    private Bank bank;

    @OneToMany(
            mappedBy = "accountType",
            cascade  =  CascadeType.ALL
//            ,fetch   =  FetchType.LAZY
    )
//    @JoinColumn(name = "ACCOUNT_TYPE_ID")
    private List<Account> accounts;

    public AccountType(String name) {
        this.name = name;
    }

    public Account addAccount(String name) {
        if (accounts == null) {
            accounts = new ArrayList<>();
        }
        Account account = new Account(name);
        accounts.add(account);
        account.setAccountType(this);
        return account;
    }

    public Bank getBank() {
        return bank;
    }

    void setBank(Bank bank) {
        this.bank = bank;
    }

    public List<Account> getAccounts() {
        return accounts;
    }

    public void setAccounts(List<Account> accounts) {
        this.accounts = accounts;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }


    @Override
    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("\n  AccountType: ");
        stringBuilder.append(name);
        stringBuilder.append("  id="+id);

//        if (accounts != null) {
//            accounts.forEach(stringBuilder::append);
//        }
        return stringBuilder.toString();
    }
}

