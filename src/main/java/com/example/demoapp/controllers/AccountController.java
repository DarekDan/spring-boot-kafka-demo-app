package com.example.demoapp.controllers;

import com.example.demoapp.dto.AccountDto;
import com.example.demoapp.services.AccountService;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Mono;

import static org.springframework.web.reactive.function.server.ServerResponse.ok;
import static org.springframework.web.reactive.function.server.ServerResponse.status;

@RestController
@RequestMapping("api/v1")
public class AccountController {

    private final AccountService accountService;

    public AccountController(AccountService accountService) {
        this.accountService = accountService;
    }

    @PostMapping("/createAccount")
    public Mono<ServerResponse> createAccount(@RequestBody Mono<AccountDto> accountDto) {
        return accountDto
                .flatMap(accountService::createAccount)
                .flatMap(r -> r.isSuccess() ? ok().build() :
                        status(HttpStatus.INTERNAL_SERVER_ERROR)
                                .contentType(MediaType.APPLICATION_JSON)
                                .bodyValue(r));
    }
}
