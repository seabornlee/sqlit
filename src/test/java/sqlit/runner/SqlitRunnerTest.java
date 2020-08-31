package sqlit.runner;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class SqlitRunnerTest {

    @Test
    void should_throw_exception_when_shutdown_without_before_running_an_instance() {
        SqlitRunner sqlitRunner = new SqlitRunner();
        assertThat(sqlitRunner.shutdown()).isFalse();
    }
}
