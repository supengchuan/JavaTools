package com.supc.controller;

import com.supc.CLSignature.CLParamters;
import com.supc.CLSignature.CLSignature;
import com.supc.TaxiService.MyService;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TextArea;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.URL;
import java.util.ResourceBundle;

public class MainStageController implements ControlledStage, Initializable {
    StageController stageController;
    private static final String mainViewID = "MainViewID";

    @FXML
    private TextArea serviceText;

    public void setStageController(StageController stageController) {
        this.stageController = stageController;
    }

    public void initialize(URL location, ResourceBundle resources) {
        new Thread(() -> {
            try {
                final ServerSocket serverSocket = new ServerSocket(10000);
                Platform.runLater(() -> {
                    serviceText.appendText("现在开始服务，等待用户连接........\n");
                });

                CLParamters paramters = CLParamters.getInstance();
                //System.out.println(paramters.getG().toBytes());
                CLSignature cl = new CLSignature(paramters.getPairing(), paramters.getG(), paramters.getX(), paramters.getY(), paramters.getAlpha());


                while (true) {
                    final Socket socket = serverSocket.accept();
                    new Thread(new MyService(socket, cl, paramters, serviceText)).start();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }).start();
    }

    @FXML
    public void eventQuit() {
        stageController.getStage(mainViewID).close();
    }


}


