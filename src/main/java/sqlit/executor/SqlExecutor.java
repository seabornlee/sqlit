package sqlit.executor;

import sqlit.Dbms;
import sqlit.table.TableDefinition;

public class SqlExecutor {
    private Dbms dbms = new Dbms();

    public String execute(String statement) {
        String statementInLowerCase = statement.toLowerCase();
        if (statementInLowerCase.startsWith("create table")) {
            TableDefinition tableDefinition = TableDefinition.from(statement);
            boolean isSuccessfully = dbms.createTable(tableDefinition);
            if (isSuccessfully) {
                return "Table '" + tableDefinition.getTableName() + "' created.";
            }
        }
        return "No table found.";
    }
}
