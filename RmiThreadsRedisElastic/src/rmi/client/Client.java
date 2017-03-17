package rmi.client;

import rmi.server.Worker;

public interface Client
{
    
    Worker connectingToServer(String host, int port, String bindingName);

}
