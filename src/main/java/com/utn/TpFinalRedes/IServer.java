package com.utn.TpFinalRedes;

import java.net.Socket;

public interface IServer {
    void setSocket(Socket client);
    void init();
    void disconnect();
    void start();
}
