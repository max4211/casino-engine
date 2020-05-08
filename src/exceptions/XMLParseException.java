package exceptions;

import java.io.File;

/**
 * General XML parsing exceptioin
 * @author Max Smith
 */
public class XMLParseException extends RuntimeException {

    public XMLParseException(Exception e, File file) {
        super(e.getMessage());
    }
}
