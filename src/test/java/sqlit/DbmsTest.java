package sqlit;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import sqlit.table.Column;
import sqlit.table.TableDefinition;

import java.io.File;

import static org.assertj.core.api.Assertions.assertThat;

class DbmsTest {
    @BeforeEach
    void setUp() {
        deleteDatabaseFile();
    }

    private void deleteDatabaseFile() {
        new File("./" + Dbms.DEFAULT_FILE_NAME).delete();
    }

    @Test
    void should_show_table_when_no_table_exist() {
        Dbms dbms = new Dbms();
        String tables = dbms.showTables();
        assertThat(tables).isEqualTo("No table found.");
    }

    @Test
    void should_create_table() {
        Dbms dbms = new Dbms();

        dbms.createTable(getTableDefinition("Users"));

        String tables = dbms.showTables();
        assertThat(tables).isEqualTo("Users");
    }

    @Test
    void should_create_multiple_tables() {
        Dbms dbms = new Dbms();

        dbms.createTable(getTableDefinition("Users"));
        dbms.createTable(getTableDefinition("Orders"));

        String tables = dbms.showTables();
        assertThat(tables).isEqualTo("Users\r\nOrders");
    }

    private TableDefinition getTableDefinition(String tableName) {
        TableDefinition td = new TableDefinition();
        td.setTableName(tableName);
        Column column = new Column();
        column.setName("UserId");
        column.setType("int");
        td.addColumn(column);
        return td;
    }
}