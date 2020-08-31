package sqlit.executor;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class SqlExecutorTest {

    @Test
    void should_show_tables() {
        SqlExecutor sqlExecutor = new SqlExecutor();
        String result = sqlExecutor.execute("show tables;");
        assertThat(result).isEqualTo("No table found.");
    }

    @Test
    void should_create_table() {
        SqlExecutor sqlExecutor = new SqlExecutor();
        String result = sqlExecutor.execute("CREATE TABLE Users ( PersonID int, LastName varchar(255) )");
        assertThat(result).isEqualTo("Table 'Users' created.");
    }
}
