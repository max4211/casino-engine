Card c = Dealer.dealCard();
Dealer.shuffle();
try {
    Card newCard = Dealer.dealCard(c);
} catch (CardNotFoundException e) {
    GameView.displayError(e);
}
GameView.giveCard(betViewOne, newCard);
GameView.showCard(betViewOne, newCard);