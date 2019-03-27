package com.supc.Server;

import com.supc.User.User;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Server {
    private static final Logger logger = Logger.getLogger(Server.class.getName());

    public static void main(String[] args) throws Exception {
        ServerSocket serverSocket = new ServerSocket(10000);

        while (true) {
            Socket socket = serverSocket.accept();
            invoke(socket);
        }
    }

    private static void invoke(final Socket socket) throws IOException {
        new Thread(
                new Runnable() {
                    @Override
                    public void run() {
                        ObjectInputStream is = null;
                        ObjectOutputStream os = null;
                        try {
                            is = new ObjectInputStream(new BufferedInputStream(socket.getInputStream()));
                            os = new ObjectOutputStream(socket.getOutputStream());

                            Object obj = is.readObject();
                            User user = (User) obj;
                            System.out.println("user:" + user.getName() + "/" + user.getPassword());
                            user.setName(user.getName() + "_new");
                            user.setPassword(user.getPassword() + "_new");

                            os.writeObject(user);
                            os.flush();
                        } catch (IOException e) {
                            logger.log(Level.SEVERE, null, e);
                        } catch (ClassNotFoundException e) {
                            logger.log(Level.SEVERE, null, e);
                        } finally {
                            try {
                                is.close();
                            } catch (Exception ex) {
                            }
                            try {
                                os.close();
                            } catch (Exception ex) {
                            }
                            try {
                                socket.close();
                            } catch (Exception ex) {
                            }
                        }
                    }
                })
                .start();
    }
}
