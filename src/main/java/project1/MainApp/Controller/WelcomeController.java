package project1.MainApp.Controller;

import javafx.event.ActionEvent;
import project1.MainApp.Main;

public class WelcomeController {

	private Main mainApp;
	
	public void setMainApp(Main mainApp) {
		this.mainApp = mainApp;
    }
	
	public void switchToDashboard(ActionEvent event) {
		mainApp.switchToDashboard();
	}

	public void switchToStream(ActionEvent event) {
		mainApp.switchToStream();
	}

	public void switchToExplorer(ActionEvent event) {
		mainApp.switchToExplorer("", "");
	}
	
}
