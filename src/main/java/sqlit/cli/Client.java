package sqlit.cli;

import java.net.ConnectException;

public class Client {
    public Client(String host, int port) {
    }

    public void connect() throws ConnectException {
        throw new ConnectException("Cannot connect to [localhost:3000]. Probably server not running.");
    }

    public boolean isConnected() {
        return false;
    }
}
