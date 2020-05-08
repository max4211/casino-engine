package Utility.handbundle;

import java.util.ArrayList;
import java.util.List;

/**
 * Bundle parameters inside of a hand (within XML), used to facilitate refleciton later on
 * @author Max Smith
 */
public class HandBundle implements HandBundleInterface{

    private static final int NAME_INDEX = 0;
    private static final int PARAMS_INDEX = 1;
    private static final int MULT_INDEX = 2;
    private static final int VIEW_INDEX = 3;

    private static final String EMPTY_STRING = "";

    private final String myName;
    private final List<Double> myParams;
    private final double myMultiplier;
    private final String myViewName;

    public HandBundle(String name, String params, String multiplier, String viewName) {
        this.myName = name;
        this.myParams = parseParams(params);
        this.myMultiplier = parseMultiplier(multiplier);
        this.myViewName = parseViewName(viewName);
    }

    public HandBundle(List<String> params) {
        this.myName = params.get(NAME_INDEX);
        this.myParams = parseParams(params.get(PARAMS_INDEX));
        this.myMultiplier = parseMultiplier(params.get(MULT_INDEX));
        this.myViewName = parseViewName(params.get(VIEW_INDEX));
    }

    private String parseViewName(String viewName) {
        if (viewName.equals(EMPTY_STRING))
            viewName = this.myName;
        return viewName;
    }

    // Default assigned is 1 x (logical)
    private double parseMultiplier(String multiplier) {
        double d;
        try {
            d = Double.parseDouble(multiplier);
        } catch (NumberFormatException e) {
            d = 1;
        }
        return d;
    }

    private List<Double> parseParams(String params) {
        List<Double> list = new ArrayList<>();
        try {
            for (String s: params.split("\\s"))
                list.add(Double.parseDouble(s));
        } catch (Exception e) {
            ;
        }
        return list;
    }

    /**
     * Get name of the hand to facilitate reflection in hand classificioant
     * @return te name of the hand
     */
    @Override
    public String getName() {
        return this.myName;
    }

    /**
     * Get the list of parameters that make up this hand
     * @return parameters inside of the hand
     */
    @Override
    public List<Double> getParams() {
        return this.myParams;
    }

    /**
     * Get the multiplier to help with payoffs
     * @return multiplier of hand
     */
    @Override
    public double getMultiplier() {
        return this.myMultiplier;
    }

    /**
     * Get the name of the hand internally to the view
     * @return view name (optional0
     */
    @Override
    public String getViewName() {
        return this.myViewName;
    }
}
