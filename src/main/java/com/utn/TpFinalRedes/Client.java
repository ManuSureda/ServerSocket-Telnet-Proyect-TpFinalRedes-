package com.utn.TpFinalRedes;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Scanner;

public class Client {


    public static void main(String[] args) {

        final String HOST = "127.0.0.1";
        final Integer PORT = 3000;

        DataInputStream in;
        DataOutputStream out;

        Scanner sc = new Scanner(System.in);
        String msg = "";
        try {
            Socket client = new Socket(HOST, PORT);

            //con esto recibire los mensajes del servidor
            in = new DataInputStream(client.getInputStream());
            //con esto mandare mensajes al Servidor
            out = new DataOutputStream(client.getOutputStream());

            while (!msg.equals("x")) {

                //en este punto el servidor esta esperando un mensaje del cliente
                System.out.println("que quiere decirle al servidor?: ");
                msg = sc.nextLine();

                out.writeUTF(msg);//aca mande mi mensaje al server

                if (msg.equals("x")) {
                    client.close();
                } else {
                    msg = in.readUTF();//y aca leo la respuesta del server
                    System.out.println("respuesta del server: " + msg);
                }
            }
            sc.close();
            //client.close();
        } catch (UnknownHostException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }


    }


}
