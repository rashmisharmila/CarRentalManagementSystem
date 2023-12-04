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
import lk.ijse.carrentalmanagementsystem.model.VehicleModel;
import lk.ijse.carrentalmanagementsystem.to.Vehicle;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;

public class ManageVehicle {
    public TextField txtvehicleName;
    public TextField txtvehicleModel;
    public TextField txtVehicleColor;
    public TextField txtvehicleId;
    public TextField txtVehicleLicenceId;
    public TextField txtliterofFuel;
    public TextField txtvehicleTransmission;
    public ComboBox<String> cmbFuelType;
    public TableView<Vehicle> tblVehicle;
    public TableColumn<Vehicle, String> tblId;
    public TableColumn<Vehicle, String> tblname;
    public TableColumn<Vehicle, String> tblmodel;
    public TableColumn<Vehicle, String> tblColour;
    public TableColumn<Vehicle, String> tblLiceneNumber;
    public TableColumn<Vehicle, String> tblFeulType;
    public TableColumn<Vehicle, String> tblLiterOfFuel;
    public TableColumn<Vehicle, String> tblTransmission;
    public ComboBox<String> cmdvehicleTransmission;
    public TextField txtvehiclePrice;
    public TableColumn<Vehicle, String> tblPrice;
    public Button btnfileChooser;
    public ImageView imgvehicle;
    public TextField txtSearch;
    public ComboBox<String> cmbStatus;
    public TableColumn<Vehicle, String> tblImage;
    public TableColumn<Vehicle, String> tblStatus;

    public String imageName;


    public void initialize() {
        generateVehicleId();
        loadStatus();
        loadTransmission();
        loadFuelType();
        setCellFactories();
        loadDataToTable();
        AddListener();
    }

    private void generateVehicleId() {
        String lastId = null;
        try {
            lastId = VehicleModel.getLastId();
            if (lastId == null) {
                txtvehicleId.setText("V001");
            } else {
                String[] split = lastId.split("[V]");
                int lastDigit = Integer.parseInt(split[1]);
                lastDigit++;
                String newId = String.format("V%03d", lastDigit);
                txtvehicleId.setText(newId);
            }
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }

    }


    private void loadFuelType() {
        ObservableList<String> fuelList = FXCollections.observableArrayList("Petrol", "Diesel");
        cmbFuelType.setItems(fuelList);
    }

    private void loadTransmission() {
        ObservableList<String> transmissionList = FXCollections.observableArrayList("Automatic", "Manual");
        cmdvehicleTransmission.setItems(transmissionList);
    }

    private void loadStatus() {
        ObservableList<String> statusList = FXCollections.observableArrayList("Available", "Unavailable");
        cmbStatus.setItems(statusList);
    }

    private void setCellFactories() {
        tblId.setCellValueFactory(new PropertyValueFactory<>("vehicleId"));
        tblname.setCellValueFactory(new PropertyValueFactory<>("vehicleName"));
        tblmodel.setCellValueFactory(new PropertyValueFactory<>("vehicleModel"));
        tblColour.setCellValueFactory(new PropertyValueFactory<>("vehicleColour"));
        tblLiceneNumber.setCellValueFactory(new PropertyValueFactory<>("vehicleLicenceId"));
        tblFeulType.setCellValueFactory(new PropertyValueFactory<>("vehicleFuelType"));
        tblLiterOfFuel.setCellValueFactory(new PropertyValueFactory<>("vehicleLiterOfFuel"));
        tblTransmission.setCellValueFactory(new PropertyValueFactory<>("vehicleTransmission"));
        tblPrice.setCellValueFactory(new PropertyValueFactory<>("vehiclePrice"));
        tblImage.setCellValueFactory(new PropertyValueFactory<>("image"));
        tblStatus.setCellValueFactory(new PropertyValueFactory<>("status"));
    }

    private void AddListener() {
        tblVehicle.getSelectionModel().selectedItemProperty().addListener((observableValue, oldValue, newValue) -> {
            if (newValue != null) {
                loadDataToTextField(newValue);
            }
        });
    }

