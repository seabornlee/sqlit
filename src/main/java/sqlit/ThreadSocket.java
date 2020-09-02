package sqlit;

import sqlit.executor.SqlExecutor;

import java.io.*;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ThreadSocket extends Thread {
    private static final Logger logger = Logger.getLogger(ThreadSocket.class.getPackage().getName());

    private final Socket socket;

    public ThreadSocket(Socket socket) {
        this.socket = socket;
    }

    @Override
    public void run() {
        try {
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            String statement = bufferedReader.readLine();

            String result = new SqlExecutor().execute(statement);

            OutputStream outputStream = socket.getOutputStream();
            PrintWriter printWriter = new PrintWriter(outputStream);
            printWriter.write(result);
            printWriter.write('\n');
            printWriter.flush();
        } catch (IOException e) {
            logger.log(Level.WARNING, e.getMessage());
        }
    }
}
