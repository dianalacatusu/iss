package controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

import model.Abonat;
import model.Carte;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import service.Service;


import java.io.IOException;
import java.util.List;
import java.util.Objects;

public class MainPageAngajatController {

    private Service servicesEx;

    public MainPageAngajatController() throws IOException {
    }

    public void setService(Service service) {
        this.servicesEx = service;
    }



    @FXML
    private TextField fieldCnp;


    @FXML
    private TextField fieldNume;

    @FXML
    private TextField fieldTelefon;

    @FXML
    private TextField fieldAdresa;

    @FXML
    private TextField fieldTitlu;

    @FXML
    private TextField fieldAutor;

    @FXML
    private TextField fieldEditura;


    @FXML
    private Button btnCauta;

    @FXML
    private Button btnRezervare;

    @FXML
    private Button btnLogout;

    @FXML
    private TableView<Abonat> tableView;

    @FXML
    private TableColumn<Abonat, String> columnCnp;

    @FXML
    private TableColumn<Abonat, String> columnNume;

    @FXML
    private TableColumn<Abonat, Integer> columnAdresa;

    @FXML
    private TableColumn<Abonat, String> columnTelefon;


    ObservableList<Abonat> abonati = FXCollections.observableArrayList();

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




    static Service getService() throws IOException {
        // pentru configurare folosind XML
        //ApplicationContext context=new ClassPathXmlApplicationContext("LoginConfig.xml");

        //pentru configurare folosind JavaConfig

        ApplicationContext context = new AnnotationConfigApplicationContext(Config.class);
        Service services = context.getBean(Service.class);

        return services;
    }

    @FXML
    public void initialize() {



    }

    public void populateTableCarti() {

        List<Carte> carti = servicesEx.findCarti();

        tableCarti.getItems().clear();
        tableCarti.getItems().addAll(carti);

    }


    @FXML
    public void onCautaButtonClick(ActionEvent actionEvent) throws IOException {
        String cnp = fieldCnp.getText();

        Abonat abonat = servicesEx.cautaAbonat(cnp);
        tableView.getItems().clear();
        tableView.getItems().addAll(abonat);
        //for (int i = 0; i<excursii.size(); i++){
         //   listExcursii1.getItems().add(excursii.get(i));
       // }

    }

    @FXML
    public void onAdaugaButtonClick(ActionEvent actionEvent) throws IOException {
        String cnp = fieldCnp.getText();
        String nume = fieldNume.getText();
        String telefon = fieldTelefon.getText();
        String adresa  = fieldAdresa.getText();
        if(servicesEx.cautaAbonat(cnp)==null){
            Abonat abonat = new Abonat(cnp, nume, adresa, telefon);
            servicesEx.adaugaAbonat(abonat);
        }
        else {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error ");
            alert.setContentText("Abonatul exista");
            alert.showAndWait();
        }

    }

    @FXML
    public void onStergeButtonClick(ActionEvent actionEvent) throws IOException {
        String cnp = fieldCnp.getText();
        servicesEx.stergeAbonat(cnp);
    }

    @FXML
    public void onModificaButtonClick(ActionEvent actionEvent) throws IOException {
        String cnp = fieldCnp.getText();
        String nume = fieldNume.getText();
        String telefon = fieldTelefon.getText();
        String adresa  = fieldAdresa.getText();
        Abonat abonat = new Abonat(cnp, nume, adresa, telefon);
        servicesEx.actualizareAbonat(abonat.getCnp(), abonat);
        tableView.getItems().clear();
        tableView.setItems(abonati);

    }

    @FXML
    public void onReturnareButtonClick(ActionEvent actionEvent) throws IOException {
        String titlu = fieldTitlu.getText();
        String autor = fieldAutor.getText();
        String editura = fieldEditura.getText();

        Carte carte = servicesEx.findById(titlu);
        //carte.setStatus("imprumutata");
        if(Objects.equals(carte.getStatus(), "imprumutata"))
            servicesEx.updateStatus("disponibila", carte);
        else{
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setContentText("Cartea este disponibila");
            alert.showAndWait();
        }
        populateTableCarti();
    }

}
