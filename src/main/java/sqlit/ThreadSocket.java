package sqlit;

import java.io.*;
import java.net.Socket;

public class ThreadSocket extends Thread {
    private Socket socket;

    public ThreadSocket(Socket socket) {
        this.socket = socket;
    }

    @Override
    public void run() {
        System.out.println("Connected.");
        try {
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            String statement = bufferedReader.readLine();
            System.out.println("statement = " + statement);
            OutputStream outputStream = socket.getOutputStream();
            PrintWriter printWriter = new PrintWriter(outputStream);
            printWriter.write("No table found.");
            printWriter.write('\n');
            printWriter.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
