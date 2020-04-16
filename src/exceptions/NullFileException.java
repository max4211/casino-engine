package exceptions;

//TODO: correct extension?
public class NullFileException extends NullPointerException {

    private static final String EXCEPTION_MESSAGE = "Invalid TYPE XML file. Please pick a valid one!";
    private static final String REPLACED_SEQEUNCE = "TYPE";

    public NullFileException(String xmlType) {
        super(EXCEPTION_MESSAGE.replace(REPLACED_SEQEUNCE, xmlType));
    }
}
