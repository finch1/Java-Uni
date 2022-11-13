package server.dto;


import server.dbconn.RoomTypeDBConnection;
import server.dbconn.UserDBConnection;
import java.sql.SQLException;
import java.util.List;

public class RoomTypeDTO {

    private static List<String> getQuery(String sqlStatement){
        try {
            return RoomTypeDBConnection.readDataFromDB(sqlStatement);
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return null;
    }

    public static List<String> getAllRoomTypes(){
        return getQuery("SELECT roomcat.typeOfRoom FROM roomcat;");
    }

}

