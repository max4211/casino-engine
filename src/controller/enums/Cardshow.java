package controller.enums;

import java.util.function.BiConsumer;

public enum Cardshow {

    ALL("All"),
    ACTIVE("Active"),
    OTHER("Other");

    private final String myType;

    private Cardshow(String type) {
        this.myType = type;
    }

    @Override
    public String toString() {return myType;}

}
