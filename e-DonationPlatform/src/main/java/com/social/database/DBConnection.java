package com.social.database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.logging.Logger;

/**
 * Created by niranjan.agrawal on 2/6/16.
 */
public class DBConnection {
    private static Connection connection;

    private static final Logger log = Logger.getLogger(DBConnection.class.getSimpleName());

    public static Connection getConnection(){
        if(null==connection){
            setupConnection();
        }
        return connection;
    }

    static Connection setupConnection(){
        try {
            Class.forName("org.postgresql.Driver");
            connection = DriverManager.getConnection(
                    "jdbc:postgresql://127.0.0.1:5432/delivery_platform", "postgres",
                    "postgres");

        } catch (ClassNotFoundException e) {
            log.severe("Postgres driver not found");
            e.printStackTrace();
        } catch (SQLException e) {
            log.severe("SQL exception occured at the time of setting up connection");
            e.printStackTrace();
        }
        return connection;
    }
}
