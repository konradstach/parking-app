package com.example.parkingapp.controllers;

import com.example.parkingapp.model.Booking;
import com.example.parkingapp.model.ParkingSpot;
import com.example.parkingapp.repository.BookingRepository;
import com.example.parkingapp.repository.ParkingSpotRepository;
import com.example.parkingapp.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;

@Controller
@RequestMapping(path="/parkingapp/parking")
public class ParkingController {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private ParkingSpotRepository parkingSpotRepository;

    @Autowired
    private BookingRepository bookingRepository;

    @GetMapping(value = "")
    public String getParkingSchema (Model model, HttpServletRequest request){
        model.addAttribute("user", request.getRemoteUser());
        model.addAttribute("userId", userRepository.findUserIdByUsername(request.getRemoteUser()));
        model.addAttribute("spot1", parkingSpotRepository.findOne(1) );
        model.addAttribute("spot2", parkingSpotRepository.findOne(2) );
        model.addAttribute("spot3", parkingSpotRepository.findOne(3) );
    return "parking_schema";
    }

    @PostMapping(value = "/booking")
    public String bookParkingSpot (Model model,
                                   HttpServletRequest request,
                                   @RequestParam ("userId") int userId,
                                   @RequestParam ("parkingSpotId") int parkingSpotId){
        ParkingSpot spot = parkingSpotRepository.findOne(parkingSpotId);
        spot.setBlocked(true);
        parkingSpotRepository.save(spot);

        Booking booking = new Booking();
        booking.setParkingSpotId(parkingSpotId);
        booking.setUserId(userId);
        booking.setBookingDate(new Date());
        bookingRepository.save(booking);

        //tu dać zeby wysuwała sie odpowiednia blokada, włączał led

        return "redirect:/parkingapp/parking";
    }
}
