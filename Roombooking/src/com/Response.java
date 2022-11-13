package com;

import client.*;
import server.dto.BookingDTO;
import server.dto.RoomDTO;
import server.dto.RoomTypeDTO;
import server.dto.UserDTO;
import java.util.List;

public class Response {

    private String  command;
    public Object payload;

    public Response (Request myrequest) {
        this.command = myrequest.getCommand();
        this.payload = myrequest.getPayload();
        decode();

    }

    private void decode(){
        switch (this.command){
            case "login":
                User myUser = UserDTO.getUser_by_Username((Credentials)payload);

                if(myUser.gethashpass().equals(((Credentials) payload).getPassword()))
                {
                    payload = myUser;
                }else{
                    payload = null;
                }

                break;
            case "userlistview":
                payload = UserDTO.getAllUser();
                break;
            case "userdelete":
                UserDTO.deleteUserByID((String)payload);
                break;
            case "useradd":
                UserDTO.insertUser((String[])payload);
                break;
            case "userpassChange":
                UserDTO.updateUserByID((String[])payload);
                break;
            case "roomlistview":
                payload = RoomDTO.getAllRooms();
                break;
            case "roomsearch":
                payload = RoomDTO.searchRooms((String)payload);
                break;
            case "roomtypeview":
                payload = RoomTypeDTO.getAllRoomTypes();
                break;
            case "bookinglistview":
                payload = BookingDTO.getAllBooking();
                break;
            case "bookingUpdate":
                BookingDTO.updateBooking((String[])payload);
                break;
            case "bookingdelete":
                BookingDTO.deleteBookingByID((String)payload);
                break;
            case "checkbookingavailability":
                payload = BookingDTO.getAllBookingBetween((List<List<String>>) payload);
                break;
        }
    }




}
