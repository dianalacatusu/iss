package controller;



import com.example.pb1.HelloApplication;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import model.Angajat;
import service.Service;


import java.io.IOException;
import java.util.logging.Level;


public class LoginController {

    private Service service;

    public void setService(Service service){
        this.service=service;
    }

    @FXML
    private Button btnLogin;

    @FXML
    private TextField fieldUsername;

    @FXML
    private TextField fieldPassword;



    @FXML
    public void onLoginButtonClick(ActionEvent actionEvent) throws IOException {

        String textUsername = fieldUsername.getText();
        String textPassword = fieldPassword.getText();
        if(textUsername.equals("") || textPassword.equals("")) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error ");
            alert.setContentText("Please enter the username and password!");
            alert.showAndWait();
        }
        else
        {
            Angajat angajat= new Angajat(textUsername, textPassword);
            if(service.loginAngajat(angajat.getUsername(), angajat.getPassword()) != null){
                System.out.println("ok");
                fieldUsername.clear();
                fieldPassword.clear();
                FXMLLoader loader = new FXMLLoader(HelloApplication.class.getResource("MainPageAngajat.fxml"));
                Scene scene = new Scene(loader.load(), 672, 506);


                Stage newWindow = new Stage();
                newWindow.setTitle("Angajat");
                newWindow.setScene(scene);
                newWindow.show();
                ((Node)(actionEvent.getSource())).getScene().getWindow().hide();
            }
            else
            {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Error ");
                alert.setContentText("Please enter the username and password!");
                alert.showAndWait();



            }
        }
    }


}
