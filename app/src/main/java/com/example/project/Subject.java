package com.example.project;

public interface Subject { //call to add new observer
    void register(Observer o); //call to remove the observer
    void unregister(Observer o);
    //inform all registered observers when state changes
    void notifyObservers();
}