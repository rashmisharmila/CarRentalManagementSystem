package lk.ijse.carrentalmanagementsystem.to;

import javafx.scene.image.ImageView;

import java.sql.Date;
import java.time.LocalDate;
import java.time.LocalTime;

public class Payment {

    private String PaymentId;
    private String reservationId;
    private LocalDate paymentDate;
    private LocalTime PaymentTime;
    private double payment;

    private String image;


    public Payment(String paymentId, String reservationId, LocalDate paymentDate, LocalTime paymentTime, double payment, String imageOfPaymentSlip) {
        PaymentId = paymentId;
        this.reservationId = reservationId;
        this.paymentDate = paymentDate;
        PaymentTime = paymentTime;
        this.payment = payment;
        this.image = imageOfPaymentSlip;
    }


    // Getters and Setters
    public String getReservationId() {
        return reservationId;
    }

    public void setReservationId(String reservationId) {
        this.reservationId = reservationId;
    }

    public double getPayment() {
        return payment;
    }

    public void setPayment(double payment) {
        this.payment = payment;
    }


    public String getPaymentId() {
        return PaymentId;
    }

    public void setPaymentId(String paymentId) {
        PaymentId = paymentId;
    }

    public LocalDate getPaymentDate() {
        return paymentDate;
    }

    public void setPaymentDate(LocalDate paymentDate) {
        this.paymentDate = paymentDate;
    }

    public LocalTime getPaymentTime() {
        return PaymentTime;
    }

    public void setPaymentTime(LocalTime paymentTime) {
        PaymentTime = paymentTime;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    @Override
    public String toString() {
        return "Payment{" +
                "PaymentId='" + PaymentId + '\'' +
                ", reservationId='" + reservationId + '\'' +
                ", paymentDate=" + paymentDate +
                ", PaymentTime='" + PaymentTime + '\'' +
                ", payment=" + payment +
                ", image='" + image + '\'' +
                '}';
    }
}
