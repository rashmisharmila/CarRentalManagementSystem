package lk.ijse.carrentalmanagementsystem.controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import lk.ijse.carrentalmanagementsystem.model.UserModel;
import lk.ijse.carrentalmanagementsystem.model.VehicleModel;
import lk.ijse.carrentalmanagementsystem.to.User;
import lk.ijse.carrentalmanagementsystem.to.Vehicle;

import java.sql.SQLException;
import java.util.ArrayList;

public class ManageUser {

    public TextField txtUserID;
    public TextField txtUserName;
    public TextField txtUserAddress;
    public TextField txtUserPhoneNumber;
    public TextField txtUserEmail;
    public ComboBox<String> txtUserRole;
    public TextField txtUsername;
    public PasswordField txtUserPassword;
    public TableView<User> tblUser;

    public TableColumn tblId;
    public TableColumn tblname;
    public TableColumn tblAddress;
    public TableColumn tblPhonenumber;
    public TableColumn tblEmail;
    public TableColumn tblRole;
    public TableColumn tblUsername;
    public TableColumn tblPassword;

    public TextField txtSearch;

    public void initialize() {
        generateUserId();
        setCellFactories();
        loadDataToTable();
        AddListener();
        loadRoleType();
    }

    private void loadRoleType() {
        ObservableList<String> statusList = FXCollections.observableArrayList("Admin", "User");
        txtUserRole.setItems(statusList);
    }

    private void AddListener() {
        tblUser.getSelectionModel().selectedItemProperty().addListener((observableValue, oldValue, newValue) -> {
            if (newValue != null) {
                loadDataToTextField(newValue);
            }
        });
    }

    private void loadDataToTextField(User user) {
        txtUserID.setText(user.getId());
        txtUserName.setText(user.getName());
        txtUserAddress.setText(user.getAddress());
        txtUserPhoneNumber.setText(user.getPhoneNumber());
        txtUserEmail.setText(user.getEmail());
        txtUserRole.setValue(user.getRole());
        txtUsername.setText(user.getUsername());
        txtUserPassword.setText(user.getPassword());
    }

    private void loadDataToTable() {
        ObservableList<User> userObservableList = FXCollections.observableArrayList();

        ArrayList<User> getUser = null;
        try {
            getUser = UserModel.getAllVehicle();

        for (User user : getUser) {
            User user1 = new User(
                    user.getId(),
                    user.getName(),
                    user.getAddress(),
                    user.getPhoneNumber(),
                    user.getEmail(),
                    user.getRole(),
                    user.getUsername(),
                    user.getPassword());
            userObservableList.add(user1);
        }
        tblUser.setItems(userObservableList);
        tblUser.refresh();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }

    }

    private void setCellFactories() {
        tblId.setCellValueFactory(new PropertyValueFactory<>("userid"));
        tblname.setCellValueFactory(new PropertyValueFactory<>("name"));
        tblAddress.setCellValueFactory(new PropertyValueFactory<>("address"));
        tblPhonenumber.setCellValueFactory(new PropertyValueFactory<>("phoneNumber"));
        tblEmail.setCellValueFactory(new PropertyValueFactory<>("email"));
        tblRole.setCellValueFactory(new PropertyValueFactory<>("role"));
        tblUsername.setCellValueFactory(new PropertyValueFactory<>("username"));
        tblPassword.setCellValueFactory(new PropertyValueFactory<>("password"));

    }

    private void generateUserId() {
        String lastId = null;
        try {
            lastId = UserModel.getLastId();
            if (lastId == null) {
                txtUserID.setText("U001");
            } else {
                String[] split = lastId.split("[U]");
                int lastDigit = Integer.parseInt(split[1]);
                lastDigit++;
                String newId = String.format("U%03d", lastDigit);
                txtUserID.setText(newId);
            }
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }

    }

    public void deleteOnAction(ActionEvent actionEvent) throws SQLException, ClassNotFoundException {
        if (UserModel.deleteUser(txtUserID.getText())) {
            new Alert(Alert.AlertType.WARNING, "Delete Successfully...!");
            loadDataToTable();
            clearAllTextFiled();
        } else {
            new Alert(Alert.AlertType.ERROR, "Try Again");
        }
    }

