package sqlit.statement;

import java.util.HashMap;
import java.util.Map;

public class InsertStatement {
    private String tableName;

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

    public void addColumn(String name, String value) {
        this.values.put(name, value);
    }
}
