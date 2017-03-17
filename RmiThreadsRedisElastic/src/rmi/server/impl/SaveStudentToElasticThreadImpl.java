package rmi.server.impl;

import org.elasticsearch.client.transport.TransportClient;

import com.google.gson.Gson;

import data.Student;
import rmi.server.SaveStudentToElasticThread;

public class SaveStudentToElasticThreadImpl implements SaveStudentToElasticThread, Runnable
{
    private TransportClient client;
    private String index;
    private String type;
    private Student student;
    private WorkerImpl worker;
    public SaveStudentToElasticThreadImpl(TransportClient client, String index, String type, Student student, WorkerImpl worker) {
	super();
	this.client = client;
	this.index = index;
	this.type = type;
	this.student = student;
	this.worker = worker;
    }

    public SaveStudentToElasticThreadImpl() {
	super();
	// TODO Auto-generated constructor stub
    }

    @Override
    public void run()
    {

	Gson gson = new Gson();
	client.prepareIndex(index, type).setSource(gson.toJson(student)).get();

	worker.countOfThread--;
	
//	System.out.println("WorkerImpl.countOfThread: " + WorkerImpl.countOfThread);

    }

}
