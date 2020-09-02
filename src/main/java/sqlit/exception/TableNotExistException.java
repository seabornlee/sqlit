package sqlit.exception;

public class TableNotExistException extends RuntimeException {
    public TableNotExistException(String tableName) {
        super("Table '" + tableName + "' not found.");
    }
}
