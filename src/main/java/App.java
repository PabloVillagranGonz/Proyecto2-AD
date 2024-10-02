import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import org.example.proyectoad.Controller.AppController;
import org.example.proyectoad.util.R;

public class App extends Application {

    @Override
    public void start(Stage stage) throws Exception {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(R.getUI("concierto2.fxml"));
        VBox loginBox = loader.load();

        Scene loginScene = new Scene(loginBox);
        stage.setScene(loginScene);
        stage.setTitle("Login");
        stage.show();

        // Controlador para la pantalla de login
        AppController appController = loader.getController();
        appController.cargarDatos();
    }

    public void showMainScreen() throws Exception {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(R.getUI("concierto.fxml"));
        VBox mainBox = loader.load();

        Scene mainScene = new Scene(mainBox);
        Stage stage = (Stage) mainBox.getScene().getWindow();
        stage.setScene(mainScene);
        stage.setTitle("Pantalla Principal");
        stage.show();
    }

    @Override
    public void stop() throws Exception {
        super.stop();
    }

    public static void main(String[] args) {
        launch();
        /*Properties properties= new Properties();
        String host="";
        String port="";
        String name="";
        String username="";
        String password="";
        try {
            properties.load(new FileInputStream(new File("src/main/resources/configuration/database.properties")));

            //System.out.println(properties.get("driver"));
            host=String.valueOf(properties.get("host"));
            port=String.valueOf(properties.get("port"));
            name=String.valueOf(properties.get("name"));
            username=String.valueOf(properties.get("username"));
            password=String.valueOf(properties.get("password"));
            System.out.println(host+"  "+port+"  "+name+"  "+username+"  "+password);
        } catch (FileNotFoundException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

         */
    }
}
