package Utility.handbundle;

import java.util.List;

public interface HandBundleInterface {

    /**
     * Get name of the hand to facilitate reflection in hand classificioant
     * @return te name of the hand
     */
    String getName();

    /**
     * Get the list of parameters that make up this hand
     * @return parameters inside of the hand
     */
    List<Double> getParams();

    /**
     * Get the multiplier to help with payoffs
     * @return multiplier of hand
     */
    double getMultiplier();

    /**
     * Get the name of the hand internally to the view
     * @return view name (optional0
     */
    String getViewName();

}
