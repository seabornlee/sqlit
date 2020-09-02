package sqlit.table;

import java.util.ArrayList;
import java.util.List;

import static java.lang.Integer.parseInt;

public class TableDefinition {
    private String tableName;
    private final List<Column> columns = new ArrayList<>();

    public static TableDefinition from(String statement) {
        TableDefinition tableDefinition = new TableDefinition();
        tableDefinition.setTableName(parseTableName(statement));
        tableDefinition.addColumns(parseColumns(statement));
        return tableDefinition;
    }

    private void addColumns(List<Column> columns) {
        this.columns.addAll(columns);
    }

    private static List<Column> parseColumns(String statement) {
        List<Column> columns = new ArrayList<>();
        String columnsDefitionStr = extractFieldsDefinition(statement);
        for (String columnDefinition : columnsDefitionStr.split(",")) {
            columns.add(getColumn(columnDefinition));
        }
        return columns;
    }

    private static Column getColumn(String columnDefinition) {
        String[] array = columnDefinition.trim().split(" ");

        Column e = new Column();
        e.setName(array[0]);
        if (array[1].contains("(")) {
            e.setType(parseType(array[1]));
            e.setLength(parseInt(parseLength(array[1])));
        } else {
            e.setType(array[1]);
        }
        return e;
    }

    private static String parseLength(String s) {
        return s.substring(s.indexOf("(") + 1, s.length() - 1);
    }

    private static String parseType(String s) {
        return s.substring(0, s.indexOf("("));
    }

    private static String extractFieldsDefinition(String statement) {
        return statement.substring(statement.indexOf("(") + 1, statement.length() - 1).trim();
    }

    public void setTableName(String tableName) {
        this.tableName = tableName;
    }

    private static String parseTableName(String statement) {
        return statement.split("\\s")[2];
    }

    public String getTableName() {
        return tableName;
    }

    public int getColumnsCount() {
        return columns.size();
    }

    public List<Column> getColumns() {
        return columns;
    }

    public void addColumn(Column column) {
        this.columns.add(column);
    }
}
