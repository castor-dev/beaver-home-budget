package dev.beaver.beaverhomebudgetspring.service.impl;

import dev.beaver.beaverhomebudgetspring.dao.AccountDAO;
import dev.beaver.beaverhomebudgetspring.dao.TransactionDAO;
import dev.beaver.beaverhomebudgetspring.dto.TransactionDTO;
import dev.beaver.beaverhomebudgetspring.exception.NotFoundException;
import dev.beaver.beaverhomebudgetspring.repository.AccountRepository;
import dev.beaver.beaverhomebudgetspring.repository.TransactionRepository;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class TransactionServiceImpl {

    private final AccountRepository accountRepository;
    private final TransactionRepository transactionRepository;
    private final ModelMapper modelMapper;

    public TransactionDTO createTransaction(TransactionDTO transactionDTO){
        AccountDAO account = accountRepository.findById(transactionDTO.getAccountId()).orElseThrow(NotFoundException::new);

        transactionDTO.setValue(transactionDTO.getValue().abs());
        TransactionDAO dao = modelMapper.map(transactionDTO, TransactionDAO.class);
        dao.setAccount(account);

        return modelMapper.map(transactionRepository.save(dao), TransactionDTO.class);
    }


}
