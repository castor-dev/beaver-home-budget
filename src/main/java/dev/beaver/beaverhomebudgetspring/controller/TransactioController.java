package dev.beaver.beaverhomebudgetspring.controller;

import dev.beaver.beaverhomebudgetspring.dto.TransactionDTO;
import dev.beaver.beaverhomebudgetspring.service.impl.TransactionServiceImpl;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

@RestController
@RequestMapping("/transaction")
@AllArgsConstructor
public class TransactioController {

    private final TransactionServiceImpl transactionService;

    @PostMapping
    public TransactionDTO createTransaction(TransactionDTO transaction){
        return transactionService.createTransaction(transaction);
    }

}
