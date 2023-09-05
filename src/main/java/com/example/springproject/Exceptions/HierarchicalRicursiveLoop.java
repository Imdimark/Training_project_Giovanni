package com.example.springproject.Exceptions;

import com.example.springproject.Model.Manager;

public class HierarchicalRicursiveLoop extends Exception {
    private Manager manager;
    
    public HierarchicalRicursiveLoop(Manager manager2) {
        super("Manager you are trying to add is under a path that causes HierarchicalRicursiveLoop: " + manager2);
        this.manager = manager2; 
    }

    public Manager getManagerCausesLoop(){
        return manager;
    }
}
