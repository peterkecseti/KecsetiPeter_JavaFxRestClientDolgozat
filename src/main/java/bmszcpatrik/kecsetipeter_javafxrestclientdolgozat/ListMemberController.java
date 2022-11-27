package bmszcpatrik.kecsetipeter_javafxrestclientdolgozat;

import com.google.gson.Gson;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.Optional;

public class ListMemberController {
    @javafx.fxml.FXML
    private Button insertButton;
    @javafx.fxml.FXML
    private Button updateButton;
    @javafx.fxml.FXML
    private Button deleteButton;
    @javafx.fxml.FXML
    private TableView<Member> tableTable;
    @javafx.fxml.FXML
    private TableColumn<Member, Integer> idCol;
    @javafx.fxml.FXML
    private TableColumn<Member, String> nameCol;
    @javafx.fxml.FXML
    private TableColumn<Member, Integer> ageCol;
    @javafx.fxml.FXML
    private TableColumn<Member, String> minositesCol;
    @javafx.fxml.FXML
    private TableColumn<Member, String> sizeCol;
    @javafx.fxml.FXML
    private TableColumn<Member, Boolean> elementaryCol;

    @FXML
    private void initialize(){
        idCol.setCellValueFactory(new PropertyValueFactory<>("id"));
        nameCol.setCellValueFactory(new PropertyValueFactory<>("name"));
        sizeCol.setCellValueFactory(new PropertyValueFactory<>("size"));
        ageCol.setCellValueFactory(new PropertyValueFactory<>("age"));
        minositesCol.setCellValueFactory(new PropertyValueFactory<>("minosites"));
        elementaryCol.setCellValueFactory(new PropertyValueFactory<>("elementary"));

        try {

            loadMembersFromServer();

        } catch (IOException e) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Hiba");
            alert.setHeaderText("Hiba az adatok lekérése során");
            alert.setContentText(e.getMessage());

            Platform.runLater(() ->{
                alert.showAndWait();
                Platform.exit();
            });

        }
    }
    @javafx.fxml.FXML
    public void insertClick(ActionEvent actionEvent) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource("create-member-view.fxml"));
            Scene scene = new Scene(fxmlLoader.load(), 640, 480);
            Stage stage = new Stage();
            stage.setTitle("Create member");
            stage.setScene(scene);
            stage.setOnCloseRequest(event -> {
                try {
                    loadMembersFromServer();
                } catch (IOException e) {
                    Alert alert = new Alert(Alert.AlertType.ERROR);
                    alert.setTitle("Hiba");
                    alert.setHeaderText("Nem sikerült kapcsolódni a szerverhez!");
                    alert.showAndWait();
                }
            });
            stage.showAndWait();
        } catch (IOException e) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Hiba");
            alert.setHeaderText("Hiba történt az űrlap betöltése során");
            alert.setContentText(e.getMessage());
            alert.showAndWait();
        }
    }

    @javafx.fxml.FXML
    public void updateClick(ActionEvent actionEvent) {
    }

    @javafx.fxml.FXML
    public void deleteClick(ActionEvent actionEvent) {
        Member selected = tableTable.getSelectionModel().getSelectedItem();
        if (selected == null){
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Figyelmeztetés");
            alert.setHeaderText("Törléshez előbb válasszon ki egy elemet!");
            alert.showAndWait();
            return;
        }

        Alert confirmation = new Alert(Alert.AlertType.CONFIRMATION);
        confirmation.setTitle("Biztos?");
        confirmation.setHeaderText("Biztos, hogy törölni akarod az alábbi rekordot?\n" + selected.getName());
        Optional<ButtonType> obt = confirmation.showAndWait();
        if (obt.isPresent()
                &&
                obt.get().equals(ButtonType.OK)){

            String url = App.BASE_URL + "/" + selected.getId();

            try {
                RequestHandler.delete(url);
                loadMembersFromServer();
            }catch(IOException e){
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Hiba");
                alert.setHeaderText("Nem sikerült kapcsolódni a szerverhez!");
                alert.showAndWait();
            }
        }

    }



    private void loadMembersFromServer() throws IOException {
        Response response =
                RequestHandler.get(App.BASE_URL);
        String content = response.getContent();
        System.out.println(response.getContent());
        Gson converter = new Gson();
        Member[] members = converter.fromJson(content, Member[].class);
        tableTable.getItems().clear();

        for (Member member: members){
            tableTable.getItems().add(member);
        }
    }
}
