package engine.dealer;

/**
 * Thrown for cards that are not found (requested by user)
 * Not implemented, but would be used in a game of roulette/craps
 */
public class CardNotFoundException extends RuntimeException {

    private final Error myError;
    private final Card myCard;

    private static final String MESSAGE = ", could not find card: ";

    public CardNotFoundException(Error e, Card c) {
        this.myError = e;
        this.myCard = c;
    }

    @Override
    public String toString() {
        return myError.toString() + MESSAGE + myCard.toString();
    }

}
