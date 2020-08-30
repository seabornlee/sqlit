package sqlit.runner;

import java.io.IOException;
import java.net.ServerSocket;

public class SqlitRunner {
    public void run() {
        try {
            ServerSocket serverSocket = new ServerSocket(3000);
            serverSocket.accept();
            System.out.println("Server started.");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
