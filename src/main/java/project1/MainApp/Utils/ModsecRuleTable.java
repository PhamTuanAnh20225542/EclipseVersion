package project1.MainApp.Utils;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import project1.MainApp.Log.LogEntry.Rule;
import project1.MainApp.Log.Parser.ParseAuditLog;

public class ModsecRuleTable {

    @SuppressWarnings("unchecked")
    public static void createTable(TableView<Rule> ruleTable) {
        // Declare each cols
        TableColumn<Rule, String> ruleColumn = new TableColumn<>("Triggered Rule");
        TableColumn<Rule, Integer> freColumn = new TableColumn<>("Number of Requests");

        // Set cell 
        ruleColumn.setCellValueFactory(cellData -> cellData.getValue().triggeredRuleProperty());
        freColumn.setCellValueFactory(cellData -> cellData.getValue().numOfRequestsProperty().asObject());

        ruleTable.getColumns().addAll(ruleColumn, freColumn);
    }

    public static void addData(TableView<Rule> ruleTable, String dateTarget) {
        String filePath = Config.modsecLogPath;
        ObservableList<Rule> rules = FXCollections.observableArrayList();
        HashMap<String, Integer> hm  = new HashMap<>();
        try {
            ParseAuditLog parser = new ParseAuditLog();
            FileReader fileReader = new FileReader(filePath);
            BufferedReader bufferedReader = new BufferedReader(fileReader);
            String line;
            while ((line = bufferedReader.readLine()) != null) {
                if (parser.getDate(line).equals(dateTarget)) {
                    String rule = parser.getTriggeredRule(line);
                    if (hm.containsKey(rule)) {
                        hm.put(rule, hm.get(rule) + 1);
                    }
                    else {
                        hm.put(rule, 1);
                    }
                }
            }
            bufferedReader.close();
        }
        catch (IOException e) {
            e.printStackTrace();
        }

        for(String rule : hm.keySet()) {
            rules.add(new Rule(rule, hm.get(rule)));
        }

        ruleTable.setItems(rules);
    }
}

