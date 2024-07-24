package ru.practice5.model;

import java.math.BigInteger;
import java.util.Date;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.NoArgsConstructor;
@Entity
@Getter
@Setter
@NoArgsConstructor
@Table(name = "agreement")
public class AgreementStructure {
    @Id
    @GeneratedValue( strategy = GenerationType.IDENTITY)
    private BigInteger id;
    @ManyToOne( fetch = FetchType.LAZY)
    @JoinColumn( name = "product_id", nullable = false)
    private Tpp productId;
    @Column(name = "general_agreement_id")
    private String generalAgreementId;
    @Column(name = "supplementary_agreement_id")
    private String supplementaryAgreementId;
    @Column(name = "arrangement_type")
    private String arrangementType;
    @Column(name = "sheduler_job_id")
    private BigInteger shedulerJobId;
    @Column(name = "number")
    private String number;
    @Column(name = "opening_date")
    private Date openingDate;
    @Column(name = "closing_date")
    private Date closingDate;
    @Column(name = "cancel_date")
    private Date cancelDate;
    @Column(name = "validity_duration")
    private BigInteger validityDuration;
    @Column(name = "cancellation_reason")
    private String cancellationReason;
    private String status;
    @Column(name = "interest_calculation_date")
    private Date interestCalculationDate;
    @Column(name = "interest_rate")
    private Double interestRate;
    private Double coefficient;
    @Column(name = "coefficient_action")
    private String coefficientAction;
    @Column(name = "minimum_interest_rate")
    private Double minimumInterestRate;
    @Column(name = "minimum_interest_rate_coefficient")
    private Double minimumInterestRateCoefficient;
    @Column(name = "minimum_interest_rate_coefficient_action")
    private String minimumInterestRateCoefficientAction;
    @Column(name = "maximal_interest_rate")
    private Double maximalInterestRate;
    @Column(name = "maximal_interest_rate_coefficient")
    private Double maximalInterestRateCoefficient;
    @Column(name = "maximal_interest_rate_coefficient_action")
    private String maximalInterestRateCoefficientAction;
    transient private boolean checkNew;
    public AgreementStructure(String generalAgreementId, String supplementaryAgreementId, String arrangementType, BigInteger shedulerJobId,
                              String number, Date openingDate, Date closingDate, Date cancelDate, BigInteger validityDuration, String cancellationReason, String status, Date interestCalculationDate,
                              Double interestRate, Double coefficient, String coefficientAction,Double minimumInterestRate, Double minimumInterestRateCoefficient, String minimumInterestRateCoefficientAction,
                              Double maximalInterestRate, Double maximalInterestRateCoefficient, String maximalInterestRateCoefficientAction) {
        this.checkNew = true;
        this.generalAgreementId = generalAgreementId;
        this.supplementaryAgreementId = supplementaryAgreementId;
        this.arrangementType = arrangementType;
        this.shedulerJobId = shedulerJobId;
        this.number = number;
        this.interestCalculationDate = interestCalculationDate;
        this.interestRate = interestRate;
        this.coefficient = coefficient;
        this.coefficientAction = coefficientAction;
        this.maximalInterestRate = maximalInterestRate;
        this.maximalInterestRateCoefficient = maximalInterestRateCoefficient;
        this.maximalInterestRateCoefficientAction = maximalInterestRateCoefficientAction;
        this.minimumInterestRate = minimumInterestRate;
        this.minimumInterestRateCoefficient = minimumInterestRateCoefficient;
        this.minimumInterestRateCoefficientAction = minimumInterestRateCoefficientAction;
        this.openingDate = openingDate;
        this.closingDate = closingDate;
        this.cancelDate = cancelDate;
        this.validityDuration = validityDuration;
        this.cancellationReason = cancellationReason;
        this.status = status;

    }
}
