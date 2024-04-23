package dev.beaver.beaverhomebudgetspring.controller;

import dev.beaver.beaverhomebudgetspring.dto.AccountDTO;
import dev.beaver.beaverhomebudgetspring.service.impl.AccountServiceImpl;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/account")
@AllArgsConstructor
public class AccountController {

    private final AccountServiceImpl accountService;

    @PostMapping
    public AccountDTO createAccount(AccountDTO accountDTO) {
        return accountService.createAccount(accountDTO);
    }

    @PutMapping("/{accountId}")
    public AccountDTO updateAccount(AccountDTO accountDTO, UUID accountId) {
        return accountService.updateAccount(accountId, accountDTO);
    }

    @DeleteMapping("/{accountId}")
    public void deleteAccount(UUID accountId) {
        accountService.deleteAccount(accountId);
    }

    @GetMapping("/all_accounts")
    public List<AccountDTO> listAllAccount() {
        return accountService.getAllAccounts();
    }

    @GetMapping("/user_accounts/{userName}")
    public List<AccountDTO> listUserAccounts(String userName){
        return accountService.getUserAccounts(userName);
    }


}
