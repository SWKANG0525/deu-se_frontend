package deu.se.team4.deu_se_frontend;

import deu.se.team4.deu_se_frontend.model.APICenter;
import deu.se.team4.deu_se_frontend.model.ParkModel;
import javafx.application.Application;
import static javafx.application.Application.launch;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.text.Font;
import javafx.stage.Stage;

public class MainApp extends Application {

    VersionState version_state;

    @Override
    public void start(Stage stage) throws Exception {
        try {
            Parent root = FXMLLoader.load(getClass().getResource(APICenter.getInstance().isVersionVaild()));
            setState(new VersionCheckSuccessContext());
            version_state.versionCheckProcess();
            Scene scene = new Scene(root);
            stage.setScene(scene);
            
        } catch (NullPointerException e) {

            setState(new VersionCheckVaildContext());
            version_state.versionCheckProcess();
            stage.close();

        }

      
        stage.setTitle("A-INFO");
        stage.setResizable(false);

        stage.show();

        Font.loadFont(getClass().getResource("/font/NanumSquareB.ttf").toExternalForm(), 15);
        Font.loadFont(getClass().getResource("/font/NanumSquareR.ttf").toExternalForm(), 15);

    }

    public void setState(final VersionState version_state) {
        this.version_state = version_state;
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

    public class VersionCheckSuccessContext implements VersionState {

        @Override
        public void versionCheckProcess() {
            System.out.println("무결성 검증 완료.");
            // 빌드 번호 출력
            // 버전 번호 출력
        }

    }

    public class VersionCheckVaildContext implements VersionState {

        @Override
        public void versionCheckProcess() {
            System.out.println("서버와 클라이언트의 버전이 다릅니다.");
            System.out.println("프로그램을 종료합니다.");
            System.exit(0);

        }

    }
}
