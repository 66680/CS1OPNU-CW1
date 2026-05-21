package uk.ac.cs1opnu.adventure.model;

public class TreasureItem extends Item {
    private final boolean winningItem;

    public TreasureItem(String id, String name, String description, boolean winningItem) {
        super(id, name, description);
        this.winningItem = winningItem;
    }

    public boolean isWinningItem() {
        return winningItem;
    }
}