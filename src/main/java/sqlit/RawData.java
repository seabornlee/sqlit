package sqlit;

import com.alibaba.fastjson.JSON;
import sqlit.table.TableDefinition;

import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

import static java.util.Collections.emptyList;

public class RawData {
    private final List<TableDefinition> tableDefinitions = new ArrayList<>();
    private final Map<String, List<Map<String, String>>> values = new HashMap<>();
    private static final Logger logger = Logger.getLogger(RawData.class.getPackage().getName());

    static String readText() {
        try (BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(new FileInputStream("./" + Dbms.DEFAULT_FILE_NAME)))) {
            return bufferedReader.readLine();
        } catch (IOException e) {
            logger.log(Level.WARNING, e.getMessage());
        }
        return "";
    }

    static RawData load() {
        String json = readText();
        RawData rawData = JSON.parseObject(json, RawData.class);
        if (rawData == null) {
            return new RawData();
        }
        return rawData;
    }

    public List<TableDefinition> getTableDefinitions() {
        return tableDefinitions;
    }

    public Map<String, List<Map<String, String>>> getValues() {
        return values;
    }

    void addTableDefinition(TableDefinition tableDefinition) {
        getTableDefinitions().add(tableDefinition);
        getValues().put(tableDefinition.getTableName(), emptyList());
    }

    boolean removeTable(String tableName) {
        return getTableDefinitions().removeIf(tableDefinition -> tableDefinition.getTableName().equals(tableName));
    }

    void save() {
        try (PrintWriter printWriter = new PrintWriter(new FileOutputStream(new File("./" + Dbms.DEFAULT_FILE_NAME)))) {
            printWriter.println(JSON.toJSONString(this));
        } catch (FileNotFoundException e) {
            logger.log(Level.WARNING, e.getMessage());
        }
    }

    public boolean hasTable(String tableName) {
        return getTableDefinitions().stream().anyMatch(td -> td.getTableName().equals(tableName));
    }
}
