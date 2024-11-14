package com.shiraku.testbank.service;

import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.shiraku.testbank.DTO.OperationType;
import com.shiraku.testbank.exception.InsufficientFundsException;
import com.shiraku.testbank.exception.WalletNotFoundException;
import com.shiraku.testbank.model.Wallet;
import com.shiraku.testbank.repository.WalletRepository;

@Service
public class WalletService {

    @Autowired
    private WalletRepository walletRepository;

    @Transactional
    public Wallet processOperation(UUID walletId, OperationType operationType, double amount){
        Wallet wallet = getWallet(walletId);
        switch(operationType){
            case DEPOSIT:
                wallet.setBalance(wallet.getBalance() + amount);
                break;
            case WITHDRAW:
                if(wallet.getBalance() < amount){
                    throw new InsufficientFundsException("Insufficient funds");
                }
                wallet.setBalance(wallet.getBalance() + amount);
                break;
        }

        return walletRepository.save(wallet);
    }

    public Wallet getWallet(UUID walletId){
        return walletRepository.findByWalletId(walletId)
        .orElseThrow( ()-> new WalletNotFoundException("Wallet not found") );   
    }

    public Wallet createWallet(UUID walletId, double initBalance){
        Wallet wallet = new Wallet(walletId,initBalance);
        return walletRepository.save(wallet);
    }
}
