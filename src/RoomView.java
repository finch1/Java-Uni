import java.util.List;

public class RoomView {

    private static void showRooms(List<Room> rooms){
        if(rooms.size() <=0){
            System.out.println("There are no rooms that match your criteria.");
        }else{
            System.out.println("List of rooms:");
            for(Room room : rooms){
                room.displayRoom();
            }
            System.out.println();
        }

    }

    public static void displayRooms(int Sel, String s_choice){
        if(Sel == 0){
            final List<Room> allRooms = RoomDTO.getAllRooms();
            showRooms(allRooms);
        }else if(Sel == 1){
            final List<Room> allRoomByName = RoomDTO.getAllRoomsByType(s_choice);
            showRooms(allRoomByName);
        }
    }
}
