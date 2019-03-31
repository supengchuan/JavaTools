package com.supc;

import com.supc.controller.StageController;
import javafx.application.Application;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

public class AppMain extends Application {
    static final String mainViewID = "MainViewID";
    static final String mainViewRes = "/view/MainStage.fxml";

    private StageController stageController;

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        stageController = new StageController();

        stageController.setPrimaryStage("primaryStage", primaryStage);
        stageController.loadStage(mainViewID, mainViewRes, StageStyle.UNDECORATED);
        stageController.setStage(mainViewID);
    }
}
