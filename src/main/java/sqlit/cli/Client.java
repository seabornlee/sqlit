package sqlit.cli;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ConnectException;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Client {
    private static final Logger logger = Logger.getLogger(Client.class.getPackage().getName());

    private final String host;
    private final int port;
    private boolean isConnected;
    private Socket socket;

    public Client(String host, int port) {
        this.host = host;
        this.port = port;
    }

    public void connect() throws ConnectException {
        socket = new Socket();
        try {
            socket.connect(new InetSocketAddress(host, port));
            isConnected = true;
        } catch (IOException e) {
            logger.log(Level.WARNING, e.getMessage());
            throw new ConnectException("Cannot connect to [localhost:3000]. Probably server not running.");
        }
    }

    public boolean isConnected() {
        return isConnected;
    }

    public String exec(String sql) throws IOException {
        PrintWriter writer = new PrintWriter(socket.getOutputStream());
        writer.write(sql);
        writer.write('\n');
        writer.flush();

        BufferedReader reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        return reader.readLine();
    }
}
