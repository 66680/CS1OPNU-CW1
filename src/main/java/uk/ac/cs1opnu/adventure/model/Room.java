package uk.ac.cs1opnu.adventure.model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.EnumMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

public class Room extends GameObject {
    private final Map<Direction, String> exits = new EnumMap<Direction, String>(Direction.class);
    private final Map<Direction, Door> doors = new EnumMap<Direction, Door>(Direction.class);
    private final Map<String, Item> items = new LinkedHashMap<String, Item>();
    private Puzzle puzzle;

    public Room(String id, String name, String description) {
        super(id, name, description);
    }

    public void connect(Direction direction, String roomId) {
        exits.put(direction, roomId);
    }

    public void connect(Direction direction, String roomId, Door door) {
        exits.put(direction, roomId);
        doors.put(direction, door);
    }

    public Optional<String> getExit(Direction direction) {
        return Optional.ofNullable(exits.get(direction));
    }

    public Map<Direction, String> getExits() {
        return Collections.unmodifiableMap(exits);
    }

    public Optional<Door> getDoor(Direction direction) {
        return Optional.ofNullable(doors.get(direction));
    }

    public boolean canExit(Direction direction) {
        if (!exits.containsKey(direction)) {
            return false;
        }
        Door door = doors.get(direction);
        return door == null || !door.isLocked();
    }

    public void addItem(Item item) {
        items.put(item.getId(), item);
    }

    public boolean hasItem(String itemId) {
        return items.containsKey(itemId);
    }

    public Optional<Item> findItem(String itemId) {
        return Optional.ofNullable(items.get(itemId));
    }

    public Optional<Item> removeItem(String itemId) {
        return Optional.ofNullable(items.remove(itemId));
    }

    public List<Item> getItems() {
        return Collections.unmodifiableList(new ArrayList<Item>(items.values()));
    }

    public Optional<Puzzle> getPuzzle() {
        return Optional.ofNullable(puzzle);
    }

    public void setPuzzle(Puzzle puzzle) {
        this.puzzle = puzzle;
    }

    public String describe() {
        StringBuilder builder = new StringBuilder();
        builder.append(getName()).append(System.lineSeparator());
        builder.append(getDescription());
        if (!items.isEmpty()) {
            builder.append(System.lineSeparator()).append("Items: ");
            List<String> names = new ArrayList<String>();
            for (Item item : items.values()) {
                names.add(item.getId());
            }
            builder.append(String.join(", ", names));
        }
        if (puzzle != null && !puzzle.isSolved()) {
            builder.append(System.lineSeparator()).append("Puzzle: ").append(puzzle.getPrompt());
        }
        if (!exits.isEmpty()) {
            builder.append(System.lineSeparator()).append("Exits: ");
            List<String> names = new ArrayList<String>();
            for (Direction direction : exits.keySet()) {
                names.add(direction.name().toLowerCase());
            }
            builder.append(String.join(", ", names));
        }
        return builder.toString();
    }
}