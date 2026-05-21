package uk.ac.cs1opnu.adventure.engine;

import org.junit.jupiter.api.Test;
import uk.ac.cs1opnu.adventure.factory.WorldFactory;
import uk.ac.cs1opnu.adventure.model.Direction;
import uk.ac.cs1opnu.adventure.model.Room;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

class PathFinderTest {
    @Test
    void findsShortestPathToItemUsingBreadthFirstSearch() {
        Map<String, Room> rooms = WorldFactory.createDefaultWorld();

        Optional<PathHint> hint = PathFinder.findPath(rooms, "entrance", "brass_key");

        assertTrue(hint.isPresent());
        assertEquals("workshop", hint.get().getTargetRoomId());
        assertEquals(Arrays.asList(Direction.EAST), hint.get().getDirections());
    }

    @Test
    void findsPathToRoomWhenDoorHasBeenUnlocked() {
        Map<String, Room> rooms = WorldFactory.createDefaultWorld();
        rooms.get("workshop").getDoor(Direction.EAST).get().unlockWith("brass_key");

        Optional<PathHint> hint = PathFinder.findPath(rooms, "workshop", "vault");

        assertTrue(hint.isPresent());
        assertEquals("vault", hint.get().getTargetRoomId());
        assertEquals(Arrays.asList(Direction.EAST, Direction.NORTH), hint.get().getDirections());
    }

    @Test
    void doesNotRouteThroughLockedDoor() {
        Map<String, Room> rooms = WorldFactory.createDefaultWorld();

        Optional<PathHint> hint = PathFinder.findPath(rooms, "workshop", "vault");

        assertFalse(hint.isPresent());
    }

    @Test
    void returnsEmptyWhenTargetDoesNotExist() {
        Map<String, Room> rooms = WorldFactory.createDefaultWorld();

        Optional<PathHint> hint = PathFinder.findPath(rooms, "entrance", "missing_target");

        assertFalse(hint.isPresent());
    }

    @Test
    void returnsZeroLengthPathWhenAlreadyAtTargetRoom() {
        Map<String, Room> rooms = WorldFactory.createDefaultWorld();

        Optional<PathHint> hint = PathFinder.findPath(rooms, "entrance", "entrance");

        assertTrue(hint.isPresent());
        assertEquals("entrance", hint.get().getTargetRoomId());
        assertTrue(hint.get().getDirections().isEmpty());
    }

    @Test
    void pathHintDefensivelyCopiesDirections() {
        List<Direction> directions = Arrays.asList(Direction.NORTH);
        PathHint hint = new PathHint("library", directions);

        assertThrows(UnsupportedOperationException.class, () -> hint.getDirections().add(Direction.EAST));
    }
}