package com.example.demoapp.services;

import com.example.demoapp.dto.AccountDto;
import com.example.demoapp.entities.Account;
import com.example.demoapp.exceptions.AccountProcessingException;
import com.example.demoapp.mappers.AccountMapper;
import com.example.demoapp.repositories.AccountRepository;
import jakarta.transaction.Transactional;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import org.springframework.stereotype.Service;

@Service
public class AccountService {

  private final AccountRepository accountRepository;

  public AccountService(AccountRepository accountRepository) {
    this.accountRepository = accountRepository;
  }

  public Optional<Account> getAccount(Long id) {
    return accountRepository.findById(id);
  }

  public List<Account> getAllAccounts() {
    return accountRepository.findAll();
  }

  @Transactional
  public Account createAccount(Account account) throws AccountProcessingException {
    if (!Objects.isNull(account.getId())) {
      throw new AccountProcessingException("New account request must not have an ID");
    }
    return accountRepository.save(account);
  }

  @Transactional
  public Account updateAccount(Account account){
    return accountRepository.save(account);
  }
}
