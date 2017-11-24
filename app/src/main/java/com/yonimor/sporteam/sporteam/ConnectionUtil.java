package com.yonimor.sporteam.sporteam;

import android.os.AsyncTask;

import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.util.ArrayList;
import java.util.concurrent.ExecutionException;

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

        //!!!!!!!!!!!!!!IP Must Change To NetBeans Machine IP AND NOT 127.0.0.1
        clientSocket  = new Socket("192.168.0.106", 30545); //mor
        //clientSocket  = new Socket("10.0.0.6", 30545);//yoni
        output = clientSocket.getOutputStream();
        input = clientSocket.getInputStream();
        oos = new ObjectOutputStream(output);
        ois = new ObjectInputStream(input);
    }

    public int LogIn(String email, String password)
    {
        ConnectionData requestCD = new ConnectionData();
        requestCD.setRequestCode(ConnectionData.LOGIN);
        requestCD.setEmail(email);
        requestCD.setPassword(password);

        AsyncClassInt i = new AsyncClassInt(requestCD);
        try {
            Integer a = i.execute().get();
            return a;
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
        return 3;
    }





    public ArrayList GetAllGames()
    {
        ConnectionData requestCD = new ConnectionData();
        requestCD.setRequestCode(ConnectionData.ALLGAMES);

        AsyncClassArrayList i = new AsyncClassArrayList(requestCD);
        try {
            ArrayList a = i.execute().get();
            return a;
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
        return null;

    }

    public int Register(User us)
    {
        /*ConnectionData requestCD = new ConnectionData();
        ConnectionData responseCD = new ConnectionData();
        requestCD.setRequestCode(ConnectionData.REGISTER);
        requestCD.setUser(us);


        try {
            oos.writeObject(requestCD);
            responseCD = (ConnectionData)ois.readObject();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        catch (Exception e){
            e.printStackTrace();
        }


        return  responseCD.getWorked();*/

        ConnectionData requestCD = new ConnectionData();
        requestCD.setRequestCode(ConnectionData.REGISTER);
        requestCD.setUser(us);
        AsyncClassInt i = new AsyncClassInt(requestCD);
        try {
            Integer a = i.execute().get();
            return a;
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
        return 3;
    }

///////////////////////AsyncClasses////////////////////////
    class AsyncClassInt extends AsyncTask<Void,Void,Integer>
    {
        ConnectionData requestCD;
        ConnectionData responseCD = new ConnectionData();
        AsyncClassInt(ConnectionData requestCD)
        {
            this.requestCD = requestCD;
        }

        @Override
        protected Integer doInBackground(Void... params) {

            try {
                oos.writeObject(requestCD);
                responseCD = (ConnectionData)ois.readObject();
            } catch (IOException e) {
                e.printStackTrace();
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }
            catch (Exception e){
                e.printStackTrace();
            }
            return responseCD.getWorked();
        }

        @Override
        protected void onPostExecute(Integer integer) {
            super.onPostExecute(integer);
        }
    }

    class AsyncClassArrayList extends AsyncTask<Void,Void,ArrayList>
    {
        ConnectionData requestCD;
        ConnectionData responseCD = new ConnectionData();
        AsyncClassArrayList(ConnectionData requestCD)
        {
            this.requestCD = requestCD;
        }

        @Override
        protected ArrayList doInBackground(Void... params) {

            try {
                oos.writeObject(requestCD);
                responseCD = (ConnectionData)ois.readObject();
            } catch (IOException e) {
                e.printStackTrace();
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }
            catch (Exception e){
                e.printStackTrace();
            }
            return responseCD.getArrayList();
        }

        @Override
        protected void onPostExecute(ArrayList arrayList) {
            super.onPostExecute(arrayList);
        }
    }




}
