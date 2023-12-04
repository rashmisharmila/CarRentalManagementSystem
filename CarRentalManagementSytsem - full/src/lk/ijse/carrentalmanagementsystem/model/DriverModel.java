package lk.ijse.carrentalmanagementsystem.model;

import lk.ijse.carrentalmanagementsystem.to.Customer;
import lk.ijse.carrentalmanagementsystem.to.Driver;
import lk.ijse.carrentalmanagementsystem.util.CrudUtil;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class DriverModel {
    public static ArrayList<Driver> getAllDriver() throws SQLException, ClassNotFoundException {
        ResultSet resultSet = CrudUtil.execute("SELECT * FROM Driver");
        ArrayList<Driver> driverArrayList = new ArrayList<>();
        while (resultSet.next()) {
            driverArrayList.add(new Driver(
                    resultSet.getString("driverId"),
                    resultSet.getString("driverName"),
                    resultSet.getString("driverAddress"),
                    resultSet.getString("driverPhoneNumber"),
                    resultSet.getString("driverNIC"),
                    resultSet.getDouble("preDayValue")

            ));
        }
        return driverArrayList;
    }

    public static boolean deleteDriver(String driverId) throws SQLException, ClassNotFoundException {
        return CrudUtil.execute("DELETE FROM Driver where driverId=?",driverId);

    }

    public static boolean updateDriver(Driver driver) throws SQLException, ClassNotFoundException {

        return CrudUtil.execute("UPDATE Driver SET driverName=?, driverAddress=?, driverPhoneNumber=?, driverNIC=?,preDayValue=? WHERE driverId=?",
                driver.getDriverName(),
                driver.getDriverAddress(),
                driver.getDriverPhoneNumber(),
                driver.getDriverNIC(),
                driver.getPreDayValue(),
                driver.getDriverId());
    }

    public static boolean saveDriver(Driver driver) throws SQLException, ClassNotFoundException {
        return CrudUtil.execute("INSERT INTO Driver VALUES (?, ?, ?, ?, ?, ?)",
                driver.getDriverId(),
                driver.getDriverName(),
                driver.getDriverAddress(),
                driver.getDriverPhoneNumber(),
                driver.getDriverNIC(),
                driver.getPreDayValue());
    }


    public static String getLastId() throws SQLException, ClassNotFoundException {
        ResultSet resultSet = CrudUtil.execute("SELECT driverId  from Driver order by driverId desc limit 1");
        if (resultSet.next()) {
            return resultSet.getString(1);
        } else {
            return null;
        }
    }
}
