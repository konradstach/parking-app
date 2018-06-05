package com.example.parkingapp.repository;

import com.example.parkingapp.model.Booking;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import java.util.List;

public interface BookingRepository extends JpaRepository<Booking, Integer> {

    @Query(nativeQuery = true, value = "SELECT * FROM booking b WHERE b.user_id =:user_id")
    List<Booking> findBookingByUserId(@Param("user_id") int userId);

    Booking findFirstByUserId(int userId);
}
