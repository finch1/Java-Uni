import java.sql.SQLException;
import java.util.*;


public class HotelDTO {

    private static List<Hotel> getHotels(String sqlStatement){
        try {
            return HotelDBConn.readDataFromDB(sqlStatement);
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return null;
    }

    public static List<Hotel> getAllHotels(){
            return getHotels("SELECT * FROM hotel;");
    }

    public static List<Hotel> getAllHotelsByCity(String city){
        return getHotels(String.format("SELECT * FROM hotel WHERE city = '%s';", city));
    }

    public static List<Hotel> getAllHotelsByName(String name){
        return getHotels(String.format("SELECT * FROM hotel WHERE hotelName = '%s';", name));
    }

    private static void executeNonQuery(String sqlStatement){
        try {
            HotelDBConn.writeDataToDB(sqlStatement);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void insertHotel(Hotel hotel){
        final String sqlStatement = String.format("INSERT INTO hotel VALUES(DEFAULT, '%s', '%s');", hotel.get_name(), hotel.get_city());
            executeNonQuery(sqlStatement);
    }

    public static void updateHotel(Hotel hotel){
        final String sqlStatement = String.format("UPDATE hotel SET hotelName='%s', city='%s' WHERE hotelNo=%d;", hotel.get_name(), hotel.get_city(), hotel.get_number());
            executeNonQuery(sqlStatement);
    }

    public static void deleteHotelByID(Hotel hotel){
        final String sqlStatement = String.format("DELETE FROM hotel WHERE hotelNo='%d';", hotel.get_number());
        executeNonQuery(sqlStatement);
    }

}
