package lk.ijse.carrentalmanagementsystem.model;

import lk.ijse.carrentalmanagementsystem.to.Customer;
import lk.ijse.carrentalmanagementsystem.to.Vehicle;
import lk.ijse.carrentalmanagementsystem.util.CrudUtil;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class CustomerModel {
    public static boolean saveCustomer(Customer customer) throws SQLException, ClassNotFoundException {
        return CrudUtil.execute("INSERT INTO Customer  VALUES(?,?,?,?,?)",
                customer.getCustomerId(),
                customer.getCustomerName(),
                customer.getCustomerAddress(),
                customer.getCustomerPhoneNumber(),
                customer.getCustomerNIC()
        );
    }
    public static boolean updateCustomer(Customer customer) throws SQLException, ClassNotFoundException {

       return CrudUtil.execute("UPDATE Customer SET customerName=?, customerAddress=?, customerPhoneNumber=?, customerNIC=? WHERE customerId=?",
                customer.getCustomerName(),
                customer.getCustomerAddress(),
                customer.getCustomerPhoneNumber(),
                customer.getCustomerNIC(),
                customer.getCustomerId());

    }



    public static boolean deleteCustomer(String customerId) throws SQLException, ClassNotFoundException {
        return CrudUtil.execute("DELETE FROM Customer where customerId=?",customerId);
    }

    public static ArrayList<Customer> getAllCustomer() throws SQLException, ClassNotFoundException {
        ResultSet resultSet = CrudUtil.execute("SELECT * FROM Customer");
        ArrayList<Customer> customerArrayList = new ArrayList<>();
        while (resultSet.next()) {
            customerArrayList.add(new Customer(
                    resultSet.getString("customerId"),
                    resultSet.getString("customerName"),
                    resultSet.getString("customerAddress"),
                    resultSet.getString("customerPhoneNumber"),
                    resultSet.getString("customerNIC")

            ));
        }
        return customerArrayList;
    }

    public static String getLastId() throws SQLException, ClassNotFoundException {
        ResultSet resultSet = CrudUtil.execute("SELECT customerId  from Customer order by customerId desc limit 1");
        if (resultSet.next()) {
            return resultSet.getString(1);
        } else {
            return null;
        }
    }
}
