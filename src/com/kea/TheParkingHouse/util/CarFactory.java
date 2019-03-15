package com.kea.TheParkingHouse.util;

import java.util.ArrayList;
import java.util.Random;

public class CarFactory
{
    private int carId = 0;
    private Random rand = new Random();

    private final int MAX_CARS = 20; //Park house limit is 10 so this might result in a denial of storage of certain cars

    public CarFactory() {}

    // generate a random amount of cars
    public ArrayList<Car> createCars()
    {
        ArrayList<Car> cars = new ArrayList<>();
        int amount = rand.nextInt(MAX_CARS) + 1; //add 1 to avoid 0 result

        for (int i = 0; i < amount; i++)
        {
            carId++;
            CarType type = CarType.values()[rand.nextInt(CarType.getMaxCarTypes()) + 1]; //also add 1 to avoid 0 result
            cars.add(new Car(carId, type));
        }

        return cars;
    }
}
