package exceptions;

import org.xml.sax.SAXException;

import java.io.IOException;

public class ValidatorException extends RuntimeException {

    public ValidatorException(SAXException e) {
        super(e.getMessage());
    }

    public ValidatorException(IOException e) {
        super(e.getMessage());
    }
}
