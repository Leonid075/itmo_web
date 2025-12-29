package com.p3xi.lab4.controller;

import com.p3xi.lab4.model.Point;
import com.p3xi.lab4.repository.PointRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api/point")
public class PointController {

    private final PointRepository pointRepository;

    public PointController(PointRepository pointRepository) {
        this.pointRepository = pointRepository;
    }

    @PostMapping
    public ResponseEntity<Point> addPoint(@RequestBody Point request) {
        if (request == null || request.getX() == null || request.getY() == null || request.getR() == null) {
            return ResponseEntity.badRequest().build();
        }

        Integer x = request.getX();
        BigDecimal y = request.getY();
        Integer r = request.getR();

        if (x < -5 || x > 3) {
            return ResponseEntity.badRequest().build();
        }

        if (y.compareTo(new BigDecimal("-3")) < 0 || y.compareTo(new BigDecimal("5")) > 0) {
            return ResponseEntity.badRequest().build();
        }

        if (r < 0 || r > 3) {
            return ResponseEntity.badRequest().build();
        }

        Point point = new Point();
        point.setX(x);
        point.setY(y);
        point.setR(r);

        boolean hit = Point.checkArea(BigDecimal.valueOf(x), y, BigDecimal.valueOf(r));
        point.setHit(hit);
        point.setTimestamp(LocalDateTime.now());

        pointRepository.save(point);

        return ResponseEntity.ok(point);
    }

    @GetMapping
    public List<Point> getAll() {
        List<Point> all = new ArrayList<>();
        pointRepository.findAll().forEach(all::add);
        return all;
    }
}