package com.example.demoapp.services;

import com.example.demoapp.dto.AccountDto;
import com.example.demoapp.entities.Account;
import com.example.demoapp.exceptions.AccountProcessingException;
import com.example.demoapp.mappers.AccountMapper;
import com.example.demoapp.repositories.AccountRepository;
import java.util.Objects;
import java.util.Optional;
import org.springframework.stereotype.Service;

@Service
public class AccountService {

  private final AccountRepository accountRepository;

  public AccountService(AccountRepository accountRepository) {
    this.accountRepository = accountRepository;
  }

  public Optional<Account> getAccount(String id) {
    return accountRepository.findById(id);
  }

  public Account createAccount(AccountDto accountDto) throws AccountProcessingException {
    if (!Objects.isNull(accountDto.getId())) {
      throw new AccountProcessingException("New account request must not have an ID");
    }
    return accountRepository.save(AccountMapper.INSTANCE.accountDtoToAccount(accountDto));
  }
}
