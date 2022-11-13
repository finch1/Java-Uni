package client;

import com.*;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;

import javafx.collections.*;
import server.dto.RoomDTO;

import java.net.URL;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.temporal.ChronoUnit;
import java.util.*;

public class Controller implements Initializable{

    @FXML private Tab userTab;
    @FXML private Tab roomTab;
    @FXML private Tab bookingTab;

    @FXML private TextField textuserName;
    @FXML private PasswordField textuserPass;
    @FXML private Label userLoginMsg;
    @FXML private Button BtnLogin;

    @FXML private TableView<Room> roomView;
    @FXML private Label roomMsg;
    @FXML private TextField textRoomNumber;
    @FXML private TextField textRoomType;
    @FXML private TextField textRoomCapacity;
    @FXML private TextField textRoomPhone;
    @FXML private DatePicker textRoomDate;
    @FXML private TextField textMinCap;
    @FXML private Button BtnsearchRoom;
    @FXML private Button RoomRefresh;
    @FXML private ComboBox roomTypeCombo;

    @FXML private TextField textName;
    @FXML private TextField textEmail;
    @FXML private TextField textPhone;
    @FXML private TextField textOffice;
    @FXML private TextField textPassword;
    @FXML private CheckBox textRole;

    @FXML private TableView<Booking> bookingView;
    @FXML private Label bookingMsg;
    @FXML private TextField textBookingBookedBy;
    @FXML private TextField textBookingAttending;
    @FXML private TextField textBookingEvent;
    @FXML private Button bookingSearch;
    @FXML private TextField searchBookingRoom;
    @FXML private TextField searchBookingBy;
    @FXML private ComboBox roomNumCombo;

    @FXML private DatePicker bookingDatePick;
    @FXML private Button bookingRefresh;
    @FXML private Spinner bookingTimeDetails;
    @FXML private Spinner bookingDurationDetails;

    @FXML private Button bookingEditBtn;
    @FXML private Button bookingDeleteBtn;

    @FXML private TableView<User> userTable;

    @FXML private RadioButton radioOneOff;
    @FXML private RadioButton radioWeekly;
    @FXML private TextField weeklyAmount;
    @FXML private TextField textBookingEst;
    @FXML private TextField textBookingNewEvent;
    @FXML private DatePicker bookingDate;
    @FXML private Spinner bookingTime;
    @FXML private Spinner bookingDuration;
    @FXML private ComboBox bookingRoomNum;
    @FXML private Label newBookingMsg;
    @FXML private Button btnBooking;

    private Request myRequest;
    private UserAgent ua = new UserAgent();
    private User myuser;
    private List<Room> roomList;

    ObservableList<String> bookTime = FXCollections.observableArrayList(//
            "07:00", //
            "08:00", "09:00", "10:00", "11:00",//
            "12:00", "13:00", "14:00", "15:00", //
            "16:00", "17:00", "18:00", "19:00");

    private SpinnerValueFactory<String> valueFactoryTime = new SpinnerValueFactory.ListSpinnerValueFactory<String>(bookTime);

    ObservableList<String> bookDuration = FXCollections.observableArrayList(//
            "01:00", "02:00", "03:00", "04:00", "05:00", "06:00");

    private SpinnerValueFactory<String> valueFactoryDuration = new SpinnerValueFactory.ListSpinnerValueFactory<String>(bookDuration);

    @FXML
    @Override
    public void initialize(URL location, ResourceBundle resources){
        userTab.setDisable(true);
        roomTab.setDisable(true);
        bookingTab.setDisable(true);
    }

