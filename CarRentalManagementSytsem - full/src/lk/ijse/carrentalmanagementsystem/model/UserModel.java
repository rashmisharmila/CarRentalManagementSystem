package lk.ijse.carrentalmanagementsystem.model;


import lk.ijse.carrentalmanagementsystem.to.Driver;
import lk.ijse.carrentalmanagementsystem.to.User;
import lk.ijse.carrentalmanagementsystem.util.CrudUtil;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class UserModel {
    public static boolean check(String adminUsername, String adminPassword) throws SQLException, ClassNotFoundException {
        ResultSet resultSet= CrudUtil.execute("SELECT username,password from User");
        while (resultSet.next()){
            if (adminUsername.equals(resultSet.getString("username")) & adminPassword.equals(resultSet.getString("password"))) {
                return true;
            }
        }
        return false;
    }

    public static boolean deleteUser(String userId) throws SQLException, ClassNotFoundException {
        return CrudUtil.execute("DELETE FROM User where userid=?",userId);

    }

    public static ArrayList<User> getAllVehicle() throws SQLException, ClassNotFoundException {

        ResultSet resultSet = CrudUtil.execute("SELECT * FROM User");
        ArrayList<User> driverArrayList = new ArrayList<>();
        while (resultSet.next()) {
            driverArrayList.add(new User(
                    resultSet.getString("userid"),
                    resultSet.getString("name"),
                    resultSet.getString("address"),
                    resultSet.getString("phoneNumber"),
                    resultSet.getString("email"),
                    resultSet.getString("role"),
                    resultSet.getString("username"),
                    resultSet.getString("password")


            ));
        }
        return driverArrayList;
    }

    public static boolean updateUser(User user) throws SQLException, ClassNotFoundException {
        return CrudUtil.execute("UPDATE User set name=?,address=?,phoneNumber=?,email =?,role=?,username=?,password=? where userid=?",
                user.getId(),
                user.getName(),
                user.getAddress(),
                user.getPhoneNumber(),
                user.getEmail(),
                user.getRole(),
                user.getUsername(),
                user.getPassword());
    }

    public static boolean saveUser(User user) throws SQLException, ClassNotFoundException {
        return CrudUtil.execute("INSERT INTO User  VALUES(?,?,?,?,?,?,?,?)",
                user.getId(),
                user.getName(),
                user.getAddress(),
                user.getPhoneNumber(),
                user.getEmail(),
                user.getRole(),
                user.getUsername(),
                user.getPassword()
        );
    }

    public static String getLastId() throws SQLException, ClassNotFoundException {
        ResultSet resultSet = CrudUtil.execute("SELECT userid  from User  order by userid desc limit 1");
        if (resultSet.next()) {
            return resultSet.getString(1);
        } else {
            return null;
        }

    }
}
