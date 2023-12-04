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

public class DashboardController {
    public AnchorPane mainApane;
    public Button btndb;
    public Button btnvehicle;
    public Button btnDriver;
    public Button btnUser;
    public Button btnReports;
    public Button btnLogout;
    public AnchorPane subAnchorPaneDb;
    public Label lbVehicleCount;
    public Label lbDriverCount;
    public Label lbUsercount;
    public WebView webView;
    public WebEngine webEngine;
    public Label lblDate;
    public Label lblTime;
    public Label lblcustomercount;
    private volatile boolean stop;
    public void initialize() throws SQLException, ClassNotFoundException {
        dispalyValueofVahicle();
        dispalyValueofUser();
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

    private void dispalyValueofUser() throws SQLException, ClassNotFoundException {
        ResultSet resultSet= CrudUtil.execute("SELECT count(userId) FROM User");
        if (resultSet.next()){
            lbUsercount.setText(String.valueOf(resultSet.getInt(1)));
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
        navigate(Router.DASHBOARD,mainApane);
        loadmap();
    }

    public void btnvehicleOnAction(ActionEvent actionEvent) throws IOException {
        navigate(Router.VEHICLE,subAnchorPaneDb);
    }

    public void btnDriverOnAction(ActionEvent actionEvent) throws IOException {
        navigate(Router.DRIVER,subAnchorPaneDb);
    }

    public void btnUserOnAction(ActionEvent actionEvent) throws IOException {
        navigate(Router.USER,subAnchorPaneDb);
    }

    public void btnReportsOnAction(ActionEvent actionEvent) throws IOException {
        navigate(Router.REPORTS,subAnchorPaneDb);
    }

    public void btnLogoutOnAction(ActionEvent actionEvent) throws IOException {
        navigate(Router.LOGOUT,mainApane);
    }
}
