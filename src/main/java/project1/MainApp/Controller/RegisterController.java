package project1.MainApp.Controller;

import java.io.File;
import java.net.URL;
import java.util.ResourceBundle;

import com.google.gson.JsonObject;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.TextField;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import project1.MainApp.Main;
import project1.MainApp.Utils.Config;

public class RegisterController implements Initializable{

    private Main mainApp;

    @FXML
    TextField profileName;

    @FXML
    TextField password;

    @FXML
    TextField apacheLogPath;

    @FXML
    TextField modsecLogPath;

    public void setMainApp(Main main) {
        this.mainApp = main;
    }

    public void apacheBrowse(ActionEvent event) {
        apacheLogPath.setText("");
        Stage stage = (Stage)((Node) event.getSource()).getScene().getWindow();
		FileChooser filechooser = new FileChooser();
		filechooser.setTitle("Select the log source");

        File resourceDir = new File("src/main/resources/project1/MainApp/LogSamples");
		filechooser.setInitialDirectory(resourceDir);
        java.io.File selectedFile = filechooser.showOpenDialog(stage);

        if (selectedFile != null) {
            apacheLogPath.setText(selectedFile.getAbsolutePath());
        }
    }

    public void modsecBrowse(ActionEvent event) {
        modsecLogPath.setText("");
        Stage stage = (Stage)((Node) event.getSource()).getScene().getWindow();
		FileChooser filechooser = new FileChooser();
		filechooser.setTitle("Select the log source");

        File resourceDir = new File("src/main/resources/project1/MainApp/LogSamples");
		filechooser.setInitialDirectory(resourceDir);
        java.io.File selectedFile = filechooser.showOpenDialog(stage);

        if (selectedFile != null) {
            modsecLogPath.setText(selectedFile.getAbsolutePath());
        }
    }

    public void backToLogin(ActionEvent event) {
        mainApp.switchToLogin();
    }

    public void submit(ActionEvent event) {
        createNewProfile();
        mainApp.switchToWelcome();
    }
    
    private void createNewProfile() {
        JsonObject newProfile = new JsonObject();
        newProfile.addProperty("profileName", profileName.getText());
        newProfile.addProperty("password", password.getText());
        newProfile.addProperty("apacheLogPath", apacheLogPath.getText());
        newProfile.addProperty("modsecLogPath", modsecLogPath.getText());
        Config.createNewProfile(newProfile);
    }

    @Override
    public void initialize(URL arg0, ResourceBundle arg1) {
        // TODO Auto-generated method stub
        
    }

}
