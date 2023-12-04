package lk.ijse.carrentalmanagementsystem.to;

import java.time.LocalDate;

public class Reservation {
    // Fields
    private String reservationId;
    private String customerId;
    private String vehicleId;
    private String driverId;
    private LocalDate startDate;
    private LocalDate endDate;
    private String location;
    private double totalKm;
    private int totalDays;
    private double basicAmount;

    public Reservation(String reservationId, String customerId, String vehicleId, String driverId, LocalDate startDate, LocalDate endDate, String location, double totalKm, int totalDays, double basicAmount) {
        this.reservationId = reservationId;
        this.customerId = customerId;
        this.vehicleId = vehicleId;
        this.driverId = driverId;
        this.startDate = startDate;
        this.endDate = endDate;
        this.location = location;
        this.totalKm = totalKm;
        this.totalDays = totalDays;
        this.basicAmount = basicAmount;
    }

    public Reservation(String vehicleId, String customerId, double basicAmount) {
        this.vehicleId = vehicleId;
        this.customerId = customerId;
        this.basicAmount = basicAmount;

    }


    public String getReservationId() {
        return reservationId;
    }

    public void setReservationId(String reservationId) {
        this.reservationId = reservationId;
    }

    public String getCustomerId() {
        return customerId;
    }

    public void setCustomerId(String customerId) {
        this.customerId = customerId;
    }

    public String getVehicleId() {
        return vehicleId;
    }

    public void setVehicleId(String vehicleId) {
        this.vehicleId = vehicleId;
    }

    public String getDriverId() {
        return driverId;
    }

    public void setDriverId(String driverId) {
        this.driverId = driverId;
    }

    public LocalDate getStartDate() {
        return startDate;
    }

    public void setStartDate(LocalDate startDate) {
        this.startDate = startDate;
    }

    public LocalDate getEndDate() {
        return endDate;
    }

    public void setEndDate(LocalDate endDate) {
        this.endDate = endDate;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public double getTotalKm() {
        return totalKm;
    }

    public void setTotalKm(double totalKm) {
        this.totalKm = totalKm;
    }

    public int getTotalDays() {
        return totalDays;
    }

    public void setTotalDays(int totalDays) {
        this.totalDays = totalDays;
    }

    public double getBasicAmount() {
        return basicAmount;
    }

    public void setBasicAmount(double basicAmount) {
        this.basicAmount = basicAmount;
    }
}

