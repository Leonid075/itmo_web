package ru.itmo.web.lab3;

import java.math.BigDecimal;

public class AreaChecker {
    public static boolean checkArea(BigDecimal x, BigDecimal y, BigDecimal r) {

        if (x.compareTo(BigDecimal.ZERO) >= 0) {
            if (y.compareTo(BigDecimal.ZERO) >= 0) {
                return x.compareTo(r) <= 0 && y.compareTo(r) <= 0;
            } else {
                return x.pow(2).add(y.pow(2)).compareTo(r.pow(2)) <= 0;
            }
        } else {
            if (y.compareTo(BigDecimal.ZERO) >= 0) {
                return false;
            } else {
                return y.compareTo(x.negate().add(r.negate())) >= 0;
            }
        }
    }
}
