package xml.xmlvalidator;

import exceptions.ValidatorException;
import org.xml.sax.SAXException;

import javax.xml.XMLConstants;
import javax.xml.transform.stream.StreamSource;
import javax.xml.validation.Schema;
import javax.xml.validation.SchemaFactory;
import java.io.File;
import java.io.IOException;

/**
 * Abstract validaor object (command design pattern) to validate appropriate xml files
 * @author Max Smith
 */
public abstract class Validator implements ValidatorInterface {

    protected final SchemaFactory mySchemaFactory;
    private final File myValidationFile;

    public Validator(String validationFile) {
        this.mySchemaFactory =  SchemaFactory.newInstance(XMLConstants.W3C_XML_SCHEMA_NS_URI);
        this.myValidationFile = new File(validationFile);
    }

    /**
     * Attempt to validate the file according to given XML schema
     * @param xmlFile is the user selected file
     * @return true if the file was validated
     * @throws ValidatorException if there was an error parsing the schema
     */
    @Override
    public boolean validate(File xmlFile) throws ValidatorException {
        try {
            Schema schema = this.mySchemaFactory.newSchema(this.myValidationFile);
            javax.xml.validation.Validator validator = schema.newValidator();
            StreamSource streamSource = new StreamSource(xmlFile);
            validator.validate(streamSource);
            return true;
        } catch (SAXException e) {
            throw new ValidatorException(e);
        } catch (IOException e) {
            throw new ValidatorException(e);
        }
    }

}
