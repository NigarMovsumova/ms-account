package az.bank.msaccount.mapper;

import az.bank.msaccount.model.dto.OperationDto;
import az.bank.msaccount.model.entity.OperationEntity;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class OperationMapper {

    public OperationDto mapEntityToDto(OperationEntity operationEntity) {
        return OperationDto.builder()
                .accountId(operationEntity.getAccountId())
                .date(operationEntity.getDate())
                .increased(operationEntity.getIncreased())
                .amount(operationEntity.getAmount())
                .currency(operationEntity.getCurrency())
                .category(operationEntity.getCategory())
                .description(operationEntity.getDescription())
                .build();
    }

    public List<OperationDto> mapEntityListToDtoList(List<OperationEntity> operationEntities) {
        return operationEntities.stream()
                .map(this::mapEntityToDto)
                .collect(Collectors.toList());
    }
}

