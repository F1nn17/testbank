package com.shiraku.testbank.model;

import java.util.UUID;

import org.springframework.data.annotation.Version;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.Data;

@Entity
@Data
public class Wallet {
    @Id
    private UUID walletId;
    private double balance;
    @Version
    private Long version;
}
