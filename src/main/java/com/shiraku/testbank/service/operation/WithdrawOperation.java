package com.shiraku.testbank.service.operation;

import com.shiraku.testbank.exception.InsufficientFundsException;
import com.shiraku.testbank.model.Wallet;

public class WithdrawOperation implements Operation {
 @Override
    public void execute(Wallet wallet, double amount) {
        if (wallet.getBalance() < amount) {
            throw new InsufficientFundsException("Insufficient funds");
        }
        wallet.setBalance(wallet.getBalance() - amount);
    }
}
