package com.kea.TheParkingHouse.util;

import java.util.ArrayList;

public class ParkingHouse implements Runnable
{
    private ArrayList<Car> carList;
    private ArrayList<Car> carsToAccept;
    private final int MAX_CARS = 10;

    public ParkingHouse()
    {
        carList = new ArrayList<>();
        carsToAccept = null;
    }

    //constructor required if this is running as a seperate thread
    public ParkingHouse(ArrayList<Car> carsToAccept)
    {
        carList = new ArrayList<>();
        this.carsToAccept = carsToAccept;
    }

    public void run()
    {
        if (carsToAccept == null)
        {
            System.out.println("Wrong constructor used to run a new thread.\nMust take an ArrayList of cars to accept as parameter.");
            return;
        }

        for (Car car : carsToAccept)
        {
            System.out.println("Car to store: " + car.toString());

            if (canAccept()) {
                acceptCar(car);
                System.out.println("Car stored: " + car.toString());
            } else {
                System.out.println("Car could not be stored because max limit of " + getMaxCars() + " was reached. Car: " + car.toString());
            }

            System.out.println("Park House status: " + getCarsCount() + " out of " + getMaxCars() + " cars parked");
        }
    }

    public void acceptCar(Car car)
    {
        if (canAccept())
        {
            carList.add(car);
        }
    }

    public boolean canAccept() { return carList.size() < MAX_CARS; }

    public int getMaxCars() { return MAX_CARS; }

    public int getCarsCount() { return carList.size(); }

}
