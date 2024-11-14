package com.shiraku.testbank.controller;

import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.shiraku.testbank.DTO.WalletRequest;
import com.shiraku.testbank.exception.InsufficientFundsException;
import com.shiraku.testbank.exception.WalletNotFoundException;
import com.shiraku.testbank.model.Wallet;
import com.shiraku.testbank.service.WalletService;



@RestController
@RequestMapping("/api/v1")
public class WalletController {

    @Autowired
    private WalletService walletService;

    @PostMapping("/wallet")
    public ResponseEntity<Wallet> processOperation(@RequestBody WalletRequest request) {
        try {
            Wallet wallet =  walletService.processOperation(request.getWalletId(), request.getOperationType(), request.getAmount());
            return ResponseEntity.ok(wallet);
        } catch (WalletNotFoundException | InsufficientFundsException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
        }
    }

    @GetMapping("/wallets/{walletId}")
    public ResponseEntity<Wallet> getBalnce(@PathVariable UUID walletId) {
        try {
            Wallet wallet = walletService.getWallet(walletId);
            return ResponseEntity.ok(wallet);
        } catch (InsufficientFundsException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
    }
    
    @PostMapping("/wallet/new/{walletId}")
    public ResponseEntity<Wallet> createWallet(@PathVariable UUID walletId, @RequestBody CreateWalletRequest createWalletRequest) {
        Wallet wallet = walletService.createWallet(walletId, createWalletRequest.getInitialBalance());
        return ResponseEntity.status(HttpStatus.CREATED).body(wallet);
    }

    public static class CreateWalletRequest {
        private double initialBalance;

        public double getInitialBalance() {
            return initialBalance;
        }

        public void setInitialBalance(double initialBalance) {
            this.initialBalance = initialBalance;
        }
    }

    
}