    @FXML public void pressBtnLogin(ActionEvent event) throws Exception {

    /*
     * User clicks the login button and the application verifies that all text fields have been filled.
     * Empty fields will be indicated with a red border.
    */
        ValidLogin vl = new ValidLogin(textuserName, textuserPass, userLoginMsg);

    /*
     * If all fields are filled in, the password is hashed, and credentials are stored as an object.
    */
        if (vl.nameVerify() && vl.passVerify()) {

            myRequest = new Request("login", vl.packCredentials());

            //The client tries to connect with the server.
            Object myObj = ua.processRequest(myRequest);

            try {
                myuser = (User) myObj;

                userLoginMsg.setText("welcome " + myuser.getName());
                userTab.setDisable(true);

                if(myuser.getRole()){
                    userTab.setDisable(false);

                    try {
                        //get user list from server
                        myRequest = new Request("userlistview", null);
                        myObj = ua.processRequest(myRequest);

                        List<User> listUser = (List<User>) myObj;
                        ObservableList<User> oListUser = FXCollections.observableArrayList(listUser);
                        userTable.setItems(oListUser);

                    } catch (NullPointerException e) {
                        System.out.println("some data would help in the user table.\n");
                    }
                }

                roomTab.setDisable(false);
                bookingTab.setDisable(false);
                BtnLogin.setDisable(true);
                textuserName.setStyle("-fx-text-box-border: grey");
                textuserPass.setStyle("-fx-text-box-border: grey");
                textuserName.setText("");
                textuserPass.setText("");

                textRoomNumber.setEditable(false);
                textRoomType.setEditable(false);
                textRoomCapacity.setEditable(false);
                textRoomPhone.setEditable(false);
                textBookingBookedBy.setEditable(false);

                try {
                    //get room list from server
                    myRequest = new Request("roomlistview", null);
                    myObj = ua.processRequest(myRequest);

                    List<Room> listRoom = (List<Room>) myObj;
                    roomList = (List<Room>) myObj; //for future use
                    ObservableList<Room> oListRoom = FXCollections.observableArrayList(listRoom);
                    roomView.setItems(oListRoom);

                    //get room type from server
                    myRequest = new Request("roomtypeview", null);
                    myObj = ua.processRequest(myRequest);

                    List<String> listRoomType = (List<String>) myObj;
                    ObservableList<String> oListRoomType = FXCollections.observableArrayList(listRoomType);
                    roomTypeCombo.getItems().addAll(oListRoomType);

                } catch (NullPointerException e) {
                    System.out.println("some data would help in the room table.\n");
                }

                try {
                    //get booking list
                    myRequest = new Request("bookinglistview", null);
                    myObj = ua.processRequest(myRequest);

                    List<Booking> listBooking = (List<Booking>) myObj;

                    ObservableList<Booking> oListBooking = FXCollections.observableArrayList(listBooking);

                    bookingView.setItems(oListBooking);

                } catch (NullPointerException e) {
                    System.out.printf("some data would help in the booking table.");
                }

                bookingTime.setValueFactory(valueFactoryTime);
                bookingDuration.setValueFactory(valueFactoryDuration);

                newBookingMsg.setText("");

                for(int i = 0; i < roomList.size(); i ++){
                    bookingRoomNum.getItems().addAll(roomList.get(i).get_room_number());
                }

                // Group
                ToggleGroup group = new ToggleGroup();
                radioWeekly.setToggleGroup(group);
                radioOneOff.setToggleGroup(group);
                radioOneOff.setSelected(true);

            } catch (NullPointerException e) {
                userLoginMsg.setText("no matching account");
                textuserName.setText("");
                textuserPass.setText("");
            }


        }
    }

    @FXML public void handleRoomRowSelect() {

        Room row = roomView.getSelectionModel().getSelectedItem();
        if (row != null){
            textRoomNumber.setText(String.valueOf(row.get_room_number()));
            textRoomType.setText(row.get_room_type());
            textRoomCapacity.setText(String.valueOf(row.get_room_capacity()));
            textRoomPhone.setText(String.valueOf(row.get_room_phone()));
        }
    }

    @FXML public void handleBookingRowSelect() {
        Booking row = bookingView.getSelectionModel().getSelectedItem();
        if (row == null) return;
        else {

            valueFactoryTime.setValue(row.get_time());
            bookingTimeDetails.setValueFactory(valueFactoryTime);

            valueFactoryDuration.setValue(row.get_duration());
            bookingDurationDetails.setValueFactory(valueFactoryDuration);

            roomNumCombo.setPromptText(row.get_roomNumber());

            for(int i = 0; i < roomList.size(); i ++){
                roomNumCombo.getItems().addAll(roomList.get(i).get_room_number());
            }

            textBookingBookedBy.setText("Name: " +row.get_bookedBy().getName() + " Email: " + row.get_bookedBy().getEmail() + " Phone: " + row.get_bookedBy().getPhone() + " Office: " + row.get_bookedBy().getOffice());
            textBookingAttending.setText(row.get_estimatedAttendies());
            textBookingEvent.setText(row.get_eventName());
            bookingMsg.setText("");

            bookingEditBtn.setDisable(true);
            bookingDeleteBtn.setDisable(true);
            if(row.get_bookedBy().get_id() == myuser.get_id()){
                bookingEditBtn.setDisable(false);
                bookingDeleteBtn.setDisable(false);
            }
        }
    }

    @FXML public void addRoom(ActionEvent event) {

        ObservableList<Room> oListRoom = FXCollections.observableArrayList(RoomDTO.getAllRooms());

        roomView.setItems(oListRoom);

        textRoomNumber.setText("");
        textRoomType.setText("");
    }

