package uk.ac.cs1opnu.adventure.events;

import java.util.ArrayList;
import java.util.List;

public class Observable {
    private final List<Observer> observers = new ArrayList<Observer>();

    public void addObserver(Observer observer) {
        if (observer != null && !observers.contains(observer)) {
            observers.add(observer);
        }
    }

    public void removeObserver(Observer observer) {
        observers.remove(observer);
    }

    protected void notifyObservers(GameEvent event) {
        List<Observer> snapshot = new ArrayList<Observer>(observers);
        for (Observer observer : snapshot) {
            observer.update(event);
        }
    }
}