package bmszcpatrik.kecsetipeter_javafxrestclientdolgozat;

import javafx.scene.control.Alert;

public abstract class Controller {

    protected void warning(String headerText){
        alert(Alert.AlertType.ERROR, "Figyelmeztetés", headerText, "");
    }
    protected void error(String headerText){
        error(headerText, "");
    }
    protected void error(String headerText, String contentText){
        alert(Alert.AlertType.ERROR, "Hiba", headerText, contentText);

    }

    protected void alert(Alert.AlertType alertType, String title, String headerText, String contentText){
        Alert alert = new Alert(alertType);
        alert.setTitle(title);
        alert.setContentText(contentText);
        alert.setHeaderText(headerText);
        alert.showAndWait();
    }

}