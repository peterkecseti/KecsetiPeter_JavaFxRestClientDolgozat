package bmszcpatrik.kecsetipeter_javafxrestclientdolgozat;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;

import java.io.IOException;

public class CreateMemberController extends Controller {

    @javafx.fxml.FXML
    private TextField textFieldName;
    @javafx.fxml.FXML
    private Spinner<Integer> spinnerAge;
    @javafx.fxml.FXML
    private Button submitButton;
    @javafx.fxml.FXML
    private TextField textFieldMinosites;
    @javafx.fxml.FXML
    private TextField textFieldSize;
    @javafx.fxml.FXML
    private CheckBox checkboxElementary;

    @FXML
    private void initialize(){
        SpinnerValueFactory.IntegerSpinnerValueFactory vf =
                new SpinnerValueFactory.IntegerSpinnerValueFactory(0, 30, 12, 1);
        spinnerAge.setValueFactory(vf);
    }

    @FXML
    public void submitClick(ActionEvent actionEvent) {
        String nev = textFieldName.getText().trim();
        String meret = textFieldSize.getText().trim();
        int eletkor = spinnerAge.getValue();
        String minosites = textFieldMinosites.getText().trim();
        Boolean nyolcAltalanos = checkboxElementary.isSelected();

        if (nev.isEmpty()){
            warning("Név megadása kötelező!");
            return;
        }
        if (meret.isEmpty()){
            warning("Email megadása kötelező");
            return;
        }
        if (minosites.isEmpty()){
            warning("Minosites megadása kötelező");
            return;
        }

        Member newPerson = new Member(0, nev, meret, eletkor, minosites, nyolcAltalanos);
        Gson converter = new GsonBuilder().excludeFieldsWithoutExposeAnnotation().create();
        String json = converter.toJson(newPerson);
        try {
            Response response = RequestHandler.post(App.BASE_URL, json);
            if (response.getResponseCode() == 201){
                textFieldName.setText("");
                textFieldMinosites.setText("");
                textFieldSize.setText("");
                checkboxElementary.setSelected(false);
                spinnerAge.getValueFactory().setValue(12);

            }
            else{
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Hiba");
                alert.setHeaderText("Hiba történt felvétel során");
                alert.showAndWait();
            }
        } catch (IOException e) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Hiba");
            alert.setHeaderText("Nem sikerült kapcsolódni a szerverhez!");
            alert.showAndWait();
        }

    }
}
