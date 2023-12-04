package lk.ijse.carrentalmanagementsystem.controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.embed.swing.SwingFXUtils;
import javafx.event.ActionEvent;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.FileChooser;
import lk.ijse.carrentalmanagementsystem.model.PaymentModel;
import lk.ijse.carrentalmanagementsystem.model.ReservationModel;
import lk.ijse.carrentalmanagementsystem.to.Customer;
import lk.ijse.carrentalmanagementsystem.to.Payment;
import lk.ijse.carrentalmanagementsystem.to.Reservation;
import rex.utils.S;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;

public class ManagePayment {
    public ComboBox<String> cmdRservationIds;
    public Label lblVehicleName;
    public Label lblCustomerName;
    public Label lblTotalPayment;
    public Label lblAmount;
    public TableView<Payment> tblPayment;
    public TableColumn tblPaymentId;
    public TableColumn tbldate;
    public TableColumn tbltime;
    public TableColumn tblamount;
    public Label lblPaymentId;
    public TableColumn tblreservation;
    public ImageView imageOfPaymentSlip;
    public Button btnfileChooser;
    public String imageName;
    public void initialize() {
        generatePaymentId();
        loadResevationToCmb();
        setReservationDetails();

    }

    private void setReservationDetails() {
        Reservation reservation = null;
        try {
                reservation = PaymentModel.getReservation(cmdRservationIds.getValue());
                if (reservation != null) {
                    lblVehicleName.setText(reservation.getVehicleId());
                    lblCustomerName.setText(reservation.getCustomerId());
                    lblAmount.setText(String.valueOf(reservation.getBasicAmount()));
                    System.out.println(reservation);
                }

        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    private void loadResevationToCmb() {
        ArrayList<String> reservationId = null;
        try {
            reservationId = PaymentModel.loadReservationIds();
            ObservableList<String> observableList = FXCollections.observableArrayList();
            for (String id : reservationId) {
                observableList.add(id);
            }
            cmdRservationIds.setItems(observableList);
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

    }


    private void generatePaymentId() {
        String lastId = null;
        try {
            lastId = PaymentModel.getLastId();
            if (lastId == null) {
                lblPaymentId.setText("P001");
            } else {
                String[] split = lastId.split("[P]");
                int lastDigit = Integer.parseInt(split[1]);
                lastDigit++;
                String newId = String.format("P%03d", lastDigit);
                lblPaymentId.setText(newId);
            }
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public void printOnAction(ActionEvent actionEvent) {
    }

    public void saveOnAction(ActionEvent actionEvent) throws SQLException, ClassNotFoundException {
        String paymentId=lblPaymentId.getText();
        String reservationIds= String.valueOf(cmdRservationIds.getValue());
        LocalDate date= LocalDate.now();
        LocalTime time= LocalTime.now();
        double payment= Double.parseDouble(lblAmount.getText());

        Payment payment1=new Payment(
                paymentId,
                reservationIds,
                date,
                time,
                payment,
                imageName
        );

        if (PaymentModel.savePayment(payment1)){
            new Alert(Alert.AlertType.CONFIRMATION, "Payment Successfully...!").show();
            generatePaymentId();
            loadResevationToCmb();
        }else {
            new Alert(Alert.AlertType.WARNING, "Payment Fail...!").show();
        }
    }


    public void onActionOpenBtnFileChooser(ActionEvent actionEvent) {
        FileChooser fileChooser = new FileChooser();
        FileChooser.ExtensionFilter extensionFilterPNG = new FileChooser.ExtensionFilter("PNG files (*.png)", "*.PNG");
        FileChooser.ExtensionFilter extensionFilterJPG = new FileChooser.ExtensionFilter("JPG files (*.jpg)", "*.JPG");

        fileChooser.getExtensionFilters().addAll(extensionFilterPNG, extensionFilterJPG);
        File file = fileChooser.showOpenDialog(null);
        imageName = file.getAbsolutePath();

        try {
            BufferedImage bufferedImage = ImageIO.read(file);
            Image image = SwingFXUtils.toFXImage(bufferedImage, null);
            imageOfPaymentSlip.setImage(image);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }

    public void cmbReservationIdsOnAction(ActionEvent actionEvent) {
        setReservationDetails();
    }
}
