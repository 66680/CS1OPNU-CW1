package uk.ac.cs1opnu.adventure.model;

import org.junit.jupiter.api.Test;
import uk.ac.cs1opnu.adventure.events.GameEvent;
import uk.ac.cs1opnu.adventure.events.GameEventType;

import static org.junit.jupiter.api.Assertions.*;

class PlayerTest {
    @Test
    void playerTracksLocationInventoryAndMessages() {
        Player player = new Player("Alice", "entrance");
        Item key = new Item("brass_key", "Brass Key", "A key.");

        player.moveTo("library");
        player.addItem(key);
        player.update(new GameEvent(GameEventType.ITEM_TAKEN, "Bob", "Bob picked up a note."));

        assertEquals("library", player.getCurrentRoomId());
        assertTrue(player.hasItem("brass_key"));
        assertEquals(key, player.getItem("brass_key").orElse(null));
        assertEquals(1, player.getMessages().size());
    }
}