    @FXML public void editBooking(ActionEvent event) {
        Booking row = bookingView.getSelectionModel().getSelectedItem();
        if (row != null){
            Integer bookingID = row.get_id();
            String date;
            String time = (String) bookingTimeDetails.getValue();
            String duration = (String) bookingDurationDetails.getValue();
            Integer roomNum = (Integer) roomNumCombo.getValue();
            Integer estimatedAtt;
            String eventName;

            if(roomNum == null){
                roomNum = row.get_roomNumberInt();
            }

            try {
                date = bookingDatePick.getValue().toString();
            } catch (NullPointerException e) {
                date = row.get_date();
            }

            try {
                estimatedAtt = Integer.parseInt(textBookingAttending.getText());
            } catch (NumberFormatException e) {
                bookingMsg.setText("OOPS wrong input. Default 0");
                estimatedAtt = 0;
            }

            //check for max room capacity
            if (estimatedAtt > roomList.get(roomNum).get_room_capacity()){
                bookingMsg.setText("Set Attendance to Max Room Capacity");
                return;
            }

                eventName = textBookingEvent.getText();
            if (eventName == null || eventName.isEmpty()) {
                eventName = "";
            }

            String[] bookingEdit = {bookingID.toString(), date + " " + time, duration, roomNum.toString(), estimatedAtt.toString(),  eventName};

            try {
                myRequest = new Request("bookingUpdate", bookingEdit);
                ua.processRequest(myRequest);

                //update booking list
                myRequest = new Request("bookinglistview", null);
                Object myObj = ua.processRequest(myRequest);

                List<Booking> listBooking = (List<Booking>) myObj;
                ObservableList<Booking> olistBooking = FXCollections.observableArrayList(listBooking);
                bookingView.setItems(olistBooking);

            } catch (NullPointerException e) {
                ObservableList<Booking> olistBooking = FXCollections.observableArrayList();
                bookingView.setItems(olistBooking);
            }

        }
    }

    @FXML public void deleteBooking(ActionEvent event) {

        Booking row = bookingView.getSelectionModel().getSelectedItem();
        if (row != null){
            Integer bookingID = row.get_id();

            try {
                myRequest = new Request("bookingdelete", bookingID.toString());
                ua.processRequest(myRequest);

                //update booking list
                myRequest = new Request("bookinglistview", null);
                Object myObj = ua.processRequest(myRequest);

                List<Booking> listBooking = (List<Booking>) myObj;
                ObservableList<Booking> oListBooking = FXCollections.observableArrayList(listBooking);
                bookingView.setItems(oListBooking);

            } catch (NullPointerException e) {
                ObservableList<Booking> oListBooking = FXCollections.observableArrayList();
                bookingView.setItems(oListBooking);
            }
        }
    }

    @FXML public void RefreshRoomList(ActionEvent event) {
        try {
            myRequest = new Request("roomlistview", null);
            Object myObj = ua.processRequest(myRequest);

            List<Room> listRoom = (List<Room>) myObj;
            roomList = (List<Room>) myObj; //for future use
            ObservableList<Room> oListRoom = FXCollections.observableArrayList(listRoom);
            roomView.setItems(oListRoom);

        } catch (NullPointerException e) {
            ObservableList<Room> oListRoom = FXCollections.observableArrayList();
            roomView.setItems(oListRoom);
        }
    }

    @FXML public void RefreshBookingList(ActionEvent event){


        try {
            myRequest = new Request("bookinglistview", null);
            Object myObj = ua.processRequest(myRequest);

            List<Booking> listBooking = (List<Booking>) myObj;
            ObservableList<Booking> oListBooking = FXCollections.observableArrayList(listBooking);
            bookingView.setItems(oListBooking);

        } catch (NullPointerException e) {
            ObservableList<Booking> oListBooking = FXCollections.observableArrayList();
            bookingView.setItems(oListBooking);
        }
    }

    @FXML public void SearchARoom(ActionEvent event){

        String date;
        Integer minCap;

        try{
            date = textRoomDate.getValue().toString();
        }catch (NullPointerException e){
            date = "";
        }

        String roomType = (String)roomTypeCombo.getValue();
        if(roomType == null || roomType.isEmpty()){
            roomType = "%";
        }else{
            roomType += "%";
        }

        try{
            minCap = Integer.parseInt(textMinCap.getText());
        }catch (NumberFormatException e){
            minCap = 0;
        }

        String roomSearch = date + ":" + minCap.toString() + ":" + roomType;

        try{
            myRequest = new Request("roomsearch", roomSearch);
            Object myObj = ua.processRequest(myRequest);

            List<Room> listRoom = (List<Room>) myObj;
            ObservableList<Room> oListRoom = FXCollections.observableArrayList(listRoom);
            roomView.setItems(oListRoom);
        }catch (NullPointerException e){
            ObservableList<Room> oListRoom = FXCollections.observableArrayList();
            roomView.setItems(oListRoom);
        }

    }

