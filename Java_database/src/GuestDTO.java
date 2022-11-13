import java.sql.SQLException;
import java.util.*;


public class GuestDTO {

    private static List<Guest> getGuests(String sqlStatement){
        try {
            return GuestDBConn.readDataFromDB(sqlStatement);
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return null;
    }

    public static List<Guest> getAllGuests(){
        return getGuests("SELECT * FROM guest;");
    }


    public static List<Guest> getAllGuestsByName(String guestName){
        return getGuests(String.format("SELECT * FROM guest WHERE guestName = '%s';", guestName));
    }

    private static void executeNonQuery(String sqlStatement){
        try {
            GuestDBConn.writeDataToDB(sqlStatement);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void insertGuest(Guest guest){
        final String sqlStatement = String.format("INSERT INTO guest VALUES(DEFAULT, '%s', '%s');", guest.get_name(), guest.get_add());
        executeNonQuery(sqlStatement);
    }

    public static void updateGuest(Guest guest){
        final String sqlStatement = String.format("UPDATE guest SET guestName='%s', city='%s' WHERE guestNo=%d;", guest.get_name(), guest.get_add(), guest.get_number());
        executeNonQuery(sqlStatement);
    }

    public static void deleteGuestByID(Guest guest){
        final String sqlStatement = String.format("DELETE FROM guest WHERE guestNo='%d';", guest.get_number());
        executeNonQuery(sqlStatement);
    }

}
