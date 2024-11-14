package com.shiraku.testbank.model;

import java.util.UUID;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.Data;

@Entity
@Data
public class Wallet {
    @Id
    private UUID walletId;
    private double balance;

    public Wallet(){}

    public Wallet(UUID walletId, double balance){
        this.walletId = walletId;
        this.balance = balance;
    }
}
