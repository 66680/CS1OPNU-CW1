package uk.ac.cs1opnu.adventure.model;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ItemTest {
    @Test
    void keyItemStoresIdentityAndUnlockTarget() {
        KeyItem key = new KeyItem("brass_key", "Brass Key", "A heavy brass key.", "control_room");

        assertEquals("brass_key", key.getId());
        assertEquals("Brass Key", key.getName());
        assertEquals("A heavy brass key.", key.getDescription());
        assertEquals("control_room", key.getUnlocksRoomId());
    }

    @Test
    void treasureItemCanRepresentWinningItem() {
        TreasureItem treasure = new TreasureItem("star_crystal", "Star Crystal", "The observatory treasure.", true);

        assertTrue(treasure.isWinningItem());
        assertEquals("star_crystal", treasure.getId());
    }
}