package lk.ijse.carrentalmanagementsystem;



import javafx.animation.PauseTransition;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.net.URL;


public class AppInitializer extends Application {

    public static Stage mainStage;

    public static void main(String[] args) {
        launch(args);
    }
    @Override
    public void start(Stage stage) throws Exception {
        Parent loginRoot = FXMLLoader.load(getClass().getResource("/lk/ijse/carrentalmanagementsystem/view/LoginForm.fxml"));
        Stage logStage= new Stage();
        logStage.setScene(new Scene(loginRoot,1366,768));
        this.mainStage=logStage;

        PauseTransition delay = new PauseTransition(Duration.seconds(2));
        delay.setOnFinished( event -> stage.close() );
        delay.play();
        PauseTransition delayLog = new PauseTransition(Duration.seconds(2));
        delayLog.setOnFinished( event -> logStage.show() );
        delayLog.play();
    }
}
