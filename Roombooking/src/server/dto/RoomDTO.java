package server.dto;

import com.Room;
import server.dbconn.RoomDBConnection;

import java.sql.*;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.Date;

public class RoomDTO {

    private static List<Room> getRooms(String sqlStatement){
        try {
            return RoomDBConnection.readDataFromDB(sqlStatement);
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return null;
    }

    public static List<Room> getAllRooms(){
        return getRooms("SELECT room.roomNumber, roomCat.typeOfRoom, room.capacity, room.phoneNumber " +
                                    "FROM room " +
                                    "INNER JOIN roomCat ON room.roomType = roomCat.roomType " +
                                    "ORDER BY roomNumber;");
    }

    public static Object searchRooms(String payload) {

        Date dNow = new Date();
        SimpleDateFormat ft = new SimpleDateFormat("yyyy-MM-dd");


        String[] parts = payload.split(":");
        String roomDate = parts[0];
        String roomCap = parts[1];
        String roomType = parts[2];

        roomDate = roomDate.isEmpty() ? ft.format(dNow) : roomDate;
        roomCap = roomCap.isEmpty() ? "0" : roomCap;

        return getRooms(String.format(  "SELECT room.roomNumber, roomCat.typeOfRoom, room.capacity, room.phoneNumber " +
                                        "FROM room INNER JOIN roomCat ON room.roomType = roomCat.roomType " +
                                        "WHERE room.roomNumber " +
                                        "NOT IN     (SELECT DISTINCT booking.roomNumber " +
                                                    "FROM booking " +
                                                    "WHERE date_time BETWEEN '%s 00:00:00' and '%s 23:59:59') AND room.capacity > '%s' AND roomcat.typeOfRoom LIKE '%s' " +
                                        "ORDER BY room.id ASC;", roomDate, roomDate, roomCap, roomType));


    }

}
