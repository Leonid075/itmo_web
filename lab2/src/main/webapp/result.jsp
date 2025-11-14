<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>

<%@ page import="java.util.List" %>
<%@ page import="com.p3xi.lab2.Result" %>
<%@ page import="com.p3xi.lab2.Model" %>
<%@ page import="java.util.Collections" %>
<%@ page import="java.time.format.DateTimeFormatter" %>
<!DOCTYPE html>

<html>

<head>
    <meta charset="UTF-8" />
    <meta name="viewport" content="width=device-width, initial-scale=1.0" />
    <title>Lab2</title>
    <link rel="stylesheet" href="style.css" />
</head>

<body>
    <header>
        <span>
            <h1>Гребенюк Леонид Сергеевич, p3232, вариант 232343</h1>
        </span>
    </header>

    <div style="display: flex; flex-direction: column; align-items: center">
        <h1>Результаты</h1>

        <%
            List<Result> results = new Model(session).getResults();
            Collections.reverse(results);
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy HH:mm:ss");
        %>
        <div>
            <table>
                <thead>
                    <tr>
                        <th>Время запроса</th>
                        <th>X</th>
                        <th>Y</th>
                        <th>R</th>
                        <th>Статус</th>
                    </tr>
                </thead>
                <tbody>
                    <% for (Result result : results) { %>
                        <tr>
                            <td><%= result.getRequestTime().format(formatter) %></td>
                            <td><%= result.getX() %></td>
                            <td><%= result.getY() %></td>
                            <td><%= result.getR() %></td>
                            <td><%= result.isC() ? "Да" : "Нет" %></td>
                        </tr>
                    <% } %>
                </tbody>
            </table>
        </div>

        <div style="margin-top: 40px;">
            <a href="/lab2/controller">Назад</a>
        </div>
    </div>
</body>

</html>