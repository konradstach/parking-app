package com.example.parkingapp.model;


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="parking_spot")
public class ParkingSpot {

    @Id
    @Column(name="parking_spot_id")
    private int parkingSpotId;

    @Column(name="blocked")
    private boolean blocked;

    @Column(name="active")
    private boolean active;

    public ParkingSpot(){};

    public ParkingSpot(boolean blocked, boolean active) {
        this.blocked = blocked;
        this.active = active;
    }

    public int getParkingSpotId() {
        return parkingSpotId;
    }

    public void setParkingSpotId(int parkingSpotId) {
        this.parkingSpotId = parkingSpotId;
    }

    public boolean isBlocked() {
        return blocked;
    }

    public void setBlocked(boolean blocked) {
        this.blocked = blocked;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }
}
