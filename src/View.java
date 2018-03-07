public class View {

    public static void create(){
        System.out.print(   "0.Create Hotel\n" +
                            "1.Create Room\n" +
                            "2.Create Guest\n" +
                            "3.Create Booking\n");

        int selection = InputHelper.getIntInput("Choose an option: ");

        switch (selection) {
            case 0:
                String hotelName = InputHelper.getStringInput("Enter hotel name:");
                String city      = InputHelper.getStringInput("Enter hotel city:");
                city = city.toUpperCase();
                HotelDTO.insertHotel(new Hotel(hotelName, city));
                break;

            case 1:
                String type = InputHelper.getStringInput("Enter room type:");
                float price = InputHelper.getFloatInput("Enter room price:");
                RoomDTO.insertRoom(new Room(type, price));
                break;

            case 2:
                String guestName = InputHelper.getStringInput("Enter guest name:");
                String guestAdd  = InputHelper.getStringInput("Enter guest address:");
                GuestDTO.insertGuest(new Guest(guestName, guestAdd));
                break;

            case 3:
                String dateFrom = InputHelper.getStringInput("Enter date from:");
                String dateTo   = InputHelper.getStringInput("Enter date to:");
                int guestNumber = InputHelper.getIntInput("Enter guest number:");
                BookingDTO.insertBooking(new Booking(guestNumber, dateFrom, dateTo));
                break;
            default:
                System.out.println("wrong choice!");
                break;
        }//switch
    }//create

    public static void update(){
        System.out.print(   "0.Update Hotel\n" +
                            "1.Update Room\n" +
                            "2.Update Guest\n" +
                            "3.Update Booking\n");

        int selection = InputHelper.getIntInput("Choose an option: ");

        switch (selection) {
            case 0:
                int hotelNo      = InputHelper.getIntInput("Enter hotel number:");
                String hotelName = InputHelper.getStringInput("Enter hotel name:");
                String city      = InputHelper.getStringInput("Enter hotel city:");
                city = city.toUpperCase();
                HotelDTO.updateHotel(new Hotel(hotelNo, hotelName, city));
                break;

            case 1:
                String type = InputHelper.getStringInput("Enter room type:");
                float price = InputHelper.getFloatInput("Enter room price:");
                RoomDTO.updateRoom(new Room(type, price));
                break;

            case 2:
                String guestName = InputHelper.getStringInput("Enter guest name:");
                String guestAdd  = InputHelper.getStringInput("Enter guest city:");
                GuestDTO.updateGuest(new Guest(guestName, guestAdd));
                break;

            case 3:
                String dateFrom = InputHelper.getStringInput("Enter date from:");
                String dateTo   = InputHelper.getStringInput("Enter date to:");
                int guestNumber = InputHelper.getIntInput("Enter guest number:");
                BookingDTO.insertBooking(new Booking(guestNumber, dateFrom, dateTo));
                break;
            default:
                System.out.println("wrong choice!");
                break;

        }//switch
    }//update


    public static void delete() {
        System.out.print(   "0.Delete Hotel\n" +
                            "1.Delete Room\n" +
                            "2.Delete Guest\n" +
                            "3.Delete Booking\n");

        int selection = InputHelper.getIntInput("Choose an option: ");

        switch (selection) {
            case 0:
                HotelView.displayHotels(0,null);
                int hotelNo = InputHelper.getIntInput("Enter hotel number:");
                HotelDTO.deleteHotelByID(new Hotel(hotelNo));
                break;

            case 1:
                RoomView.displayRooms(0,null);
                int roomNo = InputHelper.getIntInput("Enter room number:");
                RoomDTO.deleteRoomByID(new Room(roomNo));
                break;

            case 2:
                GuestView.displayGuests(0,null);
                int guestNo = InputHelper.getIntInput("Enter guest number:");
                GuestDTO.deleteGuestByID(new Guest(guestNo));
                break;

            case 3:
                BookingView.displayBookings(0,0);
                int bookingNo = InputHelper.getIntInput("Enter guest number:");
                BookingDTO.deleteBookingByGuestNo(new Booking(bookingNo));
                break;
            default:
                System.out.println("wrong choice!");
                break;

        }//switch
    }//delete


    public static void read() {
        System.out.print(   "0.Select Hotel\n" +
                            "1.Select Room\n" +
                            "2.Select Booking\n" +
                            "3.Select Guest\n");

        int selection = InputHelper.getIntInput("Choose an option: ");

        switch (selection) {
            case 0:
                System.out.print(   "0.All Hotel\n" +
                                    "1.Hotel by name\n" +
                                    "2.Hotel\\s in city\n");
                int hotelSelection = InputHelper.getIntInput("Choose an option: ");

                switch (hotelSelection){
                    case 0:
                        HotelView.displayHotels(0,null);
                        break;

                    case 1:
                        String hotelname = InputHelper.getStringInput("Enter hotel name: ");
                        HotelView.displayHotels(1,hotelname);
                        break;

                    case 2:
                        String hotelcity = InputHelper.getStringInput("Enter hotel city: ");
                        hotelcity = hotelcity.toUpperCase();
                        HotelView.displayHotels(2,hotelcity);
                        break;

                    default:
                        System.out.println("wrong choice!");
                        break;
                }
                break;

            case 1:
                System.out.print(   "0.All Rooms\n" +
                                    "1.Room by type\n");
                int roomSelection = InputHelper.getIntInput("Choose an option: ");

                switch (roomSelection){
                    case 0:
                        RoomView.displayRooms(0,null);
                        break;

                    case 1:
                        String roomtype = InputHelper.getStringInput("Enter room type: ");
                        RoomView.displayRooms(1,roomtype);
                        break;

                    default:
                        System.out.println("wrong choice!");
                        break;
                }
                break;

            case 2:
                System.out.print(   "0.All Bookings\n" +
                                    "1.Bookings by guest number\n" +
                                    "2.Bookings in hotel number\n");
                int bookingSelection = InputHelper.getIntInput("Choose an option: ");

                switch (bookingSelection){
                    case 0:
                        BookingView.displayBookings(0,0);
                        break;

                    case 1:
                        int guestNunmber = InputHelper.getIntInput("Enter guest number: ");
                        BookingView.displayBookings(1,guestNunmber);
                        break;

                    case 2:
                        int hotelNumber = InputHelper.getIntInput("Enter hotel number: ");
                        BookingView.displayBookings(2,hotelNumber);
                        break;

                    default:
                        System.out.println("wrong choice!");
                        break;
                }
                break;

            case 3:
                System.out.print(   "0.All Guests\n" +
                                    "1.Guest by name\n");
                int guestSelection = InputHelper.getIntInput("Choose an option: ");

                switch (guestSelection){
                    case 0:
                        GuestView.displayGuests(0,null);
                        break;

                    case 1:
                        String guestName = InputHelper.getStringInput("Enter guest name: ");
                        GuestView.displayGuests(1,guestName);
                        break;

                    default:
                        System.out.println("wrong choice!");
                        break;
                }
                break;
        }//switch
    }//read
}//View
