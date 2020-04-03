Map allActions = XMLReader.getActions();
List midTurnActions = allActions.get("MidGameAction");
String actionChoice = GameViewInterface.selectAction(midTurnActions);
try {
  Action userAction = ActionFactory.createAction(actionChoice);
  userAction.execute(Bet userBet);
} catch (ClassNotFoundException e) {
  GameView.displayError(e);
  }