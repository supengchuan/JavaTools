package com.supc.TaxiService;

import com.supc.CLSignature.*;
import com.supc.Entity.Message;
import com.supc.Entity.MessageType;
import com.supc.Entity.RealNameUser;
import javafx.application.Platform;
import javafx.scene.control.TextArea;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

public class MyService implements Runnable, MessageType {

    private final Socket socket;
    private CLParamters clParamters;
    private CLSignature clSignature;
    private TextArea textArea;

    private ObjectInputStream is = null;
    private ObjectOutputStream os = null;

    public MyService(Socket socket, CLSignature clSignature, CLParamters clParamters, TextArea textArea) {
        System.out.println("启动服务。。。。。。");
        this.socket = socket;
        this.clParamters = clParamters;
        this.clSignature = clSignature;
        this.textArea = textArea;
        try {
            is = new ObjectInputStream(new BufferedInputStream(socket.getInputStream()));
            os = new ObjectOutputStream(socket.getOutputStream());
        } catch (IOException e){}

    }

    public void run() {
        //super.run();
        try {
            Message m = (Message) is.readObject();
            System.out.println("从客户端获得数据");
            Message sendBack = null;
            switch (m.getType()) {
                case GET_CL_PK:
                    System.out.println(clSignature.getG());
                    CLPK clpk = clSignature.getPK();
                    sendBack = new Message(GET_CL_PK, clpk);
                    break;
                case REAL_NAME_REG: {
                    RealNameUser user = (RealNameUser) m.getBody();
                    SignatureBody signatureBody = clSignature.sign(user.getC());
                    sendBack = new Message(REAL_NAME_REG, signatureBody);

                    break;
                }
                case ANONY_NAME_REG: {
                    sendBack = new Message(ANONY_NAME_REG, new Boolean(true));
                    break;
                }
                case ZKPF: {
                    ZKPFBody zkpfBody = (ZKPFBody) m.getBody();
                    boolean res = clSignature.zkpfVerify(zkpfBody.getSignatureBody(), zkpfBody.getR(), zkpfBody.getVxy());
                    sendBack = new Message(ZKPF, new Boolean(res));

                    break;
                }
            }
            System.out.println("准备写入数据....");
            System.out.println(sendBack.getType() + sendBack.getBody().getClass().getName());
            os.writeObject(sendBack);
            os.flush();

        } catch (IOException e) {
            Platform.runLater(() -> {
                textArea.appendText("出现异常 " + e.toString() + "\n");
            });
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } finally {

        }
    }


}
