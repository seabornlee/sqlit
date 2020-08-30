package sqlit.cli;

import java.io.IOException;
import java.net.ConnectException;
import java.net.InetSocketAddress;
import java.net.Socket;

public class Client {
    private final String host;
    private final int port;
    private boolean isConnected;

    public Client(String host, int port) {
        this.host = host;
        this.port = port;
    }

    public void connect() throws ConnectException {
        Socket socket = new Socket();
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
}
