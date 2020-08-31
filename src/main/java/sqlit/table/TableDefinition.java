package sqlit.table;

import java.util.ArrayList;
import java.util.List;

import static java.lang.Integer.parseInt;

public class TableDefinition {
    private String tableName;
    private List<Column> columns = new ArrayList<>();

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
        System.out.println("columnsDefitionStr = " + columnsDefitionStr);
        String[] columnsDefinition = columnsDefitionStr.split(",");
        for (String columnDefinition : columnsDefinition) {
            String[] array = columnDefinition.trim().split(" ");

            Column e = new Column();
            e.setName(array[0]);
            if (array[1].contains("(")) {
                int indexOfLeftBracket = array[1].indexOf("(");
                String type = array[1].substring(0, indexOfLeftBracket);
                e.setType(type);

                int length = parseInt(array[1].substring(indexOfLeftBracket + 1, array[1].length() - 1));
                e.setLength(length);
            } else {
                e.setType(array[1]);
            }
            columns.add(e);
        }
        return columns;
    }

    private static String extractFieldsDefinition(String statement) {
        return statement.substring(statement.indexOf("(") + 1, statement.length() - 1).trim();
    }

    private void setTableName(String tableName) {
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
}
