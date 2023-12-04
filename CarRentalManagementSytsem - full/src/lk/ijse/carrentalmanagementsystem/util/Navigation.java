package lk.ijse.carrentalmanagementsystem.util;

import javafx.fxml.FXMLLoader;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.IOException;

public class Navigation {

    private static AnchorPane pane;

    public static void navigate(Router router,AnchorPane pane)throws IOException {
        Navigation.pane = pane;
        Navigation.pane.getChildren().clear();
        Stage window = (Stage) Navigation.pane.getScene().getWindow();

        switch (router){
            case DASHBOARD:
                window.setTitle("CarMe");
                initUI("Dashboard.fxml");
                break;
            case VEHICLE:
                window.setTitle(("CarMe"));
                initUI("ManageVehicle.fxml");
                break;
            case DRIVER:
                window.setTitle(("CarMe"));
                initUI("ManageDriver.fxml");
                break;
            case REPORTS:
                window.setTitle(("CarMe"));
                initUI("Reports.fxml");
                break;
            case LOGOUT:
                window.setTitle(("CarMe"));
                initUI("LoginForm.fxml");
                break;
            case USERDASHBOARD:
                window.setTitle("CarMe");
                initUI("UserDashBorad.fxml");
                break;
            case CUSTOMER:
                window.setTitle(("CarMe"));
                initUI("ManageCustomer.fxml");
                break;
            case RESERVATION:
                window.setTitle(("CarMe"));
                initUI("ManageReservation.fxml");
                break;
            case PAYMENT:
                window.setTitle(("CarMe"));
                initUI("Payment.fxml");
                break;
            case USER:
                window.setTitle(("CarMe"));
                initUI("ManageUser.fxml");
                break;

        }
    }

    public static void initUI(String location) throws IOException{
        Navigation.pane.getChildren().add(FXMLLoader.load(Navigation.class.getResource("/lk/ijse/carrentalmanagementsystem/view/"+location)));
    }
}
