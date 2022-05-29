
import com.example.pb1.HelloApplication;
import controller.Config;
import controller.LoginController;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.stage.Stage;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import service.Service;

import javax.net.ssl.HandshakeCompletedEvent;
import java.io.IOException;

public class MainFX extends Application {
    @Override
    public void start(Stage primaryStage) throws Exception {
        FXMLLoader loader = new FXMLLoader(HelloApplication.class.getResource("Loginangajat.fxml"));
        Scene scene = new Scene(loader.load(), 600, 400);
        LoginController ctrl = loader.getController();
        ctrl.setService(getService());
        primaryStage.setTitle("Login");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

        public static void main(String[] args) {
            launch(args);
        }

    static Service getService() throws IOException {
        // pentru configurare folosind XML
        //ApplicationContext context=new ClassPathXmlApplicationContext("LoginConfig.xml");

        //pentru configurare folosind JavaConfig
        ApplicationContext context = new AnnotationConfigApplicationContext(Config.class);
        Service services = context.getBean(Service.class);
        return services;
    }
}