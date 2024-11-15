package com.shiraku.testbank.service;

import java.sql.Timestamp;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.shiraku.testbank.DTO.OperationType;
import com.shiraku.testbank.DTO.WalletRequest;
import com.shiraku.testbank.exception.WalletNotFoundException;
import com.shiraku.testbank.model.Transaction;
import com.shiraku.testbank.model.Wallet;
import com.shiraku.testbank.repository.TransactionRepository;
import com.shiraku.testbank.repository.WalletRepository;
import com.shiraku.testbank.service.operation.Operation;
import com.shiraku.testbank.service.operation.OperationFactory;

@Service
public class WalletService {

    @Autowired
    private WalletRepository walletRepository;
    @Autowired
    private TransactionRepository transactionRepository;

    @Transactional
    public void processOperation(UUID walletId, OperationType operationType, double amount){
        Wallet wallet = getWallet(walletId);
        Operation operation = OperationFactory.getOperation(operationType);
        operation.execute(wallet, amount);
        walletRepository.save(wallet);
        
        //Log transaction
        Transaction transaction = new Transaction();
        transaction.setTransactionId(UUID.randomUUID());
        transaction.setWalletId(walletId);
        transaction.setOperationType(operationType);
        transaction.setBalance(amount);
        transaction.setTimestamp(new Timestamp(System.currentTimeMillis()));
        transactionRepository.save(transaction);
    }

    public Wallet getWallet(UUID walletId){
        return walletRepository.findByWalletId(walletId)
        .orElseThrow( ()-> new WalletNotFoundException("Wallet not found") );   
    }

    public double getBalance(UUID walletId) {
        Wallet wallet = walletRepository.findByWalletId(walletId)
            .orElseThrow(() -> new WalletNotFoundException("Wallet not found"));
        return wallet.getBalance();
    }

    public Wallet createWallet(WalletRequest walletRequest){
        Wallet wallet = new Wallet();
        wallet.setWalletId(walletRequest.getWalletId());
        wallet.setBalance(walletRequest.getBalance()); 
        return walletRepository.save(wallet);
    }
}
