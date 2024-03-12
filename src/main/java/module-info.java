module worldcup {
    requires javafx.controls;
    requires javafx.fxml;

    opens worldcup to javafx.fxml;
    exports worldcup;
}
