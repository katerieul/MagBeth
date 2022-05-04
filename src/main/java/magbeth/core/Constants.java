package magbeth.core;

import javafx.scene.paint.Color;

// Global constants that we don't really want to abuse too much
// Please get rid of this file if it proves unnecessary
public class Constants {
    public static final String STARTING_POSITION="rnbqkbnr/pppppppp/8/8/8/8/PPPPPPPP/RNBQKBNR w KQkq - 0 1";
    public static final int BOARDSIZE=8;
    public static final Color FIELD_COLOR0 = Color.color(210d/255, 250d/255, 170d/255);
    public static final Color ACTIVE_COLOR0 = Color.color(100d/255, 180d/255, 80d/255);
    public static final Color FIELD_COLOR1 = Color.color(50d/255, 100d/255, 50d/255);
    public static final Color ACTIVE_COLOR1 = Color.color(50d/255, 110d/255, 40d/255);
}
