package client;

import com.Booking;
import com.Room;
import javafx.collections.ObservableList;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.MouseButton;
import javafx.stage.Stage;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.loadui.testfx.GuiTest;
import org.testfx.api.FxToolkit;
import org.testfx.framework.junit.ApplicationTest;
import static org.junit.Assert.*;

public class RowSelect extends ApplicationTest{

    @Override
    public void start (Stage stage) throws Exception {
        Parent mainNode = FXMLLoader.load(Main.class.getResource("userInterface.fxml"));
        stage.setScene(new Scene(mainNode));
        stage.show();
        stage.toFront();
    }

    @Before
    public void setUp () throws Exception {
        System.out.println("Run @Before");
        clickOn("#textuserName");
        write("peter");
        clickOn("#textuserPass");
        write("1234");
        clickOn("#BtnLogin");
        clickOn("#roomTab");

    }

    @Test
    public void testRoomTableHasData () {
        System.out.println("Run @Test testRoomTableHasData");
        TableView tv = GuiTest.find("#roomView");
        ObservableList<Room> oListRoom = tv.getItems();
        assertNotNull("Verify that Room List is NOT null", oListRoom);
    }

    @Test
    public void testBookingTableHasData () {
        System.out.println("Run @Test testBookingTableHasData");
        TableView tv = GuiTest.find("#bookingView");
        ObservableList<Booking> oListBooking = tv.getItems();
        assertNotNull("Verify that Booking List is NOT null", oListBooking);
    }

    @Test
    public void testOverMaxRoomCapacity () {
        System.out.println("Run @Test testOverMaxRoomCapacity");
        clickOn("#textMinCap");
        write("50");
        clickOn("#BtnsearchRoom");
        TableView tv = GuiTest.find("#roomView");
        ObservableList<Room> oListRoom = tv.getItems();
        assertEquals("Verify that Room List is null", 0, oListRoom.size());
    }

    @Test
    public void testEditMyBookingAllow () {

        Button editButton = GuiTest.find("#bookingEditBtn");

        System.out.println("Run @Test testEditMyBookingAllow");
        TableView tv = GuiTest.find("#bookingView");
        ObservableList<Booking> oListBooking = tv.getItems();
        oListBooking.forEach((row) -> {
            //if name is peter and the button is disabled = false
            //if name is peter and the button is not disabled = true
            //if name is not peter and the button is disabled = true
            //if name is not peter and the button is not disabled = false
            if(row.get_bookedByName().equals("peter") ){
                assertNotEquals("Verify that user cannot edit other users' bookings", row.get_bookedByName().equals("peter") , editButton.isDisabled());
            }
            if(!row.get_bookedByName().equals("peter")){
                assertEquals("Verify that user cannot edit other users' bookings", row.get_bookedByName().equals("peter") , editButton.isDisabled());
            }
        });
    }

    @After
    public void tearDown () throws Exception {
        System.out.println("Run @After");
        FxToolkit.hideStage();
        release(new KeyCode[]{});
        release(new MouseButton[]{});
    }


}