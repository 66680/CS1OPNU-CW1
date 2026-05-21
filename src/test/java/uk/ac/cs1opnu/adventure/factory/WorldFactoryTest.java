package uk.ac.cs1opnu.adventure.factory;

import org.junit.jupiter.api.Test;
import uk.ac.cs1opnu.adventure.model.Direction;
import uk.ac.cs1opnu.adventure.model.Room;

import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

class WorldFactoryTest {
    @Test
    void defaultWorldContainsRequiredRoomsAndObjects() {
        Map<String, Room> world = WorldFactory.createDefaultWorld();

        assertTrue(world.containsKey("entrance"));
        assertTrue(world.containsKey("library"));
        assertTrue(world.containsKey("workshop"));
        assertTrue(world.containsKey("control_room"));
        assertTrue(world.containsKey("vault"));
        assertTrue(world.get("library").hasItem("note"));
        assertTrue(world.get("workshop").hasItem("brass_key"));
        assertTrue(world.get("vault").hasItem("star_crystal"));
    }

    @Test
    void defaultWorldHasLockedProgressionPath() {
        Map<String, Room> world = WorldFactory.createDefaultWorld();
        Room workshop = world.get("workshop");

        assertEquals("control_room", workshop.getExit(Direction.EAST).orElse(null));
        assertFalse(workshop.canExit(Direction.EAST));
    }
}