package lk.ijse.carrentalmanagementsystem.controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.scene.control.Alert;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import lk.ijse.carrentalmanagementsystem.model.CustomerModel;
import lk.ijse.carrentalmanagementsystem.model.DriverModel;
import lk.ijse.carrentalmanagementsystem.model.VehicleModel;
import lk.ijse.carrentalmanagementsystem.to.Customer;
import lk.ijse.carrentalmanagementsystem.to.Driver;

import java.sql.SQLException;
import java.util.ArrayList;

public class ManageDriver {
    public TextField txtDriverId;
    public TextField txtDriverName;
    public TextField txtDriverAddress;
    public TextField txtDriverPhoneNumber;
    public TextField txtDriverNIC;
    public TextField txtDriverValue;
    public TableView<Driver> tblDriver;
    public TableColumn tblId;
    public TableColumn tblname;
    public TableColumn tblAddress;
    public TableColumn tblPhonenumber;
    public TableColumn tblNIC;
    public TableColumn tblDriverValue;
    public TextField txtSearch;

    public void initialize() {
        generateDriverId();
        setCellFactories();
        loadDataToTable();
        AddListener();
    }

    private void AddListener() {
        tblDriver.getSelectionModel().selectedItemProperty().addListener((observableValue, oldValue, newValue) -> {
            if (newValue != null) {
                loadDataToTextField(newValue);
            }
        });
    }

    private void loadDataToTextField(Driver driver) {
        txtDriverId.setText(driver.getDriverId());
        txtDriverName.setText(driver.getDriverName());
        txtDriverAddress.setText(driver.getDriverAddress());
        txtDriverPhoneNumber.setText(driver.getDriverPhoneNumber());
        txtDriverNIC.setText(driver.getDriverNIC());
        txtDriverValue.setText(String.valueOf(driver.getPreDayValue()));
    }

