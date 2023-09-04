package com.example.springproject.Exceptions;

public class TryingAssignEmployeeAsManager extends Exception {
    private int employeeId;
    
    
    public TryingAssignEmployeeAsManager(int employeeId) {
        
        super("Manager you are trying to use is an employee and cannot do this job, this is the id: " + employeeId  );
        this.employeeId = employeeId; 
    }

    public int getEmployeeId(){
        return employeeId;
    }
}
