package com.example.demoapp.services;

import com.example.demoapp.dto.AccountDto;
import com.example.demoapp.entities.Account;
import com.example.demoapp.exceptions.AccountProcessingException;
import com.example.demoapp.models.Result;
import com.example.demoapp.repositories.AccountRepository;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

import java.util.Optional;

@Service
public class AccountService {

    private final AccountRepository accountRepository;

    public AccountService(AccountRepository accountRepository) {
        this.accountRepository = accountRepository;
    }

    public Optional<Account> getAccount(String id){
        return accountRepository.findById(id);
    }

    public Mono<Result<String>> createAccount(AccountDto accountDto) throws AccountProcessingException {
        return Mono.just(Result.error(new AccountProcessingException("Function not yet implemented")));
    }
}
