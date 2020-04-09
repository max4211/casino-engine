package engine.table;

import engine.bet.Bet;

import java.util.function.Consumer;

public interface TableInterface {

    /**
     * Producer for the view to accept strings in response to bet creation
     * @param s is the string that corresponds to the action
     */
    void acceptString(String s);

    /**
     * prompt the table to prompt all players to place entry bet
     * @param s is the type of bet for all players to place
     */
    void placeEntryBet(String s, Consumer<Bet> betConsumer);
}
