package xml.xmlvalidator;

/**
 * Interface implemented by validator factory, shows single service (create Validator objects)
 * @author Max Smith
 */
public interface ValidatorFactoryInterface {

    /**
     * Create a validator object based on the inputted type
     * @param validator is the type of file to validate
     * @return an abstract Validator (command design pattern)
     */
    Validator createValidator(String validator);
}
