package com.kea.TheParkingHouse.test.thread;

import com.kea.TheParkingHouse.util.Car;
import com.kea.TheParkingHouse.util.CarFactory;
import com.kea.TheParkingHouse.util.ParkingHouse;

import java.util.ArrayList;

public class _Thread
{
    public static void main(String[] args)
    {
        //create car factory
        CarFactory carFactory = new CarFactory();

        //create new cars from factory
        ArrayList<Car> newCars = carFactory.createCars();

        //create new parking house with new cars as parameter
        ParkingHouse parkingHouse = new ParkingHouse(newCars);

        //create new thread with parking house as parameter
        Thread parkingHouseThread = new Thread(parkingHouse);
        parkingHouseThread.start();
    }
}
