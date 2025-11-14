package com.p3xi.lab2;

import java.math.BigDecimal;

public class AreaChecker {
    public static boolean checkArea(BigDecimal x, BigDecimal y, BigDecimal r) {
        if (x.compareTo(new BigDecimal(0)) >= 0) {
            if (y.compareTo(new BigDecimal(0)) >= 0)
                return (x.pow(2).add(y.pow(2)).compareTo(r.pow(2)) <= 0);
            else
                return (x.compareTo(r.divide(new BigDecimal(2))) <= 0 && y.compareTo(r)<=0);
        } else {
            if (y.compareTo(new BigDecimal(0)) >= 0){
                return false;
            }
            else
                return y.compareTo(x.negate().add(r.negate().divide(new BigDecimal(2)))) >= 0;
        }
    }

    public static boolean checkParams(BigDecimal x, BigDecimal y, BigDecimal r) {
        if (x == null || y == null || r == null) {
            return false;
        }

        if (x.scale() > 0) {
            return false;
        }
        if (x.compareTo(new BigDecimal(-3)) < 0 || x.compareTo(new BigDecimal(5)) > 0) {
            return false;
        }

        if (y.compareTo(new BigDecimal(-3)) < 0 || y.compareTo(new BigDecimal(3)) > 0) {
            return false;
        }

        boolean rIsValid = false;
        BigDecimal[] validRValues = {
            new BigDecimal("1"),
            new BigDecimal("1.5"),
            new BigDecimal("2"),
            new BigDecimal("2.5"),
            new BigDecimal("3")
        };
        for (BigDecimal validR : validRValues) {
            if (r.compareTo(validR) == 0) {
                rIsValid = true;
                break;
            }
        }
        return rIsValid;
    }
}