package az.bank.msaccount.specification;

import az.bank.msaccount.model.AccountRequest;
import az.bank.msaccount.model.entity.AccountEntity;
import org.springframework.data.jpa.domain.Specification;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.ArrayList;
import java.util.List;

public class AccountSpecification implements Specification<AccountEntity> {

    private AccountRequest.AccountFilter filter;
    private String customerId;

    public AccountSpecification(AccountRequest.AccountFilter filter, String customerId) {
        this.filter = filter;
        this.customerId= customerId;
    }

    @Override
    public Predicate toPredicate(Root<AccountEntity> root,
                                 CriteriaQuery<?> criteriaQuery,
                                 CriteriaBuilder criteriaBuilder) {
        List<Predicate> predicates = new ArrayList<>();

        predicates.add(
                criteriaBuilder.equal
                        (root.get(Field.CUSTOMERID),
                                customerId)
        );

        if (filter.getCurrency() != "") {
            predicates.add(
                    criteriaBuilder.equal
                            (root.get(Field.CURRENCY),
                                    filter.getCurrency())
            );
        }

        predicates.add(criteriaBuilder.between(root.get(Field.AMOUNT),
                filter.getMinAmount(), filter.getMaxAmount()));
        return criteriaBuilder.and(predicates.toArray(new Predicate[0]));
    }

    private abstract class Field {

        private static final String CUSTOMERID = "customerId";
        private static final String CURRENCY = "currency";
        private static final String AMOUNT = "amount";

        private Field() {
        }
    }
}
