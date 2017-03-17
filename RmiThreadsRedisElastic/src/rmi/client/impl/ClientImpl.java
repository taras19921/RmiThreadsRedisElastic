package rmi.client.impl;

import java.rmi.Naming;

import rmi.client.Client;
import rmi.server.Worker;

public class ClientImpl implements Client
{
    public Worker connectingToServer(String host, int port, String bindingName)
    {
	Worker worker = null;
	try
	{
	    worker = (Worker) Naming.lookup("rmi://" + host + ":" + port + "/" + bindingName);
	    return worker;
	} catch (Exception e)
	{
	    // TODO Auto-generated catch block
	    e.printStackTrace();
	    return null;
	}
    }
}
