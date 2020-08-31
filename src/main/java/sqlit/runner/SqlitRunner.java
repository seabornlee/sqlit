package sqlit.runner;

import sqlit.ThreadSocket;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

public class SqlitRunner {

    private ServerSocket serverSocket;

    public void run() {
        try {
            serverSocket = new ServerSocket(3000);
            System.out.println("Server started.");
            while (true) {
                Socket socket = serverSocket.accept();
                ThreadSocket threadSocket = new ThreadSocket(socket);
                threadSocket.start();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public boolean shutdown() {
        if (serverSocket == null) {
            return false;
        }

        try {
            serverSocket.close();
            return true;
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
    }
}
