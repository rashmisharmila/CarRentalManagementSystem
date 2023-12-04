package lk.ijse.carrentalmanagementsystem.controller;


import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.scene.web.WebEngine;
import javafx.scene.web.WebView;
import lk.ijse.carrentalmanagementsystem.util.CrudUtil;
import lk.ijse.carrentalmanagementsystem.util.Router;

import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Date;


import static lk.ijse.carrentalmanagementsystem.util.Navigation.navigate;

public class UserDashBoard {
    public AnchorPane mainApane;
    public Button btnCustomer;
    public Button btnReservation;
    public Button btnLogout;
    public Button btnPayment;
    public AnchorPane subAnchorPaneDb;
    public Label lbVehicleCount;
    public Label lbDriverCount;
    public WebView webView;
    public Label lblcustomercount;
    public Label lblDate;
    public Label lblTime;
    public Button btndb;
    public WebEngine webEngine;
    private volatile boolean stop;
    public void initialize() throws SQLException, ClassNotFoundException {
        dispalyValueofVahicle();
        dispalyValueofDriver();
        dispalyValueofCustomer();
        loadmap();
        displayDateAndTime();


    }
    private void dispalyValueofVahicle() throws SQLException, ClassNotFoundException {
        ResultSet resultSet= CrudUtil.execute("SELECT count(vehicleId) FROM Vehicle");
        if (resultSet.next()){
            lbVehicleCount.setText(String.valueOf(resultSet.getInt(1)));
        }
    }

    private void dispalyValueofDriver() throws SQLException, ClassNotFoundException {
        ResultSet resultSet= CrudUtil.execute("SELECT count(driverId) FROM Driver");
        if (resultSet.next()){
            lbDriverCount.setText(String.valueOf(resultSet.getInt(1)));
        }
    }

    private void dispalyValueofCustomer() throws SQLException, ClassNotFoundException {
        ResultSet resultSet= CrudUtil.execute("SELECT count(customerId) FROM customer");
        if (resultSet.next()){
            lblcustomercount.setText(String.valueOf(resultSet.getInt(1)));
        }
    }

    private void displayDateAndTime() {
        Thread thread = new Thread(() -> {
            SimpleDateFormat time= new SimpleDateFormat("hh:mm:ss");
            SimpleDateFormat date = new SimpleDateFormat("dd/MM/yyyy");
            while (!stop) {
                try {
                    Thread.sleep(1000);
                } catch (Exception e) {

                }
                final String timenow = time.format(new Date());
                Platform.runLater(() -> {
                    lblTime.setText(timenow);
                });
                final String dateNow = date.format(new Date());
                Platform.runLater(() -> {
                    lblDate.setText(dateNow);
                });
            }
        });
        thread.start();
    }

    private void loadmap() {
        webEngine=webView.getEngine();
        webEngine.load("https://www.googlemaps.com");
    }
    public void btndbOnAction(ActionEvent actionEvent) throws IOException {
        navigate(Router.USERDASHBOARD,mainApane);
        loadmap();
    }

    public void btnCustomerOnAction(ActionEvent actionEvent) throws IOException {
        navigate(Router.CUSTOMER,subAnchorPaneDb);
    }

    public void btnReservationOnAction(ActionEvent actionEvent) throws IOException {
        navigate(Router.RESERVATION,subAnchorPaneDb);
    }

    public void btnLogoutOnAction(ActionEvent actionEvent) throws IOException {
        navigate(Router.LOGOUT,mainApane);
    }

    public void btnPaymentOnAction(ActionEvent actionEvent) throws IOException {
        navigate(Router.PAYMENT,subAnchorPaneDb);
    }
}
