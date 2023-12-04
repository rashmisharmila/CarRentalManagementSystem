package lk.ijse.carrentalmanagementsystem.model;

import lk.ijse.carrentalmanagementsystem.to.Customer;
import lk.ijse.carrentalmanagementsystem.to.Payment;
import lk.ijse.carrentalmanagementsystem.to.Reservation;
import lk.ijse.carrentalmanagementsystem.util.CrudUtil;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;

public class PaymentModel {
    public static ArrayList<String> loadReservationIds() throws SQLException, ClassNotFoundException {
        ResultSet idSet = CrudUtil.execute("SELECT reservationId FROM reservation");
        ArrayList<String>id=new ArrayList<>();
        while (idSet.next()){
            id.add(idSet.getString(1));
        }
        return id;
    }

    public static String getLastId() throws SQLException, ClassNotFoundException {
        ResultSet resultSet = CrudUtil.execute("SELECT PaymentId  from payments  order by PaymentId desc limit 1");
        if (resultSet.next()) {
            return resultSet.getString(1);
        } else {
            return null;
        }
    }


    public static Reservation getReservation(String value) throws SQLException, ClassNotFoundException {
        ResultSet resultSet=CrudUtil.execute("SELECT vehicleId,customerId,basicAmount  from  reservation  WHERE reservationId =?",value);
        if (resultSet.next()){
            Reservation reservation=new Reservation(
                    resultSet.getString("vehicleId"),
                    resultSet.getString("customerId"),
                    resultSet.getDouble("basicAmount"));

            return reservation;
        }else{
            return null;
        }
    }

    public static boolean savePayment(Payment payments) throws SQLException, ClassNotFoundException {
        return CrudUtil.execute("INSERT INTO payments  VALUES(?,?,?,?,?,?)",
                payments.getPaymentId(),
                payments.getReservationId(),
                payments.getPaymentDate(),
                payments.getPaymentTime(),
                payments.getPayment(),
                payments.getImage()
        );
    }
}
