package com.utn.TpFinalRedes;

import java.io.DataInput;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {

    public static void main(String[] args) {

        ServerSocket server = null;
        Socket client = null;

        DataInputStream in;
        DataOutputStream out;

        final int PORT = 3000;

        String msg = "";// cuando convierta a hilos tengo que ver bien donde inicializar el msg, para q no empiece como x con los nuevos clientes
        try {
            server = new ServerSocket(PORT);
            System.out.println("servidor iniciado");


            //mantengo el servidor corriendo, a la espera de un cliente
            while (true) {

                msg = "";//creo q cuando maneje varios usuarios necesito eso para q no asigne una x a los nuevos y cierre su coneccion automaticamente...creo

                //acepto al cliente
                client = server.accept();
                System.out.println("el cliente" + client.toString() + " fue aceptado");

                //con esto recibire los mensajes del cliente
                in = new DataInputStream(client.getInputStream());
                //con esto mandare mensajes al cliente
                out = new DataOutputStream(client.getOutputStream());

                while (!msg.equals("x")) {//voy a recibir los mensajes del cliente mientras que no mande x
                    //aca recibo el mensaje del cliente
                    msg = in.readUTF();
                    System.out.println("mensaje del cliente: " + msg);

                    if (msg.equals("x")) {
                        //si llega aca, quiere decir que puso x, con lo cual se quiere ir
                        out.writeUTF("adios " + client.toString());
                        System.out.println("el cliente se ah desconectado");
                        client.close();
                    } else {
                        //esto le va a llegar al cliente
                        out.writeUTF("gracias por comunicarce con el servidor...");
                    }
                }

            }

        } catch (IOException e) {
            e.printStackTrace();
        }


    }


}
