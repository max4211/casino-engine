package data.xmlreader;

public class Pair {

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
}
