package sqlit.table;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class TableDefinitionTest {

    @Test
    void should_parse_table_definition_from_sql() {
        TableDefinition tableDefinition = TableDefinition.from("CREATE TABLE Users ( PersonID int, LastName varchar(255) )");
        assertThat(tableDefinition.getTableName()).isEqualTo("Users");
        assertThat(tableDefinition.getColumnsCount()).isEqualTo(2);
        assertThat(tableDefinition.getColumns().get(0).getName()).isEqualTo("PersonID");
        assertThat(tableDefinition.getColumns().get(0).getType()).isEqualTo(Column.Type.INT);
        assertThat(tableDefinition.getColumns().get(1).getName()).isEqualTo("LastName");
        assertThat(tableDefinition.getColumns().get(1).getType()).isEqualTo(Column.Type.VARCHAR);
        assertThat(tableDefinition.getColumns().get(1).getLength()).isEqualTo(255);
    }
}