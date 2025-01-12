package com.funfit.model;

public class Participant {
    private int id;
    private String name;
    private int age;
    private String email;
    private int batchId;
    private String batchName;
    
    public Participant() {
    	
    }
    
    public Participant(String name, int age, String email, int batchId) {
    	this.name = name;
    	this.age = age;
    	this.email = email;
    	this.batchId = batchId;
    }
    
    public String getBatchName() {
        return batchName;
    }
    
    public void setBatchName(String batchName) {
        this.batchName = batchName;
    }
    
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getAge() {
		return age;
	}
	public void setAge(int age) {
		this.age = age;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public int getBatchId() {
		return batchId;
	}
	public void setBatchId(int batchId) {
		this.batchId = batchId;
	}
}

