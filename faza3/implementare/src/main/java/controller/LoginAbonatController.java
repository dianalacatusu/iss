package controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import model.Abonat;
import model.Carte;
import model.Status;
import service.Service;

import java.io.IOException;


public class LoginAbonatController {

    private Service service;

    public void setService(Service service){
        this.service=service;
    }

    @FXML
    private TextField fieldCnp;



    @FXML
    public void onLoginAbonatClick(ActionEvent actionEvent) throws IOException {

        String cnp = fieldCnp.getText();
        if(cnp.equals("")) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error ");
            alert.setContentText("Please enter your cnp!");
            alert.showAndWait();
        }
        else
        {
            if(service.loginAbonat(cnp) != null){
                System.out.println("ok");
                fieldCnp.clear();
                FXMLLoader loader = new FXMLLoader();
                loader.setLocation(getClass().getResource("/Returnarecarte.fxml"));
                AnchorPane root = loader.load();
                CartiController controller = loader.getController();
                controller.setService(this.service);
                controller.initialize();
                Scene scene = new Scene(root, 818, 400);


                Stage newWindow = new Stage();
                newWindow.setTitle("Imprumut");
                newWindow.setScene(scene);
                newWindow.show();
                ((Node)(actionEvent.getSource())).getScene().getWindow().hide();
            }
            else
            {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Error ");
                alert.setContentText("Please enter the cnp!");
                alert.showAndWait();



            }
        }
    }
}
