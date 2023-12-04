package lk.ijse.carrentalmanagementsystem.controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.embed.swing.SwingFXUtils;
import javafx.event.ActionEvent;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import lk.ijse.carrentalmanagementsystem.model.ReservationModel;
import lk.ijse.carrentalmanagementsystem.model.UserModel;
import lk.ijse.carrentalmanagementsystem.model.VehicleModel;
import lk.ijse.carrentalmanagementsystem.to.*;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;

public class ManageReservation {
    public ComboBox<String> cmbVehicleIds;
    public TextField txtTransmissionType;
    public TextField txtVehiclePrice;
    public TextField txtvehicleName;
    public TextField txtFuelType;
    public ComboBox<String> cmbDriveId;
    public ComboBox<String> cmbCustomerId;
    public TextField txtDriverName;
    public TextField txtDriverPhoneNumber;
    public TextField txtDriverValue;
    public TextField txtCustomerName;
    public TextField txtCustomerAddress;
    public TextField txtCustomerPhoneNumber;
    public TextField txtCustomerNIC;
    public TextField TxtKms;
    public DatePicker endDate;
    public TextField txtDays;
    public TextField txtLocation;
    public Label lblReservationId;
    public Label lblTotal;
    public DatePicker startDate;
    public TableView<Reservation> tblReservation;
    public TableColumn tblId;
    public TableColumn tblVehicleID;
    public TableColumn tblDriverId;
    public TableColumn tblCustomerID;
    public TableColumn tblStartDate;
    public TableColumn tblEndDate;
    public TableColumn tblTotalKm;
    public TableColumn tblDays;
    public TableColumn tblLocation;
    public TableColumn tblPayement;
    public TextField txtSearch;
    public ImageView imageOfVehicle;


    public void initialize() {
        generateReservationId();
        setCellFactories();
        loadDataToTable();
        AddListener();
        loadVehicleIdToCmb();
        loadDriverIdToCmb();
        loadCustomerIdToCmb();

        setCustomerDetails();
        setDriverDetails();
        setVehicleDetails();
    }

