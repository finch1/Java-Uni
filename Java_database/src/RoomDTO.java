import java.sql.SQLException;
import java.util.*;


public class RoomDTO {

    private static List<Room> getRooms(String sqlStatement){
        try {
            return RoomDBConn.readDataFromDB(sqlStatement);
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return null;
    }

    public static List<Room> getAllRooms(){
        return getRooms("SELECT * FROM room;");
    }

    public static List<Room> getAllRoomsByType(String type){
        return getRooms(String.format("SELECT * FROM room WHERE type = '%s';", type));
    }

    private static void executeNonQuery(String sqlStatement){
        try {
            RoomDBConn.writeDataToDB(sqlStatement);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void insertRoom(Room room){
        final String sqlStatement = String.format("INSERT INTO room VALUES(DEFAULT, '%s', '%s', '%s');", room.get_hotel_number(), room.get_room_type(), room.get_room_price());
        executeNonQuery(sqlStatement);
    }

    public static void updateRoom(Room room){
        final String sqlStatement = String.format("UPDATE room SET hotelNo='%s', type='%s', price='%s' WHERE roomNo=%d;", room.get_hotel_number(), room.get_room_type(), room.get_room_price());
        executeNonQuery(sqlStatement);
    }

    public static void deleteRoomByID(Room room){
        final String sqlStatement = String.format("DELETE FROM room WHERE roomNo='%d';", room.get_room_number());
        executeNonQuery(sqlStatement);
    }

}
