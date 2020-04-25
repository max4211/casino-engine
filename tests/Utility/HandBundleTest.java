package Utility;

import Utility.handbundle.HandBundle;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class HandBundleTest {

    @Test
    void createTest() {
//        HandBundle(String name, String params, String multiplier, String viewName) {
        String name = "SumOverX";
        String params = "21";
        String multiplier = "1.0";
        String viewName = "SumOverX";
        HandBundle bundle = new HandBundle(name, params, multiplier, viewName);
        assertEquals(bundle.getName(), name);
        assertEquals(bundle.getParams(), new ArrayList<Double>(List.of(21.)));
        assertEquals(bundle.getMultiplier(), 1.);
        assertEquals(bundle.getViewName(), viewName);
    }

}