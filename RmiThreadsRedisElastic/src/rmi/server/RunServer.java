package rmi.server;

public interface RunServer
{
    
    void runServer(String host, int port, String bindingName, int maxThread);

}
