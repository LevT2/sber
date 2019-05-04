package com.lt.vbank.model;


import com.fasterxml.jackson.annotation.JsonIgnore;
import org.hibernate.annotations.CreationTimestamp;
import org.joda.time.DateTime;

import javax.persistence.*;
import java.util.Date;

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


    @Column(name = "DATE_ACC")
    @CreationTimestamp
    @Temporal(TemporalType.TIMESTAMP)
    private Date dateAcc;

    public Account() {
    }

    public Account(String name) {
        this.name = name;
    }

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

    public Date getDateAcc() {
        return dateAcc;
    }

    public void setDateAcc(Date dateAcc) {
        this.dateAcc = dateAcc;
    }

    @Override
    public String toString() {
        return "Account{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", type=" + accountType.getName() +
                '}';
    }
}
