package rmi.server.impl;

import java.net.MalformedURLException;
import java.net.UnknownHostException;
import java.rmi.Naming;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;

import rmi.server.RunServer;

public class RunServerImpl implements RunServer
{
    
public void runServer(String host, int port, String bindingName, int maxThread) {
	
	WorkerImpl addServerImpl;
	try
	{
	    addServerImpl = new WorkerImpl(host, port, maxThread);
	    LocateRegistry.createRegistry(port);
	    Naming.rebind("//" + host + ":" + port + "/" + bindingName, addServerImpl);
	} catch (RemoteException e)
	{
	    // TODO Auto-generated catch block
	    e.printStackTrace();
	} catch (UnknownHostException e)
	{
	    // TODO Auto-generated catch block
	    e.printStackTrace();
	} catch (MalformedURLException e)
	{
	    // TODO Auto-generated catch block
	    e.printStackTrace();
	}
    }

}
