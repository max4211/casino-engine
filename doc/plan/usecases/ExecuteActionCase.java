Map allActions = XMLReader.getActions();
List midTurnActions = allActions.get("MidGameAction");
String actionChoice = GameViewInterface.selectAction(midTurnActions);
Action userAction = ActionFactory.createAction(actionChoice);
userAction.execute(Bet userBet);