package com.example.stock.eink.liabrary.observe;


import java.util.ArrayList;
import java.util.List;

public class Subject {
    private List<Observer> observers = new ArrayList<Observer>();
    private int state;

    public int getState() {
        return state;
    }

    public void setState(int state) {
        this.state = state;
        notifyAllObservers();
    }

    public void attach(Observer observer){
        observers.add(observer);
    }

    public void delete(Observer observer){
        for (Observer observerpt : observers) {
            if(observerpt == observer){
                observers.remove(observerpt);
            }
        }
    }

    public void notifyAllObservers(){
        for (Observer observer : observers) {
            observer.update();
        }
    }


}
