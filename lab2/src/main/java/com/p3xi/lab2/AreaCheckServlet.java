package com.p3xi.lab2;

import java.io.IOException;
import java.math.BigDecimal;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/areacheck")
public class AreaCheckServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            Boolean fromController = (Boolean) request.getAttribute("fromController");
            if (fromController == null || !fromController) {
                response.sendError(HttpServletResponse.SC_FORBIDDEN);
                return;
            }

            Model model = new Model(request.getSession());

            BigDecimal x = null, y = null, r = null;
            try {
                x = new BigDecimal(request.getParameter("x"));
                y = new BigDecimal(request.getParameter("y"));
                r = new BigDecimal(request.getParameter("r"));

                if (!AreaChecker.checkParams(x, y, r)) {
                    response.sendError(HttpServletResponse.SC_BAD_REQUEST, "Неверные параметры.");
                    return;
                }
            } catch (NumberFormatException | IllegalStateException e) {
                response.sendError(HttpServletResponse.SC_BAD_REQUEST, "Неверный формат параметров.");
                return;
            } catch (NullPointerException e) {
                response.sendError(HttpServletResponse.SC_BAD_REQUEST, "Отсутствуют параметры.");
                return;
            }

            model.addResult(x.intValue(), y, r.floatValue(), AreaChecker.checkArea(x, y, r));

            request.getRequestDispatcher("/result.jsp").forward(request, response);
        } catch (Exception e) {
            response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "Internal server error.");
        }
    }
}
