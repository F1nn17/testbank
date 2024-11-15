package com.shiraku.testbank.controller;

import java.util.HashMap;
import java.util.Map;
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
import com.shiraku.testbank.model.Wallet;
import com.shiraku.testbank.service.WalletService;



@RestController
@RequestMapping("/api/v1")
public class WalletController {

    @Autowired
    private WalletService walletService;

    @PostMapping("/wallet")
    public ResponseEntity<Wallet> processOperation(@RequestBody WalletRequest request) {
        walletService.processOperation(request.getWalletId(), request.getOperationType(), request.getBalance());
        return ResponseEntity.ok().build();
    }

    @GetMapping("/wallets/{walletId}")
    public ResponseEntity<Map<String, Double> > getBalnce(@PathVariable UUID walletId) {
        double balance = walletService.getBalance(walletId);
        Map<String, Double> response = new HashMap<>();
        response.put("balance", balance);
        return ResponseEntity.ok(response);
    }
    
    @PostMapping("/wallet/new")
    public ResponseEntity<Wallet> createWallet(@RequestBody WalletRequest walletRequest) {
        Wallet wallet = walletService.createWallet(walletRequest);
        return new ResponseEntity<>(wallet, HttpStatus.CREATED);
    }

}
