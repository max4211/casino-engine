package xml.xmlvalidator;

public interface ValidatorFactoryInterface {

    /**
     * Create a validator object based on the inputted type
     * @param validator
     * @return
     */
    Validator createValidator(String validator);
}
