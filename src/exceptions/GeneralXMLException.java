package exceptions;

/**
 * General exception thrown when parsing xml
 * @author Max Smith
 */
public class GeneralXMLException extends RuntimeException {

    public GeneralXMLException(Exception e) {
        super(e);
    }

}
