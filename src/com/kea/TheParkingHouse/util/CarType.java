package com.kea.TheParkingHouse.util;

public enum CarType
{
    DEFAULT(0),
    CITROEN(1),
    BMW(2),
    CHRYSLER(3),
    HYUNDAI(4),
    SUBARU(5),
    MERCEDES(6),
    AUDI(7),
    FORD(8);

    private int id;

    CarType(int id)
    {
        this.id = id;
    }

    public int getId()
    {
        return id;
    }

    public static int getMaxCarTypes() { return values().length - 1; } //subtract 1 because "default" doesn't count
}
