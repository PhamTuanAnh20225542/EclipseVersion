package project1.MainApp.Controller;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import project1.MainApp.Main;
import project1.MainApp.Utils.Config;

public class LoginController implements Initializable{

    private Main mainApp;

    @FXML
    ComboBox<String> profileCb;

    @FXML
    TextField password;

    public void addNewProfile(ActionEvent event) {
        mainApp.switchToRegister();
    }

    public void start(ActionEvent event) {
        if (profileCb.getItems().isEmpty() || password.getText().isEmpty()) {
            return;
        }
        if (Config.checkProfile(password.getText(), profileCb.getSelectionModel().getSelectedItem())) {
            mainApp.switchToWelcome();
        }
    }

    public void setMainApp(Main main) {
        this.mainApp = main;
    }

    private void setupProfileCb() {
        Config.loadProfile(profileCb);
    }

    @Override
    public void initialize(URL arg0, ResourceBundle arg1) {
        // TODO Auto-generated method stub
        Config.profiles = new ArrayList<>();
        setupProfileCb();
    }

}
