package UI.Icons;

/**
 * Enumerated type used to specify the size of an icon. Two sizes are given with Normal icons being 40 by 40 and Large icons being 120 by 120.
 * It is assumed that all Icons are square. All icons are displayed as Normal, except GameStarters are Large.
 */
public enum IconSize {
    NORMAL (40),
    LARGE (120);

    private int mySize;

    /**
     * Basic enum constructor that takes in one parameter and sets it equal to the size instance variable, representing the length of one side of the displayed icon.
     * @param size is an int that is the side length of the square icon.
     */
    IconSize(int size) {
        mySize = size;
    }

    /**
     * Basic getter method that returns the size of the side length as an int for the icon. This is called to specify the Icon's pref width and height.
     * @return an integer representing the icon's side length.
     */
    public int getSize() {
        return mySize;
    }
}
