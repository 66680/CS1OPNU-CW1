package uk.ac.cs1opnu.adventure.events;

import org.junit.jupiter.api.Test;
import uk.ac.cs1opnu.adventure.model.Player;

import static org.junit.jupiter.api.Assertions.*;

class ObserverPatternTest {
    @Test
    void eventLogNotifiesRegisteredPlayersAndStoresEvents() {
        EventLog eventLog = new EventLog();
        Player alice = new Player("Alice", "entrance");
        Player bob = new Player("Bob", "entrance");

        eventLog.addObserver(alice);
        eventLog.addObserver(bob);
        eventLog.publish(new GameEvent(GameEventType.ITEM_TAKEN, "Alice", "Alice picked up the brass key."));

        assertEquals(1, eventLog.getEvents().size());
        assertEquals(1, alice.getMessages().size());
        assertEquals(1, bob.getMessages().size());
        assertEquals("Alice picked up the brass key.", bob.getMessages().get(0));
    }

    @Test
    void removedObserverDoesNotReceiveFutureEvents() {
        EventLog eventLog = new EventLog();
        Player bob = new Player("Bob", "entrance");

        eventLog.addObserver(bob);
        eventLog.removeObserver(bob);
        eventLog.publish(new GameEvent(GameEventType.PLAYER_MOVED, "Alice", "Alice moved north."));

        assertTrue(bob.getMessages().isEmpty());
    }
}