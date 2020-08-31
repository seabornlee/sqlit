package sqlit.table;

public class Column {
    private String name;
    private Type type;

    private int length;

    public void setName(String name) {
        this.name = name;
    }

    public void setType(String type) {
        this.type = Type.valueOf(type.toUpperCase());
    }

    public void setLength(int length) {
        this.length = length;
    }

    public Column() {
    }

    public int getLength() {
        return length;
    }

    public String getName() {
        return name;
    }

    public Type getType() {
        return type;
    }

    public enum Type {
        VARCHAR, INT
    }
}
