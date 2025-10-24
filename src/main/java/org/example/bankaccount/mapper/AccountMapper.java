package org.example.bankaccount.mapper;

import org.example.bankaccount.dao.AccountDTO;
import org.example.bankaccount.module.Account;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;



@Mapper(componentModel = "spring")
public interface AccountMapper {

    AccountMapper INSTANCE = Mappers.getMapper(AccountMapper.class);
    @Mapping(target = "password", ignore = true)
    Account toEntity(AccountDTO dto);
    AccountDTO toDto(Account account);
}
