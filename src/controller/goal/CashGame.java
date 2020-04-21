package controller.goal;

import engine.player.Player;

import java.util.Collection;
import java.util.function.Supplier;

public class CashGame extends Goal {

    public CashGame(Supplier<Collection<Player>> getPlayers) {
        super(getPlayers);
    }

    @Override
    public String evaluate() {
        return null;
    }
}
