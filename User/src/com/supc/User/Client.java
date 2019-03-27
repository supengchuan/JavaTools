package com.supc.User;

import java.io.*;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Client {
    private static final Logger logger = Logger.getLogger(Client.class.getName());

    public static void main(String[] args) throws Exception {
        for (int i = 0; i < 100; i++) {
            Socket socket = null;
            ObjectOutputStream os = null;
            ObjectInputStream is = null;

            try {
                socket = new Socket("localhost", 10000);
                os = new ObjectOutputStream(socket.getOutputStream());
                User user = new User("user_" + i, "password_" + i);
                os.writeObject(user);
                os.flush();

                is = new ObjectInputStream(new BufferedInputStream(socket.getInputStream()));
                Object object = is.readObject();
                if (object != null) {
                    user = (User) object;
                    System.out.println("user:" + user.getName() + "/" + user.getPassword());
                }
            } catch (IOException e) {
                logger.log(Level.SEVERE, null, e);
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
}
