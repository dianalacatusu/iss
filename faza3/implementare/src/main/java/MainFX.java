
import controller.LoginAbonatController;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import org.hibernate.SessionFactory;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import repository.RepoAngajat;
import repository.RepoCarte;
import repository.RepositoryAngajat;
import repository.RepositoryCarte;
import service.Service;

import java.util.Properties;

public class MainFX extends Application {


    private static SessionFactory sessionFactory;

    static void initialize() {
        // A SessionFactory is set up once for an application!
        final StandardServiceRegistry registry = new StandardServiceRegistryBuilder()
                .configure() // configures settings from hibernate.cfg.xml
                .build();
        try {
            sessionFactory = new MetadataSources( registry ).buildMetadata().buildSessionFactory();
        }
        catch (Exception e) {
            System.err.println("Exceptie "+e);
            StandardServiceRegistryBuilder.destroy( registry );
        }
    }

    static void close() {
        if ( sessionFactory != null ) {
            sessionFactory.close();
        }
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        Properties serverProps=new Properties();

        initialize();

        RepositoryAngajat repoAngajat = new RepoAngajat(sessionFactory);
        RepositoryCarte repoCarte = new RepoCarte(sessionFactory);

        Service service = new Service(repoAngajat, repoCarte);

        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("/loginabonat.fxml"));
        Parent loginWindow = loader.load();
        LoginAbonatController loginAbonatController = loader.getController();
        loginAbonatController.setService(service);
        primaryStage.setOnCloseRequest(event -> System.exit(0));
        primaryStage.setTitle("Biblioteca");
        primaryStage.setScene(new Scene(loginWindow, 600, 400));
        primaryStage.show();
    }

        public static void main(String[] args) {
            try {
                Application.launch(args);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }


}