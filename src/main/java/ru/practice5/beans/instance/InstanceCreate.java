package ru.practice5.beans.instance;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Date;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonSetter;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;
@Getter
@Setter
public class InstanceCreate {
    private BigInteger instanceId;
    @NotEmpty(message = "пустой productType")
    private String productType;
    @NotEmpty(message = "пустой productCode")
    private String productCode;
    @NotEmpty(message = "пустой registerType")
    private String registerType;
    @NotEmpty(message = "пустой mdmCode")
    private String mdmCode;
    @NotEmpty(message = "пустой contractNumber")
    private String contractNumber;
    @NotNull(message = "пустой contractDate")
    private Date contractDate;
    @NotNull(message = "пустой priority")
    private Integer priority;
    private Double interestRatePenalty;
    private Double minimalBalance;
    private Double thresholdAmount;
    private String accountingDetails;
    private String rateType;
    private Double taxPercentageRate;
    private Double technicalOverdraftLimitAmount;
    private Long contractId;
    @JsonProperty("BranchCode")
    @JsonSetter("BranchCode")
    private String branchCode;
    @JsonProperty("IsoCurrencyCode")
    @JsonSetter("IsoCurrencyCode")
    private String isoCurrencyCode;
    private String urgencyCode;
    @JsonProperty("ReferenceCode")
    @JsonSetter("ReferenceCode")
    private Long referenceCode;
    private InstanceAddonsList additionalPropertiesVip;
    private ArrayList<InstanceAgreement> instanceAgreement;
}
