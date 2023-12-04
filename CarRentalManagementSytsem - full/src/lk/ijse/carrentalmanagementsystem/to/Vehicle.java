package lk.ijse.carrentalmanagementsystem.to;

public class Vehicle {
    private String vehicleId;
    private String vehicleName;
    private String vehicleModel;
    private String vehicleColour;
    private String vehicleLicenceId;
    private String vehicleFuelType;
    private double vehicleLiterOfFuel;
    private String vehicleTransmission;
    private double vehiclePrice;
    private String image;
    private String status;


    // Constructor


    public Vehicle(String vehicleName, String vehicleFuelType, String vehicleTransmission, double vehiclePrice,String image) {
        this.vehicleName = vehicleName;
        this.vehicleFuelType = vehicleFuelType;
        this.vehicleTransmission = vehicleTransmission;
        this.vehiclePrice = vehiclePrice;
        this.image = image;
    }

    public Vehicle(String vehicleId, String vehicleName, String vehicleModel, String vehicleColour,
                   String vehicleLicenceId, String vehicleFuelType, double vehicleLiterOfFuel,
                   String vehicleTransmission, double vehiclePrice, String image,String status) {
        this.vehicleId = vehicleId;
        this.vehicleName = vehicleName;
        this.vehicleModel = vehicleModel;
        this.vehicleColour = vehicleColour;
        this.vehicleLicenceId = vehicleLicenceId;
        this.vehicleFuelType = vehicleFuelType;
        this.vehicleLiterOfFuel = vehicleLiterOfFuel;
        this.vehicleTransmission = vehicleTransmission;
        this.vehiclePrice = vehiclePrice;
        this.image = image;
        this.status = status;
    }

    // Getter and Setter methods for encapsulation
    public String getVehicleId() {
        return vehicleId;
    }

    public void setVehicleId(String vehicleId) {
        this.vehicleId = vehicleId;
    }

    public String getVehicleName() {
        return vehicleName;
    }

    public void setVehicleName(String vehicleName) {
        this.vehicleName = vehicleName;
    }

    public String getVehicleModel() {
        return vehicleModel;
    }

    public void setVehicleModel(String vehicleModel) {
        this.vehicleModel = vehicleModel;
    }

    public String getVehicleColour() {
        return vehicleColour;
    }

    public void setVehicleColour(String vehicleColour) {
        this.vehicleColour = vehicleColour;
    }

    public String getVehicleLicenceId() {
        return vehicleLicenceId;
    }

    public void setVehicleLicenceId(String vehicleLicenceId) {
        this.vehicleLicenceId = vehicleLicenceId;
    }

    public String getVehicleFuelType() {
        return vehicleFuelType;
    }

    public void setVehicleFuelType(String vehicleFuelType) {
        this.vehicleFuelType = vehicleFuelType;
    }

    public double getVehicleLiterOfFuel() {
        return vehicleLiterOfFuel;
    }

    public void setVehicleLiterOfFuel(double vehicleLiterOfFuel) {
        this.vehicleLiterOfFuel = vehicleLiterOfFuel;
    }

    public String getVehicleTransmission() {
        return vehicleTransmission;
    }

    public void setVehicleTransmission(String vehicleTransmission) {
        this.vehicleTransmission = vehicleTransmission;
    }

    public double getVehiclePrice() {
        return vehiclePrice;
    }

    public void setVehiclePrice(double vehiclePrice) {
        this.vehiclePrice = vehiclePrice;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "Vehicle{" +
                "vehicleId='" + vehicleId + '\'' +
                ", vehicleName='" + vehicleName + '\'' +
                ", vehicleModel='" + vehicleModel + '\'' +
                ", vehicleColour='" + vehicleColour + '\'' +
                ", vehicleLicenceId='" + vehicleLicenceId + '\'' +
                ", vehicleFuelType='" + vehicleFuelType + '\'' +
                ", vehicleLiterOfFuel=" + vehicleLiterOfFuel +
                ", vehicleTransmission='" + vehicleTransmission + '\'' +
                ", vehiclePrice=" + vehiclePrice +
                ", image='" + image + '\'' +
                ", status='" + status + '\'' +
                '}';
    }
}