    private void loadDataToTextField(Vehicle vehicle) {
        txtvehicleId.setText(vehicle.getVehicleId());
        txtvehicleName.setText(vehicle.getVehicleName());
        txtvehicleModel.setText(vehicle.getVehicleModel());
        txtVehicleColor.setText(vehicle.getVehicleColour());
        txtVehicleLicenceId.setText(vehicle.getVehicleLicenceId());
        cmbFuelType.setValue(vehicle.getVehicleFuelType());
        txtliterofFuel.setText(String.valueOf(vehicle.getVehicleLiterOfFuel()));
        cmdvehicleTransmission.setValue(vehicle.getVehicleTransmission());
        txtvehiclePrice.setText(String.valueOf(vehicle.getVehiclePrice()));
        setPic(vehicle.getImage());
        cmbStatus.setValue(vehicle.getStatus());
    }

    private void setPic(String image) {
        try {
            File file = new File(image);
            BufferedImage bufferedImage = ImageIO.read(file);
            Image image1 = SwingFXUtils.toFXImage(bufferedImage, null);
            imgvehicle.setImage(image1);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }


    private void loadDataToTable() {
        ObservableList<Vehicle> vehicleObservableList = FXCollections.observableArrayList();

        try {
            ArrayList<Vehicle> getVehicle = VehicleModel.getAllVehicle();
            for (Vehicle vehicle : getVehicle) {
                Vehicle vehicle1 = new Vehicle(
                        vehicle.getVehicleId(),
                        vehicle.getVehicleName(),
                        vehicle.getVehicleModel(),
                        vehicle.getVehicleColour(),
                        vehicle.getVehicleLicenceId(),
                        vehicle.getVehicleFuelType(),
                        vehicle.getVehicleLiterOfFuel(),
                        vehicle.getVehicleTransmission(),
                        vehicle.getVehiclePrice(),
                        vehicle.getImage(),
                        vehicle.getStatus());
                vehicleObservableList.add(vehicle1);
            }
            tblVehicle.setItems(vehicleObservableList);
            tblVehicle.refresh();


        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }


    public void deleteOnAction(ActionEvent actionEvent) throws SQLException, ClassNotFoundException {
        if (VehicleModel.deleteVehicle(txtvehicleId.getText())) {
            new Alert(Alert.AlertType.WARNING, "Delete Successfully...!");
            loadDataToTable();
            clearAllTextFiled();
        } else {
            new Alert(Alert.AlertType.ERROR, "Try Again");
        }
    }

    public void updateOnAction(ActionEvent actionEvent) throws SQLException, ClassNotFoundException {
        if (txtvehicleId.getText().matches("^(V)([0-9]{3,})$")) {
            if (txtvehicleName.getText().matches("^[A-z ]{3,15}$")) {
                if (txtvehicleModel.getText().matches("^[A-z ]{3,15}$")) {
                    if (txtVehicleColor.getText().matches("^[A-z ]{3,15}$")) {
                        if (txtVehicleLicenceId.getText().matches("[0-9 A-Za-z,/]{2,}")) {
                            if (txtliterofFuel.getText().matches("^[0-9]+\\.?[0-9]*$")) {
                                if (txtvehiclePrice.getText().matches("^[0-9]+\\.?[0-9]*$")) {

                                    String id = txtvehicleId.getText();
                                    String name = txtvehicleName.getText();
                                    String model = txtvehicleModel.getText();
                                    String colour = txtVehicleColor.getText();
                                    String licenceId = txtVehicleLicenceId.getText();
                                    String fuelType = cmbFuelType.getValue();
                                    double literOfFuel = Double.parseDouble(txtliterofFuel.getText());
                                    String transmission = cmdvehicleTransmission.getValue();
                                    double price = Double.parseDouble(txtvehiclePrice.getText());
                                    String status = cmbStatus.getValue();

                                    Vehicle vehicle = new Vehicle(
                                            id,
                                            name,
                                            model,
                                            colour,
                                            licenceId,
                                            fuelType,
                                            literOfFuel,
                                            transmission,
                                            price,
                                            imageName,
                                            status

                                    );

                                    if (VehicleModel.updateVehicle(vehicle)) {
                                        new Alert(Alert.AlertType.CONFIRMATION, "Update Vehicle's Details...!").show();
                                        clearAllTextFiled();
                                        loadDataToTable();
                                        loadFuelType();
                                        loadTransmission();
                                        loadStatus();
                                    } else {
                                        new Alert(Alert.AlertType.WARNING, "Update Fail...!").show();

                                    }

                                } else {
                                    txtvehicleTransmission.requestFocus();
                                }

                            } else {
                                txtliterofFuel.requestFocus();
                            }
                        } else {
                            txtVehicleLicenceId.requestFocus();
                        }
                    } else {
                        txtVehicleColor.requestFocus();
                    }
                } else {
                    txtvehicleModel.requestFocus();
                }
            } else {
                txtvehicleName.requestFocus();
            }
        } else {
            txtvehicleId.requestFocus();
        }
    }

    public void saveOnAction(ActionEvent actionEvent) throws SQLException, ClassNotFoundException {
        if (txtvehicleId.getText().matches("^(V)([0-9]{3,})$")) {
            if (txtvehicleName.getText().matches("^[A-z ]{3,15}$")) {
                if (txtvehicleModel.getText().matches("^[A-z ]{3,15}$")) {
                    if (txtVehicleColor.getText().matches("^[A-z ]{3,15}$")) {
                        if (txtVehicleLicenceId.getText().matches("[0-9 A-Za-z,/]{2,}")) {
                            if (txtliterofFuel.getText().matches("^[0-9]+\\.?[0-9]*$")) {
                                if (txtvehiclePrice.getText().matches("^[0-9]+\\.?[0-9]*$")) {

                                    String id = txtvehicleId.getText();
                                    String name = txtvehicleName.getText();
                                    String model = txtvehicleModel.getText();
                                    String colour = txtVehicleColor.getText();
                                    String licenceId = txtVehicleLicenceId.getText();
                                    String fuelType = cmbFuelType.getValue();
                                    double literOfFuel = Double.parseDouble(txtliterofFuel.getText());
                                    String transmission = cmdvehicleTransmission.getValue();
                                    double price = Double.parseDouble(txtvehiclePrice.getText());
                                    String status = cmbStatus.getValue();
                                    Vehicle vehicle = new Vehicle(
                                            id,
                                            name,
                                            model,
                                            colour,
                                            licenceId,
                                            fuelType,
                                            literOfFuel,
                                            transmission,
                                            price,
                                            imageName,
                                            status
                                    );

                                    if (VehicleModel.saveVehicle(vehicle)) {
                                        new Alert(Alert.AlertType.CONFIRMATION, "Added New Vehicle...!").show();
                                        clearAllTextFiled();
                                        loadDataToTable();
                                    }

                                } else {
                                    txtvehicleTransmission.requestFocus();
                                }

                            } else {
                                txtliterofFuel.requestFocus();
                            }
                        } else {
                            txtVehicleLicenceId.requestFocus();
                        }
                    } else {
                        txtVehicleColor.requestFocus();
                    }
                } else {
                    txtvehicleModel.requestFocus();
                }
            } else {
                txtvehicleName.requestFocus();
            }
        } else {
            txtvehicleId.requestFocus();
        }
    }

    private void clearAllTextFiled() {
        txtvehicleId.clear();
        txtvehicleName.clear();
        txtvehicleModel.clear();
        txtVehicleColor.clear();
        txtVehicleLicenceId.clear();
        txtliterofFuel.clear();
        cmbFuelType.getItems().clear();
        txtliterofFuel.clear();
        cmdvehicleTransmission.getItems().clear();
        txtvehiclePrice.clear();
        imgvehicle.setImage(null);
        cmbStatus.getItems().clear();

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
            imgvehicle.setImage(image);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
