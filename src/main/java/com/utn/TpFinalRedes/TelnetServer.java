package com.utn.TpFinalRedes;

import java.io.DataInputStream;
import java.io.IOException;
import java.io.PrintStream;
import java.net.Socket;
import java.util.Scanner;

public class TelnetServer extends Thread implements IServer {

    private Socket client;
    private Scanner sc;
    private DataInputStream in;
    private PrintStream ps; //esto me sirve para que la app de telnet vea los mensajes

    public TelnetServer(){ }

    @Override
    public void setSocket(Socket client) {
        this.client = client;
    }

    @Override
    public void disconnect() {
        try {
            System.out.println("El cliente: " + client.getLocalAddress() + " " + client.getPort() + " se ah desconectado");
            String msg = "adios " + client.getLocalAddress() + " " + client.getPort();
            ps.println(msg);
            ps.flush();
            ps.close();
            sc.close();
            client.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void init() {

        try {
            sc = new Scanner(client.getInputStream());
            in = new DataInputStream(System.in);
            ps = new PrintStream(client.getOutputStream());

            String msg = "Bienvenido...";
            ps.println(msg);

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

                System.out.println("Mensaje del cliente " + client.getLocalAddress() + " " + client.getPort() + ": " + msg);
                System.out.println("Respuesta: ");

                msg = in.readLine();
                ps.println("Respuesta del servidor: " + msg);
            }

            disconnect();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
