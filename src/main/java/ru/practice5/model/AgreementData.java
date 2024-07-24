package ru.practice5.model;

import java.math.BigInteger;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
@Repository
public interface AgreementData extends CrudRepository<AgreementStructure, Long>
{
    AgreementStructure findFirstById(BigInteger id);

    static AgreementStructure findFirstByNumber(String number) {
        return null;
    }
}
