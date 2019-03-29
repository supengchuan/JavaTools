package com.supc.TaxiService;

import com.supc.CLSignature.CLSignature;
import com.supc.CLSignature.SignatureBody;
import com.supc.CLSignature.ZKPFBody;
import com.supc.Entity.AnonymousUser;
import com.supc.Entity.RealNameUser;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

public class RegisterService {
    private String host;
    private int port;


    public static void invoke(final Socket socket, final CLSignature clSignature) throws IOException {
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
