package ru.practice5.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.practice5.model.AccountSelect;
import ru.practice5.model.Account;
import ru.practice5.model.Tpp;
import ru.practice5.model.TppReg;

@Service
public class RegEngine {
    @Transactional
    public void setProduct(Tpp tppProduct, AccountSelect accountRepo) {
        for (TppReg register : tppProduct.getRegisters())
            if (register.isCheckNew() && (register.getAccount() != null)) {
                Account account = accountRepo.findFirstById(register.getAccount());
                register.setAccountNumber(account.getAccountNumber());
                account.setBussy(true);
                accountRepo.save(account);
            }
    }
}
