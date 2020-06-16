package com.utn.TpFinalRedes;

public class MainClient {
    public static void main(String[] args) {
        Thread client = new Client();
        client.start();
    }
}
