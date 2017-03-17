package rmi.server;

import rmi.server.impl.RunServerImpl;

public class Main2
{

    public static void main(String[] args)
    {
	new RunServerImpl().runServer("localhost", 1098, "save-student", 3);
	System.out.println("Server is running on port 1098");

    }

}