    private void setVehicleDetails() {
        Vehicle vehicle = null;
        try {
            vehicle = ReservationModel.getVehicleDetails(cmbVehicleIds.getValue());
            if (vehicle != null) {
                txtvehicleName.setText(vehicle.getVehicleName());
                txtFuelType.setText(String.valueOf(vehicle.getVehicleFuelType()));
                txtTransmissionType.setText(vehicle.getVehicleTransmission());
                txtVehiclePrice.setText(String.valueOf(vehicle.getVehiclePrice()));
                setImage(vehicle.getImage());
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    private void setImage(String image) {
        try {
            File file = new File(image);
            BufferedImage bufferedImage = ImageIO.read(file);
            Image image1 = SwingFXUtils.toFXImage(bufferedImage, null);
            imageOfVehicle.setImage(image1);
        } catch (IOException e) {
            e.printStackTrace();

        }
    }

    private void setDriverDetails() {
        Driver driver = null;
        try {
            driver = ReservationModel.getDriverDetails(cmbDriveId.getValue());
            if (driver != null) {
                txtDriverName.setText(driver.getDriverName());
                txtDriverPhoneNumber.setText(driver.getDriverPhoneNumber());
                txtDriverValue.setText(String.valueOf(driver.getPreDayValue()));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    private void setCustomerDetails() {
        Customer customer = null;
        try {
            customer = ReservationModel.getCustomerDetails(cmbCustomerId.getValue());
            if (customer != null) {
                txtCustomerName.setText(customer.getCustomerName());
                txtCustomerAddress.setText(customer.getCustomerAddress());
                txtCustomerPhoneNumber.setText(customer.getCustomerPhoneNumber());
                txtCustomerNIC.setText(customer.getCustomerNIC());
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    private void loadCustomerIdToCmb() {
        ArrayList<String> cusomerId = null;
        try {
            cusomerId = ReservationModel.loadCustomerid();
            ObservableList<String> observableList = FXCollections.observableArrayList();
            for (String id : cusomerId) {
                observableList.add(id);
            }
            cmbCustomerId.setItems(observableList);
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    private void loadDriverIdToCmb() {
        ArrayList<String> driverId = null;
        try {
            driverId = ReservationModel.loadDriverId();
            ObservableList<String> observableList = FXCollections.observableArrayList();
            for (String id : driverId) {
                observableList.add(id);
            }
            cmbDriveId.setItems(observableList);
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    private void loadVehicleIdToCmb() {
        ArrayList<String> vehicleId = null;
        try {
            vehicleId = ReservationModel.loadVehicleId();
            ObservableList<String> observableList = FXCollections.observableArrayList();
            for (String id : vehicleId) {
                observableList.add(id);
            }
            cmbVehicleIds.setItems(observableList);
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }


    }

    private void AddListener() {
        tblReservation.getSelectionModel().selectedItemProperty().addListener((observableValue, oldValue, newValue) -> {
            if (newValue != null) {
                loadDataToTextField(newValue);
            }
        });
    }

    private void loadDataToTextField(Reservation reservation) {
        lblReservationId.setText(reservation.getReservationId());
        cmbVehicleIds.setValue(reservation.getVehicleId());
        cmbCustomerId.setValue(reservation.getCustomerId());
        cmbDriveId.setValue(reservation.getDriverId());
        startDate.setValue(reservation.getStartDate());
        endDate.setValue(reservation.getEndDate());
        TxtKms.setText(String.valueOf(reservation.getTotalKm()));
        txtDays.setText(String.valueOf(reservation.getTotalDays()));
        txtLocation.setText(reservation.getLocation());
        lblTotal.setText(String.valueOf(reservation.getBasicAmount()));
    }

    private void loadDataToTable() {
        ObservableList<Reservation> reservationObservableList = FXCollections.observableArrayList();

        ArrayList<Reservation> getReservation = null;
        try {
            getReservation = ReservationModel.getAllReservation();

            for (Reservation reservation : getReservation) {
                Reservation reservation1 = new Reservation(
                        reservation.getReservationId(),
                        reservation.getCustomerId(),
                        reservation.getVehicleId(),
                        reservation.getDriverId(),
                        reservation.getStartDate(),
                        reservation.getEndDate(),
                        reservation.getLocation(),
                        reservation.getTotalKm(),
                        reservation.getTotalDays(),
                        reservation.getBasicAmount());
                reservationObservableList.add(reservation1);
            }
            tblReservation.setItems(reservationObservableList);
            tblReservation.refresh();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    private void setCellFactories() {
        tblId.setCellValueFactory(new PropertyValueFactory<>("reservationId"));
        tblCustomerID.setCellValueFactory(new PropertyValueFactory<>("customerId"));
        tblVehicleID.setCellValueFactory(new PropertyValueFactory<>("vehicleId"));
        tblDriverId.setCellValueFactory(new PropertyValueFactory<>("driverId"));
        tblStartDate.setCellValueFactory(new PropertyValueFactory<>("startDate"));
        tblEndDate.setCellValueFactory(new PropertyValueFactory<>("endDate"));
        tblTotalKm.setCellValueFactory(new PropertyValueFactory<>("location"));
        tblDays.setCellValueFactory(new PropertyValueFactory<>("totalKm"));
        tblLocation.setCellValueFactory(new PropertyValueFactory<>("totalDays"));
        tblPayement.setCellValueFactory(new PropertyValueFactory<>("basicAmount"));


    }

    private void generateReservationId() {
        String lastId = null;
        try {
            lastId = ReservationModel.getLastId();
            if (lastId == null) {
                lblReservationId.setText("R001");
            } else {
                String[] split = lastId.split("[R]");
                int lastDigit = Integer.parseInt(split[1]);
                lastDigit++;
                String newId = String.format("R%03d", lastDigit);
                lblReservationId.setText(newId);
            }
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }


    public void updateOnAction(ActionEvent actionEvent) throws SQLException, ClassNotFoundException {
        String reservationId = lblReservationId.getText();
        String customerId = cmbCustomerId.getValue();
        String vehicleId = cmbVehicleIds.getValue();
        String driverId = cmbDriveId.getValue();
        LocalDate sDate = startDate.getValue();
        LocalDate eDate = endDate.getValue();
        String location = txtLocation.getText();

        double totalkms = Double.parseDouble(TxtKms.getText());
        int days = Integer.parseInt(txtDays.getText());
        double vehiclePrice = Double.parseDouble(txtVehiclePrice.getText());
        double payment = totalkms * days * vehiclePrice;
        lblTotal.setText(String.valueOf(payment));


        Reservation reservation = new Reservation(
                reservationId,
                customerId,
                vehicleId,
                driverId,
                sDate,
                eDate,
                location,
                totalkms,
                days,
                payment
        );
        if (location == null || totalkms == 0 || days == 0 || sDate == null || eDate == null) {
            new Alert(Alert.AlertType.ERROR, "Please fill this Details!");

        } else if (ReservationModel.updateReservation(reservation)) {
            new Alert(Alert.AlertType.CONFIRMATION, "Save Reservation Details...!");
            loadDataToTable();
            generateReservationId();
            clearAllTextFiled();
            loadCustomerIdToCmb();
            loadDriverIdToCmb();
            loadVehicleIdToCmb();
        } else {
            new Alert(Alert.AlertType.WARNING, "Save Fail...!").show();

        }
    }

    public void saveOnAction(ActionEvent actionEvent) throws SQLException, ClassNotFoundException {
        String reservationId = lblReservationId.getText();
        String customerId = cmbCustomerId.getValue();
        String vehicleId = cmbVehicleIds.getValue();
        String driverId = cmbDriveId.getValue();
        LocalDate sDate = startDate.getValue();
        LocalDate eDate = endDate.getValue();
        String location = txtLocation.getText();
        double driverPrice= Double.parseDouble(txtDriverValue.getText());

        double totalkms = Double.parseDouble(TxtKms.getText());
        int days = Integer.parseInt(txtDays.getText());
        double vehiclePrice = Double.parseDouble(txtVehiclePrice.getText());
        double payment = (days * vehiclePrice)+totalkms+driverPrice;
        lblTotal.setText(String.valueOf(payment));


        Reservation reservation = new Reservation(
                reservationId,
                customerId,
                vehicleId,
                driverId,
                sDate,
                eDate,
                location,
                totalkms,
                days,
                payment
        );
        if (location == null || totalkms == 0 || days == 0 || sDate == null || eDate == null) {
            new Alert(Alert.AlertType.ERROR, "Please fill this Details!");

        } else if (ReservationModel.saveReservation(reservation)) {
            new Alert(Alert.AlertType.CONFIRMATION, "Save Reservation Details...!");
            loadDataToTable();
            generateReservationId();
            clearAllTextFiled();
            loadCustomerIdToCmb();
            loadDriverIdToCmb();
            loadVehicleIdToCmb();
        } else {
            new Alert(Alert.AlertType.WARNING, "Save Fail...!").show();

        }
    }


    public void cmbVehicleIdsOnAction(ActionEvent actionEvent) {
        setVehicleDetails();

    }

    public void cmbDriveIdOnAction(ActionEvent actionEvent) {
        setDriverDetails();
    }

    public void cmbCustomerIdOnAction(ActionEvent actionEvent) {
        setCustomerDetails();
    }

    public void addToCartOnAction(ActionEvent actionEvent) {
        double driverPrice= Double.parseDouble(txtDriverValue.getText());
        double totalkms = Double.parseDouble(TxtKms.getText());
        int days = Integer.parseInt(txtDays.getText());
        double vehiclePrice = Double.parseDouble(txtVehiclePrice.getText());
        double payment = (days * vehiclePrice)+totalkms+driverPrice;

        lblTotal.setText(String.valueOf(payment));
    }

    private void clearAllTextFiled() {
        cmbCustomerId.getItems().clear();
        cmbVehicleIds.getItems().clear();
        cmbDriveId.getItems().clear();
        startDate.setValue(null);
        endDate.setValue(null);
        txtLocation.clear();
        TxtKms.clear();
        txtDays.clear();
        lblTotal.setText(null);

        txtvehicleName.clear();
        txtVehiclePrice.clear();
        txtFuelType.clear();
        txtTransmissionType.clear();
        imageOfVehicle.setImage(null);

        txtCustomerName.clear();
        txtCustomerAddress.clear();
        txtCustomerNIC.clear();
        txtCustomerPhoneNumber.clear();

        txtDriverName.clear();
        txtDriverPhoneNumber.clear();
        txtDriverValue.clear();
    }
}
