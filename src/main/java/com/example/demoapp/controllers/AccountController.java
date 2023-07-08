package com.example.demoapp.controllers;

import com.example.demoapp.dto.AccountDto;
import com.example.demoapp.exceptions.AccountProcessingException;
import com.example.demoapp.services.AccountService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("api/v1/")
public class AccountController {

  private final AccountService accountService;

  public AccountController(AccountService accountService) {
    this.accountService = accountService;
  }


  @PostMapping("createAccount")
  public Mono<Void> createAccount(@RequestBody Mono<AccountDto> accountDto) {
    return accountDto.flatMap(a -> Mono.just(accountService.createAccount(a)).then())
        .switchIfEmpty(Mono.error(new AccountProcessingException("Create unsuccessful")));
  }
}
