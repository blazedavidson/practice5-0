package ru.practice5.model;

import java.math.BigInteger;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
@Repository
public interface TppRegInterface extends CrudRepository<TppReg, BigInteger> {}
