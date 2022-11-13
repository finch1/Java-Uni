import java.util.List;

public class BookingView {

    private static void showBookings(List<Booking> bookings){
        if(bookings.size() <=0){
            System.out.println("There are no bookings that match your criteria.");
        }else{
            System.out.println("List of bookings:");
            for(Booking booking : bookings){
                booking.displayBooking();
            }
            System.out.println();
        }

    }

    public static void displayBookings(int Sel, int n_choice){
        if(Sel == 0){
            final List<Booking> allBookings = BookingDTO.getAllBookings();
            showBookings(allBookings);
        }else if(Sel == 1){
            final List<Booking> allBookingByName = BookingDTO.getAllBookingsByHotelNo(n_choice);
            showBookings(allBookingByName);
        }else if(Sel == 2){
            final List<Booking> allBookingByCity = BookingDTO.getAllBookingsByGuestNo(n_choice);
            showBookings(allBookingByCity);
        }
    }
}
