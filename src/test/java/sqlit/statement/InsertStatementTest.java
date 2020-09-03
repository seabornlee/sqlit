package sqlit.statement;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class InsertStatementTest {

    @Test
    void should_parse_sql() {
        InsertStatement expectedStatement = new InsertStatement();
        expectedStatement.setTableName("Users");
        expectedStatement.addColumn("PersonID", "1");
        expectedStatement.addColumn("LastName", "Seaborn Lee");

        InsertStatement statement = InsertStatement.from("insert into Users (PersonID, LastName) values (1, 'Seaborn Lee');");

        assertThat(statement).isEqualTo(expectedStatement);
    }
}