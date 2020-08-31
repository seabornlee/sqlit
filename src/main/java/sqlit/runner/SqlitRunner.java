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
        } catch (IOException e) {
            e.printStackTrace();
        }
        new Thread(() -> {
            while (!serverSocket.isClosed()) {
                try {
                    Socket socket = serverSocket.accept();
                    ThreadSocket threadSocket = new ThreadSocket(socket);
                    threadSocket.start();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }).start();
    }

    public boolean shutdown() {
        if (!isRunning()) {
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

    private boolean isRunning() {
        return serverSocket != null;
    }
}