    private void loadDataToTable() {
        ObservableList<Driver> vehicleObservableList = FXCollections.observableArrayList();

        try {
            ArrayList<Driver> getDriver = DriverModel.getAllDriver();
            for (Driver driver : getDriver) {
                Driver driver1 = new Driver(
                        driver.getDriverId(),
                        driver.getDriverName(),
                        driver.getDriverAddress(),
                        driver.getDriverPhoneNumber(),
                        driver.getDriverNIC(),
                        driver.getPreDayValue());

                vehicleObservableList.add(driver1);
            }
            tblDriver.setItems(vehicleObservableList);
            tblDriver.refresh();


        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    private void setCellFactories() {
        tblId.setCellValueFactory(new PropertyValueFactory<>("driverId"));
        tblname.setCellValueFactory(new PropertyValueFactory<>("driverName"));
        tblAddress.setCellValueFactory(new PropertyValueFactory<>("driverAddress"));
        tblPhonenumber.setCellValueFactory(new PropertyValueFactory<>("driverPhoneNumber"));
        tblNIC.setCellValueFactory(new PropertyValueFactory<>("driverNIC"));
        tblDriverValue.setCellValueFactory(new PropertyValueFactory<>("preDayValue"));

    }

    public void deleteOnAction(ActionEvent actionEvent) throws SQLException, ClassNotFoundException {
        if (DriverModel.deleteDriver(txtDriverId.getText())) {
            new Alert(Alert.AlertType.WARNING, "Delete Successfully...!");
            loadDataToTable();
            clearAllTextFiled();
            generateDriverId();
        } else {
            new Alert(Alert.AlertType.ERROR, "Try Again");
        }
    }

    public void updateOnAction(ActionEvent actionEvent) throws SQLException, ClassNotFoundException {
        if (txtDriverId.getText().matches("^(D)([0-9]{3,})$")) {
            if (txtDriverName.getText().matches("^[A-z ]{3,15}$")) {
                if (txtDriverAddress.getText().matches("^[A-z ]{3,15}$")) {
                    if (txtDriverPhoneNumber.getText().matches("^[0-9]{10}$")) {
                        if (txtDriverNIC.getText().matches("^([0-9]{9}[x|X|v|V]|[0-9]{12})$")) {
                            if (txtDriverValue.getText().matches("[0-9]{4,}")) {

                                String id = txtDriverId.getText();
                                String name = txtDriverName.getText();
                                String address = txtDriverAddress.getText();
                                String phoneNumber = txtDriverPhoneNumber.getText();
                                String nic = txtDriverNIC.getText();
                                double dayValue = Double.parseDouble(txtDriverValue.getText());

                                Driver driver = new Driver(
                                        id,
                                        name,
                                        address,
                                        phoneNumber,
                                        nic,
                                        dayValue

                                );

                                if (DriverModel.updateDriver(driver)) {

                                    new Alert(Alert.AlertType.CONFIRMATION, "Update Customer's Details...!").show();
                                    clearAllTextFiled();
                                    loadDataToTable();
                                    generateDriverId();
                                } else {
                                    new Alert(Alert.AlertType.WARNING, "Update Fail...!").show();
                                }
                            } else {
                                txtDriverValue.requestFocus();
                            }
                        } else {
                            txtDriverNIC.requestFocus();
                        }

                    } else {
                        txtDriverPhoneNumber.requestFocus();
                    }
                } else {
                    txtDriverAddress.requestFocus();
                }
            } else {
                txtDriverName.requestFocus();
            }
        } else {
            txtDriverId.requestFocus();
        }
    }

    public void saveOnAction(ActionEvent actionEvent) throws SQLException, ClassNotFoundException {
        if (txtDriverId.getText().matches("^(D)([0-9]{3,})$")) {
            if (txtDriverName.getText().matches("^[A-z ]{3,15}$")) {
                if (txtDriverAddress.getText().matches("^[A-z ]{3,15}$")) {
                    if (txtDriverPhoneNumber.getText().matches("^[0-9]{10}$")) {
                        if (txtDriverNIC.getText().matches("^([0-9]{9}[x|X|v|V]|[0-9]{12})$")) {
                            if (txtDriverValue.getText().matches("[0-9]{4,}")) {

                                String id = txtDriverId.getText();
                                String name = txtDriverName.getText();
                                String address = txtDriverAddress.getText();
                                String phoneNumber = txtDriverPhoneNumber.getText();
                                String nic = txtDriverNIC.getText();
                                double dayValue = Double.parseDouble(txtDriverValue.getText());

                                Driver driver = new Driver(
                                        id,
                                        name,
                                        address,
                                        phoneNumber,
                                        nic,
                                        dayValue

                                );

                                if (DriverModel.saveDriver(driver)) {

                                    new Alert(Alert.AlertType.CONFIRMATION, "Update Customer's Details...!").show();
                                    clearAllTextFiled();
                                    loadDataToTable();
                                    generateDriverId();
                                } else {
                                    new Alert(Alert.AlertType.WARNING, "Update Fail...!").show();
                                }
                            } else {
                                txtDriverValue.requestFocus();
                            }
                        } else {
                            txtDriverNIC.requestFocus();
                        }

                    } else {
                        txtDriverPhoneNumber.requestFocus();
                    }
                } else {
                    txtDriverAddress.requestFocus();
                }
            } else {
                txtDriverName.requestFocus();
            }
        } else {
            txtDriverId.requestFocus();
        }
    }

    private void clearAllTextFiled() {
        txtDriverId.clear();
        txtDriverName.clear();
        txtDriverAddress.clear();
        txtDriverPhoneNumber.clear();
        txtDriverNIC.clear();
        txtDriverValue.clear();
    }

    private void generateDriverId() {
        String lastId = null;
        try {
            lastId = DriverModel.getLastId();
            if (lastId == null) {
                txtDriverId.setText("D001");
            } else {
                String[] split = lastId.split("[D]");
                int lastDigit = Integer.parseInt(split[1]);
                lastDigit++;
                String newId = String.format("D%03d", lastDigit);
                txtDriverId.setText(newId);
            }
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }

    }
}
