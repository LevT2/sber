package com.lt.vbank.dto;

public class AccountTypeInfo {
    private String name;
    private int clientsAmount;

    public AccountTypeInfo(String name, int clientsAmount) {
        this.name = name;
        this.clientsAmount = clientsAmount;
    }

    public String getName() {
        return name;
    }

    public int getClientsAmount() {
        return clientsAmount;
    }

    @Override
    public String toString() {
        return "AccountTypeInfo{" +
                "name='" + name + '\'' +
                ", clientAccountsAmount=" + clientsAmount +
                '}';
    }
}
