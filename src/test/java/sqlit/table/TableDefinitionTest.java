package sqlit.table;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class TableDefinitionTest {

    @Test
    void should_parse_table_definition_from_sql() {
        TableDefinition tableDefinition = TableDefinition.from("CREATE TABLE Users ( PersonID int, LastName varchar(255) )");
        assertThat(tableDefinition.getTableName()).isEqualTo("Users");
    }
}