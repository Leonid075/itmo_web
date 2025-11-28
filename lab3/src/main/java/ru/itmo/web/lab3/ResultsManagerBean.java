package ru.itmo.web.lab3;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Named;

@Named("resultsManagerBean")
@ApplicationScoped
public class ResultsManagerBean implements Serializable {
    private List<PointBean> points;
    private final DBManager dbManager;


    public ResultsManagerBean() {
        dbManager = new DBManager();
        points = dbManager.getPoints();
        if (points == null) {
            points = new ArrayList<>();
        }
    }

    public List<PointBean> getPoints() {
        return points;
    }

    public void setPoints(List<PointBean> points) {
        this.points = points;
    }

    public void addPoint(PointBean point) {
        if (points == null) {
            points = new ArrayList<>();
        }
        points.add(point);
        dbManager.addPoint(point);
    }
}
