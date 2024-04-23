package dev.beaver.beaverhomebudgetspring.service.impl;

import dev.beaver.beaverhomebudgetspring.dao.AccountDAO;
import dev.beaver.beaverhomebudgetspring.dao.UserDAO;
import dev.beaver.beaverhomebudgetspring.dto.AccountDTO;
import dev.beaver.beaverhomebudgetspring.exception.NotFoundException;
import dev.beaver.beaverhomebudgetspring.repository.AccountRepository;
import dev.beaver.beaverhomebudgetspring.repository.UserRepository;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class AccountServiceImpl {

    private final AccountRepository accountRepository;
    private final UserRepository userRepository;
    private final ModelMapper modelMapper;

    public AccountDTO createAccount(AccountDTO accountDTO) {
        UserDAO owner = userRepository.findById(accountDTO.getOwnerId()).orElseThrow(NotFoundException::new);

        AccountDAO accountDAO = modelMapper.map(accountDTO, AccountDAO.class);
        accountDAO.setOwner(owner);

        return modelMapper.map(accountRepository.save(accountDAO), AccountDTO.class);
    }

    public AccountDTO updateAccount(UUID accountID, AccountDTO accountDTO) {
        AccountDAO accountDAO = accountRepository.findById(accountID).orElseThrow(NotFoundException::new);

        if (!accountDAO.getOwner().getId().equals(accountDTO.getOwnerId())) {
            UserDAO dao = userRepository.findById(accountDTO.getOwnerId()).orElseThrow(NotFoundException::new);
            accountDAO.setOwner(dao);
        }

        accountDAO.setName(accountDTO.getName());
        accountDAO.setBalance(accountDTO.getBalance());

        AccountDAO saved = accountRepository.save(accountDAO);

        return AccountDTO.builder()
                .name(saved.getName())
                .balance(saved.getBalance())
                .ownerId(saved.getOwner().getId())
                .build();
    }

    public void deleteAccount(UUID accountId) {
        accountRepository.deleteById(accountId);
    }

    public List<AccountDTO> getAllAccounts() {
        return accountRepository.findAll().stream().map(dao -> AccountDTO.builder()
                .name(dao.getName())
                .balance(dao.getBalance())
                .ownerId(dao.getOwner().getId())
                .build()).collect(Collectors.toList());
    }

    public List<AccountDTO> getUserAccounts(String ownerName) {
        return accountRepository.findAll().stream()
                .filter(dao -> ownerName.equals(dao.getOwner().getName()))
                .map(dao -> AccountDTO.builder()
                        .name(dao.getName())
                        .balance(dao.getBalance())
                        .ownerId(dao.getOwner().getId())
                        .build()).collect(Collectors.toList());
    }

    public AccountDTO getAccountByID(UUID accountId) {
        return accountRepository.findById(accountId).map(dao -> AccountDTO.builder()
                        .name(dao.getName())
                        .balance(dao.getBalance())
                        .ownerId(dao.getOwner().getId())
                        .build())
                .orElseThrow(NotFoundException::new);
    }
}
