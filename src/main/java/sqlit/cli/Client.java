package sqlit.cli;

import java.io.*;
import java.net.ConnectException;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.nio.Buffer;

public class Client {
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
            e.printStackTrace();
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
