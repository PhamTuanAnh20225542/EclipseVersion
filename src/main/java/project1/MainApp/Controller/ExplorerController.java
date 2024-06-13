package project1.MainApp.Controller;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.SingleSelectionModel;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.control.TableView;
import project1.MainApp.Log.LogEntry.AccessLog;
import project1.MainApp.Log.LogEntry.AuditLog;
import project1.MainApp.Main;
import project1.MainApp.Utils.AccessLogTable;
import project1.MainApp.Utils.AuditLogTable;

public class ExplorerController implements Initializable {
	
	private Main mainApp;
	
	@FXML
	TableView<AccessLog> apacheTable;

	@FXML
	TableView<AuditLog> modsecTable;
	
	@FXML
	TabPane tablePane;

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

	private void changeTable(int index) {
		SingleSelectionModel<Tab> selectionModel = tablePane.getSelectionModel();
		selectionModel.select(index);
	}

	public void applyFilter(String typeOfFilter, String typeOfTable) {
		if (typeOfTable.equals("Apache")) {
			if (typeOfFilter.equals("")){
				return;
			}
			changeTable(0);
			AccessLogTable.addData(apacheTable, typeOfFilter);
		}
		else{
			changeTable(1);
			AuditLogTable.addData(modsecTable);
		}
	}

	public void refresh(ActionEvent event) {
		apacheTable.setItems(FXCollections.observableArrayList());
		modsecTable.setItems(FXCollections.observableArrayList());
	}

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		// TODO Auto-generated method stub
		AccessLogTable.createTable(apacheTable);
		AuditLogTable.createTable(modsecTable);
	}
}