    private void clearAllTextFiled() {
        txtUserID.clear();
        txtUserName.clear();
        txtUserAddress.clear();
        txtUserPhoneNumber.clear();
        txtUserEmail.clear();
        txtUsername.clear();

    }

    public void updateOnAction(ActionEvent actionEvent) throws SQLException, ClassNotFoundException {

        if (txtUserName.getText().matches("^[A-z ]{3,15}$")) {
            if (txtUserAddress.getText().matches("^[A-z ]{3,15}$")) {
                if (txtUserPhoneNumber.getText().matches("^[0-9]{10}$")) {
                    if (txtUserEmail.getText().matches("^([A-Z|a-z|0-9]{0,1})+[A-Z|a-z|0-9]\\@([A-Z|a-z|0-9])+((\\.){0,1}[A-Z|a-z|0-9]){2}\\.[a-z]{2,3}$")) {
                        if (txtUsername.getText().matches("[A-z ]{3,15}")) {
                            if (txtUserPassword.getText().matches("[0-9]{4,}")) {

                                    String id = txtUserID.getText();
                                    String name = txtUserName.getText();
                                    String address = txtUserAddress.getText();
                                    String phoneNumber = txtUserPhoneNumber.getText();
                                    String email = txtUserEmail.getText();
                                    String role = txtUserRole.getValue();
                                    String username = txtUsername.getText();
                                    String password = txtUserPassword.getText();


                                    User user = new User(
                                            id,
                                            name,
                                            address,
                                            phoneNumber,
                                            email,
                                            role,
                                            username,
                                            password

                                    );

                                    if (UserModel.updateUser(user)) {
                                        new Alert(Alert.AlertType.CONFIRMATION, "Update User's Details...!").show();
                                        clearAllTextFiled();
                                        loadDataToTable();
                                    } else {
                                        new Alert(Alert.AlertType.WARNING, "Update Fail...!").show();

                                    }

                                } else {
                                    txtUserPassword.requestFocus();
                                }

                            } else {
                                txtUsername.requestFocus();
                            }
                        } else {
                            txtUserEmail.requestFocus();
                        }
                    } else {
                        txtUserPhoneNumber.requestFocus();
                    }
                } else {
                    txtUserAddress.requestFocus();
                }
            } else {
                txtUserName.requestFocus();
            }
    }



    public void saveOnAction(ActionEvent actionEvent) throws SQLException, ClassNotFoundException {
            if (txtUserName.getText().matches("^[A-z ]{3,15}$")) {
                if (txtUserAddress.getText().matches("^[A-z ]{3,15}$")) {
                    if (txtUserPhoneNumber.getText().matches("^[0-9]{10}$")) {
                        if (txtUserEmail.getText().matches("^([A-Z|a-z|0-9]{0,1})+[A-Z|a-z|0-9]\\@([A-Z|a-z|0-9])+((\\.){0,1}[A-Z|a-z|0-9]){2}\\.[a-z]{2,3}$")) {
                            if (txtUsername.getText().matches("[A-z ]{3,15}")) {
                                if (txtUserPassword.getText().matches("[0-9]{4,}")) {
                                    String id = txtUserID.getText();
                                    String name = txtUserName.getText();
                                    String address = txtUserAddress.getText();
                                    String phoneNumber = txtUserPhoneNumber.getText();
                                    String email = txtUserEmail.getText();
                                    String role = txtUserRole.getValue();
                                    String username = txtUsername.getText();
                                    String password = txtUserPassword.getText();


                                    User user = new User(
                                            id,
                                            name,
                                            address,
                                            phoneNumber,
                                            email,
                                            role,
                                            username,
                                            password

                                    );

                                    if (UserModel.saveUser(user)) {
                                        new Alert(Alert.AlertType.CONFIRMATION, "Update user's Details...!").show();
                                        clearAllTextFiled();
                                        loadDataToTable();
                                    } else {
                                        new Alert(Alert.AlertType.WARNING, "Update Fail...!").show();

                                    }

                                } else {
                                    txtUserPassword.requestFocus();
                                }

                            } else {
                                txtUsername.requestFocus();
                            }
                        } else {
                            txtUserEmail.requestFocus();
                        }
                    } else {
                        txtUserPhoneNumber.requestFocus();
                    }
                } else {
                    txtUserAddress.requestFocus();
                }
            } else {
                txtUserName.requestFocus();
            }
    }
}
