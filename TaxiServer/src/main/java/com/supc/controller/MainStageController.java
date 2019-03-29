package com.supc.controller;

import com.supc.CLSignature.CLSignature;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;

import java.net.URL;
import java.util.ResourceBundle;

public class MainStageController implements ControlledStage, Initializable {
    StageController stageController;
    private static final String mainViewID = "MainViewID";

    @FXML
    private Button registerServiceButton;
    @FXML
    private TextArea registerServiceText;

    @FXML
    private Button taxiServiceButton;
    @FXML
    private TextArea taxiServiceText;
    @FXML
    private Button rechargeServiceButton;
    @FXML
    private TextArea rechargeServiceText;

    private CLSignature signature = null;


    public void setStageController(StageController stageController) {
        this.stageController = stageController;
    }

    public void initialize(URL location, ResourceBundle resources) {
    }

    public void eventQuit() {
        stageController.getStage(mainViewID).close();
    }

    //响应注册服务
    public void startRegisterService() {
        registerServiceText.setWrapText(true);
        registerServiceText.appendText("启动注册服务\n");


    }

    public void startTaxiService() {
        taxiServiceText.setWrapText(true);
        taxiServiceText.appendText("启动打车服务");
    }

    public void startRechargeService() {
        rechargeServiceText.setWrapText(true);
        rechargeServiceText.appendText("启动充值服务");
    }
}
