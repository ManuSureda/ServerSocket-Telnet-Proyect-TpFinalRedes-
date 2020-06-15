package com.utn.TpFinalRedes;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.PrintStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;

public class ServerTelnet {

    public static void main(String[] args) {
        new ServerTelnet().run();
    }

    public void run() {

        ServerSocket server = null;
        Socket client = null;

        DataInputStream in;
        DataOutputStream out;

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

                Scanner sc2 = new Scanner(client.getInputStream());
                DataInputStream disfk = new DataInputStream(System.in);
                PrintStream ps = new PrintStream(client.getOutputStream());


                msg = "Bienvenido, esciba un mensaje: ";
                ps.println(msg);
                while (!"x".equals(msg = sc2.nextLine())) {//voy a recibir los mensajes del cliente mientras que no mande x

                    System.out.println("Mensaje del cliente: " + msg);
                    System.out.println("Respuesta: ");

                    msg = disfk.readLine();
                    ps.println("Respuesta del servidor: " + msg);
                }
                System.out.println("El cliente: "+ client.getLocalAddress() + " " + client.getPort()+ " se ah desconectado");
                msg = "adios " + client.getLocalAddress() + " " + client.getPort();
                ps.println(msg);
                ps.flush();
                ps.close();
                sc.close();
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
