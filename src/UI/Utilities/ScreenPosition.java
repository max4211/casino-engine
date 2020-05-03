package UI.Utilities;

import javafx.stage.Screen;

import javafx.geometry.Rectangle2D;

/**
 * Basic enumerated type that specifies where in the screen a stage is set, with four types being Far Left, Left, Middle, and Right.
 * Uses the width of the screen to calculate placement.
 * @author Eric Doppelt and Max Smith
 */
public enum ScreenPosition {

    FARLEFT ('L'),
    LEFT ('l'),
    MIDDLE ('M'),
    RIGHT ('r');

    private final Rectangle2D SCREEN_BOUNDS = Screen.getPrimary().getBounds();
    private final double SCREEN_WIDTH = SCREEN_BOUNDS.getWidth();
    private final double SCREEN_HEIGHT = Screen.getPrimary().getBounds().getHeight();

    private static final double FARLEFT_WIDTH_MULTIPLIER = .2;
    private static final double LEFT_WIDTH_MULTIPLIER = .32;
    private static final double MIDDLE_WIDTH_MULTIPLIER = .5;
    private static final double RIGHT_WIDTH_MULTIPLIER = .68;

    private static final double FARLEFT_HEIGHT_MULTIPLIER = .25;
    private static final double LEFT_HEIGHT_MULTIPLIER = .25;
    private static final double MIDDLE_HEIGHT_MULTIPLIER = .20;
    private static final double RIGHT_HEIGHT_MULTIPLIER = .25;


    private double x;
    private double y;

    /**
     * Basic constructor that maps a char (representing the type of the enum) to two coordinates for the X and Y
     * @param type is the type of ScreenPosition, where l maps to Far Left, L maps to Left, M maps to Middle, and r maps to right.
     */
    ScreenPosition(char type) {
        switch(type) {
            case 'l': {
                setXY(FARLEFT_WIDTH_MULTIPLIER, FARLEFT_HEIGHT_MULTIPLIER);
                break;
            }
            case 'L': {
                setXY(LEFT_WIDTH_MULTIPLIER, LEFT_HEIGHT_MULTIPLIER);
                break;
            }
            case 'M': {
                setXY(MIDDLE_WIDTH_MULTIPLIER, MIDDLE_HEIGHT_MULTIPLIER);
                break;
            }
            case 'r': {
                setXY(RIGHT_WIDTH_MULTIPLIER, RIGHT_HEIGHT_MULTIPLIER);
                break;
            }
        }
    }

    /**
     * Basic getter method for the x coordinate.
     * @return the x coordinate stored in the enum.
     */
    public double getX() {return x;}

    /**
     * Basic getter method for the y coordinate.
     * @return the y coordinate stored in the enum.
     */
    public double getY() {return y;}

    private void setXY(double xOfffset, double yOffset) {
        x = SCREEN_WIDTH * xOfffset;
        y = SCREEN_HEIGHT * yOffset;
    }
}
