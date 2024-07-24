package ru.practice5.model;

import java.math.BigInteger;
import java.util.Set;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import org.springframework.data.jpa.repository.Query;
@Repository
public interface AccountSelect extends CrudRepository<Account, Long> {
    Account findFirstById(BigInteger id);
    @Query(value = "select t.id id_acc from tpp_ref_product_register_type t1, account_pool p, account t " +
                   "  where t1.value = p.registry_type_code and t.account_pool_id = p.id and t.bussy = false and t1.account_type = 'Клиентский'" +
            "      and p.branch_code = :branchCode and p.currency_code = :currCode and p.mdm_code = :mdmCode and p.priority_code = :priorityCode" +
            "      and t1.value = :regType and (t1.register_type_start_date is null or t1.register_type_start_date <= current_date)" +
            "      and (t1.register_type_end_date   is null or t1.register_type_end_date   >= current_date)" +
            "  order by p.priority_code nulls last, t.account_number nulls last", nativeQuery = true)
    Set<BigInteger> getAccountId(String branchCode, String currCode, String mdmCode, String priorityCode, String regType);
    @Query(value = "SELECT internal_id FROM tpp_ref_product_class t1 WHERE t1.value = :productCode", nativeQuery = true)
    BigInteger getProductId(String productCode);
}