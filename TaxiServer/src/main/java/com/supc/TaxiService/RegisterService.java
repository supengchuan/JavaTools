package com.supc.TaxiService;

import com.supc.CLSignature.CLParamters;
import com.supc.CLSignature.CLSignature;
import com.supc.CLSignature.SignatureBody;
import com.supc.CLSignature.ZKPFBody;
import com.supc.Entity.AnonymousUser;
import com.supc.Entity.RealNameUser;
import javafx.scene.control.TextArea;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;

public class RegisterService {
    private String host;
    private int port;


    public static void invoke(TextArea textArea) throws IOException {
        System.out.println("textArea.appendText(\"后台监听服务...\\n\");");
        ServerSocket serverSocket = new ServerSocket(10000);
        final Socket socket = serverSocket.accept();

        //CL参数采用单例模式，全局只实例化一次
        CLParamters clParamters = CLParamters.getInstance();
        final CLSignature clSignature = new CLSignature(clParamters.getPairing(), clParamters.getG(), clParamters.getX(),
                clParamters.getY(), clParamters.getAlpha());

        new Thread(
                new Runnable() {
                    public void run() {
                        ObjectInputStream is = null;
                        ObjectOutputStream os = null;

                        try {
                            is = new ObjectInputStream(new BufferedInputStream(socket.getInputStream()));
                            os = new ObjectOutputStream(socket.getOutputStream());

                            Object obj = is.readObject();

                            if (obj instanceof RealNameUser) {
                                RealNameUser user = (RealNameUser) obj;

                                //这里为简单。直接给用户签名了，没有进行数据库的操作
                                SignatureBody signatureBody = clSignature.sign(user.getC());
                                os.writeObject(signatureBody);
                                os.flush();

                            } else if (obj instanceof ZKPFBody) {
                                ZKPFBody zkpfBody = (ZKPFBody) obj;
                                boolean res = clSignature.zkpfVerify(zkpfBody.getSignatureBody(), zkpfBody.getR(), zkpfBody.getVxy());
                                if (res) {
                                    String msg = "anonymous";
                                    os.writeObject(msg);
                                    os.flush();
                                }
                            } else if (obj instanceof AnonymousUser) {
                                AnonymousUser user = (AnonymousUser) obj;
                                String msg = "getAnonymous";
                                os.writeObject(msg);
                                os.flush();
                            } else {
                                throw new ClassNotFoundException("data from user,  this class is not exist!");
                            }
                        } catch (IOException e) {
                            e.printStackTrace();
                        } catch (ClassNotFoundException e) {
                            e.printStackTrace();
                        } finally {
                            try {
                                is.close();
                            } catch (Exception e) {
                            }
                            try {
                                os.close();
                            } catch (Exception e) {
                            }

                            try {
                                socket.close();
                            } catch (Exception e) {
                            }
                        }
                    }
                }
        ).start();
    }

}
