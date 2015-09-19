package org.db.hsqldb;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class HSQLDB {
    
    private Statement statement;
    
    private static HSQLDB instance = new HSQLDB();
    public static final String URL = "jdbc:hsqldb:mem:hr";
    public static final String USER = "sa";
    public static final String PASSWORD = "";
    public static final String DRIVER_CLASS = "org.hsqldb.jdbc.JDBCDriver";
    
    private HSQLDB() {
        try {
            Class.forName(DRIVER_CLASS);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public static HSQLDB getInstance() {
        return instance;
    }

    private Connection createConnection() {
        Connection connection = null;
        try {
            connection = DriverManager.getConnection(URL, USER, PASSWORD);
        } catch (SQLException e) {
            System.out.println("ERROR: Unable to Connect to Database.");
        }
        return connection;
    }

    public static Connection getConnection() throws Exception {
        return instance.createConnection();
    }
    
    public ResultSet query(String query) throws Exception {
        statement = getConnection().createStatement();
        ResultSet resultSet = statement.executeQuery(query);
        return resultSet;
    }
    
    public int insert(String insertQuery) throws Exception {
        statement = getConnection().createStatement();
        int count = statement.executeUpdate(insertQuery);
        return count;
    }
}
