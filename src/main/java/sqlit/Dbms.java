package sqlit;

import sqlit.exception.TableNotExistException;
import sqlit.table.TableDefinition;

import java.util.logging.Logger;
import sqlit.statement.InsertStatement;

import static java.util.stream.Collectors.joining;

public class Dbms {
    public static final String DEFAULT_FILE_NAME = "sqlit.db";

    public boolean createTable(TableDefinition tableDefinition) {
        RawData rawData = RawData.load();
        rawData.addTableDefinition(tableDefinition);
        rawData.save();
        return true;
    }

    public String showTables() {
        RawData rawData = RawData.load();
        if (rawData.getTableDefinitions().isEmpty()) {
            return "No table found.";
        }
        return rawData.getTableDefinitions().stream()
                .map(TableDefinition::getTableName)
                .collect(joining("\r\n"));
    }

    public void dropTable(String tableName) {
        RawData rawData = RawData.load();
        rawData.removeTable(tableName);
        rawData.save();
    }

    public boolean insert(InsertStatement statement) {
        RawData rawData = RawData.load();
        if (!rawData.hasTable(statement.getTableName())) {
            throw new TableNotExistException(statement.getTableName());
        }
        rawData.getValues().get(statement.getTableName()).add(statement.getValues());
        rawData.save();
        return true;
    }

}
