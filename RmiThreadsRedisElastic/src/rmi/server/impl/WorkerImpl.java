package rmi.server.impl;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

import org.elasticsearch.client.transport.TransportClient;
import org.elasticsearch.common.settings.Settings;
import org.elasticsearch.common.transport.InetSocketTransportAddress;
import org.elasticsearch.transport.client.PreBuiltTransportClient;

import data.Student;
import rmi.server.Worker;

/**
 * Created by Тарас on 10.02.2017.
 */
public class WorkerImpl extends UnicastRemoteObject implements Worker
{

    /**
     * 
     */
    private static final long serialVersionUID = 1L;
    private TransportClient client;
    public static int countOfThread = 0;
    private int maxThread;

    @SuppressWarnings("resource")
    public WorkerImpl(String host, int port, int maxThread) throws RemoteException, UnknownHostException {
	super();
	Settings settings = Settings.builder().put("client.transport.ignore_cluster_name", true).put("client.transport.ping_timeout", "120s").build();
	client = new PreBuiltTransportClient(settings).addTransportAddress(new InetSocketTransportAddress(InetAddress.getByName(host), 9300));
	this.maxThread = maxThread;
    }

    @Override
    public void saveStudentToElastic(String index, String type, Student student) throws RemoteException
    {
	SaveStudentToElasticThreadImpl saveStudent = new SaveStudentToElasticThreadImpl(client, index, type, student, this);
	Thread thread = new Thread(saveStudent);
	thread.start();
	countOfThread++;
    }

    @Override
    public boolean isAvailable() throws RemoteException
    {
	
	if (countOfThread == maxThread)
	{
	    return false;
	} else
	{
	    return true;
	}
    }
}
