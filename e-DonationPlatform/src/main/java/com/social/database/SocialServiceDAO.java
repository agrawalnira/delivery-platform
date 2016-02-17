package com.social.database;

import com.social.model.Profile;

import java.sql.*;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.Date;
import java.util.logging.Logger;

/**
 * Created by niranjan.agrawal on 2/6/16.
 */
public class SocialServiceDAO {

    static private Connection connection = DBConnection.getConnection();
    private static Logger logger = Logger.getLogger(SocialServiceDAO.class.getSimpleName());

    public static boolean authenticateUser(String username,String password){
        try {
            PreparedStatement preparedStatement = connection.prepareStatement("select * from users WHERE username=? AND password=?;");
            preparedStatement.setString(1,username);
            preparedStatement.setString(2,password);
            ResultSet rs = preparedStatement.executeQuery();
            while(rs.next()){
                return true;
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return false;
    }

    public static boolean registerNewProfile(Profile profile){
        try {
            PreparedStatement preparedStatement = connection.prepareStatement("INSERT INTO users" +
                    "(name,username,password,role,phone_number,address,email,created_date,last_updated,reg_id)" +
                    "VALUES (?,?,?,?,?,?,?,?,?,?);");
            preparedStatement.setString(1,profile.getName());
            preparedStatement.setString(2,profile.getUsername());
            preparedStatement.setString(3,profile.getPassword());
            preparedStatement.setInt(4,profile.getRole());
            preparedStatement.setString(5,profile.getPhoneNumber());
            preparedStatement.setString(6,profile.getAddress());
            preparedStatement.setString(7,profile.getEmailId());
            DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
            Date date = new  Date();
            dateFormat.format(date);
            preparedStatement.setTimestamp(8,getCurrentTimeStamp());
            preparedStatement.setTimestamp(9,getCurrentTimeStamp());
            preparedStatement.setLong(10,profile.getNgoId());
            preparedStatement.executeUpdate();
            return true;

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return false;
    }

    public static Profile getProfile(String username){
        try {
            PreparedStatement preparedStatement = connection.prepareStatement("select * from users WHERE username=?;");
            preparedStatement.setString(1,username);
            ResultSet rs = preparedStatement.executeQuery();
            while(rs.next()){
                logger.info("In SocialService DAO, result found for the username:"+username);
                return new Profile(rs.getString("username"),rs.getString("password"),rs.getString("name"),rs.getInt("role"),
                        rs.getString("phone_number"),rs.getString("address"),rs.getString("email"),rs.getLong("reg_id"));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        logger.info("In SocialService DAO, no result found for the username:"+username);
        return null;

    }

    private static java.sql.Timestamp getCurrentTimeStamp() {

        java.util.Date today = new java.util.Date();
        return new java.sql.Timestamp(today.getTime());

    }

}
