package com.social.database;

import com.social.model.Donor;
import com.social.model.Profile;
import com.social.model.Requirement;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.logging.Logger;

/**
 * Created by niranjan.agrawal on 2/12/16.
 */

public class RequirementDBQueries {

    static private Connection connection = DBConnection.getConnection();
    private static Logger logger = Logger.getLogger(SocialServiceDAO.class.getSimpleName());

    public static Requirement addRequirementForNGO(long ngoId,Requirement requirement){
        try {
            PreparedStatement preparedStatement = connection.prepareStatement("INSERT INTO requirement" +
                    "(title,description,status,ngo_id,created_date,last_updated)" +
                    "VALUES (?,?,?,?,?,?) returning requirement_id;");
            preparedStatement.setString(1,requirement.getTitle());
            preparedStatement.setString(2,requirement.getDescription());
            preparedStatement.setString(3,requirement.getStatus());
            preparedStatement.setLong(4,ngoId);
            preparedStatement.setTimestamp(5,getCurrentTimeStamp());
            preparedStatement.setTimestamp(6,getCurrentTimeStamp());
            ResultSet resultSet = preparedStatement.executeQuery();

            while(resultSet.next()){
                requirement.setId(resultSet.getLong(1));
            }
            return requirement;

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return null;
    }

    public static List<Requirement> getAllRequirementsForNGO(long ngoId){
        List<Requirement> requirementList;
        try {
            PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM requirement WHERE ngo_id=?;");
            preparedStatement.setLong(1,ngoId);
            ResultSet resultSet = preparedStatement.executeQuery();
            requirementList = new ArrayList<>();
            while(resultSet.next()){
                Requirement req = new Requirement(resultSet.getLong("requirement_id"),
                        resultSet.getString("title"),resultSet.getString("description"),
                        resultSet.getString("status"),resultSet.getTimestamp("created_date"),
                        resultSet.getTimestamp("last_updated"));
                requirementList.add(req);
            }
            return requirementList;

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static List<Requirement> getAllRequirementsForStatus(long ngoId, String status){
        List<Requirement> requirementList;
        try {
            PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM requirement WHERE ngo_id=? and status=?;");
            preparedStatement.setLong(1,ngoId);
            preparedStatement.setString(2,status);
            ResultSet resultSet = preparedStatement.executeQuery();
            requirementList = new ArrayList<>();
            while(resultSet.next()){
                Requirement req = new Requirement(resultSet.getLong("requirement_id"),
                        resultSet.getString("title"),resultSet.getString("description"),
                        resultSet.getString("status"),resultSet.getTimestamp("created_date"),
                        resultSet.getTimestamp("last_updated"));
                requirementList.add(req);
            }
            return requirementList;

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;

    }

    public static List<Donor> getAllDonorsForRequirementId(long ngoId, long reqId){
        List<Donor> donorList;
        try {
            String sql = "";
            PreparedStatement preparedStatement = connection.prepareStatement("select * from users where role=2 and " +
                    "id in (select a.donor_id from requirement_donor a, requirement b where b.requirement_id=? " +
                    "and a.req_id=b.requirement_id and b.ngo_id=?);");
            preparedStatement.setLong(1,reqId);
            preparedStatement.setLong(2,ngoId);
            ResultSet resultSet = preparedStatement.executeQuery();
            donorList = new ArrayList<>();
            while(resultSet.next()){
                Donor donor = new Donor(resultSet.getString("name"),
                        resultSet.getString("phone_number"),resultSet.getString("address"),
                        resultSet.getString("email"));
                donorList.add(donor);
            }
            return donorList;

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static Requirement getRequirementForId(long ngoId, long requirementId){
        try {
            PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM requirement WHERE ngo_id=? AND requirement_id=?;");
            preparedStatement.setLong(1,ngoId);
            preparedStatement.setLong(2,requirementId);
            ResultSet resultSet = preparedStatement.executeQuery();
            Requirement req=null;
            while(resultSet.next()){
                req = new Requirement(resultSet.getLong("requirement_id"),
                        resultSet.getString("title"),resultSet.getString("description"),
                        resultSet.getString("status"),resultSet.getTimestamp("created_date"),
                        resultSet.getTimestamp("last_updated"));
            }
            return req;

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static Requirement updateRequirementForId(long ngoId, long reqId, Requirement requirement){
        try {
            PreparedStatement preparedStatement = connection.prepareStatement("UPDATE requirement SET title=?, description=?, status=?, last_updated=?" +
                    "WHERE requirement_id=? AND ngo_id=?;");
            preparedStatement.setString(1,requirement.getTitle());
            preparedStatement.setString(2,requirement.getDescription());
            preparedStatement.setString(3,requirement.getStatus());
            preparedStatement.setTimestamp(4,getCurrentTimeStamp());
            preparedStatement.setLong(5,reqId);
            preparedStatement.setLong(6,ngoId);
            preparedStatement.executeUpdate();

            return requirement;

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return null;

    }

    private static java.sql.Timestamp getCurrentTimeStamp() {

        java.util.Date today = new java.util.Date();
        return new java.sql.Timestamp(today.getTime());

    }

}
