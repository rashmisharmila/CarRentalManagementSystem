drop database if exists CRMS;
CREATE DATABASE IF NOT EXISTS CRMS;
use CRMS;
create table if not exists User(
                                   userid varchar(15),
                                   name varchar(45),
                                   address varchar(60),
                                   phoneNumber varchar(120),
                                   email varchar(20),
                                   role varchar(20),
                                   username varchar(30),
                                   password varchar(30),
                                   CONSTRAINT PRIMARY KEY (userid)
);

select * from User;

select username,password from User;


CREATE TABLE IF NOT EXISTS Customer(
    customerId VARCHAR(16) NOT NULL,
    customerName VARCHAR(30),
    customerAddress VARCHAR(30),
    customerPhoneNumber varchar(45),
    customerNIC VARCHAR(30),
    CONSTRAINT PRIMARY KEY (customerId)
    );

create table IF NOT EXISTS Vehicle(
    vehicleId VARCHAR(45) NOT NULL,
    vehicleName VARCHAR(30),
    vehicleModel varchar(45),
    vehicleColour Varchar(20),
    vehicleLicenceId VARCHAR(10),
    vehicleFuelType VARCHAR(20),
    vehicleLiterOfFuel DECIMAL(10),
    vehicleTransmission varchar(20),
    vehiclePrice DECIMAL(10),
    image varchar(100),
    status varchar(20),
    CONSTRAINT PRIMARY KEY (vehicleId)
    );

Create table IF NOT EXISTS Driver(
    driverId varchar(15),
    driverName varchar(45),
    driverAddress Varchar(45),
    driverPhoneNumber varchar(35),
    driverNIC varchar(20),
    preDayValue decimal(10),
    CONSTRAINT PRIMARY KEY (driverId)
    );

create table IF NOT EXISTS reservation(
    reservationId varchar(10),
    customerId VARCHAR(6) NOT NULL,
    vehicleId VARCHAR(45) NOT NULL,
    driverId varchar(45),
    startDate DATE,
    endDate DATE,
    location VARCHAR(100),
    totalKm decimal(10,3),
    totalDays int,
    basicAmount DECIMAL(10,2),

    CONSTRAINT PRIMARY KEY (reservationId),
    CONSTRAINT FOREIGN KEY( vehicleId) REFERENCES  vehicle(vehicleId) on Delete Cascade on Update Cascade,
    CONSTRAINT FOREIGN KEY(customerId) REFERENCES   Customer(customerId) on Delete Cascade on Update Cascade,
    CONSTRAINT FOREIGN KEY(driverId) REFERENCES   Driver(driverId) on Delete Cascade on Update Cascade
    );


Create table if not exists payments(
    PaymentId varchar(10),
    reservationId varchar(10) ,
    PaymentDate DATE,
    PaymentTime TIME,
    Payment DECIMAL(10),
    paymentSlip varchar(100),
    CONSTRAINT PRIMARY KEY (PaymentId),
    CONSTRAINT FOREIGN KEY(reservationId) REFERENCES   reservation(reservationId) on Delete Cascade on Update Cascade

);
SELECT count(vehicleId) FROM Vehicle;
SELECT count(userId) FROM User;
SELECT count(driverId) FROM Driver;
SELECT  vehicleId,vehicleName,vehicleModel,vehicleColour,vehicleLicenceId,vehicleFuelType, vehicleLiterOfFuel, vehicleTransmission,vehiclePrice  FROM Vehicle;
SELECT * FROM Vehicle;
DELETE FROM Vehicle where vehicleId='V001';
SELECT vehicleId  from Vehicle order by vehicleId desc limit 1;
UPDATE Customer set customerName=?,customerAddress=?,customerPhoneNumber=?,customerNIC =? where customerId=?;
SELECT * FROM User;
SELECT customerId from  Customer;
SELECT vehicleId,customerId,basicAmount  from  reservation  WHERE reservationId ='R001'
