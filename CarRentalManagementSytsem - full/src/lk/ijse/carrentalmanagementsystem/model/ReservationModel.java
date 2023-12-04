package lk.ijse.carrentalmanagementsystem.model;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import lk.ijse.carrentalmanagementsystem.to.*;
import lk.ijse.carrentalmanagementsystem.util.CrudUtil;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;

public class ReservationModel {
    public static ArrayList<Reservation> getAllReservation() throws SQLException, ClassNotFoundException {
        ResultSet resultSet = CrudUtil.execute("SELECT * FROM reservation");
        ArrayList<Reservation> driverArrayList = new ArrayList<>();
        while (resultSet.next()) {
            driverArrayList.add(new Reservation(
                    resultSet.getString("reservationId"),
                    resultSet.getString("customerId"),
                    resultSet.getString("vehicleId"),
                    resultSet.getString("driverId"),
                    LocalDate.parse(resultSet.getString("startDate")),
                    LocalDate.parse(resultSet.getString("endDate")),
                    resultSet.getString("location"),
                    resultSet.getDouble("totalKm"),
                    resultSet.getInt("totalDays"),
                    resultSet.getDouble("basicAmount")


            ));
        }
        return driverArrayList;
    }

    public static ArrayList<String>  loadVehicleId() throws SQLException, ClassNotFoundException {
        ResultSet idSet = CrudUtil.execute("SELECT vehicleId FROM Vehicle WHERE status = 'available'");
        ArrayList<String>id=new ArrayList<>();
        while (idSet.next()){
            id.add(idSet.getString(1));
        }
        return id;
    }

    public static ArrayList<String> loadDriverId() throws SQLException, ClassNotFoundException {
        ResultSet idSet = CrudUtil.execute("SELECT  driverId FROM Driver");
        ArrayList<String>id=new ArrayList<>();
        while (idSet.next()){
            id.add(idSet.getString(1));
        }
        return id;
    }

    public static ArrayList<String> loadCustomerid() throws SQLException, ClassNotFoundException {
        ResultSet resultSet=CrudUtil.execute("SELECT customerId from  Customer");
        ArrayList<String>id=new ArrayList<>();
        while (resultSet.next()){
            id.add(resultSet.getString(1));
        }
        return id;
    }

    public static String getLastId() throws SQLException, ClassNotFoundException {
        ResultSet resultSet = CrudUtil.execute("SELECT reservationId  from reservation order by reservationId desc limit 1");
        if (resultSet.next()) {
            return resultSet.getString(1);
        } else {
            return null;
        }
    }

    public static Customer getCustomerDetails(String value) throws SQLException, ClassNotFoundException {
        ResultSet resultSet=CrudUtil.execute("SELECT *  from  Customer  WHERE customerId =?",value);
        if (resultSet.next()){
            Customer customer=new Customer(
                    resultSet.getString("customerName"),
                    resultSet.getString("customerAddress"),
                    resultSet.getString("customerPhoneNumber"),
                    resultSet.getString("customerNIC"));

            return customer;
        }else{
            return null;
        }
    }

    public static Vehicle getVehicleDetails(String value) throws SQLException, ClassNotFoundException {
        ResultSet resultSet=CrudUtil.execute("SELECT vehicleName,vehicleFuelType,vehicleTransmission,vehiclePrice,image  from  Vehicle  WHERE vehicleId =?",value);
        if (resultSet.next()){
            Vehicle vehicle=new Vehicle(
                    resultSet.getString("vehicleName"),
                    resultSet.getString("vehicleFuelType"),
                    resultSet.getString("vehicleTransmission"),
                    resultSet.getDouble("vehiclePrice"),
                    resultSet.getString("image")

            );

            return vehicle;
        }else{
            return null;
        }
    }

    public static Driver getDriverDetails(String value) throws SQLException, ClassNotFoundException {
        ResultSet resultSet=CrudUtil.execute("SELECT driverName,driverAddress,driverPhoneNumber,preDayValue  from  Driver  WHERE driverId =?",value);
        if (resultSet.next()){
            Driver driver=new Driver(
                    resultSet.getString("driverName"),
                    resultSet.getString("driverAddress"),
                    resultSet.getString("driverPhoneNumber"),
                    resultSet.getDouble("preDayValue"));

            return driver;
        }else{
            return null;
        }
    }

    public static boolean saveReservation(Reservation reservation) throws SQLException, ClassNotFoundException {
        return CrudUtil.execute("INSERT INTO reservation VALUES(?,?,?,?,?,?,?,?,?,?)",
                reservation.getReservationId(),
                reservation.getCustomerId(),
                reservation.getVehicleId(),
                reservation.getDriverId(),
                reservation.getStartDate(),
                reservation.getEndDate(),
                reservation.getLocation(),
                reservation.getTotalKm(),
                reservation.getTotalDays(),
                reservation.getBasicAmount()
                );
    }

    public static boolean updateReservation(Reservation reservation) throws SQLException, ClassNotFoundException {
        return CrudUtil.execute("UPDATE reservation SET customerId=?, vehicleId=?, driverId=?, startDate=?, endDate=?, location=?, totalKm=?, totalDays=?, basicAmount=? WHERE reservationId=?",
                reservation.getCustomerId(),
                reservation.getVehicleId(),
                reservation.getDriverId(),
                java.sql.Date.valueOf(reservation.getStartDate()),
                java.sql.Date.valueOf(reservation.getEndDate()),
                reservation.getLocation(),
                reservation.getTotalKm(),
                reservation.getTotalDays(),
                reservation.getBasicAmount(),
                reservation.getReservationId()
        );


    }
}
