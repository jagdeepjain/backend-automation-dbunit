/**
 * @author jagdeepjain
 */
package org.jagdeep.example.hsqldb;

import java.sql.*;

public class HSQLDB {
    public static final String URL = "jdbc:hsqldb:mem:hr";
    public static final String USER = "sa";
    public static final String PASSWORD = "";
    public static final String DRIVER_CLASS = "org.hsqldb.jdbc.JDBCDriver";
    private static final HSQLDB instance = new HSQLDB();
    final java.util.logging.Logger logger =
            java.util.logging.Logger.getLogger(this.getClass().getName());

    private HSQLDB() {
        try {
            Class.forName(DRIVER_CLASS);
        } catch (ClassNotFoundException e) {
            logger.severe("ERROR: Unable to Connect to Database.");
        }
    }

    public static HSQLDB getInstance() {
        return instance;
    }

    public static Connection getConnection() {
        return instance.createConnection();
    }

    private Connection createConnection() {
        Connection connection = null;
        try {
            connection = DriverManager.getConnection(URL, USER, PASSWORD);
        } catch (SQLException e) {
            logger.severe("ERROR: Unable to Connect to Database.");
        }
        return connection;
    }

    public void create(String sqlQuery) {
        try {
            Statement statement = getConnection().createStatement();
            statement.executeQuery(sqlQuery);
        } catch (SQLException e) {
            logger.severe("ERROR: Unable to execute query.");
        }
    }

    public void insert(String insertQuery, int id, String firstName, String lastName) {
        try {
            PreparedStatement preparedStatement = getConnection().prepareStatement(insertQuery);
            preparedStatement.setInt(1, id);
            preparedStatement.setString(2, firstName);
            preparedStatement.setString(3, lastName);
            preparedStatement.execute();
        } catch (SQLException e) {
            logger.severe("ERROR: Unable to execute insert query.");
        }
    }

    public void update(String updateQuery, int id, String firstName, String lastName) {
        try {
            PreparedStatement preparedStatement = getConnection().prepareStatement(updateQuery);
            preparedStatement.setString(1, firstName);
            preparedStatement.setString(2, lastName);
            preparedStatement.setInt(3, id);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            logger.severe("ERROR: Unable to execute update query.");
        }
    }

    public ResultSet select(String selectQuery, int id) {
        ResultSet result = null;
        try {
            PreparedStatement preparedStatement = getConnection().prepareStatement(selectQuery);
            preparedStatement.setInt(1, id);
            result = preparedStatement.executeQuery();
        } catch (SQLException e) {
            logger.severe("ERROR: Unable to execute select query.");
        }
        return result;
    }
}
