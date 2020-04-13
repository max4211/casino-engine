package actionFactory;

import exceptions.ReflectionException;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ActionFactoryTest {

    // HAPPY PATH :)
    @Test
    void testCreateAction() {
        ActionFactory factory = new ActionFactory();
        Action hitTest = factory.createAction("Hit");
        Action stayTest = factory.createAction("Stay");
        Action doubleDownTest = factory.createAction("DoubleDown");
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