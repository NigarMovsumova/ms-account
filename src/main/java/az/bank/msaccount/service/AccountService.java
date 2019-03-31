package az.bank.msaccount.service;

import az.bank.msaccount.mapper.AccountMapper;
import az.bank.msaccount.model.AccountRequest;
import az.bank.msaccount.model.dto.AccountDto;
import az.bank.msaccount.model.entity.AccountEntity;
import az.bank.msaccount.repository.AccountRepository;
import az.bank.msaccount.security.UserAuthentication;
import az.bank.msaccount.specification.AccountSpecification;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AccountService {
    private final AccountRepository accountRepository;
    private final AccountMapper accountMapper;

    public AccountService(AccountRepository accountRepository,
                          AccountMapper accountMapper) {
        this.accountRepository = accountRepository;
        this.accountMapper = accountMapper;
    }

    public List<AccountDto> getAccounts(String customerId) {
        return accountMapper.mapEntityListToDtoList(
                accountRepository.findAllByCustomerId(customerId)
        );
    }

    public List<AccountDto> filterAccounts(String customerId, String currency) {
        return accountMapper.mapEntityListToDtoList(
                accountRepository.findAllByCustomerIdAndCurrency(customerId, currency)
        );
    }

    //DONE
    public List<AccountDto> getAccounts(AccountRequest request, String customerId) {

        List<AccountEntity> accountEntities = accountRepository.findAll(
                new AccountSpecification(request.getFilter(), customerId)
        );
        //accountEntities.removeIf(accountEntity -> (!accountEntity.getCustomerId().equals(customerId)));
        return accountMapper.mapEntityListToDtoList(accountEntities);
    }
}
