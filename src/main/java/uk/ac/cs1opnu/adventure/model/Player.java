package uk.ac.cs1opnu.adventure.model;

import uk.ac.cs1opnu.adventure.events.GameEvent;
import uk.ac.cs1opnu.adventure.events.Observer;

import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

public class Player implements Observer {
    private final String name;
    private String currentRoomId;
    private final Map<String, Item> inventory = new LinkedHashMap<String, Item>();
    private final List<String> messages = new ArrayList<String>();

    public Player(String name, String startingRoomId) {
        if (name == null || name.trim().isEmpty()) {
            throw new IllegalArgumentException("Player name must not be blank.");
        }
        this.name = name;
        this.currentRoomId = startingRoomId;
    }

    public String getName() {
        return name;
    }

    public String getCurrentRoomId() {
        return currentRoomId;
    }

    public void moveTo(String roomId) {
        currentRoomId = roomId;
    }

    public void addItem(Item item) {
        inventory.put(item.getId(), item);
    }

    public boolean hasItem(String itemId) {
        return inventory.containsKey(itemId);
    }

    public Optional<Item> getItem(String itemId) {
        return Optional.ofNullable(inventory.get(itemId));
    }

    public List<Item> getInventory() {
        return Collections.unmodifiableList(new ArrayList<Item>(inventory.values()));
    }

    public List<String> getMessages() {
        return Collections.unmodifiableList(messages);
    }

    public List<String> drainMessages() {
        List<String> copy = new ArrayList<String>(messages);
        messages.clear();
        return copy;
    }

    @Override
    public void update(GameEvent event) {
        messages.add(event.getMessage());
    }
}