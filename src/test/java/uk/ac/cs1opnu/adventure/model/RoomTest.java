package uk.ac.cs1opnu.adventure.model;

import org.junit.jupiter.api.Test;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

class RoomTest {
    @Test
    void roomStoresExitsAndItems() {
        Room entrance = new Room("entrance", "Entrance Hall", "A cold stone entrance.");
        Item note = new Item("note", "Note", "A note with a clue.");

        entrance.connect(Direction.NORTH, "library");
        entrance.addItem(note);

        assertEquals(Optional.of("library"), entrance.getExit(Direction.NORTH));
        assertTrue(entrance.hasItem("note"));
        assertEquals(Optional.of(note), entrance.removeItem("note"));
        assertFalse(entrance.hasItem("note"));
    }

    @Test
    void lockedDoorBlocksExitUntilUnlocked() {
        Room workshop = new Room("workshop", "Workshop", "Dusty tools cover the tables.");
        Door door = new Door("control_room", "brass_key");

        workshop.connect(Direction.EAST, "control_room", door);

        assertFalse(workshop.canExit(Direction.EAST));
        assertTrue(door.unlockWith("brass_key"));
        assertTrue(workshop.canExit(Direction.EAST));
    }
}