package com.p3xi.lab4.model;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import jakarta.persistence.*;


@Entity
@Table(name = "points")
public class Point {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Integer x;

    @Column(precision = 19, scale = 8)
    private BigDecimal y;

    private Integer r;

    private Boolean hit;

    private LocalDateTime timestamp;

    private Integer userId;

    public Point() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getX() {
        return x;
    }

    public void setX(Integer x) {
        this.x = x;
    }

    public BigDecimal getY() {
        return y;
    }

    public void setY(BigDecimal y) {
        this.y = y;
    }

    public Integer getR() {
        return r;
    }

    public void setR(Integer r) {
        this.r = r;
    }

    public Boolean getHit() {
        return hit;
    }

    public void setHit(Boolean hit) {
        this.hit = hit;
    }

    public LocalDateTime getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(LocalDateTime timestamp) {
        this.timestamp = timestamp;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public static boolean checkArea(BigDecimal x, BigDecimal y, BigDecimal r) {
        if (x.compareTo(BigDecimal.ZERO) >= 0) {
            if (y.compareTo(BigDecimal.ZERO) >= 0) {
                return x.compareTo(r) < 0 && y.compareTo(r.divide(new BigDecimal(2))) < 0;
            } else {
                return x.pow(2).add(y.pow(2)).compareTo(r.divide(new BigDecimal(2)).pow(2)) < 0;
            }
        } else {
            if (y.compareTo(BigDecimal.ZERO) >= 0) {
                return y.compareTo(x.add(r.divide(new BigDecimal(2)))) < 0;
            } else {
                return false;
            }
        }
    }
}
