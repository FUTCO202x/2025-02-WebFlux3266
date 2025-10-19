package com.parcial.dos.parcialdos.account.entity;

import jakarta.persistence.*;
import java.math.BigDecimal;

@Entity
@Table(name = "accounts", uniqueConstraints = @UniqueConstraint(columnNames = "account_number"))
public class Account {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "account_number", nullable = false, unique = true)
    private String accountNumber;
    @Column(name = "owner_name", nullable = false)
    private String ownerName;
    @Column(name = "balance", nullable = false, scale = 2, precision = 19)
    private BigDecimal balance;
    @Column(name = "active", nullable = false)
    private Boolean active = true;

    public Account() {}

    public Account(String accountNumber, String ownerName, BigDecimal balance, Boolean active) {
        this.accountNumber = accountNumber;
        this.ownerName = ownerName;
        this.balance = balance;
        this.active = active;
    }


    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getAccountNumber() { return accountNumber; }
    public void setAccountNumber(String accountNumber) { this.accountNumber = accountNumber; }

    public String getOwnerName() { return ownerName; }
    public void setOwnerName(String ownerName) { this.ownerName = ownerName; }

    public java.math.BigDecimal getBalance() { return balance; }
    public void setBalance(java.math.BigDecimal balance) { this.balance = balance; }

    public Boolean getActive() { return active; }
    public void setActive(Boolean active) { this.active = active; }
}
