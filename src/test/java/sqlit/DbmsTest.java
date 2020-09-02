package sqlit;

import org.junit.jupiter.api.Test;
import sqlit.table.Column;
import sqlit.table.TableDefinition;

import static org.assertj.core.api.Assertions.assertThat;

class DbmsTest {

    @Test
    void should_create_table() {
        Dbms dbms = new Dbms();

        TableDefinition td = new TableDefinition();
        td.setTableName("Users");
        Column column= new Column();
        column.setName("UserId");
        column.setType("int");
        td.addColumn(column);

        dbms.createTable(td);

        String tables = dbms.showTables();
        assertThat(tables).isEqualTo("Users");
    }
}