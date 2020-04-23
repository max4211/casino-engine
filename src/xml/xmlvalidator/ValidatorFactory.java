package xml.xmlvalidator;

import exceptions.ReflectionException;

import java.lang.reflect.Constructor;

public class ValidatorFactory implements ValidatorFactoryInterface {

    private static final String VALIDATOR_PATH = "src.xml.xmlvalidator";
    private static final String VALIDATOR_SUFFIX = "Validator";

    private static final String DECK = "Deck";
    private static final String GAME = "Game";
    private static final String HAND = "Hand";
    private static final String PLAYER = "Player";
    private static final String VIEW = "View";

    public ValidatorFactory() {

    }

    @Override
    public Validator createValidator(String validator) {
        try {
            Class clazz = Class.forName(createValidatorPath(validator));
            Constructor ctor = clazz.getConstructor();
            return (Validator) ctor.newInstance();
        } catch (Exception e) {
            throw new ReflectionException(e);
        }
    }

    private String createValidatorPath(String validator) {
        return String.format("%s.%s.%s", VALIDATOR_PATH, validator, VALIDATOR_SUFFIX);
    }
}
