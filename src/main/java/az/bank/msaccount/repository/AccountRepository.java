package az.bank.msaccount.repository;

import az.bank.msaccount.model.entity.AccountEntity;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface AccountRepository extends CrudRepository<AccountEntity, Integer>,
        JpaSpecificationExecutor<AccountEntity> {

    Optional<AccountEntity> findByAccountId(String accountId);

    List<AccountEntity> findAllByCustomerId(String customerId);

    List<AccountEntity> findAllByCustomerIdAndCurrency(String customerId, String currency);
}
