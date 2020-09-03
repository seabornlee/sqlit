package sqlit.executor;

import sqlit.Dbms;
import sqlit.table.TableDefinition;

public class SqlExecutor {
    private Dbms dbms = new Dbms();

    public String execute(String statement) {
        String statementWithoutSemiColon = statement.substring(0, statement.length() - 1);
        if (statement.toLowerCase().startsWith("create table")) {
            TableDefinition tableDefinition = TableDefinition.from(statement);
            boolean isSuccessfully = dbms.createTable(tableDefinition);
            if (isSuccessfully) {
                return "Table '" + tableDefinition.getTableName() + "' created.";
            }
        }

        if (statement.toLowerCase().startsWith("drop table")) {
            String tableName = extractTableName(statementWithoutSemiColon);
            dbms.dropTable(tableName);
            return "Table '" + tableName + "' dropped.";
        }
        return "No table found.";
    }

    private String extractTableName(String statementInLowerCase) {
        return statementInLowerCase.substring(statementInLowerCase.lastIndexOf(" ") + 1);
    }
}
