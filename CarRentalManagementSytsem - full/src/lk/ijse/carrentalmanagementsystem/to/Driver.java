package lk.ijse.carrentalmanagementsystem.to;

public class Driver {
    // Fields
    private String driverId;
    private String driverName;
    private String driverAddress;
    private String driverPhoneNumber;
    private String driverNIC;
    private double preDayValue;

    // Constructors
    public Driver(String driverId, String driverName, String driverAddress, String driverPhoneNumber, String driverNIC, double preDayValue) {
        this.driverId = driverId;
        this.driverName = driverName;
        this.driverAddress = driverAddress;
        this.driverPhoneNumber = driverPhoneNumber;
        this.driverNIC = driverNIC;
        this.preDayValue = preDayValue;
    }
    public Driver(String driverId, String driverName, String driverPhoneNumber, double preDayValue) {
        this.driverId = driverId;
        this.driverName = driverName;
        this.driverPhoneNumber = driverPhoneNumber;
        this.preDayValue = preDayValue;
    }

    // Getters and Setters
    public String getDriverId() {
        return driverId;
    }

    public void setDriverId(String driverId) {
        this.driverId = driverId;
    }

    public String getDriverName() {
        return driverName;
    }

    public void setDriverName(String driverName) {
        this.driverName = driverName;
    }

    public String getDriverAddress() {
        return driverAddress;
    }

    public void setDriverAddress(String driverAddress) {
        this.driverAddress = driverAddress;
    }

    public String getDriverPhoneNumber() {
        return driverPhoneNumber;
    }

    public void setDriverPhoneNumber(String driverPhoneNumber) {
        this.driverPhoneNumber = driverPhoneNumber;
    }

    public String getDriverNIC() {
        return driverNIC;
    }

    public void setDriverNIC(String driverNIC) {
        this.driverNIC = driverNIC;
    }

    public double getPreDayValue() {
        return preDayValue;
    }

    public void setPreDayValue(double preDayValue) {
        this.preDayValue = preDayValue;
    }


    public String toString() {
        return "Driver{" +
                "driverId='" + driverId + '\'' +
                ", driverName='" + driverName + '\'' +
                ", driverAddress='" + driverAddress + '\'' +
                ", driverPhoneNumber='" + driverPhoneNumber + '\'' +
                ", driverNIC='" + driverNIC + '\'' +
                ", preDayValue=" + preDayValue +
                '}';
    }


}

