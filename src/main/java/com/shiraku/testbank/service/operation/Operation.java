package com.shiraku.testbank.service.operation;

import com.shiraku.testbank.model.Wallet;

public interface Operation {
    void execute(Wallet wallet, double balance);
}
