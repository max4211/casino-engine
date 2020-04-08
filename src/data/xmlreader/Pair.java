package data.xmlreader;

public class Pair {

    private static final String SEPARATOR = " : ";

    private final String myKey;
    private final String myValue;

    public Pair(String key, String value) {
        this.myKey = key;
        this.myValue = value;
    }

    public String getKey() {
        return this.myKey;
    }

    public String getValue() {
        return this.myValue;
    }

    @Override
    public String toString() {
        return this.myKey + SEPARATOR + this.myValue;
    }
}
