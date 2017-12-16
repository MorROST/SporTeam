package com.yonimor.sporteam.sporteam;

import android.os.AsyncTask;
import android.util.Log;
import android.widget.Button;
import android.widget.TextView;

import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.net.InetSocketAddress;
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



    public ConnectionUtil() throws Exception {

        //!!!!!!!!!!!!!!IP Must Change To NetBeans Machine IP AND NOT 127.0.0.1
        clientSocket = new Socket();

        //clientSocket.connect(new InetSocketAddress("10.0.0.32", 30545),5000);
        clientSocket.connect(new InetSocketAddress("192.168.0.104", 30545),5000);
        output = clientSocket.getOutputStream();
        input = clientSocket.getInputStream();
        oos = new ObjectOutputStream(output);
        ois = new ObjectInputStream(input);
    }

    public String LogIn(String email, String password) {
        ConnectionData requestCD = new ConnectionData();
        requestCD.setRequestCode(ConnectionData.LOGIN);
        requestCD.setEmail(email);
        requestCD.setPassword(password);

        AsyncClassString i = new AsyncClassString(requestCD);
        try {
            String a = i.execute().get();
            return a;
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
        return "";
    }




    public ArrayList GetAllGames(int lastGame) {
        ConnectionData requestCD = new ConnectionData();
        requestCD.setRequestCode(ConnectionData.ALLGAMES);
        requestCD.setLastGameAtClient(lastGame);

        AsyncClassArrayList i = new AsyncClassArrayList(requestCD);
        //ArrayList a = new ArrayList();
        try {
            ArrayList a = i.execute().get();
            if(a != null) {
                return a;
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public int Register(User us) {

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

    public int InsertGame(Game g) {

        ConnectionData requestCD = new ConnectionData();
        requestCD.setRequestCode(ConnectionData.INSERTGAME);
        requestCD.setGame(g);
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
    class AsyncClassInt extends AsyncTask<Void, Void, Integer> {
        ConnectionData requestCD;
        ConnectionData responseCD = new ConnectionData();

        AsyncClassInt(ConnectionData requestCD) {
            this.requestCD = requestCD;
        }

        @Override
        protected Integer doInBackground(Void... params) {

            try {
                oos.writeObject(requestCD);
                responseCD = (ConnectionData) ois.readObject();
            } catch (IOException e) {
                Log.e("IOException", "IOException");
                return ConnectionData.SOMTHING_WRONG;
            } catch (ClassNotFoundException e) {
                Log.e("ClassNotFoundException", "ClassNotFoundException");
            } catch (Exception e) {
                Log.e("Exception", "Exception ");
                e.printStackTrace();
            }
            return responseCD.getWorked();
        }

        @Override
        protected void onPostExecute(Integer integer) {
            super.onPostExecute(integer);
        }
    }


    ////////////////Make sure if the array return int array so the first input isn't 3 (AKA ConnectionData.SOMTHING_WRONG)
    class AsyncClassArrayList extends AsyncTask<Void, Void, ArrayList> {
        ConnectionData requestCD;
        ConnectionData responseCD = new ConnectionData();

        AsyncClassArrayList(ConnectionData requestCD) {
            this.requestCD = requestCD;
        }

        @Override
        protected ArrayList doInBackground(Void... params) {

            try {
                oos.writeObject(requestCD);
                responseCD = (ConnectionData) ois.readObject();
            } catch (IOException e) {
                e.printStackTrace();
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            } catch (Exception e) {
                e.printStackTrace();
            }
            return responseCD.getArrayList();
        }

        @Override
        protected void onPostExecute(ArrayList arrayList) {
            super.onPostExecute(arrayList);
        }
    }

    class AsyncClassString extends AsyncTask<Void, Void, String> {
        ConnectionData requestCD;
        ConnectionData responseCD = new ConnectionData();

        AsyncClassString(ConnectionData requestCD) {
            this.requestCD = requestCD;
        }

        @Override
        protected String doInBackground(Void... params) {

            try {
                oos.writeObject(requestCD);
                responseCD = (ConnectionData) ois.readObject();
            } catch (IOException e) {
                Log.e("IOException", "IOException");
                return String.valueOf(ConnectionData.SOMTHING_WRONG);
            } catch (ClassNotFoundException e) {
                Log.e("ClassNotFoundException", "ClassNotFoundException");
            } catch (Exception e) {
                Log.e("Exception", "Exception ");
                e.printStackTrace();
            }
            return responseCD.getName();
        }

        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);
        }
    }

}