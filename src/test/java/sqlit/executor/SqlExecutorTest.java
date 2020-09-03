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
    void should_drop_table() {
        SqlExecutor sqlExecutor = new SqlExecutor();
        String result = sqlExecutor.execute("drop TABLE Users;");
        assertThat(result).isEqualTo("Table 'Users' dropped.");
    }

    @Test
    void should_insert_record() {
        SqlExecutor sqlExecutor = new SqlExecutor();
        sqlExecutor.execute("CREATE TABLE Users ( PersonID int, LastName varchar(255) )");

        String result = sqlExecutor.execute("insert into Users (PersonID, LastName) values (1, 'Seaborn Lee');");

        assertThat(result).isEqualTo("1 row inserted.");
    }

    @Test
    void should_create_table() {
        SqlExecutor sqlExecutor = new SqlExecutor();
        String result = sqlExecutor.execute("CREATE TABLE Users ( PersonID int, LastName varchar(255) )");
        assertThat(result).isEqualTo("Table 'Users' created.");
    }
}
