package com.p3xi.lab2;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public class Result {
    private int x;
    private BigDecimal y;
    private float r;
    private boolean c;
    private long processingTimeMS;
    private LocalDateTime requestTime;

    public Result() {
    }

    public Result(int x, BigDecimal y, float r, boolean c, long processingTimeMS, LocalDateTime requestTime) {
        this.x = x;
        this.y = y;
        this.r = r;
        this.c = c;
        this.processingTimeMS = processingTimeMS;
        this.requestTime = requestTime;
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public BigDecimal getY() {
        return y;
    }

    public void setY(BigDecimal y) {
        this.y = y;
    }

    public float getR() {
        return r;
    }

    public void setR(float r) {
        this.r = r;
    }

    public boolean isC() {
        return c;
    }

    public void setC(boolean c) {
        this.c = c;
    }

    public long getProcessingTimeMS() {
        return processingTimeMS;
    }

    public void setProcessingTimeMS(long processingTimeMS) {
        this.processingTimeMS = processingTimeMS;
    }

    public LocalDateTime getRequestTime() {
        return requestTime;
    }

    public void setRequestTime(LocalDateTime requestTime) {
        this.requestTime = requestTime;
    }
}