package server.dto;

import client.*;
import com.User;
import server.dbconn.UserDBConnection;
import java.sql.SQLException;
import java.util.List;

public class UserDTO {

    private static List<User> getUser(String sqlStatement){
        try {
            return UserDBConnection.readDataFromDB(sqlStatement);
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return null;
    }

    public static List<User> getAllUser(){
        return getUser("SELECT * FROM user;");
    }

    public static User getUser_by_Username(Credentials credentialsFromClient){
        return getUser(String.format("SELECT * FROM user WHERE name = '%s';",credentialsFromClient.getUsername())).get(0);
    }

    private static void executeNonQuery(String sqlStatement){
        try {
            UserDBConnection.writeDataToDB(sqlStatement);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void insertUser(String[] newUserDetails){
        final String sqlStatement = String.format("INSERT INTO user (name, hashpass, role, email, phoneNumber, officeNumber) VALUES('%s', '%s', '%s', '%s', '%s', %s);", newUserDetails[0], newUserDetails[1], newUserDetails[2], newUserDetails[3], newUserDetails[4], newUserDetails[5]);
        executeNonQuery(sqlStatement);
    }

    public static void deleteUserByID(String id){
        final String sqlStatement = String.format("DELETE FROM User WHERE user_id='%s';", id);
        executeNonQuery(sqlStatement);
    }

    public static void updateUserByID(String[] renewUserDetails){
        final String sqlStatement = String.format("UPDATE user SET hashpass = '%s' WHERE user_id='%s';", renewUserDetails[1], renewUserDetails[0]);
        executeNonQuery(sqlStatement);
    }

}