    @FXML public void handleUserRowSelect() {

        User row = userTable.getSelectionModel().getSelectedItem();
        if (row != null){

            textName.setText(row.getName());
            textEmail.setText(row.getEmail());
            textPhone.setText(String.valueOf(row.getPhone()));
            textOffice.setText(String.valueOf(row.getOffice()));
            textRole.setSelected(row.getRole());
        }
    }

    @FXML public void changePassword(ActionEvent event) {

        User row = userTable.getSelectionModel().getSelectedItem();
        if (row != null){
            Integer userID = row.get_id();
            String password = textPassword.getText();

            textPassword.setStyle("-fx-text-box-border: red");
            if((!password.isEmpty())){
                textPassword.setStyle("-fx-text-box-border: grey");

                try {
                    password = SHAPass.get_SHA_1_SecurePassword(password);

                    String[] renewUserDetails = {userID.toString(), password};

                    myRequest = new Request("userpassChange", renewUserDetails);
                    ua.processRequest(myRequest);

                    //update booking list
                    myRequest = new Request("userlistview", null);
                    Object myObj = ua.processRequest(myRequest);

                    List<User> listUser = (List<User>) myObj;
                    ObservableList<User> oListUser = FXCollections.observableArrayList(listUser);
                    userTable.setItems(oListUser);

                } catch (NullPointerException e) {
                    ObservableList<User> oListUser = FXCollections.observableArrayList();
                    userTable.setItems(oListUser);
                }
            }
        }
    }

    @FXML public void deleteUser(ActionEvent event) {

        User row = userTable.getSelectionModel().getSelectedItem();
        if (row != null){
            Integer userID = row.get_id();

            try {
                myRequest = new Request("userdelete", userID.toString());
                ua.processRequest(myRequest);

                //update booking list
                myRequest = new Request("userlistview", null);
                Object myObj = ua.processRequest(myRequest);

                List<User> listUser = (List<User>) myObj;
                ObservableList<User> oListUser = FXCollections.observableArrayList(listUser);
                userTable.setItems(oListUser);

            } catch (NullPointerException e) {
                ObservableList<User> oListUser = FXCollections.observableArrayList();
                userTable.setItems(oListUser);
            }
        }
    }

    @FXML public void addNewUser(ActionEvent event) {
        String name;
        String email;
        String phone;
        String office;
        String password;
        String role;

        name = textName.getText();
        email = textEmail.getText();
        phone = textPhone.getText();
        office = textOffice.getText().isEmpty() ? null : textOffice.getText();
        password = textPassword.getText();
        role = textRole.isSelected()?"1":"0";


        textName.setStyle("-fx-text-box-border: red");
        textEmail.setStyle("-fx-text-box-border: red");
        textPhone.setStyle("-fx-text-box-border: red");
        textPassword.setStyle("-fx-text-box-border: red");

        if((!name.isEmpty())){
            textName.setStyle("-fx-text-box-border: grey");

            if((!email.isEmpty()) && email.contains("@")){
                textEmail.setStyle("-fx-text-box-border: grey");


                if((!phone.isEmpty())){

                    try{

                        Integer phoneNum = Integer.parseInt(phone);
                        textPhone.setStyle("-fx-text-box-border: grey");


                        if((!password.isEmpty())){
                            textPassword.setStyle("-fx-text-box-border: grey");

                            password = SHAPass.get_SHA_1_SecurePassword(password);

                            String[] newUserDetails = {name, password, role, email, phoneNum.toString(), office};


                            try {
                                myRequest = new Request("useradd", newUserDetails);
                                ua.processRequest(myRequest);

                                //update booking list
                                myRequest = new Request("userlistview", null);
                                Object myObj = ua.processRequest(myRequest);

                                List<User> listUser = (List<User>) myObj;
                                ObservableList<User> oListUser = FXCollections.observableArrayList(listUser);
                                userTable.setItems(oListUser);

                            } catch (NullPointerException e) {
                                ObservableList<User> oListUser = FXCollections.observableArrayList();
                                userTable.setItems(oListUser);
                            }

                        }
                    }catch (NumberFormatException e){

                    }
                }
            }
        }
    }

