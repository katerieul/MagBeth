module magbeth.magbeth {
    requires javafx.controls;
    requires javafx.fxml;


    opens magbeth.magbeth to javafx.fxml;
    exports magbeth.magbeth;
}