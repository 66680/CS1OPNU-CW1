package uk.ac.cs1opnu.adventure.events;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Observable event history for the shared game session.
 *
 * <p>It records each event for testing and notifies player observers so
 * multiplayer actions are visible to the whole group.</p>
 */
public class EventLog extends Observable {
    private final List<GameEvent> events = new ArrayList<GameEvent>();

    public void publish(GameEvent event) {
        events.add(event);
        notifyObservers(event);
    }

    public List<GameEvent> getEvents() {
        return Collections.unmodifiableList(events);
    }
}