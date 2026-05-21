package uk.ac.cs1opnu.adventure.engine;

import uk.ac.cs1opnu.adventure.events.EventLog;
import uk.ac.cs1opnu.adventure.factory.WorldFactory;
import uk.ac.cs1opnu.adventure.model.Player;
import uk.ac.cs1opnu.adventure.model.Room;

import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public final class GameState {
    private static final GameState INSTANCE = new GameState();

    private Map<String, Room> rooms = new LinkedHashMap<String, Room>();
    private Map<String, Player> players = new LinkedHashMap<String, Player>();
    private String activePlayerName;
    private EventLog eventLog = new EventLog();
    private boolean gameWon;

    private GameState() {
    }

    public static GameState getInstance() {
        return INSTANCE;
    }

    public void resetForNewGame() {
        rooms = new LinkedHashMap<String, Room>();
        players = new LinkedHashMap<String, Player>();
        activePlayerName = null;
        eventLog = new EventLog();
        gameWon = false;
    }

    public void startNewGame(List<String> playerNames) {
        resetForNewGame();
        rooms = WorldFactory.createDefaultWorld();
        for (String playerName : playerNames) {
            Player player = new Player(playerName, "entrance");
            players.put(player.getName(), player);
            eventLog.addObserver(player);
            if (activePlayerName == null) {
                activePlayerName = player.getName();
            }
        }
    }

    public Map<String, Room> getRooms() {
        return Collections.unmodifiableMap(rooms);
    }

    public Map<String, Player> getPlayers() {
        return Collections.unmodifiableMap(players);
    }

    public Player getPlayer(String name) {
        Player player = players.get(name);
        if (player == null) {
            throw new IllegalArgumentException("Unknown player: " + name);
        }
        return player;
    }

    public Player getActivePlayer() {
        if (activePlayerName == null) {
            throw new IllegalStateException("No active player.");
        }
        return getPlayer(activePlayerName);
    }

    public void setActivePlayer(String playerName) {
        if (!players.containsKey(playerName)) {
            throw new IllegalArgumentException("Unknown player: " + playerName);
        }
        activePlayerName = playerName;
    }

    public Room getCurrentRoom() {
        return rooms.get(getActivePlayer().getCurrentRoomId());
    }

    public EventLog getEventLog() {
        return eventLog;
    }

    public boolean isGameWon() {
        return gameWon;
    }

    public void markGameWon() {
        gameWon = true;
    }
}