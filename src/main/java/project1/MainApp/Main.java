package project1.MainApp;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import project1.MainApp.Controller.*;

import java.io.FileInputStream;
import java.net.URL;


public class Main extends Application {

    private Stage primaryStage;
    private Scene login, register, welcome, stream, explorer, dashboard;
    private RegisterController registerController;
    private LoginController loginController;
    private WelcomeController welcomeController;
    private StreamController streamController;
    private DashboardController dashboardController;
    private ExplorerController explorerController;

    @Override
    public void start(Stage primaryStage) {
        String currentPath = System.getProperty("user.dir");
        try {
            this.primaryStage = primaryStage;
            primaryStage.setTitle("Service Log Analyze");
            Image appicon = new Image(new FileInputStream("src/main/resources/project1/MainApp/Image/detective.png"));
            primaryStage.getIcons().add(appicon);

            //load Register
            FXMLLoader registerLoader = new FXMLLoader(getClass().getResource("UI/Register.fxml"));
            Parent registerParent = registerLoader.load();
            registerController = registerLoader.getController();
            registerController.setMainApp(this);
            register = new Scene(registerParent);

            //load Login
            FXMLLoader loginLoader = new FXMLLoader(getClass().getResource("UI/Login.fxml"));
            Parent loginParen = loginLoader.load();
            loginController = loginLoader.getController();
            loginController.setMainApp(this);
            login = new Scene(loginParen);

            //load welcome
            FXMLLoader welcomeLoader = new FXMLLoader(getClass().getResource("UI/Welcome.fxml"));
            Parent welcomeParent = welcomeLoader.load();
            welcomeController = welcomeLoader.getController();
            welcomeController.setMainApp(this);
            welcome = new Scene(welcomeParent);

            //load dashboard
            FXMLLoader dashboardLoader = new FXMLLoader(getClass().getResource("UI/Dashboard.fxml"));
            Parent dashboardParent = dashboardLoader.load();
            dashboardController = dashboardLoader.getController();
            dashboardController.setMainApp(this);
            dashboard = new Scene(dashboardParent);

            //load stream
            FXMLLoader streamLoader = new FXMLLoader(getClass().getResource("UI/Stream.fxml"));
            Parent streamParent = streamLoader.load();
            streamController = streamLoader.getController();
            streamController.setMainApp(this);
            stream = new Scene(streamParent);

            //load explorer
            FXMLLoader explorerLoader = new FXMLLoader(getClass().getResource("UI/Explorer.fxml"));
            Parent explorerParent = explorerLoader.load();
            explorerController = explorerLoader.getController();
            explorerController.setMainApp(this);
            explorer = new Scene(explorerParent);

            this.primaryStage.setScene(login);
            this.primaryStage.show();

        } catch(Exception e) {
            e.printStackTrace();
        }

    }

    public void switchToWelcome() {
        dashboardController.trigger();
        streamController.trigger();
        primaryStage.setScene(welcome);
        primaryStage.setWidth(900);
        primaryStage.setHeight(600);
    }

    public void switchToDashboard() {
        double stageWidth = primaryStage.getWidth();
        double stageHeight = primaryStage.getHeight();
        primaryStage.setScene(dashboard);
        primaryStage.setWidth(stageWidth);
        primaryStage.setHeight(stageHeight);
    }

    public void switchToStream() {
        double stageWidth = primaryStage.getWidth();
        double stageHeight = primaryStage.getHeight();
        primaryStage.setScene(stream);
        primaryStage.setWidth(stageWidth);
        primaryStage.setHeight(stageHeight);
    }

    public void switchToLogin() {
        double stageWidth = primaryStage.getWidth();
        double stageHeight = primaryStage.getHeight();
        primaryStage.setScene(login);
        primaryStage.setWidth(stageWidth);
        primaryStage.setHeight(stageHeight);
    }

    public void switchToRegister() {
        double stageWidth = primaryStage.getWidth();
        double stageHeight = primaryStage.getHeight();
        primaryStage.setScene(register);
        primaryStage.setWidth(stageWidth);
        primaryStage.setHeight(stageHeight);
    }

    public void switchToExplorer(String typeOfFilter, String typeOfTable) {
        double stageWidth = primaryStage.getWidth();
        double stageHeight = primaryStage.getHeight();
        explorerController.applyFilter(typeOfFilter, typeOfTable);
        primaryStage.setScene(explorer);
        primaryStage.setWidth(stageWidth);
        primaryStage.setHeight(stageHeight);
    }

    public static void main(String[] args) {
        launch(args);
    }

}

