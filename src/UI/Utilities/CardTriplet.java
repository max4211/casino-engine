package UI.Utilities;

public class CardTriplet {

        private final Double myValue;
        private final String mySuit;
        private final int myID;

        public CardTriplet(double value, String suit, int id) {
            myValue = value;
            mySuit = suit;
            myID = id;
        }

        public Double getValue() {
            return myValue;
        }

        public String getSuit() { return mySuit; }

        public int getID() { return myID; };

    }
