package controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import model.Abonat;
import model.Carte;
import model.Status;
import service.Service;

import java.io.IOException;
import java.util.List;

public class CartiController {

    private Service service;

    public void setService(Service service){
        this.service=service;
    }

    @FXML
    private TextField fieldTitlu;

    @FXML
    private TextField fieldAutor;

    @FXML
    private TextField fieldEditura;

    @FXML
    private TableView<Carte> tableCarti;

    @FXML
    private TableColumn<Carte, String> columnTitlu;

    @FXML
    private TableColumn<Carte, String> columnAutor;

    @FXML
    private TableColumn<Carte, String> columnEditura;

    @FXML
    private TableColumn<Carte, String> columnStatus;

    ObservableList<Carte> carti = FXCollections.observableArrayList();

    @FXML
    public void initialize() {
        columnTitlu.setCellValueFactory(new PropertyValueFactory<Carte, String>("titlu"));
        columnAutor.setCellValueFactory(new PropertyValueFactory<Carte, String>("autor"));
        columnEditura.setCellValueFactory(new PropertyValueFactory<Carte, String>("editura"));
        columnStatus.setCellValueFactory(new PropertyValueFactory<Carte, String>("status"));

        tableCarti.setItems(carti);

    }

    @FXML
    public void onCautaButtonClick(ActionEvent actionEvent) throws IOException {
        String titlu = fieldTitlu.getText();
        String autor = fieldAutor.getText();

        List<Carte> carti1 = service.findCarte(titlu, autor);
        System.out.println(carti1);
        tableCarti.getItems().clear();
        carti.addAll(carti1);
        tableCarti.setItems(carti);

    }

    @FXML
    public void onReturnareButtonClick(ActionEvent actionEvent) throws IOException {

        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("/Loginangajat.fxml"));
        AnchorPane root = loader.load();
        LoginController controller = loader.getController();
        controller.setService(this.service);

        Scene scene = new Scene(root, 600, 400);


        Stage newWindow = new Stage();
        newWindow.setTitle("Returnare");
        newWindow.setScene(scene);
        newWindow.show();
        ((Node)(actionEvent.getSource())).getScene().getWindow().hide();

        //initialize();
        //for (int i = 0; i<excursii.size(); i++){
        //   listExcursii1.getItems().add(excursii.get(i));
        // }

    }

    @FXML
    public void onImprumutareButtonClick(ActionEvent actionEvent) throws IOException {
        String titlu = fieldTitlu.getText();
        String autor = fieldAutor.getText();
        String editura = fieldEditura.getText();

        Carte carte = service.findById(titlu);
        //carte.setStatus("imprumutata");
        service.updateStatus("imprumutata", carte);
        initialize();
        //for (int i = 0; i<excursii.size(); i++){
        //   listExcursii1.getItems().add(excursii.get(i));
        // }

    }
}
