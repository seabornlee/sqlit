package sqlit.runner;

import sqlit.ThreadSocket;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

public class SqlitRunner {
    public void run() {
        try {
            ServerSocket serverSocket = new ServerSocket(3000);
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
}
