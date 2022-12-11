module magbeth.magbeth {
    requires javafx.controls;
    requires javafx.fxml;
    requires lombok;

    opens magbeth to javafx.fxml;
    exports magbeth;
    exports magbeth.core;
    opens magbeth.core to javafx.fxml;
    exports magbeth.views;
    opens magbeth.views to javafx.fxml;
    exports magbeth.core.moves;
    opens magbeth.core.moves to javafx.fxml;
}