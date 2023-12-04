package lk.ijse.carrentalmanagementsystem.model;


import lk.ijse.carrentalmanagementsystem.to.Vehicle;
import lk.ijse.carrentalmanagementsystem.util.CrudUtil;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class VehicleModel {
    public static boolean saveVehicle(Vehicle vehicle) throws SQLException, ClassNotFoundException {
        return CrudUtil.execute("INSERT INTO vehicle  VALUES(?,?,?,?,?,?,?,?,?,?,?)",
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
                vehicle.getStatus()
        );

    }

    public static boolean updateVehicle(Vehicle vehicle) throws SQLException, ClassNotFoundException {
        return CrudUtil.execute("UPDATE vehicle set vehicleName=?,vehicleModel=?,vehicleColour=?,vehicleLicenceId =?,vehicleFuelType=?,vehicleLiterOfFuel=?,vehicleTransmission=?,vehiclePrice=?,image=?,status=? where vehicleId=?",
                vehicle.getVehicleName(),
                vehicle.getVehicleModel(),
                vehicle.getVehicleColour(),
                vehicle.getVehicleLicenceId(),
                vehicle.getVehicleFuelType(),
                vehicle.getVehicleLiterOfFuel(),
                vehicle.getVehicleTransmission(),
                vehicle.getVehiclePrice(),
                vehicle.getImage(),
                vehicle.getStatus(),
                vehicle.getVehicleId());
    }

    public static ArrayList<Vehicle> getAllVehicle() throws SQLException, ClassNotFoundException {
        ResultSet resultSet = CrudUtil.execute("SELECT * FROM Vehicle");
        ArrayList<Vehicle> vehicleArrayList = new ArrayList<>();
        while (resultSet.next()) {
            vehicleArrayList.add(new Vehicle(
                    resultSet.getString("vehicleId"),
                    resultSet.getString("vehicleName"),
                    resultSet.getString("vehicleModel"),
                    resultSet.getString("vehicleColour"),
                    resultSet.getString("vehicleLicenceId"),
                    resultSet.getString("vehicleFuelType"),
                    resultSet.getDouble("vehicleLiterOfFuel"),
                    resultSet.getString("vehicleTransmission"),
                    resultSet.getDouble("vehiclePrice"),
                    resultSet.getString("image"),
                    resultSet.getString("status")

            ));
        }
        return vehicleArrayList;
    }

    public static boolean deleteVehicle(String vehicleID) throws SQLException, ClassNotFoundException {
        String sql = "DELETE FROM Vehicle where vehicleId=?";
        return CrudUtil.execute(sql, vehicleID);
    }

    public static String getLastId() throws SQLException, ClassNotFoundException {
        ResultSet resultSet = CrudUtil.execute("SELECT vehicleId  from Vehicle order by vehicleId desc limit 1");
        if (resultSet.next()) {
            return resultSet.getString(1);
        } else {
            return null;
        }


    }
}

