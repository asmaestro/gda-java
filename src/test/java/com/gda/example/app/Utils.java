package com.gda.example.app;

import java.net.Socket;

public class Utils {
public static boolean serverListening(String host, int port) {
    Socket s = null;
    try
    {
        s = new Socket(host, port);
        return true;
    }
    catch (Exception e)
    {
        return false;
    }
    finally
    {
        if(s != null)
            try {s.close();}
            catch(Exception e){}
    }
}
}