package com.shiraku.testbank.DTO;

import java.util.UUID;

import lombok.Data;

@Data
public class WalletRequest {
    private UUID walletId;
    private OperationType operationType;
    private double balance;
}
