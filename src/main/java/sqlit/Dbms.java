package sqlit;

import com.alibaba.fastjson.JSON;
import sqlit.table.TableDefinition;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;
import java.util.logging.Level;
import java.util.logging.Logger;

import static java.util.stream.Collectors.joining;

public class Dbms {
    private static final Logger logger = Logger.getLogger(Dbms.class.getPackage().getName());
    public static final String DEFAULT_FILE_NAME = "sqlit.db";

    public boolean createTable(TableDefinition tableDefinition) {
        List<TableDefinition> tableDefinitions = readTableDefinitions();
        if (tableDefinitions == null) {
            tableDefinitions = new ArrayList<>();
        }
        tableDefinitions.add(tableDefinition);

        saveTableDefinitions(tableDefinitions);
        return true;
    }

    private void saveTableDefinitions(List<TableDefinition> tableDefinitions) {
        writeText(JSON.toJSONString(tableDefinitions));
    }

    private List<TableDefinition> readTableDefinitions() {
        return JSON.parseArray(readText(), TableDefinition.class);
    }

    private String readText() {
        try (BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(new FileInputStream("./" + DEFAULT_FILE_NAME)))) {
            return bufferedReader.readLine();
        } catch (IOException e) {
            logger.log(Level.WARNING, e.getMessage());
        }
        return "";
    }

    private void writeText(String json) {
        try (PrintWriter printWriter = new PrintWriter(new FileOutputStream(new File("./" + DEFAULT_FILE_NAME)))) {
            printWriter.println(json);
        } catch (FileNotFoundException e) {
            logger.log(Level.WARNING, e.getMessage());
        }
    }

    public String showTables() {
        try (BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(new FileInputStream("./" + DEFAULT_FILE_NAME)))) {
            String json = bufferedReader.readLine();
            List<TableDefinition> tableDefinitions = JSON.parseArray(json, TableDefinition.class);
            if (tableDefinitions.isEmpty()) {
                return "No table found.";
            }
            return tableDefinitions.stream()
                    .map(TableDefinition::getTableName)
                    .collect(joining("\r\n"));
        } catch (IOException e) {
            logger.log(Level.WARNING, e.getMessage());
        }
        return "No table found.";
    }

    public void dropTable(String tableName) {
        List<TableDefinition> tableDefinitions = readTableDefinitions();
        tableDefinitions.removeIf(tableDefinition -> tableDefinition.getTableName().equals(tableName));
        saveTableDefinitions(tableDefinitions);
    }
}
