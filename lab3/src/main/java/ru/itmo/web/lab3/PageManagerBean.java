package ru.itmo.web.lab3;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDateTime;

import jakarta.enterprise.context.RequestScoped;
import jakarta.inject.Named;
import jakarta.inject.Inject;

@Named("pageManagerBean")
@RequestScoped
public class PageManagerBean implements Serializable {
    private String alert;

    @Inject
    private ResultsManagerBean resultsManagerBean;

    private boolean xn20;
    private boolean xn15;
    private boolean xn10;
    private boolean xn5;
    private boolean x0;
    private boolean x5;
    private boolean x10;

    private BigDecimal y;
    private BigDecimal r = new BigDecimal("1");

    public PageManagerBean() {
    }

    public BigDecimal getX() {
        if (xn20) return new BigDecimal("-2.0");
        if (xn15) return new BigDecimal("-1.5");
        if (xn10) return new BigDecimal("-1.0");
        if (xn5) return new BigDecimal("-0.5");
        if (x0) return new BigDecimal("0");
        if (x5) return new BigDecimal("0.5");
        if (x10) return new BigDecimal("1.0");
        return null;
    }@RequestScoped


    public boolean isXn20() {
        return xn20;
    }

    public void setXn20(boolean xn20) {
        this.xn20 = xn20;
    }

    public boolean isXn15() {
        return xn15;
    }

    public void setXn15(boolean xn15) {
        this.xn15 = xn15;
    }

    public boolean isXn10() {
        return xn10;
    }

    public void setXn10(boolean xn10) {
        this.xn10 = xn10;
    }

    public boolean isXn5() {
        return xn5;
    }

    public void setXn5(boolean xn5) {
        this.xn5 = xn5;
    }

    public boolean isX0() {
        return x0;
    }

    public void setX0(boolean xn0) {
        this.x0 = xn0;
    }

    public boolean isX5() {
        return x5;
    }

    public void setX5(boolean x5) {
        this.x5 = x5;
    }

    public boolean isX10() {
        return x10;
    }

    public void setX10(boolean x10) {
        this.x10 = x10;
    }

    public BigDecimal getY() {
        return y;
    }

    public void setY(BigDecimal y) {
        this.y = y;
    }

    public BigDecimal getR() {
        return r;
    }

    public void setR(BigDecimal r) {
        this.r = r;
    }

    public String getAlert() {
        return alert;
    }

    public void setAlert(String alert) {
        this.alert = alert;
    }

    public void addPoint(){
        BigDecimal x = getX();
        if (x == null || y == null || r == null) {
            alert = "Please select valid X, Y, and R values";
            return;
        }
        resultsManagerBean.addPoint(new PointBean(x, y, r, AreaChecker.checkArea(x, y, r), LocalDateTime.now()));
        alert = null;
        alert = null;
    }
}
