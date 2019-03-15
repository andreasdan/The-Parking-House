package com.kea.TheParkingHouse.socket;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.InetAddress;
import java.net.Socket;
import java.util.Scanner;

import com.kea.TheParkingHouse.util.Car;
import com.kea.TheParkingHouse.util.CarFactory;

public class Client
{
    private final int PORT = 1234;
    private Socket socket = null;
    private Scanner input;
    private PrintWriter output;
    private CarFactory carFactory = new CarFactory();

    public Client() {}

    public void run()
    {
        InetAddress host = null;
        try
        {
            host = InetAddress.getLocalHost();
            socket = new Socket(host, PORT);
            handleConnection();
        }
        catch(Exception e)
        {
            e.printStackTrace();
            System.exit(1);
        }
    }

    private void handleConnection()
    {
        try
        {
            input = new Scanner(socket.getInputStream());
            output = new PrintWriter(socket.getOutputStream(), true);

            boolean closing = false;
            String message = "";
            String response = "";
            while (!response.equals("***CLOSE***") && !closing)
            {
                for (Car car : carFactory.createCars())
                {
                    message = car.serialize();
                    output.println(message);

                    if (input.hasNextLine())
                    {
                        response = input.nextLine();
                        System.out.println("SERVER> " + response);
                    }
                }
                output.println("***CLOSE***"); //close afterwards
                closing = true;
            }
        }
        catch (Exception e)
        {
            e.printStackTrace();
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
