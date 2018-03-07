import java.sql.SQLException;
import java.util.*;


public class BookingDTO {

    private static List<Booking> getBookings(String sqlStatement){
        try {
            return BookingDBConn.readDataFromDB(sqlStatement);
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return null;
    }

    public static List<Booking> getAllBookings(){
        return getBookings("SELECT * FROM booking;");
    }

    public static List<Booking> getAllBookingsByGuestNo(int guestNumber){
        return getBookings(String.format("SELECT * FROM booking WHERE guestNo = '%s';", guestNumber));
    }

    public static List<Booking> getAllBookingsByHotelNo(int hotelNumber){
        return getBookings(String.format("SELECT * FROM booking WHERE hotelNo = '%s';", hotelNumber));
    }

    private static void executeNonQuery(String sqlStatement){
        try {
            BookingDBConn.writeDataToDB(sqlStatement);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void insertBooking(Booking booking){
        final String sqlStatement = String.format("INSERT INTO booking VALUES('%s', '%s', '%s', '%s', '%s');",
                booking.get_guest_number(),
                booking.get_hotel_number(),
                booking.get_dateFrom(),
                booking.get_dateTo(),
                booking.get_room_number());
        executeNonQuery(sqlStatement);
    }

//    public static void updateBooking(Booking booking){
//        final String sqlStatement = String.format();
//        executeNonQuery(sqlStatement);
//    }

    public static void deleteBookingByGuestNo(Booking booking){
        final String sqlStatement = String.format("DELETE FROM booking WHERE guestNo='%d';", booking.get_guest_number());
        executeNonQuery(sqlStatement);
    }

}
