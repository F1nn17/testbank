package com.shiraku.testbank.repository;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import com.shiraku.testbank.model.Transaction;

public interface TransactionRepository extends JpaRepository<Transaction, UUID>  {
}
