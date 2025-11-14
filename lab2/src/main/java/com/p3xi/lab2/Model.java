package com.p3xi.lab2;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import jakarta.servlet.http.HttpSession;

public class Model {
    private HttpSession session;
    public Model(HttpSession session) {
        this.session = session;
    }

    public void addResult(int x, BigDecimal y, float r, boolean c) {
        @SuppressWarnings("unchecked")
        List<Result> results = (List<Result>) session.getAttribute("res");
        if (results == null) {
            results = new ArrayList<>();
        }
        results.add(new Result(x, y, r, c, 0, LocalDateTime.now()));
        session.setAttribute("res", results);
    }

    public List<Result> getResults() {
        @SuppressWarnings("unchecked")
        List<Result> results = (List<Result>) session.getAttribute("res");
        if (results == null) {
            results = new ArrayList<>();
        }
        return results;
    }
}