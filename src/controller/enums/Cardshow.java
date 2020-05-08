package controller.enums;

import java.util.function.BiConsumer;

/**
 * Enumerated type for possible cardshow policies
 * Now depracated due to xml validation process
 * @author Max Smith
 */
@Deprecated
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
