package az.bank.msaccount.repository;

import az.bank.msaccount.model.entity.OperationEntity;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OperationRepository extends CrudRepository<OperationEntity, Integer>,
        JpaSpecificationExecutor<OperationEntity> {

    @Query(value =
            "SELECT * FROM operations " +
                    "WHERE operations.account_id=?1",
            nativeQuery = true)
    List<OperationEntity> getOperations
            (String accountId);


}
