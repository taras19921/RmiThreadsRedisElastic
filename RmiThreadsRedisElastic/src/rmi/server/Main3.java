package rmi.server;

import rmi.server.impl.RunServerImpl;

public class Main3
{

    public static void main(String[] args)
    {
	new RunServerImpl().runServer("localhost", 1097, "save-student", 4);
	System.out.println("Server is running on port 1097");
    }

}
