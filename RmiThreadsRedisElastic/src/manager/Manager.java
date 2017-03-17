package manager;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.google.gson.Gson;

import data.Student;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import rmi.client.impl.ClientImpl;
import rmi.server.Worker;

public class Manager
{

    public static void main(String[] args)
    {

	System.out.println("Manager");
	Gson gson = new Gson();

	try
	{
	    // connect to redis
	    Jedis jedis = connectToJedis("localhost", 6379);

	    // add and save students into redis
	    addStudentToJedis(jedis, "students", gson);

	    ClientImpl client = new ClientImpl();

	    List<Worker> workers = new ArrayList<>();

	    workers.add(client.connectingToServer("localhost", 1099, "save-student"));
	    workers.add(client.connectingToServer("localhost", 1098, "save-student"));
	    workers.add(client.connectingToServer("localhost", 1097, "save-student"));
	    //
	    while (true)
	    {
		// get student from redis
		Student student = getStudentFromJedis(jedis, "students", gson);
		
		if (student != null)
		{
		    for (Worker worker : workers)
		    {
			if (worker.isAvailable())
			{
			    worker.saveStudentToElastic("students-ua", "group", student);
			    break;
			}
		    }
		    
		    
		} else
		    break;
	    }
	} catch (Exception e)
	{
	    e.printStackTrace();
	}

    }

    public static void addStudentToJedis(Jedis jedis, String key, Gson gson)
    {

	for (int i = 1; i <= 100; i++)
	{
	    String name = "Student" + i;
	    String group = "group" + 1;
	    Student student = new Student(name, group, new Date().getTime());
	    jedis.lpush(key, gson.toJson(student));
	}
    }

    public static Student getStudentFromJedis(Jedis jedis, String key, Gson gson)
    {

	// Get the stored data and print it
	// List<String> list = jedis.lrange("students", 0, 200);
	//
	// for (int i = 0; i < list.size(); i++)
	// {
	// System.out.println("Stored string in redis:: " + list.get(i));
	// }
	String studentJson = jedis.lpop(key);
	Student student = gson.fromJson(studentJson, Student.class);
	return student;
    }

    public static Jedis connectToJedis(String host, int port)
    {
	JedisPool pool = new JedisPool("localhost", port);
	Jedis jedis = pool.getResource();
	return jedis;
    }
}
