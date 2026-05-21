package uk.ac.cs1opnu.adventure.factory;

import uk.ac.cs1opnu.adventure.model.Direction;
import uk.ac.cs1opnu.adventure.model.Door;
import uk.ac.cs1opnu.adventure.model.Room;

import java.util.LinkedHashMap;
import java.util.Map;

/**
 * Factory for the default playable world.
 *
 * <p>Room creation, exits, locked doors, items, and puzzles are assembled here
 * so world setup stays separate from game rule execution.</p>
 */
public final class WorldFactory {
    private WorldFactory() {
    }

    public static Map<String, Room> createDefaultWorld() {
        Map<String, Room> rooms = new LinkedHashMap<String, Room>();

        Room entrance = new Room("entrance", "Entrance Hall", "You stand below the broken dome of an abandoned observatory.");
        Room library = new Room("library", "Library", "Old star charts and notebooks fill the shelves.");
        Room workshop = new Room("workshop", "Workshop", "Tools, brass gears, and a silent control console cover the benches.");
        Room controlRoom = new Room("control_room", "Control Room", "The main telescope controls hum back to life.");
        Room vault = new Room("vault", "Vault", "A small vault glows with cold starlight.");

        entrance.connect(Direction.NORTH, "library");
        entrance.connect(Direction.EAST, "workshop");
        library.connect(Direction.SOUTH, "entrance");
        workshop.connect(Direction.WEST, "entrance");
        workshop.connect(Direction.EAST, "control_room", new Door("control_room", "brass_key"));
        controlRoom.connect(Direction.WEST, "workshop");
        controlRoom.connect(Direction.NORTH, "vault");
        vault.connect(Direction.SOUTH, "control_room");

        library.addItem(GameObjectFactory.createItem("note"));
        workshop.addItem(GameObjectFactory.createItem("brass_key"));
        workshop.setPuzzle(GameObjectFactory.createPuzzle("console"));
        vault.addItem(GameObjectFactory.createItem("star_crystal"));

        rooms.put(entrance.getId(), entrance);
        rooms.put(library.getId(), library);
        rooms.put(workshop.getId(), workshop);
        rooms.put(controlRoom.getId(), controlRoom);
        rooms.put(vault.getId(), vault);

        return rooms;
    }
}