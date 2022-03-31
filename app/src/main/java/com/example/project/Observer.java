package com.example.project;

public interface Observer {
    void update(String msg);
    //Call by subject to inform observer a change in state
    // Different classes of Observer can implement this
    // different to respond to the change in state
}