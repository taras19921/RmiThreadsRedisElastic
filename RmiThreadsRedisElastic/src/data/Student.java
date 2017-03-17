package data;

import java.io.Serializable;

public class Student implements Serializable
{
    /**
     * 
     */
    private static final long serialVersionUID = 1L;
    private String name;
    private String group;
    private Long dateOfBirth;

    public Student() {
    }

    public Student(String name, String group, Long dateOfBirth) {
	this.name = name;
	this.group = group;
	this.dateOfBirth = dateOfBirth;
    }

    public String getName()
    {
	return name;
    }

    public void setName(String name)
    {
	this.name = name;
    }

    public String getGroup()
    {
	return group;
    }

    public void setGroup(String group)
    {
	this.group = group;
    }

    public Long getDateOfBirth()
    {
	return dateOfBirth;
    }

    public void setDateOfBirth(Long dateOfBirth)
    {
	this.dateOfBirth = dateOfBirth;
    }

}
