package uk.ac.cs1opnu.adventure.factory;

import org.junit.jupiter.api.Test;
import uk.ac.cs1opnu.adventure.model.Item;
import uk.ac.cs1opnu.adventure.model.KeyItem;
import uk.ac.cs1opnu.adventure.model.Puzzle;
import uk.ac.cs1opnu.adventure.model.TreasureItem;

import static org.junit.jupiter.api.Assertions.*;

class GameObjectFactoryTest {
    @Test
    void factoryCreatesKnownItems() {
        Item key = GameObjectFactory.createItem("brass_key");
        Item treasure = GameObjectFactory.createItem("star_crystal");

        assertTrue(key instanceof KeyItem);
        assertTrue(treasure instanceof TreasureItem);
        assertEquals("brass_key", key.getId());
    }

    @Test
    void factoryCreatesKnownPuzzle() {
        Puzzle puzzle = GameObjectFactory.createPuzzle("console");

        assertEquals("console", puzzle.getId());
        assertTrue(puzzle.solve("ORION"));
    }

    @Test
    void factoryRejectsUnknownTemplates() {
        assertThrows(IllegalArgumentException.class, () -> GameObjectFactory.createItem("missing"));
        assertThrows(IllegalArgumentException.class, () -> GameObjectFactory.createPuzzle("missing"));
    }
}