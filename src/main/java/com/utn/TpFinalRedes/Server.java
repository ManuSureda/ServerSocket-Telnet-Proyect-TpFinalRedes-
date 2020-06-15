package com.utn.TpFinalRedes;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;

public class Server {

//    private ServerSocket server;
//    Socket client;
//    DataInputStream in;
//    DataOutputStream out;
//    final int PORT = 3000;
//    Scanner sc;
//    String msg;

    public static void main(String[] args) {
        new Server().run();
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

                //con esto recibire los mensajes del cliente
                in = new DataInputStream(client.getInputStream());
                //con esto mandare mensajes al cliente
                out = new DataOutputStream(client.getOutputStream());

                while (!"x".equals(msg)) {//voy a recibir los mensajes del cliente mientras que no mande x
                    //aca recibo el mensaje del cliente
                    msg = in.readUTF();
                    System.out.println("mensaje del cliente: " + msg);

                    if ("x".equals(msg)) {
                        //si llega aca, quiere decir que puso x, con lo cual se quiere ir
                        out.writeUTF("adios " + client.getLocalAddress() + " " + client.getPort());
                        System.out.println("el cliente se ah desconectado");
                        client.close();
                    } else {
                        System.out.println("Respuesta: ");
                        //esto le va a llegar al cliente
                        msg = sc.nextLine();
                        out.writeUTF(msg);//aca mande mi mensaje al server
                    }
                }

            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
