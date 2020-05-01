package exceptions;

import org.xml.sax.SAXException;

import java.io.IOException;

/**
 * Thrown while attempting to validate a file with a specific schema
 * @author Max Smith
 */
public class ValidatorException extends RuntimeException {

    public ValidatorException(SAXException e) {
        super(e.getMessage());
    }

    public ValidatorException(IOException e) {
        super(e.getMessage());
    }
}
