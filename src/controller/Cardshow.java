package controller;

import java.util.function.BiConsumer;

public enum Cardshow {

    ALL("All"),
    ACTIVE("Active");

    private final String myType;

    private Cardshow(String type) {
        this.myType = type;
    }

    void showCards(BiConsumer<String, Integer> guy) {

    }

}
