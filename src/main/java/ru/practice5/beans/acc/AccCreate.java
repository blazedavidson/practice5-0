package ru.practice5.beans.acc;

import lombok.Getter;
import lombok.Setter;
import java.math.BigInteger;
import jakarta.validation.constraints.NotNull;

@Getter
@Setter
public class AccCreate {
    @NotNull(message = "instance неопределен")
    private BigInteger instanceId;
    private String registryTypeCode;
    private String accountType;
    private String currencyCode;
    private String branchCode;
    private String priorityCode;
    private String mdmCode;
    private String clientCode;
    private String trainRegion;
    private String counter;
    private String salesCode;
}
