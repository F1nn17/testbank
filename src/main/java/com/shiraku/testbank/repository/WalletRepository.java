package com.shiraku.testbank.repository;

import java.util.Optional;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import com.shiraku.testbank.model.Wallet;

public interface  WalletRepository extends JpaRepository<Wallet, UUID> {
    Optional<Wallet> findByWalletId(UUID walletId);
}
