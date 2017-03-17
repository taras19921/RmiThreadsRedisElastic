package rmi.server;

import java.rmi.Remote;
import java.rmi.RemoteException;

import data.Student;

/**
 * Created by Тарас on 10.02.2017.
 */
public interface Worker extends Remote
{
//    String saveStudentToElastic(Student student, Integer id) throws RemoteException;
    
    void saveStudentToElastic(String index, String type, Student student) throws RemoteException;
    
    boolean isAvailable() throws RemoteException;
}
