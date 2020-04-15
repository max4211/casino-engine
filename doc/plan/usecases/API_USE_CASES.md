## API Use Cases

The instructions were a bit unclear. Below, you will find code in the form of markdown Java inserts; however,
the actual code is also included in Java files to show that it compiles. The use cases also scale in complexity.

### Use Case 1: Creating a Game

After an XML file has been selected in UI.LobbyView, the game can be created using a simple createGame() call from the GameGeneratorInterface. This is clearly a simple use case.
```java
GameGenerator.createGame(chosenXMLFile);
```
While only one line, this is included to highlight how easy it is to create a game. Since this is a data-drive approach, all it takes
to sprout a new window is an XML file and one API call, which entails simplicity in our design.

### Use Case 2: Get a Card, Store it, and then Request it.
Here, our API can be used to get a card from a Dealer, remember it, and then ask for it again. This could be used to place a bet in Roulette on the previous outcome.
```java
Card c = Dealer.dealCard();
Dealer.shuffle();
Card newCard = Dealer.dealCard(c);
```

### Use Case 3: Prompt a Player with an Action
Here, we want to access the Actions that we have from the XML file, give them to the user to select,
and then execute it on an arbitrary bet that the user has.
```java
Map allActions = XMLReader.getActions();
List midTurnActions = allActions.get("MidGameAction");
String actionChoice = GameViewInterface.selectAction(midTurnActions);
Action userAction = ActionFactory.createAction(actionChoice);
userAction.execute(Bet userBet);
```

### Use Case 4: Compare Hands to Evaluate a Bet
Here, a lot of code that the Controller does to handle the executions is lacking, but the API calls
used to execute this are as follow. Say we have a Collection of two Bets, Bet1 and Bet2, which each a hand (Hand1 and Hand2):
```java
Collection<Bet> allBets = new ArrayList<Bets>();
allBets.add(Bet1);
allBets.add(Bet2);
BetEvaluationInterface.evaluateBets(allBets);
``` 
Then, in BetEvaluationInterface, HandClassification and HandEvaluator modules would be called:
```java
handClassification.classifyHand(Hand1);
handClassification.classifyHand(Hand2);
```
After these calls, HandEvaluator would be called. Note that setToWin() is not in our API since it is not
an external call in the module.
```java
int compareResult = handEvaulator.compare(Hand1, Hand2);
if (compareResult == -1) {
Bet1.setToWin();
Bet2.setToLose();
} else if (compareResult == 1) {
Bet1.setToLose();
Bet1.setToWin();
} else {
Bet1.setToWin();
Bet2.setToWin();
}
```

### Use Case 5: Throw an Exception

Lastly, a simple use case is to build upon our earlier Action production to show what happens
when the reflection fails.

```java
Map allActions = XMLReader.getActions();
List midTurnActions = allActions.get("MidGameAction");
String actionChoice = GameViewInterface.selectAction(midTurnActions);
try {
Action userAction = ActionFactory.createAction(actionChoice);
userAction.execute(Bet userBet);
} catch (ClassNotFoundException e) {
   UI.GameView.displayError(e);
}
```

### Use Case 6: Throw a Custom Exception and Display Cards

Since our first use case was so simple, we have added a sixth use case to expand upon this idea of exception catching, in addition to highlighting the ability to communicate with the frontend.
Here, we try and deal a card in the backend and frontend. We also deal with a custom exception via a simple catch statement. The card is given to an arbitrary BetView object as follows:
```
Card c = Dealer.dealCard();
Dealer.shuffle();
try {
Card newCard = Dealer.dealCard(c);
} catch (CardNotFoundException e) {
   UI.GameView.displayError(e);
}
UI.GameView.giveCard(betViewOne, newCard);
UI.GameView.showCard(betViewOne, newCard);
```

This shows the flexible nature of our frontend API, wherein any error can be handled with a simple and flexible method call.
