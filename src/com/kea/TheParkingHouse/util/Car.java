package com.kea.TheParkingHouse.util;

public class Car
{
    private int id;
    private CarType type;

    public Car()
    {
        id = 0;
        type = CarType.DEFAULT;
    }

    public Car(int id, CarType type)
    {
        this.id = id;
        this.type = type;
    }

    public int getId()
    {
        return id;
    }

    public void setId(int id)
    {
        this.id = id;
    }

    public CarType getType()
    {
        return null;
    }

    public void setType(CarType type)
    {
        this.type = type;
    }

    //save car attributes as a string for deserialization later
    public String serialize()
    {
        return id + ";" + type.getId();
    }

    //set car attributes based on a serialized string
    public void deserialize(String car)
    {
        try
        {
            String[] info = car.split(";");
            this.id = Integer.parseInt(info[0]);
            this.type = CarType.values()[Integer.parseInt(info[1])];
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
    }

    public boolean equals(Car car)
    {
        return this.id == car.id && this.type == car.type;
    }

    public String toString()
    {
        return "Id: '" + id + "' Car type: '" + type + "'";
    }
}
