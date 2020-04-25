package UI.Icons;

public enum IconSize {
    NORMAL (40),
    LARGE (120);

    private int mySize;

    IconSize(int size) {
        mySize = size;
    }

    public int getSize() {
        return mySize;
    }
}
