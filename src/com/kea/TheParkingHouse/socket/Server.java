package com.kea.TheParkingHouse.socket;

import com.kea.TheParkingHouse.util.Car;
import com.kea.TheParkingHouse.util.ParkingHouse;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;

public class Server
{
    private final int PORT = 1234;
    private Socket socket = null;
    private Scanner input;
    private PrintWriter output;
    private ParkingHouse parkingHouse = new ParkingHouse();

    public Server() {}

    public void run()
    {
        System.out.println("Opening port...\n");
        ServerSocket serverSocket = null;
        try
        {
            serverSocket = new ServerSocket(PORT);

            do
            {
                socket = serverSocket.accept();
                System.out.println("* New client connected! *");
                handleConnection();
            }while (true);
        }
        catch(IOException ioEx)
        {
            System.out.println("Unable to attach to port!");
            System.exit(1);
        }
    }

    public void handleConnection()
    {
        try
        {
            input = new Scanner(socket.getInputStream());
            output = new PrintWriter(socket.getOutputStream(), true);

            String message = input.nextLine();
            String response = "";

            while (!message.equals("***CLOSE***"))
            {
                Car car = new Car();
                car.deserialize(message);

                System.out.println("CLIENT> " + car.toString());

                if (parkingHouse.canAccept())
                {
                    parkingHouse.acceptCar(car);
                    response = "Car stored: " + car.toString();
                }
                else
                {
                    response = "Car could not be stored because max limit of " + parkingHouse.getMaxCars() + " was reached. Car: " + car.toString();
                }

                System.out.println("Park House status: " + parkingHouse.getCarsCount() + " out of " + parkingHouse.getMaxCars() + " cars parked");
                output.println(response);

                message = input.nextLine();
            }
        }
        catch (IOException ioException)
        {
            ioException.printStackTrace();
        }
        finally
        {
            try
            {
                System.out.println("* Closing connection... *");
                socket.close();
            }
            catch (IOException ioException)
            {
                System.out.println("Unable to disconnect!");
                ioException.printStackTrace();
                System.out.println("* Closing program... *");
                System.exit(1);
            }
        }
    }
}
