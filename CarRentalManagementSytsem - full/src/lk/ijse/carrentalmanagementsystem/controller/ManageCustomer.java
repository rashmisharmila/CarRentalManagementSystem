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
import lk.ijse.carrentalmanagementsystem.model.VehicleModel;
import lk.ijse.carrentalmanagementsystem.to.Customer;
import lk.ijse.carrentalmanagementsystem.to.Vehicle;

import java.sql.SQLException;
import java.util.ArrayList;

public class ManageCustomer {
    public TextField txtCustomerID;
    public TextField txtCustomerName;
    public TextField txtCustomerAddress;
    public TextField txtCustomerPhoneNumber;
    public TextField txtCustomerNIC;
    public TableView<Customer> tblCustomer;
    public TableColumn tblId;
    public TableColumn tblname;
    public TableColumn tblAddress;
    public TableColumn tblPhonenumber;
    public TableColumn tblNIC;
    public TextField txtSearch;

    public void initialize() {
        generateCustomerId();
        setCellFactories();
        loadDataToTable();
        AddListener();
    }

    private void AddListener() {
        tblCustomer.getSelectionModel().selectedItemProperty().addListener((observableValue, oldValue, newValue) -> {
            if (newValue != null) {
                loadDataToTextField(newValue);
            }
        });
    }

    private void loadDataToTextField(Customer customer) {
        txtCustomerID.setText(customer.getCustomerId());
        txtCustomerName.setText(customer.getCustomerName());
        txtCustomerAddress.setText(customer.getCustomerAddress());
        txtCustomerPhoneNumber.setText(customer.getCustomerPhoneNumber());
        txtCustomerNIC.setText(customer.getCustomerNIC());
    }

    private void loadDataToTable() {
        ObservableList<Customer> vehicleObservableList = FXCollections.observableArrayList();

        try {
            ArrayList<Customer> getCustomer = CustomerModel.getAllCustomer();
            for (Customer customer : getCustomer) {
                Customer customer1 = new Customer(
                        customer.getCustomerId(),
                        customer.getCustomerName(),
                        customer.getCustomerAddress(),
                        customer.getCustomerPhoneNumber(),
                        customer.getCustomerNIC());
                vehicleObservableList.add(customer1);
            }
            tblCustomer.setItems(vehicleObservableList);
            tblCustomer.refresh();


        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    private void setCellFactories() {
        tblId.setCellValueFactory(new PropertyValueFactory<>("customerId"));
        tblname.setCellValueFactory(new PropertyValueFactory<>("customerName"));
        tblAddress.setCellValueFactory(new PropertyValueFactory<>("customerAddress"));
        tblPhonenumber.setCellValueFactory(new PropertyValueFactory<>("customerPhoneNumber"));
        tblNIC.setCellValueFactory(new PropertyValueFactory<>("customerNIC"));
    }

    private void generateCustomerId() {
        String lastId = null;
        try {
            lastId = CustomerModel.getLastId();
            if (lastId == null) {
                txtCustomerID.setText("C001");
            } else {
                String[] split = lastId.split("[C]");
                int lastDigit = Integer.parseInt(split[1]);
                lastDigit++;
                String newId = String.format("C%03d", lastDigit);
                txtCustomerID.setText(newId);
            }
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public void deleteOnAction(ActionEvent actionEvent) throws SQLException, ClassNotFoundException {
        if (CustomerModel.deleteCustomer(txtCustomerID.getText())) {
            new Alert(Alert.AlertType.WARNING, "Delete Successfully...!");
            loadDataToTable();
            clearAllTextFiled();
            generateCustomerId();
        } else {
            new Alert(Alert.AlertType.ERROR, "Try Again");
        }


    }


    public void updateOnAction(ActionEvent actionEvent) throws SQLException, ClassNotFoundException {
        if (txtCustomerID.getText().matches("^(C)([0-9]{3,})$")) {
            if (txtCustomerName.getText().matches("^[A-z ]{3,15}$")) {
                if (txtCustomerAddress.getText().matches("^[A-z ]{3,15}$")) {
                    if (txtCustomerPhoneNumber.getText().matches("^[0-9]{10}$")) {
                        if (txtCustomerNIC.getText().matches("^([0-9]{9}[x|X|v|V]|[0-9]{12})$")) {

                            String id = txtCustomerID.getText();
                            String name = txtCustomerName.getText();
                            String address = txtCustomerAddress.getText();
                            String phoneNumber = txtCustomerPhoneNumber.getText();
                            String nic = txtCustomerNIC.getText();

                            Customer customer = new Customer(
                                    id,
                                    name,
                                    address,
                                    phoneNumber,
                                    nic

                            );

                            if (CustomerModel.updateCustomer(customer)) {

                                new Alert(Alert.AlertType.CONFIRMATION, "Update Customer's Details...!").show();
                                clearAllTextFiled();
                                loadDataToTable();
                                generateCustomerId();
                            } else {
                                new Alert(Alert.AlertType.WARNING, "Update Fail...!").show();
                            }

                        } else {
                            txtCustomerNIC.requestFocus();
                        }

                    } else {
                        txtCustomerPhoneNumber.requestFocus();
                    }
                } else {
                    txtCustomerAddress.requestFocus();
                }
            } else {
                txtCustomerName.requestFocus();
            }
        } else {
            txtCustomerID.requestFocus();
        }
    }

    public void saveOnAction(ActionEvent actionEvent) throws SQLException, ClassNotFoundException {
        if (txtCustomerID.getText().matches("^(C)([0-9]{3,})$")) {
            if (txtCustomerName.getText().matches("^[A-z ]{3,15}$")) {
                if (txtCustomerAddress.getText().matches("^[A-z ]{3,15}$")) {
                    if (txtCustomerPhoneNumber.getText().matches("^[0-9]{10}$")) {
                        if (txtCustomerNIC.getText().matches("^([0-9]{9}[x|X|v|V]|[0-9]{12})$")) {

                            String id = txtCustomerID.getText();
                            String name = txtCustomerName.getText();
                            String address = txtCustomerAddress.getText();
                            String phoneNumber = txtCustomerPhoneNumber.getText();
                            String nic = txtCustomerNIC.getText();

                            Customer customer = new Customer(
                                    id,
                                    name,
                                    address,
                                    phoneNumber,
                                    nic

                            );

                            if (CustomerModel.saveCustomer(customer)) {
                                new Alert(Alert.AlertType.CONFIRMATION, "Save Customer's Details...!").show();
                                clearAllTextFiled();
                                loadDataToTable();
                                generateCustomerId();
                            } else {
                                new Alert(Alert.AlertType.WARNING, "Save Fail...!").show();
                            }

                        } else {
                            txtCustomerNIC.requestFocus();
                        }

                    } else {
                        txtCustomerPhoneNumber.requestFocus();
                    }
                } else {
                    txtCustomerAddress.requestFocus();
                }
            } else {
                txtCustomerName.requestFocus();
            }
        } else {
            txtCustomerID.requestFocus();
        }
    }

    private void clearAllTextFiled() {
        txtCustomerID.clear();
        txtCustomerName.clear();
        txtCustomerAddress.clear();
        txtCustomerPhoneNumber.clear();
        txtCustomerNIC.clear();
    }
}
