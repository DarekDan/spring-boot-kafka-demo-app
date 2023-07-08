package com.example.demoapp.controllers;

import com.example.demoapp.dto.AccountDto;
import com.example.demoapp.entities.Account;
import com.example.demoapp.exceptions.AccountProcessingException;
import com.example.demoapp.mappers.AccountMapper;
import com.example.demoapp.services.AccountService;
import java.util.Optional;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;
import reactor.core.scheduler.Schedulers;

@RestController
@RequestMapping("api/v1/")
public class AccountController {

  private final AccountService accountService;

  public AccountController(AccountService accountService) {
    this.accountService = accountService;
  }


  @PostMapping("createAccount")
  public Mono<Account> createAccount(@RequestBody Mono<AccountDto> accountDto) {
    return accountDto
        .map(AccountMapper.INSTANCE::accountDtoToAccount)
        .map(accountService::createAccount)
        .switchIfEmpty(Mono.error(new AccountProcessingException("Create unsuccessful")));
  }

  @PostMapping("getAccount")
  public Mono<Optional<Account>> getAccount(@RequestBody Mono<AccountDto> accountDto){
    return accountDto
        .map(AccountDto::getId)
        .map(accountService::getAccount)
        .switchIfEmpty(Mono.error(new AccountProcessingException("Account does not exist")));
  }
}
