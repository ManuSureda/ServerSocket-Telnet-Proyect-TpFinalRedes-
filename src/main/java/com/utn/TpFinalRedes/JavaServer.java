package com.utn.TpFinalRedes;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.util.Scanner;

public class JavaServer extends Thread implements IServer {

    private Socket client;
    private DataOutputStream out;
    private DataInputStream in;
    private DataInputStream dis;
    private Scanner sc;

    public JavaServer() {
    }

    @Override
    public void setSocket(Socket client) {
        this.client = client;
    }

    public void init() {
        try {
            //con esto recibire los mensajes del cliente
            in = new DataInputStream(client.getInputStream());
            //con esto mandare mensajes al cliente
            out = new DataOutputStream(client.getOutputStream());
            out.writeUTF("Bienvenido...");
            dis = new DataInputStream(System.in);
            sc = new Scanner(client.getInputStream());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void disconnect() {
        try {
            out.close();
            in.close();
            dis.close();
            sc.close();
            client.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void run() {

        init();
        String msg = "";// cuando convierta a hilos tengo que ver bien donde inicializar el msg, para q no empiece como x con los nuevos clientes

        try {
            while (!"x".equals(msg)) {//voy a recibir los mensajes del cliente mientras que no mande x

                //aca recibo el mensaje del cliente
                msg = in.readUTF();
                System.out.println("Mensaje del cliente " + client.getLocalAddress() + " " + client.getPort() + ": " + msg);

                if ("x".equals(msg)) {
                    //si llega aca, quiere decir que puso x, con lo cual se quiere ir
                    out.writeUTF("adios " + client.getLocalAddress() + " " + client.getPort());
                    System.out.println("el cliente se ah desconectado");
                    disconnect();
                } else {
                    System.out.println("Respuesta: ");
//                    esto le va a llegar al cliente
//                    msg = sc.nextLine(); //no se muy bien por que pero si uso un scaner, es como si no tomara la linea....
                    msg = dis.readLine();
                    out.writeUTF(msg);//aca mande mi mensaje al server
                }
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
