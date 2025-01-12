package com.funfit.model;

public class Batch {
    private int id;
    private String name;
    private String timeSlot;
    
    // Default constructor
    public Batch() {}

    public Batch(String name, String timeSlot) {
        this.name = name;
        this.timeSlot = timeSlot;
    }
    
    public Batch(int id, String name, String timeSlot) {  
    	this.id = id;
        this.name = name;
        this.timeSlot = timeSlot;
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
	public String getTimeSlot() {
		return timeSlot;
	}
	public void setTimeSlot(String timeSlot) {
		this.timeSlot = timeSlot;
	}
}

