module bmszcpatrik.kecsetipeter_javafxrestclientdolgozat {
    requires javafx.controls;
    requires javafx.fxml;
    requires com.google.gson;


    opens bmszcpatrik.kecsetipeter_javafxrestclientdolgozat to javafx.fxml, com.google.gson;
    exports bmszcpatrik.kecsetipeter_javafxrestclientdolgozat;
}