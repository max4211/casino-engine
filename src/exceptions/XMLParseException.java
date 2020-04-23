package exceptions;

import java.io.File;

public class XMLParseException extends RuntimeException {

    public XMLParseException(Exception e, File file) {
        super(e.getMessage());
    }
}
