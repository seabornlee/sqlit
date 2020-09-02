package sqlit.runner;

import sqlit.ThreadSocket;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;

public class SqlitRunner {
    private static final Logger logger = Logger.getLogger(SqlitRunner.class.getPackage().getName());

    private ServerSocket serverSocket;

    public void run() {
        try {
            serverSocket = new ServerSocket(3000);
        } catch (IOException e) {
            logger.log(Level.WARNING, e.getMessage());
        }
        new Thread(() -> {
            while (!serverSocket.isClosed()) {
                try {
                    Socket socket = serverSocket.accept();
                    ThreadSocket threadSocket = new ThreadSocket(socket);
                    threadSocket.start();
                } catch (IOException e) {
                    logger.log(Level.WARNING, e.getMessage());
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
            logger.log(Level.WARNING, e.getMessage());
            return false;
        }
    }

    private boolean isRunning() {
        return serverSocket != null;
    }
}
