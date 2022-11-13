package server.dto;

import com.Booking;
import server.dbconn.BookingDBConnection;
import java.sql.SQLException;
import java.util.List;


public class BookingDTO {

    private static List<Booking> getBooking(String sqlStatement){
        try {
            return BookingDBConnection.readDataFromDB(sqlStatement);
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return null;
    }

    public static List<Booking> getAllBooking(){
        return getBooking(    "SELECT booking.id, " +
                                        "DATE_FORMAT(booking.date_time, '%X-%m-%d') AS date, " +
                                        "TIME_FORMAT(booking.date_time, '%h:%i') AS time, " +
                                        "TIME_FORMAT(booking.duration, '%h:%i') AS duration, " +
                                        "booking.roomNumber, " +
                                        "user.user_id, " +
                                        "user.name, " +
                                        "user.email, " +
                                        "user.phoneNumber, " +
                                        "user.officeNumber, " +
                                        "booking.estimatedAttendies, " +
                                        "booking.eventName " +
                                        "FROM booking left join user on user.user_id = booking.BookedByID " +
                                        "ORDER BY booking.roomNumber ASC;");
    }

    public static Integer getAllBookingBetween(List<List<String>> newBookingData ){
        Integer rowCount = 0;
        List<String> startDateList = newBookingData.get(0); //array for date plus start time
        List<String> endDateList = newBookingData.get(1); //array for date plus end time
        List<String> otherDetails = newBookingData.get(2); //room number, Est,  duration, event name, booked by

        List<Booking> bookingCheck;

        for(int i = 0; i < startDateList.size(); i++){
            try {
                    bookingCheck = getBooking( String.format(   "SELECT booking.id, " +
                                                                "booking.date_time AS date, " +
                                                                "booking.date_time AS time, " +
                                                                "booking.duration AS duration, " +
                                                                "booking.roomNumber, " +
                                                                "user.user_id, " +
                                                                "user.name, " +
                                                                "user.email, " +
                                                                "user.phoneNumber, " +
                                                                "user.officeNumber, " +
                                                                "booking.estimatedAttendies, " +
                                                                "booking.eventName " +
                                                                "FROM booking left join user on user.user_id = booking.BookedByID " +
                                                                "WHERE roomNumber = '%s' AND '%s' BETWEEN date_time and ADDTIME(date_time,duration) " +
                                                                "OR '%s' BETWEEN date_time and ADDTIME(date_time,duration)",otherDetails.get(0),startDateList.get(i),endDateList.get(i)));
                    // if one room is booked between 10:00 till 12:00, and i need a room between 09:00 and 11:00,
                    // check if 09:00 is between 10:00 and 12:00 (which is false) OR if 11:00 is between 10:00 and 12:00 (which is true)

                    rowCount += bookingCheck.size();

            }catch (NullPointerException e){
                System.err.println("Check dates empty!");
            }

        }

        if(rowCount == 0){
            for(int i = 0; i < startDateList.size(); i++) {
                insertBooking(startDateList.get(i), otherDetails);
            }

        }

        return rowCount;
    }

    private static void executeNonQuery(String sqlStatement){
        try {
            BookingDBConnection.writeDataToDB(sqlStatement);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private static void insertBooking(String startDate, List<String> otherDetails){
        final String sqlStatement = String.format(  "INSERT INTO booking( date_time, duration, roomNumber, BookedByID, estimatedAttendies, eventName) " +
                                                    "VALUES ('%s','%s','%s','%s','%s','%s');",  startDate,
                                                                                                otherDetails.get(2),
                                                                                                otherDetails.get(0),
                                                                                                otherDetails.get(4),
                                                                                                otherDetails.get(1),
                                                                                                otherDetails.get(3));
        executeNonQuery(sqlStatement);
    }

    public static void updateBooking(String[] updateStmt){
        final String sqlStatement = String.format("UPDATE booking SET date_time='%s', duration='%s', roomNumber='%s', estimatedAttendies='%s', eventName='%s' WHERE id='%s';",updateStmt[1],updateStmt[2],updateStmt[3],updateStmt[4],updateStmt[5],updateStmt[0]);
        executeNonQuery(sqlStatement);
    }

    public static void deleteBookingByID(String bookingID){
        final String sqlStatement = String.format("DELETE FROM booking WHERE booking.id='%s';", bookingID);
        executeNonQuery(sqlStatement);
    }

}
