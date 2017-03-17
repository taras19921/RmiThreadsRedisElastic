package rmi.server;

import rmi.server.impl.RunServerImpl;

public class Main1
{
    
    public static void main(String[] args)
    {
	new RunServerImpl().runServer("localhost", 1099, "save-student", 2);
	System.out.println("Server is running on port 1099");
    }

}
