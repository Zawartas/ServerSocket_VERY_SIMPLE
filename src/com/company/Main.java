package com.company;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        connectToServer();
    }

    public static void connectToServer() {
        //Try connect to the server on an unused port eg 9991. A successful connection will return a socket
        try (ServerSocket serverSocket = new ServerSocket(9991)) {
            System.out.println("Server created");
            Socket connectionSocket = serverSocket.accept();

            //Create Input&Outputstreams for the connection
            InputStream inputToServer = connectionSocket.getInputStream();
            System.out.println("Created Input Stream to Server");
            Scanner scanner = new Scanner(inputToServer, "UTF-8");
            System.out.println("Created Scanner that reads from inputToServer");

            OutputStream outputFromServer = connectionSocket.getOutputStream();
            System.out.println("Created Output Stream from Server");
            PrintWriter serverPrintOut =
                    new PrintWriter(new OutputStreamWriter(outputFromServer, "UTF-8"), true);
            System.out.println("Created PrintWriter that reads from OutputStreamWriter which has been initialized by outputFromServer");

            serverPrintOut.println("Hello World! Enter \"0 0\" to exit.");
            serverPrintOut.println("Enter your name: ");

            String name = scanner.nextLine();
            serverPrintOut.println("Hello " + name);

            boolean done = false;

            while (!done && scanner.hasNextLine()) {
                int num1 = scanner.nextInt();
                int num2 = scanner.nextInt();
                int sum = num1 + num2;
                serverPrintOut.println("Sum of two numbers: " + sum);
                if (num1 == 0 && num2 == 0) {
                    done = true;
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
