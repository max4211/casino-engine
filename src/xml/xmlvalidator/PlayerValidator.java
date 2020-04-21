package xml.xmlvalidator;

import org.xml.sax.SAXException;

import javax.xml.transform.stream.StreamSource;
import javax.xml.validation.Schema;
import java.io.File;
import java.io.IOException;

public class PlayerValidator extends Validator {

    private static final String VALIDATION_FILE = "data/schema/playerschema.xsd";

    public PlayerValidator() {
        super();
    }

    @Override
    public boolean validate(File xmlFile) {
        File validationFile = new File(VALIDATION_FILE);
        try {
            Schema schema = this.mySchemaFactory.newSchema(validationFile);
            javax.xml.validation.Validator validator = schema.newValidator();
            StreamSource streamSource = new StreamSource(xmlFile);
            validator.validate(streamSource);
            return true;
        } catch (SAXException | IOException e) {
            e.printStackTrace();
            return false;
        }
    }
}
