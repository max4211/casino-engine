package Utility.handbundle;

import java.util.ArrayList;
import java.util.List;

public class HandBundle implements HandBundleInterface{

    private static final int NAME_INDEX = 0;
    private static final int PARAMS_INDEX = 1;
    private static final int MULT_INDEX = 2;
    private static final int VIEW_INDEX = 3;

    private final String myName;
    private final List<Double> myParams;
    private final double myMultiplier;
    private final String myViewName;

    public HandBundle(String name, String params, String multiplier, String viewName) {
        this.myName = name;
        this.myParams = parseParams(params);
        this.myMultiplier = parseMultiplier(multiplier);
        this.myViewName = viewName;
    }

    public HandBundle(List<String> params) {
        this.myName = params.get(NAME_INDEX);
        this.myParams = parseParams(params.get(PARAMS_INDEX));
        this.myMultiplier = parseMultiplier(params.get(MULT_INDEX));
        this.myViewName = params.get(VIEW_INDEX);
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

    // TODO - more robust parameter parsing
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

    @Override
    public String getName() {
        return this.myName;
    }

    @Override
    public List<Double> getParams() {
        return this.myParams;
    }

    @Override
    public double getMultiplier() {
        return this.myMultiplier;
    }

    @Override
    public String getViewName() {
        return this.myViewName;
    }
}
