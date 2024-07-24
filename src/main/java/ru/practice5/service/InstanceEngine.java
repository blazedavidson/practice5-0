package ru.practice5.service;

import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.math.BigInteger;
import java.sql.SQLException;
import java.util.Set;
import ru.practice5.beans.instance.InstanceAgreement;
import ru.practice5.beans.instance.InstanceCreate;
import ru.practice5.beans.instance.InstanceData;
import ru.practice5.beans.instance.InstanceOut;
import ru.practice5.model.AgreementStructure;
import ru.practice5.model.Tpp;
import ru.practice5.model.TppReg;
import ru.practice5.model.AccountSelect;
import ru.practice5.model.AgreementData;
import ru.practice5.model.TppInterface;

@Service
public class InstanceEngine {
    @Getter
    @Setter
    String message;
    private TppInterface tppInter;
    private AgreementData agrData;
    private AccountSelect accSelect;
    private getMdm clientMdm;

    @Autowired
    public void setAccSelect(AccountSelect accSelect) {
        this.accSelect = accSelect;
    }
    @Autowired
    public void setTppInter(TppInterface tppInter) {
        this.tppInter = tppInter;
    }
    @Autowired
    public void setAgrData(AgreementData agrData) {
        this.agrData = agrData;
    }
    @Autowired
    public void setClientMdm(getMdm clientMdm) {
        this.clientMdm = clientMdm;
    }

    public BigInteger getClientIdForMdm(String mdm) {BigInteger result = clientMdm.getClientIdForMdm(mdm); return result; }
    public int setProduct(InstanceCreate instanceCreate, InstanceOut instanceResult) {
        Tpp tp = new Tpp();
        BigInteger prodId;
        try {
            if (instanceCreate.getInstanceId() != null) { tp = tppInter.findFirstById( instanceCreate.getInstanceId() );
                                                          if (tp == null) {this.message = "Отсутствует договор" + instanceCreate.getInstanceId();return 400;}
                                                         }
            else {
                Tpp tppVar = tppInter.findFirstByContNumber(instanceCreate.getContractNumber());
                if (tppVar != null) {this.message = "Экземпляр " + instanceCreate.getContractNumber() + " уже существует с " + tppVar.getId();return 400;}
                prodId = accSelect.getProductId( instanceCreate.getProductCode());
                if (prodId == null) {this.message = "Не найден продукт по коду " + instanceCreate.getProductCode();return 400;}
                BigInteger clId = getClientIdForMdm(instanceCreate.getMdmCode());
                if ( clId.longValue() == 99999L ) {this.message = "MDM не принадлежит клиентам" + instanceCreate.getMdmCode();return 400;}

                tp.setClientId(clId);
                tp.setProductCodeId( prodId);
                tp.setProductType(instanceCreate.getProductType());
                tp.setContNumber(instanceCreate.getContractNumber());
                tp.setPriority(instanceCreate.getPriority());
                tp.setInterestRateType(instanceCreate.getRateType());
                tp.setTaxRate(instanceCreate.getTaxPercentageRate());
                tp.setDateOfConclusion(instanceCreate.getContractDate());
                tp.setPenaltyRate(instanceCreate.getInterestRatePenalty());
                tp.setThresholdAmount(instanceCreate.getThresholdAmount());
                Set<BigInteger> accountIdSet = accSelect.getAccountId(instanceCreate.getBranchCode(),
                                                                      instanceCreate.getIsoCurrencyCode(),
                                                                      instanceCreate.getMdmCode(),
                                                                      String.format("%02d",
                                                                      instanceCreate.getPriority()),
                                                                      instanceCreate.getRegisterType());
                boolean isFind = false;
                for (BigInteger accountId : accountIdSet) {isFind = true;
                                                           tp.insertRegister(new TppReg(instanceCreate.getRegisterType(),
                                                                             accountId,
                                                                             instanceCreate.getIsoCurrencyCode(),
                                                                        "OPEN",
                                                                   ""));
                                                           break;
                                                           }
                if (!isFind) {this.message = "Не найдены данные продукта " + instanceCreate.getRegisterType();return 400;}
            }
            if (instanceCreate.getInstanceAgreement() != null)
           {
              for ( InstanceAgreement agr : instanceCreate.getInstanceAgreement())
            {
                AgreementStructure agreementExists = AgreementData.findFirstByNumber( agr.getNumber() );
                if (agreementExists != null) {this.message = "Номер сделки " + agr.getNumber() + " уже существует для productid " + agreementExists.getProductId();return 400;}
                tp.insertAgreement( new AgreementStructure(
                                                            agr.getGeneralAgreementId(),
                                                            agr.getSupplementaryAgreementId(),
                                                            agr.getArrangementType(),
                                                            agr.getShedulerJobId(),
                                                            agr.getNumber(),
                                                            agr.getOpeningDate(),
                                                            agr.getClosingDate(),
                                                            agr.getCancelDate(),
                                                            agr.getValidityDuration(),
                                                            agr.getCancellationReason(),
                                                            agr.getStatus(),
                                                            agr.getInterestCalculationDate(),
                                                            agr.getInterestRate(),
                                                            agr.getCoefficient(),
                                                            agr.getCoefficientAction(),
                                                            agr.getMinimumInterestRate(),
                                                            agr.getMinimumInterestRateCoefficient(),
                                                            agr.getMinimumInterestRateCoefficientAction(),
                                                            agr.getMaximalInterestRate(),
                                                            agr.getMaximalInterestRateCoefficient(),
                                                            agr.getMaximalInterestRateCoefficientAction()));
            }
           }
              try
            {   setInstance(tp);
                InstanceData instData = new InstanceData();
                instData.setInstanceId( tp.getId().toString());
                for ( TppReg regs : tp.getRegisters()) if (regs.isCheckNew()) instData.setRegId( regs.getId().toString() );
                for ( AgreementStructure agreements : tp.getAgreements()) if (agreements.isCheckNew()) instData.setAgrId(agreements.getId().toString()); instanceResult.setData(instData);
                return 200;
            }
            catch (Exception ex) {this.message = ex.getMessage();return 500;}
        }
        catch (Exception ex) {this.message = ex.getMessage(); return 500; }
    }

    public void setInstance( Tpp tp) throws SQLException  {
        try { RegEngine productService = new RegEngine();
              productService.setProduct( tp, accSelect);
              tppInter.save(tp);
            }
        catch (Exception ex) {throw new SQLException( ex);}
    }
}
