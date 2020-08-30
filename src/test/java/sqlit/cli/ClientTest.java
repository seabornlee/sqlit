package sqlit.cli;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import sqlit.runner.SqlitRunner;

import java.io.IOException;
import java.net.ConnectException;

import static org.assertj.core.api.Assertions.assertThat;

public class ClientTest {
    @Test
    void should_connect_to_running_instance() throws ConnectException {
        runSqlitServer();
        Client client = new Client("localhost", 3000);

        client.connect();

        assertThat(client.isConnected()).isTrue();
    }

    private void runSqlitServer() {
        new Thread(() -> {
            SqlitRunner runner = new SqlitRunner();
            runner.run();
        }).start();
        try {
            Thread.sleep(10);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    @Test
    void should_show_tables() throws IOException {
        runSqlitServer();
        Client client = new Client("localhost", 3000);
        client.connect();

        String result = client.exec("show tables;");

        assertThat(result).isEqualTo("No table found.");
    }

    @Test
    void should_got_exception_when_instance_not_running() {
        Client client = new Client("localhost", 3000);
        ConnectException exception = Assertions.assertThrows(ConnectException.class, client::connect);

        assertThat(exception.getMessage()).isEqualTo("Cannot connect to [localhost:3000]. Probably server not running.");
        assertThat(client.isConnected()).isFalse();
    }
}
