package lk.ijse.carrentalmanagementsystem.controller;

import javafx.scene.chart.PieChart;
import lk.ijse.carrentalmanagementsystem.util.CrudUtil;

import java.sql.ResultSet;
import java.sql.SQLException;

public class Reports {
    public PieChart pieChartForReports;
    public PieChart paymentpieChartForReports;

    public void initialize() {
        try {

            int vehicleCount = getVehicleCount();
            int driverCount = getDriverCount();
            int availableCount = getVehicleCountByStatus("Available");
            int unavailableCount = getVehicleCountByStatus("Unavailable");

            pieChartForReports.getData().clear();
            paymentpieChartForReports.getData().clear();
            pieChartForReports.getData().add(new PieChart.Data("Vehicles", vehicleCount));
            pieChartForReports.getData().add(new PieChart.Data("Drivers", driverCount));
            paymentpieChartForReports.getData().add(new PieChart.Data("Available", availableCount));
            paymentpieChartForReports.getData().add(new PieChart.Data("Unavailable", unavailableCount));

        } catch (Exception e) {
            e.printStackTrace();
        }
    }



    private int getVehicleCountByStatus(String status) throws SQLException, ClassNotFoundException {
        String query = "SELECT COUNT(*) FROM Vehicle WHERE status = ?";
        try (ResultSet resultSet = CrudUtil.execute(query, status)) {
            if (resultSet.next()) {
                return resultSet.getInt(1);
            }
        }
        return 0;
    }

    private int getDriverCount() throws SQLException, ClassNotFoundException {
        ResultSet resultSet= CrudUtil.execute( "SELECT COUNT(*) FROM Driver");
        if (resultSet.next()) {
            return resultSet.getInt(1);
        }
        return 0;
    }

    private int getVehicleCount() throws SQLException, ClassNotFoundException {
        ResultSet resultSet=CrudUtil.execute( "SELECT COUNT(*) FROM Vehicle");
        if (resultSet.next()) {
            return resultSet.getInt(1);
        }
        return 0;

    }


}
