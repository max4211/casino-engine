package exceptions;

import actions.group.BetAction;
import actions.individual.DoubleDown;
import actions.individual.IndividualAction;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ActionExceptionTest {

    @Test
    void getMessage() {
        ActionException e = new ActionException(new Exception(), new BetAction());
        String result = e.getMessage();
        String expected = "Sorry, could not create action BetAction, please try again";
        assertEquals(expected, result);
        e = new ActionException(new Exception(), new DoubleDown());
        result = e.getMessage();
        expected = "Sorry, could not create action DoubleDown, please try again";
        assertEquals(expected, result);
    }
}