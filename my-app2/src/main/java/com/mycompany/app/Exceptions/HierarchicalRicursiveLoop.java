package com.mycompany.app.Exceptions;
import com.mycompany.app.Model.Manager;

public class HierarchicalRicursiveLoop extends Exception {
    private Manager manager;
    
    
    public HierarchicalRicursiveLoop(Manager manager) {
        
        super("Manager you are trying to add is under a path that causes HierarchicalRicursiveLoop: " + manager);
        this.manager = manager; 
    }

    public Manager getManagerCausesLoop(){
        return manager;
    }
}
