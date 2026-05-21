package uk.ac.cs1opnu.adventure.engine;

import uk.ac.cs1opnu.adventure.events.GameEvent;
import uk.ac.cs1opnu.adventure.events.GameEventType;
import uk.ac.cs1opnu.adventure.model.Direction;
import uk.ac.cs1opnu.adventure.model.Door;
import uk.ac.cs1opnu.adventure.model.Item;
import uk.ac.cs1opnu.adventure.model.KeyItem;
import uk.ac.cs1opnu.adventure.model.Player;
import uk.ac.cs1opnu.adventure.model.Puzzle;
import uk.ac.cs1opnu.adventure.model.Room;
import uk.ac.cs1opnu.adventure.model.TreasureItem;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class GameEngine {
    private final GameState state;

    public GameEngine() {
        this(GameState.getInstance());
    }

    GameEngine(GameState state) {
        this.state = state;
    }

    public void startNewGame(List<String> playerNames) {
        state.startNewGame(playerNames);
    }

    public GameResult switchPlayer(String playerName) {
        try {
            state.setActivePlayer(playerName);
            return GameResult.success("Switched to " + playerName + ".");
        } catch (IllegalArgumentException ex) {
            return GameResult.failure(ex.getMessage());
        }
    }

    public GameResult look() {
        return GameResult.success(state.getCurrentRoom().describe());
    }

    public GameResult move(Direction direction) {
        Player player = state.getActivePlayer();
        Room room = state.getCurrentRoom();
        Optional<String> targetRoomId = room.getExit(direction);
        if (!targetRoomId.isPresent()) {
            return GameResult.failure("There is no exit " + direction.name().toLowerCase() + ".");
        }
        if (!room.canExit(direction)) {
            return GameResult.failure("The way is locked.");
        }
        player.moveTo(targetRoomId.get());
        Room targetRoom = state.getRooms().get(targetRoomId.get());
        publish(GameEventType.PLAYER_MOVED, player.getName() + " moved to " + targetRoom.getName() + ".");
        return GameResult.success("You moved to " + targetRoom.getName() + ".");
    }

    public GameResult take(String itemId) {
        Player player = state.getActivePlayer();
        Room room = state.getCurrentRoom();
        Optional<Item> item = room.removeItem(itemId);
        if (!item.isPresent()) {
            return GameResult.failure("There is no " + itemId + " here.");
        }
        player.addItem(item.get());
        publish(GameEventType.ITEM_TAKEN, player.getName() + " picked up " + item.get().getName() + ".");
        if (item.get() instanceof TreasureItem && ((TreasureItem) item.get()).isWinningItem()) {
            state.markGameWon();
            publish(GameEventType.GAME_WON, player.getName() + " recovered the Star Crystal and won the game.");
            return GameResult.success("You recovered the Star Crystal. The team wins.");
        }
        return GameResult.success("You picked up " + item.get().getName() + ".");
    }

    public GameResult solve(String puzzleId, String answer) {
        Player player = state.getActivePlayer();
        Room room = state.getCurrentRoom();
        Optional<Puzzle> puzzle = room.getPuzzle();
        if (!puzzle.isPresent() || !puzzle.get().getId().equals(puzzleId)) {
            return GameResult.failure("There is no puzzle named " + puzzleId + " here.");
        }
        if (!puzzle.get().solve(answer)) {
            return GameResult.failure("That answer does not work.");
        }
        publish(GameEventType.PUZZLE_SOLVED, player.getName() + " solved the " + puzzleId + " puzzle.");
        return GameResult.success("The console accepts the answer.");
    }

    public GameResult use(String itemId) {
        Player player = state.getActivePlayer();
        Optional<Item> item = player.getItem(itemId);
        if (!item.isPresent()) {
            return GameResult.failure("You do not have " + itemId + ".");
        }
        if (!(item.get() instanceof KeyItem)) {
            return GameResult.failure("You cannot use " + itemId + " here.");
        }
        Room room = state.getCurrentRoom();
        Optional<Puzzle> puzzle = room.getPuzzle();
        if (puzzle.isPresent() && !puzzle.get().isSolved()) {
            return GameResult.failure("The console must be solved before the lock responds.");
        }
        KeyItem key = (KeyItem) item.get();
        for (Direction direction : Direction.values()) {
            Optional<Door> door = room.getDoor(direction);
            if (door.isPresent()
                    && door.get().getTargetRoomId().equals(key.getUnlocksRoomId())
                    && door.get().unlockWith(itemId)) {
                publish(GameEventType.DOOR_UNLOCKED, player.getName() + " unlocked the way to the control room.");
                return GameResult.success("The brass key unlocks the control room.");
            }
        }
        return GameResult.failure("There is nothing nearby for that key.");
    }

    public GameResult inventory() {
        Player player = state.getActivePlayer();
        List<String> ids = new ArrayList<String>();
        for (Item item : player.getInventory()) {
            ids.add(item.getId());
        }
        if (ids.isEmpty()) {
            return GameResult.success("Inventory is empty.");
        }
        return GameResult.success("Inventory: " + String.join(", ", ids));
    }

    public GameResult players() {
        return GameResult.success("Players: " + String.join(", ", state.getPlayers().keySet()));
    }

    public GameResult hint(String targetId) {
        Optional<PathHint> path = PathFinder.findPath(
                state.getRooms(),
                state.getActivePlayer().getCurrentRoomId(),
                targetId
        );
        if (!path.isPresent()) {
            return GameResult.failure("No available route to " + targetId + ". A locked door or missing target may be blocking the path.");
        }
        PathHint hint = path.get();
        if (hint.getDirections().isEmpty()) {
            return GameResult.success("You are already at " + hint.getTargetRoomId() + ".");
        }
        return GameResult.success("Shortest route to " + targetId + " is through "
                + hint.getTargetRoomId() + ": " + formatDirections(hint.getDirections()) + ".");
    }

    private String formatDirections(List<Direction> directions) {
        List<String> names = new ArrayList<String>();
        for (Direction direction : directions) {
            names.add(direction.name().toLowerCase());
        }
        return String.join(" -> ", names);
    }

    private void publish(GameEventType type, String message) {
        state.getEventLog().publish(new GameEvent(type, state.getActivePlayer().getName(), message));
    }
}