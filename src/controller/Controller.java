package controller;

import data.xmlreader.Pair;
import engine.table.Table;

import java.util.List;

public class Controller {

    private Table myTable;
    private final String myEntryBet;
    private final List<String> myPlayerActions;
    private final Pair myDealerAction;



    public Controller(Table table, String entryBet, List<String> playerActions, Pair dealerAction) {
        this.myTable = table;
        this.myEntryBet = entryBet;
        this.myPlayerActions = playerActions;
        this.myDealerAction = dealerAction;
    }
}
