package com.shiraku.testbank.service.operation;

import com.shiraku.testbank.model.Wallet;

public class DepositOperation implements Operation  {

    @Override
    public void execute(Wallet wallet, double amount) {
        wallet.setBalance(wallet.getBalance() + amount);
    }

}
