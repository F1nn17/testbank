package com.shiraku.testbank.service.operation;

import java.util.HashMap;
import java.util.Map;

import com.shiraku.testbank.DTO.OperationType;

public class OperationFactory {
 private static final Map<OperationType, Operation> operations = new HashMap<>();

    static {
        operations.put(OperationType.DEPOSIT, new DepositOperation());
        operations.put(OperationType.WITHDRAW, new WithdrawOperation());
    }

    public static Operation getOperation(OperationType operationType) {
        return operations.get(operationType);
    }
}
