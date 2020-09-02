package sqlit;

import com.alibaba.fastjson.JSON;
import sqlit.table.TableDefinition;

import java.io.*;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Dbms {
    private static final Logger logger = Logger.getLogger(Dbms.class.getPackage().getName());

    public boolean createTable(TableDefinition tableDefinition) {
        String json = JSON.toJSONString(tableDefinition);
        appendTable(json);
        return true;
    }

    private void appendTable(String json) {
        writeText(json);
    }

    private void writeText(String json) {
        try {
            PrintWriter printWriter = new PrintWriter(new FileOutputStream(new File("./sqlit.db")));
            printWriter.println(json);
            printWriter.close();
        } catch (FileNotFoundException e) {
            logger.log(Level.WARNING, e.getMessage());
        }
    }

    public String showTables() {
        try {
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(new FileInputStream("./sqlit.db")));
            String json = bufferedReader.readLine();
            TableDefinition tableDefinition = JSON.parseObject(json, TableDefinition.class);
            return tableDefinition.getTableName();
        } catch (IOException e) {
            logger.log(Level.WARNING, e.getMessage());
        }
        return "";
    }
}
