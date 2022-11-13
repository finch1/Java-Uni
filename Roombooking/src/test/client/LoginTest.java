package client;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.input.KeyCode;
import javafx.scene.input.MouseButton;
import javafx.stage.Stage;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.loadui.testfx.GuiTest;
import org.testfx.api.FxToolkit;
import org.testfx.framework.junit.ApplicationTest;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;

public class LoginTest extends ApplicationTest{

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
    }

    @Test
    public void testUserNameOnly () {
        System.out.println("Run @Test testUserNameOnly");
        Label label = (Label) GuiTest.find("#userLoginMsg");

        clickOn("#textuserName");
        write("peter");
        clickOn("#BtnLogin");
        assertThat(label.getText(), is("No password specified!"));

    }

    @Test
    public void testPasswordOnly () {
        System.out.println("Run @Test testPasswordOnly");
        Label label = (Label) GuiTest.find("#userLoginMsg");

        clickOn("#textuserPass");
        write("1234");
        clickOn("#BtnLogin");
        assertThat(label.getText(), is("No username specified!"));

    }

    @Test
    public void testBadLogin () {
        System.out.println("Run @Test testBadLogin");
        Label label = (Label) GuiTest.find("#userLoginMsg");

        clickOn("#textuserName");
        write("peter");

        clickOn("#textuserPass");
        write("12345");

        clickOn("#BtnLogin");
        assertThat(label.getText(), is("no matching account"));
    }

    @Test
    public void testCorrectLogin () {
        System.out.println("Run @Test testCorrectLogin");
        Label label = (Label) GuiTest.find("#userLoginMsg");

        clickOn("#textuserName");
        write("peter");

        clickOn("#textuserPass");
        write("1234");

        clickOn("#BtnLogin");
        assertThat(label.getText(), is("welcome peter"));
    }

    @After
    public void tearDown () throws Exception {
        System.out.println("Run @After");
        FxToolkit.hideStage();
        release(new KeyCode[]{});
        release(new MouseButton[]{});
    }


}