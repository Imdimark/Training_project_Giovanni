package com.example.springproject.Exceptions;

public class ManagerNotFound extends Exception{
    private Integer id;
    
    
    public ManagerNotFound(int id) {
        
        super("Manager with this id: " + id+ "cannot be find");
        this.id = id; 
    }

    public int getId(){
        return id;
    }
}
