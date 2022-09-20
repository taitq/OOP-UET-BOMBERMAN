module com.example.uet_oop_bomberman {
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.graphics;


    opens com.example.uet_oop_bomberman to javafx.fxml;
    exports com.example.uet_oop_bomberman;
}