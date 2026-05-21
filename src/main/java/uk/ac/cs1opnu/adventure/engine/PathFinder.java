package uk.ac.cs1opnu.adventure.engine;

import uk.ac.cs1opnu.adventure.model.Direction;
import uk.ac.cs1opnu.adventure.model.Room;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.EnumMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Queue;
import java.util.Set;

/**
 * Breadth-first search route finder for the room graph.
 *
 * <p>Rooms are graph nodes and exits are edges. Because each exit has equal
 * cost, BFS finds the shortest currently available route in O(V + E) time.</p>
 */
public final class PathFinder {
    private PathFinder() {
    }

    public static Optional<PathHint> findPath(Map<String, Room> rooms, String startRoomId, String targetId) {
        if (rooms == null || !rooms.containsKey(startRoomId) || targetId == null || targetId.trim().isEmpty()) {
            return Optional.empty();
        }

        Queue<SearchNode> frontier = new ArrayDeque<SearchNode>();
        Set<String> visited = new HashSet<String>();
        frontier.add(new SearchNode(startRoomId, new ArrayList<Direction>()));
        visited.add(startRoomId);

        while (!frontier.isEmpty()) {
            SearchNode current = frontier.remove();
            Room room = rooms.get(current.roomId);
            if (matchesTarget(room, targetId)) {
                return Optional.of(new PathHint(room.getId(), current.path));
            }

            for (Map.Entry<Direction, String> exit : orderedExits(room).entrySet()) {
                Direction direction = exit.getKey();
                String nextRoomId = exit.getValue();
                if (!room.canExit(direction) || visited.contains(nextRoomId) || !rooms.containsKey(nextRoomId)) {
                    continue;
                }
                List<Direction> nextPath = new ArrayList<Direction>(current.path);
                nextPath.add(direction);
                frontier.add(new SearchNode(nextRoomId, nextPath));
                visited.add(nextRoomId);
            }
        }

        return Optional.empty();
    }

    private static boolean matchesTarget(Room room, String targetId) {
        return room.getId().equals(targetId) || room.hasItem(targetId);
    }

    private static Map<Direction, String> orderedExits(Room room) {
        Map<Direction, String> ordered = new EnumMap<Direction, String>(Direction.class);
        for (Direction direction : Direction.values()) {
            Optional<String> exit = room.getExit(direction);
            if (exit.isPresent()) {
                ordered.put(direction, exit.get());
            }
        }
        return ordered;
    }

    private static class SearchNode {
        private final String roomId;
        private final List<Direction> path;

        private SearchNode(String roomId, List<Direction> path) {
            this.roomId = roomId;
            this.path = path;
        }
    }
}