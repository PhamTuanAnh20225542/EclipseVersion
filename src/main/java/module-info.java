module com.example.weblogservice {
    requires javafx.controls;
    requires javafx.fxml;
    requires com.maxmind.geoip2;
    requires com.google.gson;


    opens project1.MainApp to javafx.fxml;
    exports project1.MainApp;
    exports project1.MainApp.Controller;
    opens project1.MainApp.Controller to javafx.fxml;
}