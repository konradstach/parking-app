package com.example.parkingapp;

import com.example.parkingapp.model.ParkingSpot;
import com.example.parkingapp.repository.ParkingSpotRepository;
import com.example.parkingapp.repository.UserRepository;
import com.fazecast.jSerialComm.SerialPort;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.thymeleaf.ThymeleafProperties;
import org.springframework.context.annotation.Bean;
import org.thymeleaf.templateresolver.FileTemplateResolver;
import org.thymeleaf.templateresolver.ITemplateResolver;

import java.io.InputStream;
import java.util.Scanner;

@SpringBootApplication
public class ParkingAppApplication implements CommandLineRunner {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private ParkingSpotRepository parkingSpotRepository;

    public static void main(String[] args) {
        SpringApplication.run(ParkingAppApplication.class, args);

    }

    @Autowired
    private ThymeleafProperties properties;

    @Value("${spring.thymeleaf.templates_root:}")
    private String templatesRoot;

    @Bean
    public ITemplateResolver defaultTemplateResolver() {
        FileTemplateResolver resolver = new FileTemplateResolver();
        resolver.setSuffix(properties.getSuffix());
        resolver.setPrefix(templatesRoot);
        //resolver.setTemplateMode(properties.getMode());
        resolver.setCacheable(properties.isCache());
        return resolver;
    }


    @Override
    public void run(String... args) throws Exception {

//        SerialPort[] ports = SerialPort.getCommPorts();
//
//        SerialPort serialPort = ports[0];
//        if (serialPort.openPort())
//            System.out.println("Port opened successfully.");
//        else {
//            System.out.println("Unable to open the port.");
//            return;
//        }
//        //serialPort.setComPortParameters(9600, 8, 1, SerialPort.NO_PARITY);
//        serialPort.setComPortTimeouts(SerialPort.TIMEOUT_READ_BLOCKING, 1000, 0);
//
//        Scanner data = new Scanner(serialPort.getInputStream());
//        String value = "";
//        while (data.hasNextLine()) {
//            try {
//                value = data.nextLine();
//            } catch (Exception e) {
//            }
//            System.out.println(value);        }
//        System.out.println("Done.");
    }
}

