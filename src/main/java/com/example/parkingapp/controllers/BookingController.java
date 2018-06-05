package com.example.parkingapp.controllers;

import com.example.parkingapp.model.Booking;
import com.example.parkingapp.repository.BookingRepository;
import com.example.parkingapp.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;

@Controller
@RequestMapping(path = "/parkingapp/booking")
public class BookingController {
    @Autowired
    private BookingRepository bookingRepository;
    @Autowired
    private UserRepository userRepository;

    @GetMapping(value = "")
    public String getBookingPage(Model model, HttpServletRequest request) {
        model.addAttribute("username", request.getRemoteUser());
        model.addAttribute("user", userRepository.findOne(userRepository.findUserIdByUsername(request.getRemoteUser())));
        model.addAttribute("booking", bookingRepository.findBookingByUserId(userRepository.findUserIdByUsername(request.getRemoteUser())).get(0));
        return "booking";
    }


    @PostMapping(value = "/opengate")
    public String openGate(Model model,
                           HttpServletRequest request,
                           @RequestParam("parkingSpotId") int parkingSpotId) {
        //tu otwierać odopwiednią bramkę

        return "booking";


    }

    @PostMapping(value = "/checklines")
    public String checkIfInLine(Model model,
                                HttpServletRequest request,
                                @RequestParam("parkingSpotId") int parkingSpotId,
                                @RequestParam("userId") int userId) {
        //tu sprawdzić czy jest w liniach

        Booking booking = bookingRepository.findFirstByUserId(userId);
        bookingRepository.delete(booking.getBookingId());

        return "redirect:/parkingapp/parking";
    }
}
