package UI.Selectors;

import UI.DukeApplicationTest;
import exceptions.IncompatibleBetRestrictionException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
class WagerSelectorTest extends DukeApplicationTest {

    // SAD PATH :(
    @Test
    void invalidBetInput() {
        Assertions.assertThrows(RuntimeException.class, () -> launch(WagerSelectorTester.class));
    }

}