package UI.Utilities;

import javafx.stage.Screen;

import javafx.geometry.Rectangle2D;

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

    private void setXY(double xOfffset, double yOffset) {
        x = SCREEN_WIDTH * xOfffset;
        y = SCREEN_HEIGHT * yOffset;
    }

    public double getX() {return x;}

    public double getY() {return y;}
}
