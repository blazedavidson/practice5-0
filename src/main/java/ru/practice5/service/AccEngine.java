package ru.practice5.service;

import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.practice5.beans.acc.AccCreate;
import ru.practice5.beans.acc.AccContent;
import ru.practice5.beans.acc.AccOut;
import ru.practice5.beans.acc.AccStatus;
import ru.practice5.model.AccountSelect;
import ru.practice5.model.TppInterface;
import ru.practice5.model.Tpp;
import ru.practice5.model.TppReg;
import java.math.BigInteger;
import java.sql.SQLException;
import java.util.Set;

@Service
public class AccEngine {
    private TppInterface tppIf;
    private AccountSelect accSelect;
    @Autowired
    public void setAccSelect(AccountSelect accSelect) {
        this.accSelect = accSelect;
    }
    @Autowired
    public void setTppIf(TppInterface tppIf) {
        this.tppIf = tppIf;
    }
    @Getter
    @Setter
    String message;
    public void setRegistry( Tpp tppProduct) throws SQLException
    {
        try
        {   RegEngine regEngine = new RegEngine();
            regEngine.setProduct(tppProduct, accSelect);
            tppIf.save(tppProduct);
        }
        catch (Exception ex) {throw new SQLException( ex); }
    }
    public int setAccount(AccCreate accountCreate, AccOut accountResult) {
        Tpp tp;
        boolean bFlag;
        tp = tppIf.findFirstById( accountCreate.getInstanceId() );
        if (tp == null) { this.message = "нет договора с id" + accountCreate.getInstanceId(); return 400; }
        for ( TppReg tppReg : tp.getRegisters())
        {
            if ( tppReg.getRegisterValue().equals( accountCreate.getRegistryTypeCode())) {
                this.message = "Параметр registryTypeCode~" + accountCreate.getRegistryTypeCode() + " уже существует~" + accountCreate.getInstanceId(); return 400;
            }
        }
        Set<BigInteger> accIds = accSelect.getAccountId(accountCreate.getBranchCode(), accountCreate.getCurrencyCode(),
                accountCreate.getMdmCode(), accountCreate.getPriorityCode(), accountCreate.getRegistryTypeCode());
        bFlag = false;
        for (BigInteger accId : accIds)
        {
            bFlag = true; tp.insertRegister(new TppReg(accountCreate.getRegistryTypeCode(), accId, accountCreate.getCurrencyCode(), AccStatus.OPENED.name(), ""));
            break;
        }
        if (!bFlag) { this.message = "Ошибка счетов :" + accountCreate.getRegistryTypeCode();
            return 400;
        }
        try
        {
            setRegistry(tp);
            AccContent accCont = new AccContent();
            for ( TppReg re : tp.getRegisters())
                if (re.isCheckNew()) accCont.setAccId( re.getId().toString() ); accountResult.setData( accCont);
                return 200;
        }
        catch (Exception ex) {this.message = ex.getMessage(); return 500; }
    }
}