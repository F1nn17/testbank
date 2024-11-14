package com.shiraku.testbank.DTO;

import java.util.UUID;

import lombok.Data;

@Data
public class WalletRequest {
    private UUID walletId;
    private OperationType operationType;
    private double amount;

    public WalletRequest(){}

    public WalletRequest(UUID walletId, OperationType operationType, double amount){
        this.walletId = walletId;
        this.operationType = operationType;
        this.amount = amount;
    }
}