    @FXML public void makeNewBooking(ActionEvent event) {
        int estimatedAtt; //estimated attending
        int roomNum; //room number
        String eventName; //event name

        List<List<String>> newBookingData = new ArrayList<>(); //placeholder for date-start, date-end, rest of info
        ArrayList<String> startDateList = new ArrayList<>(); //array for date plus start time
        ArrayList<String> endDateList = new ArrayList<>(); //array for date plus end time
        ArrayList<String> otherDetails = new ArrayList<>(); //room number, Est,  duration, event name, booked by

        try {//room - empty
            newBookingMsg.setText(""); // clear message display
            roomNum = (Integer) bookingRoomNum.getValue(); // get value of room number


            try{//date - empty

                newBookingMsg.setText(""); // clear message display
                LocalDate date3 = bookingDate.getValue(); // get value of date picker

                //some timing computation
                LocalTime st = LocalTime.parse( (String)bookingTime.getValue() ); // get start time
                LocalTime dt = LocalTime.parse( (String)bookingDuration.getValue() ); // get duration
                LocalTime et = st.plusHours(dt.getHour()).plusMinutes(dt.getMinute());  // add start time to end time

                if(radioOneOff.isSelected()){ // for one time booking
                    startDateList.add(date3.toString() + " " + bookingTime.getValue()); // date + start time added to array
                    endDateList.add(date3.toString() + " " + et.toString()); // date + end time added to array
                }else if(radioWeekly.isSelected()) {

                    try {
                        newBookingMsg.setText(""); // clear message display
                        int numberofWeeks = Integer.parseInt((weeklyAmount.getText())); // get number of how many weeks the event is gonna occur

                        st = LocalTime.parse( bookingTime.getValue().toString() ); // get start time
                        dt = LocalTime.parse( bookingDuration.getValue().toString() ); // get duration
                        et = st.plusHours(dt.getHour()).plusMinutes(dt.getMinute());  // add start time to end time

                        for (int i = numberofWeeks; i > 0; i--) {
                            LocalDate nextWeek = date3.plus(i-1, ChronoUnit.WEEKS); // number of week-1 + start date = end week date

                            startDateList.add(nextWeek.toString() + " " + bookingTime.getValue()); // add date plus start time to list
                            endDateList.add(nextWeek.toString() + " " + et.toString()); // add date plus end time to list
                        }

                    } catch (NumberFormatException e) { // if user doesn't specify weeks frequency or non number
                        newBookingMsg.setText("how many number of weeks?");
                        return;
                    }
                }

                    try {//estimated  - empty - over
                        newBookingMsg.setText(""); //clear message display
                        estimatedAtt = Integer.parseInt(textBookingEst.getText()); // get estimated number of attending people

                        //check for max room capacity
                        if (estimatedAtt > roomList.get(roomNum).get_room_capacity()) { // if user enters a bigger room capacity
                            newBookingMsg.setText("Set Attendance to Max Room Capacity");
                        }

                        try {//event - empty
                            eventName = textBookingNewEvent.getText(); // get event name

                            if (eventName.isEmpty()) { // if no event name specified
                                newBookingMsg.setText("Enter Event Name");
                            } else {
                                try {
                                    otherDetails.add(Integer.toString(roomNum));//room number
                                    otherDetails.add(Integer.toString(estimatedAtt));//Est,
                                    otherDetails.add(bookingDuration.getValue().toString());//duration
                                    otherDetails.add(eventName);//event name
                                    otherDetails.add(Integer.toString(myuser.get_id()));//booked by

                                    //put arrays together
                                    newBookingData.add(startDateList);
                                    newBookingData.add(endDateList);
                                    newBookingData.add(otherDetails);

                                    //get booking list
                                    myRequest = new Request("checkbookingavailability", newBookingData);
                                    Object myObj = ua.processRequest(myRequest);

                                    Integer rowCount = (Integer) myObj;

                                    newBookingMsg.setText("Dates chosen are in use");
                                    if(rowCount == 0){
                                        newBookingMsg.setText("Booking done");
                                    }


                                } catch (NullPointerException e) {
                                    newBookingMsg.setText("sumting wong");
                                }
                            }

                        } catch (NullPointerException e) { //event - empty
                            newBookingMsg.setText("Enter Event Name");
                        }

                    } catch (NumberFormatException e) { //estimated  - empty - over
                        newBookingMsg.setText("Estimated Attendees Incorrect");
                    }

            }catch (NullPointerException e){ //room - empty
                newBookingMsg.setText("Choose Date");
            }

        } catch (NullPointerException e) { //date - empty
            newBookingMsg.setText("Choose a Room Number");

        }

    }

}




