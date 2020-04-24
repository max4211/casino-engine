package Utility;

import javafx.stage.Screen;

import javafx.geometry.Rectangle2D;

public enum ScreenPosition {

    LEFT ('L'),
    MIDDLE ('M'),
    RIGHT ('R');

    // TODO: why will it let me access some but not all?
    private final Rectangle2D SCREEN_BOUNDS = Screen.getPrimary().getBounds();
    private final double SCREEN_WIDTH = SCREEN_BOUNDS.getWidth();
    private final double SCREEN_HEIGHT = Screen.getPrimary().getBounds().getHeight();

    private static final double LEFT_WIDTH_MULTIPLIER = .32;
    private static final double MIDDLE_WIDTH_MULTIPLIER = .5;
    private static final double RIGHT_WIDTH_MULTIPLIER = .68;

    private final double LEFT_HEIGHT_MULTIPLIER = .25;
    private final double MIDDLE_HEIGHT_MULTIPLIER = .20;
    private final double RIGHT_HEIGHT_MULTIPLIER = .25;


    private double x;
    private double y;

    ScreenPosition(char type) {
        switch(type) {
            case 'L': {
                x = SCREEN_WIDTH * LEFT_WIDTH_MULTIPLIER;
                y = SCREEN_HEIGHT * LEFT_HEIGHT_MULTIPLIER;
                break;
            }

            case 'M': {
                x = SCREEN_WIDTH * MIDDLE_WIDTH_MULTIPLIER;
                y = SCREEN_HEIGHT * MIDDLE_HEIGHT_MULTIPLIER;
                break;
            }

            case 'R': {
                x = SCREEN_WIDTH * RIGHT_WIDTH_MULTIPLIER;
                y = SCREEN_HEIGHT * RIGHT_HEIGHT_MULTIPLIER;
                break;
            }
        }
    }

    public double getX() {return x;}

    public double getY() {return y;}
}
