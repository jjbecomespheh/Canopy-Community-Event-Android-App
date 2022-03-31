package com.example.project;

import java.util.ArrayList;

public class Hashtag implements Subject {
    private String message; //the status
    private ArrayList<Observer> observers;

    public Hashtag() {
        observers = new ArrayList<Observer>();
    }

    @Override
    public void register(Observer o) {
        observers.add(o);
    }

    @Override
    public void unregister(Observer o) {
        observers.remove(o);
    }

    public void notifyObservers() {
        for (Observer o : observers) {
            o.update(this.message);
        }
    }
}
