package com.example.parkingapp.service;

import com.example.parkingapp.repository.BookingRepository;
import com.example.parkingapp.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.PropertySource;

@PropertySource("classpath:application.properties")
@org.springframework.stereotype.Service("service")
public class Service {

    @Autowired
    private BookingRepository bookingRepository;

    @Autowired
    private UserRepository userRepository;


}
