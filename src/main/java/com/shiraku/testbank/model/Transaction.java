package com.shiraku.testbank.model;

import java.sql.Timestamp;
import java.util.UUID;

import com.shiraku.testbank.DTO.OperationType;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.Data;

@Entity
@Data
public class Transaction {
    @Id
    private UUID transactionId;
    private UUID walletId;
    private OperationType operationType;
    private double balance;
    private Timestamp timestamp;
}
