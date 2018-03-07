import java.util.List;

public class HotelView {

    private static void showHotels(List<Hotel> hotels){
        if(hotels.size() <=0){
            System.out.println("There are no hotels that match your criteria.");
        }else{
            System.out.println("List of hotels:");
            for(Hotel hotel : hotels){
                hotel.displayHotel();
            }
            System.out.println();
        }

    }

    public static void displayHotels(int Sel, String s_choice){
        if(Sel == 0){
            final List<Hotel> allHotels = HotelDTO.getAllHotels();
            showHotels(allHotels);
        }else if(Sel == 1){
            final List<Hotel> allHotelByName = HotelDTO.getAllHotelsByName(s_choice);
            showHotels(allHotelByName);
        }else if(Sel == 2){
            final List<Hotel> allHotelByCity = HotelDTO.getAllHotelsByCity(s_choice);
            showHotels(allHotelByCity);
        }
    }
}
