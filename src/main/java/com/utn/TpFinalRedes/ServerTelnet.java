package com.utn.TpFinalRedes;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;

public class ServerTelnet {

    public static void main(String[] args) {
        new Server().run();
    }

    public void run() {
        ServerSocket server = null;
        Socket client = null;
        DataInputStream in = new DataInputStream(System.in);
        final int PORT = 3000;
        Scanner sc = new Scanner(System.in);
        String msg = "";// cuando convierta a hilos tengo que ver bien donde inicializar el msg, para q no empiece como x con los nuevos clientes
        try {
            server = new ServerSocket(PORT);
            System.out.println("servidor iniciado");
            //mantengo el servidor corriendo, a la espera de un cliente
            while (true) {
                msg = "";//creo q cuando maneje varios usuarios necesito eso para q no asigne una x a los nuevos y cierre su coneccion automaticamente...creo
                //acepto al cliente
                client = server.accept();
                System.out.println("el cliente " + client.getLocalAddress() + " " + client.getPort() + " fue aceptado");
                PrintStream ps = new PrintStream(client.getOutputStream());
                while (sc.hasNextLine() && !"x".equals(msg = sc.nextLine()) && !msg.equals("x")) {//voy a recibir los mensajes del cliente mientras que no mande x
                    System.out.println("Mensaje recibido de cliente " + client.toString() + ": " + msg);
                    System.out.print("Escriba su Mensaje a cliente: " + client.toString() + ": ");
                    msg = in.readLine();
                    ps.println("Servidor: " + msg);
                    ps.print("Mensaje: ");
                }
                PrintWriter out = new PrintWriter(client.getOutputStream());
                System.out.println("Cliente " + client.toString() + " desconectado");
                out.flush();
                out.close();
                client.close();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
