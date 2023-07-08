package com.example.demoapp.mappers;

import com.example.demoapp.dto.AccountDto;
import com.example.demoapp.entities.Account;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface AccountMapper {

  AccountMapper INSTANCE = Mappers.getMapper(AccountMapper.class);

  AccountDto accountToAccountDto(Account account);

  Account accountDtoToAccount(AccountDto accountDto);

}
