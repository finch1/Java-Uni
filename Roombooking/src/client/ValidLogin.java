package client;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

public class ValidLogin {
    private TextField nameField;
    private PasswordField passField;
    private Label loginMsg;
    private String name;
    private String pass;

    public ValidLogin(TextField nameField, PasswordField passField, Label loginMsg) {
        this.nameField = nameField;
        this.passField = passField;
        this.loginMsg = loginMsg;
        this.name = nameField.getText();
        this.pass = passField.getText();
    }

    public Boolean nameVerify(){

        if(this.name == null || this.name.isEmpty()){
            nameField.setStyle("-fx-text-box-border: red");
            nameField.setText("");
            loginMsg.setText("No username specified!");
            return false;
        }
        return true;
    }

    public Boolean passVerify(){

        if(this.pass == null || this.pass.isEmpty()){
            passField.setStyle("-fx-text-box-border: red");
            passField.setText("");
            loginMsg.setText("No password specified!");
            return false;
        }
        return true;
    }

    public Credentials packCredentials(){
        return new Credentials(name, SHAPass.get_SHA_1_SecurePassword(pass));
    }

    public String getName() {
        return name;
    }

    public String getPass() {
        return pass;
    }
}
