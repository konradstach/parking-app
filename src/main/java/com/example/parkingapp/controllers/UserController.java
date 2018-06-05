package com.example.parkingapp.controllers;

import com.example.parkingapp.repository.BookingRepository;
import com.example.parkingapp.repository.ParkingSpotRepository;
import com.example.parkingapp.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;

@Controller
@RequestMapping(path = "/parkingapp/myaccount")
public class UserController {
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private ParkingSpotRepository parkingSpotRepository;

    @Autowired
    private BookingRepository bookingRepository;

    @GetMapping(value = "")
    public String getMyAccountPage(Model model, HttpServletRequest request) {
        model.addAttribute("username", request.getRemoteUser());
        model.addAttribute("user", userRepository.findOne(userRepository.findUserIdByUsername(request.getRemoteUser())));
        model.addAttribute("numberOfBookings", bookingRepository.findBookingByUserId(userRepository.findUserIdByUsername(request.getRemoteUser())).size());
        return "my_account";
    }

}
