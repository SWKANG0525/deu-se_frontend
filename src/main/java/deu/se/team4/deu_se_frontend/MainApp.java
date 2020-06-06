package deu.se.team4.deu_se_frontend;

import deu.se.team4.deu_se_frontend.model.APICenter;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import javafx.application.Application;
import static javafx.application.Application.launch;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

public class MainApp extends Application {
    
    @Override
    public void start(Stage stage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource(APICenter.getInstance().isVersionVaild()));
        Scene scene = new Scene(root);
        stage.initStyle(StageStyle.UNDECORATED);
        stage.setTitle("A-INFO");
        stage.setScene(scene);
        stage.setResizable(false);
       
        stage.show();


        Font.loadFont(getClass().getResource("/font/NanumSquareB.ttf").toExternalForm(), 15);
        Font.loadFont(getClass().getResource("/font/NanumSquareR.ttf").toExternalForm(), 15);

    }

    /**
     * The main() method is ignored in correctly deployed JavaFX application.
     * main() serves only as fallback in case the application can not be
     * launched through deployment artifacts, e.g., in IDEs with limited FX
     * support. NetBeans ignores main().
     *
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        System.setProperty("prism.lcdtext", "false");
        System.setProperty("prism.text", "t2k");
        launch(args);

    }

    private void initalize() {

    }

}
