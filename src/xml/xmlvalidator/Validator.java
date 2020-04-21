package xml.xmlvalidator;

import javax.xml.XMLConstants;
import javax.xml.validation.SchemaFactory;

public abstract class Validator implements ValidatorInterface {

    protected final SchemaFactory mySchemaFactory;

    public Validator() {
        this.mySchemaFactory =  SchemaFactory.newInstance(XMLConstants.W3C_XML_SCHEMA_NS_URI);
    }

}
