package ru.itmo.web.lab3;

import java.io.IOException;
import java.io.InputStream;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

public class DBManager {
    private String url;
    private String user;
    private String password;

    public DBManager() {
        Properties props = new Properties();
        try (InputStream input = getClass().getClassLoader().getResourceAsStream("database.properties")) {
            props.load(input);
            this.url = props.getProperty("db.url");
            this.user = props.getProperty("db.user");
            this.password = props.getProperty("db.password");
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    private Connection getConnection() throws SQLException {
        Connection connection = DriverManager.getConnection(url, user, password);
        connection.setAutoCommit(false);
        return connection;
    }

    public void addPoint(PointBean point) {
        try (Connection connection = getConnection();
            PreparedStatement statement = connection.prepareStatement(
                   "INSERT INTO points (x, y, r, result, time) VALUES (?, ?, ?, ?, ?)")) {
            statement.setBigDecimal(1, point.getX());
            statement.setBigDecimal(2, point.getY());
            statement.setBigDecimal(3, point.getR());
            statement.setBoolean(4, point.isHit());
            statement.setTimestamp(5, Timestamp.valueOf(point.getRequestTime()));
            statement.executeUpdate();
            connection.commit();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public List<PointBean> getPoints() {
        List<PointBean> points = new ArrayList<>();
        try (Connection connection = getConnection();
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("SELECT x, y, r, result, time FROM points")) {
            while (resultSet.next()) {
                PointBean point = new PointBean();
                point.setX(resultSet.getBigDecimal("x"));
                point.setY(resultSet.getBigDecimal("y"));
                point.setR(resultSet.getBigDecimal("r"));
                point.setHit(resultSet.getBoolean("result"));
                point.setRequestTime(resultSet.getTimestamp("time").toLocalDateTime());
                points.add(point);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return points;
    }
}
