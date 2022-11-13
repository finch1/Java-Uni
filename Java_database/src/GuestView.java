import java.util.List;

public class GuestView {

    private static void showGuests(List<Guest> guests){
        if(guests.size() <=0){
            System.out.println("There are no guests that match your criteria.");
        }else{
            System.out.println("List of guests:");
            for(Guest guest : guests){
                guest.displayGuest();
            }
            System.out.println();
        }

    }

    public static void displayGuests(int Sel, String s_choice){
        if(Sel == 0){
            final List<Guest> allGuests = GuestDTO.getAllGuests();
            showGuests(allGuests);
        }else if(Sel == 1){
            final List<Guest> allGuestByName = GuestDTO.getAllGuestsByName(s_choice);
            showGuests(allGuestByName);
        }
    }
}
