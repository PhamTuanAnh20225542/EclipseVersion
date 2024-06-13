package project1.MainApp.Controller;

import java.net.URL;
import java.util.ResourceBundle;
import java.util.concurrent.Executors;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableView;
import project1.MainApp.Log.LogEntry.AccessLog;
import project1.MainApp.Main;
import project1.MainApp.Utils.StreamLogTable;


public class StreamController implements Initializable  {
	private int clickCnt = 0;
	private Main mainApp;
	
	@FXML
	Button streamButton;

	@FXML
	ComboBox<String> timeInterval;

	@FXML
	TableView<AccessLog> streamTable;
	
	public void setMainApp(Main mainApp) {
		this.mainApp = mainApp;
    }
	
	public void switchToWelcome(ActionEvent event) {
		mainApp.switchToWelcome();
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

	private void setupStreamButton() {
		streamButton.setOnMouseClicked(e -> {
			if ((!e.isControlDown()) && (e.getClickCount() == 1)) {
				clickCnt += 1;
				if (clickCnt % 2 == 1) {
					StreamLogTable.scheduler = Executors.newSingleThreadScheduledExecutor();
					try {
						StreamLogTable.timeInterval = timeInterval.getSelectionModel().getSelectedItem();
						StreamLogTable.startStreaming(streamTable);
					} catch (Exception e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
					streamButton.setText("Stop");
				}
				else{
					StreamLogTable.stopStreaming();
					streamButton.setText("Start");
				}
			}
		});
	}
  
	private void setupTimeInterval() {
		timeInterval.getItems().addAll("1",
									   "5",
									   "10",
									   "30");
		timeInterval.setValue("5");
	}

	private void setupStreamTable() {
		StreamLogTable.createTable(streamTable);
		try {
			StreamLogTable.setPointer();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void trigger() {
		setupStreamTable();
		setupStreamButton();
	}
	
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		setupTimeInterval();
	}
}
