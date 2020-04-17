package actions;

import actions.factory.ActionFactory;
import actions.individual.DoubleDown;
import actions.individual.Hit;
import actions.individual.IndividualAction;
import actions.individual.Stay;
import exceptions.ReflectionException;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class IndividualActionFactoryTest {

    // HAPPY PATH :)
    @Test
    void testCreateAction() {
        ActionFactory factory = new ActionFactory();
        IndividualAction hitTest = factory.createAction("Hit");
        IndividualAction stayTest = factory.createAction("Stay");
        IndividualAction doubleDownTest = factory.createAction("DoubleDown");
        assertEquals(hitTest.getClass(), Hit.class);
        assertEquals(stayTest.getClass(), Stay.class);
        assertEquals(doubleDownTest.getClass(), DoubleDown.class);
    }

    // SAD PATH :)
    @Test
    void testInvalidAction() {
        ActionFactory factory = new ActionFactory();
        Exception e = assertThrows(RuntimeException.class, () -> factory.createAction("blah"));
        assertEquals(e.getClass(), ReflectionException.class);
        // TODO - test exception matches string
    }
}