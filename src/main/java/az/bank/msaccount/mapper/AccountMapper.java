package az.bank.msaccount.mapper;

import az.bank.msaccount.model.dto.AccountDto;
import az.bank.msaccount.model.entity.AccountEntity;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class AccountMapper {
    public AccountDto mapEntityToDto(AccountEntity accountEntity) {
        return AccountDto.builder()
                .customerId(accountEntity.getCustomerId())
                .amount(accountEntity.getAmount())
                .currency(accountEntity.getCurrency())
                .accountId(accountEntity.getAccountId())
                .build();
    }

    public List<AccountDto> mapEntityListToDtoList(List<AccountEntity> accountEntities) {
        return accountEntities.stream()
                .map(this::mapEntityToDto)
                .collect(Collectors.toList());
    }
}
