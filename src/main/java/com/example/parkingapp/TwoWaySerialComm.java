package com.example.parkingapp;

import com.fazecast.jSerialComm.SerialPort;

import java.util.Scanner;

public class TwoWaySerialComm {

    public static void main(String[] args) {

        SerialPort[] ports = SerialPort.getCommPorts();

        SerialPort serialPort = ports[0];
        if (serialPort.openPort())
            System.out.println("Port opened successfully.");
        else {
            System.out.println("Unable to open the port.");
            return;
        }
        //serialPort.setComPortParameters(9600, 8, 1, SerialPort.NO_PARITY);
        serialPort.setComPortTimeouts(SerialPort.TIMEOUT_READ_BLOCKING, 1000, 0);

        Scanner data = new Scanner(serialPort.getInputStream());
        String value = "";
        while (data.hasNextLine()) {
            try {
                value = data.nextLine();
            } catch (Exception e) {
            }
            System.out.println(value);        }
        System.out.println("Done.");
    }
}
