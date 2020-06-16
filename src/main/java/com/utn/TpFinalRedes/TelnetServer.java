package com.utn.TpFinalRedes;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.PrintStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;

public class TelnetServer extends Thread implements IServer {

    private Socket client;
    private Scanner sc;
    private DataInputStream in;
    private PrintStream ps;

    public TelnetServer(){ }

    @Override
    public void setSocket(Socket client) {
        this.client = client;
    }

    @Override
    public void disconnect() {
        try {
            client.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void init() {

//        Scanner sc2 = new Scanner(client.getInputStream());
//        DataInputStream dis = new DataInputStream(System.in);
//        PrintStream ps = new PrintStream(client.getOutputStream());
        try {
            sc = new Scanner(client.getInputStream());
            in = new DataInputStream(System.in);
            ps = new PrintStream(client.getOutputStream());

//            out = new DataOutputStream(client.getOutputStream());
            String msg = "Bienvenido...";
            ps.println(msg);

//            dis = new DataInputStream(System.in);
//            sc = new Scanner(client.getInputStream());
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    @Override
    public void run() {
        try {
            init();
            String msg = "";

            msg = "Bienvenido, esciba un mensaje: ";
            ps.println(msg);
            while (!"x".equals(msg = sc.nextLine())) {//voy a recibir los mensajes del cliente mientras que no mande x

                System.out.println("Mensaje del cliente: " + msg);
                System.out.println("Respuesta: ");

                msg = in.readLine();
                ps.println("Respuesta del servidor: " + msg);
            }
            System.out.println("El cliente: " + client.getLocalAddress() + " " + client.getPort() + " se ah desconectado");
            msg = "adios " + client.getLocalAddress() + " " + client.getPort();
            ps.println(msg);
            ps.flush();
            ps.close();
            sc.close();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
