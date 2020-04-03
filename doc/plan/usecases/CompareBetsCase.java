  Collection<Bet> allBets = new ArrayList<Bets>();
  allBets.add(Bet1);
  allBets.add(Bet2);
  BetEvaluationInterface.evaluateBets(allBets);

  handClassification.classifyHand(Hand1);
  handClassification.classifyHand(Hand2);

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