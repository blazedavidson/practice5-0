package ru.practice5.model;

import java.math.BigInteger;
import lombok.Getter;
import lombok.Setter;
import lombok.NoArgsConstructor;
import jakarta.persistence.*;
@Entity
@Getter
@Setter
@NoArgsConstructor
@Table(name = "tpp_product_register")
public class TppReg {
    @Id
    @GeneratedValue( strategy = GenerationType.IDENTITY)
    private BigInteger id;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn( name = "product_id", nullable = true, updatable = false)
    private Tpp productId;
    @Column(name = "type", updatable = false)
    private String registerValue;
    @Column(name = "account", updatable = false)
    private BigInteger account;
    @Column(name = "currency_code", updatable = false)
    private String currencyCode;
    @Column(name = "state", updatable = false)
    private String state;
    @Column(name = "account_number")
    private String accountNumber;
    transient private boolean checkNew;
    public TppReg(String registerValue, BigInteger account, String currencyCode, String state, String accountNumber)
    {
        this.account = account;
        this.accountNumber = accountNumber;
        this.currencyCode = currencyCode;
        this.state = state;
        this.checkNew = true;
        this.registerValue = registerValue;
    }
}
