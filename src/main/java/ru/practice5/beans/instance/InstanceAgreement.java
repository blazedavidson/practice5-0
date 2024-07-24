package ru.practice5.beans.instance;

import lombok.Getter;
import lombok.Setter;
import java.math.BigInteger;
import java.util.Date;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonSetter;
@Getter
@Setter
public class InstanceAgreement {
    @JsonProperty("GeneralAgreementId")
    @JsonSetter("GeneralAgreementId")
    String generalAgreementId;
    @JsonProperty("SupplementaryAgreementId")
    @JsonSetter("SupplementaryAgreementId")
    String supplementaryAgreementId;
    String arrangementType;
    BigInteger shedulerJobId;
    @JsonProperty("Number")
    @JsonSetter("Number")
    String number;
    Date openingDate;
    Date closingDate;
    @JsonProperty("CancelDate")
    @JsonSetter("CancelDate")
    Date cancelDate;
    BigInteger validityDuration;
    String cancellationReason;
    @JsonProperty("Status")
    @JsonSetter("Status")
    String status;
    Date interestCalculationDate;
    Double interestRate;
    Double coefficient;
    String coefficientAction;
    Double minimumInterestRate;
    Double minimumInterestRateCoefficient;
    String minimumInterestRateCoefficientAction;
    Double maximalInterestRate;
    Double maximalInterestRateCoefficient;
    String maximalInterestRateCoefficientAction;
}
