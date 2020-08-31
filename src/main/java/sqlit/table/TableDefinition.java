package sqlit.table;

public class TableDefinition {
    private String tableName;

    public static TableDefinition from(String statement) {
        TableDefinition tableDefinition = new TableDefinition();
        tableDefinition.setTableName(parseTableName(statement));
        return tableDefinition;
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
}
