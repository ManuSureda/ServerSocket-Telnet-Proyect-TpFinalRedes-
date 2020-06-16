package com.utn.TpFinalRedes;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

public class MainServer {

    public static void main(String[] args) throws IOException {

        ServerSocket serverSocket;
        IServer server = null;
        System.out.println("Iniciando servidor...");

        try {
            serverSocket = new ServerSocket(3000);//levanto el server
            System.out.println("Servidor iniciado...");
            System.out.println(serverSocket.toString());
            System.out.println("Esperando a algun cliente...");

            while (true) {
                Socket client = serverSocket.accept();//acepto al cliente
                server = setServerType();//elijo si el cliente se conectara por consola de java o por telnet
                System.out.println("El cliente " + client.getLocalAddress() + " " + client.getPort() + " fue aceptado");

                server.setSocket(client);
                server.start();//ejecuto el run del server, ya sea java o telnet
            }

        } catch (IOException e) {
            Logger.getLogger(MainServer.class.getName()).log(Level.SEVERE, null, e);
        }
    }

    private static IServer setServerType() {
        Scanner sc = new Scanner(System.in);
        int opt = 0;
        int flag = 0;

        IServer server = null;

        while (flag == 0) {
            System.out.println("Elija un tipo de servidor");
            System.out.println("1. Servidor java");
            System.out.println("2. Servidor telnet");
            System.out.println("Opcion: ");

            if (sc.hasNextInt()) {
                opt = sc.nextInt();
                if (opt == 1) {
                    server = new JavaServer();
                    flag = 1;
                } else if (opt == 2) {
                    server = new TelnetServer();
                    flag = 1;
                } else {
                    System.out.println("Opcion incorrecta");
                }
            } else {
                System.out.println("Opcion invalida");
            }
        }
        return server;
    }
}
