package sqlit.runner;

import org.junit.jupiter.api.Test;
import sqlit.cli.Client;

import java.net.ConnectException;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

class SqlitRunnerTest {

    @Test
    void should_not_shutdown_when_server_not_running() {
        SqlitRunner sqlitRunner = new SqlitRunner();
        assertThat(sqlitRunner.shutdown()).isFalse();
    }

    @Test
    void should_shutdown_running_server() {
        SqlitRunner sqlitRunner = new SqlitRunner();
        sqlitRunner.run();

        boolean isSuccessfully = sqlitRunner.shutdown();

        assertThat(isSuccessfully).isTrue();
        Client client = new Client("localhost", 3000);
        assertThrows(ConnectException.class, client::connect);
    }

    @Test
    void should_run_server_and_accept_client() throws ConnectException {
        SqlitRunner sqlitRunner = new SqlitRunner();
        sqlitRunner.run();
        Client client = new Client("localhost", 3000);

        client.connect();

        assertThat(client.isConnected()).isTrue();
        sqlitRunner.shutdown();
    }
}
