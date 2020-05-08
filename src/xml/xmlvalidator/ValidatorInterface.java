package xml.xmlvalidator;

import java.io.File;

/**
 * Interface implemented by all XML validation objects
 */
public interface ValidatorInterface {

    /**
     * Validate the specific file according to schema
     * @param file is the file to validate
     * @return true if the file was successfully validated
     */
    boolean validate(File file) ;
}
