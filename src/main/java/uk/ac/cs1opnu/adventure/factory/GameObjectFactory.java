package uk.ac.cs1opnu.adventure.factory;

import uk.ac.cs1opnu.adventure.model.Item;
import uk.ac.cs1opnu.adventure.model.KeyItem;
import uk.ac.cs1opnu.adventure.model.Puzzle;
import uk.ac.cs1opnu.adventure.model.TreasureItem;

/**
 * Factory for reusable game object templates.
 *
 * <p>The engine asks for objects by template ID instead of directly coupling
 * itself to every concrete item or puzzle constructor.</p>
 */
public final class GameObjectFactory {
    private GameObjectFactory() {
    }

    public static Item createItem(String templateId) {
        if ("brass_key".equals(templateId)) {
            return new KeyItem("brass_key", "Brass Key", "A heavy key marked with an observatory symbol.", "control_room");
        }
        if ("star_crystal".equals(templateId)) {
            return new TreasureItem("star_crystal", "Star Crystal", "A bright crystal from the locked vault.", true);
        }
        if ("note".equals(templateId)) {
            return new Item("note", "Observer's Note", "The note says: The hunter constellation opens the console.");
        }
        throw new IllegalArgumentException("Unknown item template: " + templateId);
    }

    public static Puzzle createPuzzle(String templateId) {
        if ("console".equals(templateId)) {
            return new Puzzle("console", "The console asks for the hunter constellation.", "ORION");
        }
        throw new IllegalArgumentException("Unknown puzzle template: " + templateId);
    }
}