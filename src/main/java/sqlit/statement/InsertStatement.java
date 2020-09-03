package sqlit.statement;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

import static java.util.Arrays.stream;
import static java.util.stream.Collectors.toList;

public class InsertStatement {
    private String tableName;

    public static InsertStatement from(String statement) {
        InsertStatement insertStatement = new InsertStatement();
        insertStatement.setTableName(extractTableName(statement));
        List<String> columns = parseColumns(statement);
        List<String> values = parseValues(statement);

        for (int i = 0; i < columns.size(); i++) {
            insertStatement.addColumn(columns.get(i), values.get(i));
        }
        return insertStatement;
    }

    private static List<String> parseValues(String statement) {
        String substring = statement.substring(statement.lastIndexOf("(") + 1, statement.lastIndexOf(")"));
        return stream(substring.split(",")).map(s -> s.trim().replace("'", "")).collect(toList());
    }

    private static List<String> parseColumns(String statement) {
        String substring = statement.substring(statement.indexOf("(") + 1, statement.indexOf(")"));
        return stream(substring.split(",")).map(String::trim).collect(toList());
    }

    private static String extractTableName(String statement) {
        return statement.substring("insert into".length(), statement.indexOf("(")).trim();
    }

    public String getTableName() {
        return tableName;
    }

    public void setTableName(String tableName) {
        this.tableName = tableName;
    }

    public Map<String, String> getValues() {
        return values;
    }

    public void setValues(Map<String, String> values) {
        this.values = values;
    }

    private Map<String, String> values = new HashMap<>();

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        InsertStatement that = (InsertStatement) o;
        return Objects.equals(tableName, that.tableName) &&
                Objects.equals(values, that.values);
    }

    @Override
    public int hashCode() {
        return Objects.hash(tableName, values);
    }

    @Override
    public String toString() {
        return "InsertStatement{" +
                "tableName='" + tableName + '\'' +
                ", values=" + values +
                '}';
    }

    public void addColumn(String name, String value) {
        this.values.put(name, value);
    }
}
