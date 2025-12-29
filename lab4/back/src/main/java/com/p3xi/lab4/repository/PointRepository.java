package com.p3xi.lab4.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.p3xi.lab4.model.Point;

@Repository
public interface PointRepository extends CrudRepository<Point, Long> {
}
