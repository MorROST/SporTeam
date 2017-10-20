package com.yonimor.sporteam.sporteam;

import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.net.Socket;
import com.yonimor.sporteam.sporteam.com.data.*;

/**
 * Created by TheYoni on 20/10/2017.
 */

public class ConnectionUtil {
    Socket clientSocket;
    InputStream input;
    OutputStream output;
    ObjectOutputStream oos;
    ObjectInputStream ois;

    public ConnectionUtil() throws IOException {

        clientSocket  = new Socket("127.0.0.1", 54321);
        input = clientSocket.getInputStream();
        output = clientSocket.getOutputStream();
        oos = new ObjectOutputStream(output);
        ois = new ObjectInputStream(input);
    }

    public boolean LogIn(String email, String password)
    {
        ConnectionData requestCD = new ConnectionData();
        ConnectionData responseCD = new ConnectionData();
        requestCD.setRequestCode(ConnectionData.LOGIN);
        requestCD.setEmail(email);
        requestCD.setPassword(password);

        try {
            oos.writeObject(requestCD);
            responseCD = (ConnectionData)ois.readObject();
        } catch (Exception ex) {
            System.out.println("connection problems... faild to write objecton LogIn\n" + ex.getMessage());
        }

        return  responseCD.isWorked();
    }

    public boolean Register(User us)
    {
        ConnectionData requestCD = new ConnectionData();
        ConnectionData responseCD = new ConnectionData();
        requestCD.setRequestCode(ConnectionData.LOGIN);
        requestCD.setUser(us);

        try {
            oos.writeObject(requestCD);
            responseCD = (ConnectionData)ois.readObject();
        } catch (Exception ex) {
            System.out.println("connection problems... faild to write objecton Register\n" + ex.getMessage());
        }

        return  responseCD.isWorked();
    }
}
