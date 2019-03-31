package az.bank.msaccount.service;

import az.bank.msaccount.exceptions.NoAccountOperationsException;
import az.bank.msaccount.exceptions.UnauthorizedAccessException;
import az.bank.msaccount.mapper.OperationMapper;
import az.bank.msaccount.model.OperationRequest;
import az.bank.msaccount.model.dto.OperationDto;
import az.bank.msaccount.model.entity.AccountEntity;
import az.bank.msaccount.model.entity.OperationEntity;
import az.bank.msaccount.repository.AccountRepository;
import az.bank.msaccount.repository.OperationRepository;
import az.bank.msaccount.security.UserAuthentication;
import az.bank.msaccount.specification.OperationSpecification;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;

@Service
public class OperationService {
    private final AccountRepository accountRepository;
    private final OperationRepository operationRepository;
    private final OperationMapper operationMapper;

    public OperationService(AccountRepository accountRepository,
                            OperationRepository operationRepository,
                            OperationMapper operationMapper) {
        this.accountRepository = accountRepository;
        this.operationRepository = operationRepository;
        this.operationMapper = operationMapper;
    }

    public List<OperationDto> getOperations(String accountId, String customerId) {
        AccountEntity accountEntity = accountRepository.findByAccountId(accountId)
                .orElseThrow(() -> new UnauthorizedAccessException("Account doesn't exist"));

        if (!accountEntity.getCustomerId().equals(customerId)) {
            throw new UnauthorizedAccessException("You can access information only for your own accounts");
        }

        List<OperationEntity> operationEntities = operationRepository.getOperations(accountId);

//        if (operationEntities.isEmpty()) {
//            throw new NoAccountOperationsException("No operations are found for this account");
//        }
        return operationMapper.mapEntityListToDtoList(operationEntities);
    }

    public List<OperationDto> getOperations(OperationRequest request, String accountId, String customerId) {

        AccountEntity accountEntity = accountRepository.findByAccountId(accountId)
                .orElseThrow(() -> new UnauthorizedAccessException("Account doesn't exist"));

        if (!accountEntity.getCustomerId().equals(customerId)) {
            throw new UnauthorizedAccessException("You can access information only for your own accounts");
        }
        List<OperationEntity> operationEntities = operationRepository.findAll(
                new OperationSpecification(request.getFilter(), accountId));
        System.out.println(request.getFilter().toString());
        System.out.println(Arrays.toString(operationEntities.toArray()));
        return operationMapper.mapEntityListToDtoList(operationEntities);
    }
}
