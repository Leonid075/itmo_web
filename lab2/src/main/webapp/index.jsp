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
    <link rel="stylesheet" href="style.css" />
    <title>Lab2</title>
</head>

<body>
    <%
        List<Result> results = new Model(session).getResults();
        Collections.reverse(results);
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy HH:mm:ss");
    %>
    <header>
        <span>
            <h1>Гребенюк Леонид Сергеевич, p3232, вариант 232343</h1>
        </span>
    </header>

    <div style="display: flex; flex-direction: column; align-items: center">
        <div style="width: 400px; height: 400px; cursor: crosshair;" id="graph">
            <svg viewBox="0 0 400 400" xmlns="http://www.w3.org/2000/svg" id="svg">
                <circle cx="200" cy="200" r="150" class="light-blue" />
                <rect x="50" y="50" width="150" height="300" style="fill:white" />
                <rect x="200" y="200" width="150" height="150" style="fill:white" />
                <rect x="200" y="200" width="150" height="75" class="light-blue" />
                <polygon points="200,200 200,275 125,200" class="light-blue" />
                <line x1="200" y1="20" x2="200" y2="380" stroke="black" />
                <line x1="20" y1="200" x2="380" y2="200" stroke="black" />
                <line x1="370" y1="204" x2="380" y2="200" stroke="black" />
                <line x1="370" y1="196" x2="380" y2="200" stroke="black" />
                <line x1="204" y1="30" x2="200" y2="20" stroke="black" />
                <line x1="196" y1="30" x2="200" y2="20" stroke="black" />
                <line x1="197" y1="275" x2="203" y2="275" stroke="black" />
                <line x1="197" y1="350" x2="203" y2="350" stroke="black" />
                <line x1="197" y1="125" x2="203" y2="125" stroke="black" />
                <line x1="197" y1="50" x2="203" y2="50" stroke="black" />
                <line x1="50" y1="197" x2="50" y2="203" stroke="black" />
                <line x1="125" y1="197" x2="125" y2="203" stroke="black" />
                <line x1="275" y1="197" x2="275" y2="203" stroke="black" />
                <line x1="350" y1="197" x2="350" y2="203" stroke="black" />
                <text x="210" y="30" class="small">y</text>
                <text x="370" y="190" class="small">x</text>
                <text x="46" y="195" class="small">R</text>
                <text x="346" y="195" class="small">R</text>
                <text x="205" y="54" class="small">R</text>
                <text x="205" y="354" class="small">R</text>
                <text x="115" y="195" class="small">R/2</text>
                <text x="265" y="195" class="small">R/2</text>
                <text x="205" y="129" class="small">R/2</text>
                <text x="205" y="279" class="small">R/2</text>
            </svg>
        </div>

        <div>
            <fieldset class="input-element">
                <legend>Set X:</legend>
                <span>
                    <input type="checkbox" data-group="x" value="-3">
                    <label>-3 </label>
                </span>
                <span>
                    <input type="checkbox" data-group="x" value="-2">
                    <label for="louie">-2 </label>
                </span>
                <span>
                    <input type="checkbox" data-group="x" value="-1">
                    <label for="louie">-1 </label>
                </span>
                <span>
                    <input type="checkbox" data-group="x" value="0">
                    <label for="louie">0 </label>
                </span>
                <span>
                    <input type="checkbox" data-group="x" value="1">
                    <label for="louie">1 </label>
                </span>
                <span>
                    <input type="checkbox" data-group="x" value="2">
                    <label for="louie">2 </label>
                </span>
                <span>
                    <input type="checkbox" data-group="x" value="3">
                    <label for="louie">3 </label>
                </span>
                <span>
                    <input type="checkbox" data-group="x" value="4">
                    <label for="louie">4 </label>
                </span>
                <span>
                    <input type="checkbox" data-group="x" value="5">
                    <label for="louie">5 </label>
                </span>
            </fieldset>

            <fieldset class="input-element">
                <legend>Type Y:</legend>
                <input type="text" size="10" maxlength="20" id="y" placeholder="-3 ≤ Y ≤ 3" required
                    style="width: 200px;" />
            </fieldset>

            <fieldset class="input-element">
                <legend>Set R:</legend>

                <span>
                    <button data-group="r" value="1">1</button>
                </span>
                <span>
                    <button data-group="r" value="1.5">1.5</button>
                </span>
                <span>
                    <button data-group="r" value="2">2</button>
                </span>
                <span>
                    <button data-group="r" value="2.5">2.5</button>
                </span>
                <span>
                    <button data-group="r" value="3">3</button>
                </span>
            </fieldset>
        </div>

        <div>
            <p id="alert"></p>
        </div>

        <div>
            <button id="submit">Send</button>
        </div>

        <div style="margin-top: 40px;">
            <% if (results != null && !results.isEmpty()) { %>
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
                <tbody id="table">
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
            <% } else { %>
                <p>Результаты пока отсутствуют</p>
            <% } %>
        </div>
    </div>
    <script src="script.js"></script>
    <script src="points.js"></script>
</body>

</html>