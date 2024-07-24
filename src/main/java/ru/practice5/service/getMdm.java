package ru.practice5.service;

import java.math.BigInteger;
import java.util.Random;
import org.springframework.stereotype.Service;
@Service
public class getMdm {
    public BigInteger getClientIdForMdm(String mdmCode) {
        BigInteger bInteg;
        if (mdmCode.isEmpty()) bInteg = BigInteger.valueOf( 99999L);
        else { Random rd = new Random();long idClient = rd.nextInt(); bInteg = BigInteger.valueOf( idClient); }
        return bInteg;
    }
}
