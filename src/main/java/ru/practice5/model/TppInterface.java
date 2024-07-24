package ru.practice5.model;

import java.math.BigInteger;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
@Repository
public interface TppInterface extends CrudRepository<Tpp, BigInteger> { Tpp findFirstById(BigInteger id); Tpp findFirstByContNumber(String number); }