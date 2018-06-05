package com.example.parkingapp.model;

import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Date;

@Entity
@Table(name="booking")
public class Booking {

    @Id
    @Column(name="booking_id")
    private int bookingId;

    @Column(name="user_id")
    private int userId;

    @Column(name="parking_spot_id")
    private int parkingSpotId;

    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm")
    @Column(name="booking_date")
    private Date bookingDate;

    public Booking(){};

    public Booking(int userId, int parkingSpotId, Date bookingDate) {
        this.userId = userId;
        this.parkingSpotId = parkingSpotId;
        this.bookingDate = bookingDate;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public int getParkingSpotId() {
        return parkingSpotId;
    }

    public void setParkingSpotId(int parkingSpotId) {
        this.parkingSpotId = parkingSpotId;
    }

    public Date getBookingDate() {
        return bookingDate;
    }

    public void setBookingDate(Date bookingDate) {
        this.bookingDate = new Date();
    }

    public int getBookingId() {
        return bookingId;
    }

    public void setBookingId(int bookingId) {
        this.bookingId = bookingId;
    }
}
