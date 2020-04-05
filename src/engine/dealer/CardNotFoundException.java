package engine.dealer;

public class CardNotFoundException extends RuntimeException {

    private final Error myError;
    private final Card myCard;

    private static final String MESSAGE = ", could not find card: ";

    public CardNotFoundException(Error e, Card c) {
        this.myError = e;
        this.myCard = c;
    }

    // TODO refactor to properties file response
    @Override
    public String toString() {
        return myError.toString() + MESSAGE + myCard.toString();
    }

}
