package xml.xmlvalidator;

import exceptions.ValidatorException;
import org.xml.sax.SAXException;

import javax.xml.XMLConstants;
import javax.xml.transform.stream.StreamSource;
import javax.xml.validation.Schema;
import javax.xml.validation.SchemaFactory;
import java.io.File;
import java.io.IOException;

public abstract class Validator implements ValidatorInterface {

    protected final SchemaFactory mySchemaFactory;
    private final File myValidationFile;

    public Validator(String validationFile) {
        this.mySchemaFactory =  SchemaFactory.newInstance(XMLConstants.W3C_XML_SCHEMA_NS_URI);
        this.myValidationFile = new File(validationFile);
    }

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
