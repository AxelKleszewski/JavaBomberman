package fr.univartois.butinfo.r304.bomberman.model.map;

import fr.univartois.butinfo.r304.bomberman.model.BombermanGame;
import fr.univartois.butinfo.r304.bomberman.model.IMovable;
import fr.univartois.butinfo.r304.bomberman.model.map.wall.Wall;
import fr.univartois.butinfo.r304.bomberman.model.bonus.BonusAddBomb;
import fr.univartois.butinfo.r304.bomberman.model.bonus.BonusInvincibility;
import fr.univartois.butinfo.r304.bomberman.model.bonus.BonusLife;
import fr.univartois.butinfo.r304.bomberman.view.Sprite;
import fr.univartois.butinfo.r304.bomberman.view.SpriteStore;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import static fr.univartois.butinfo.r304.bomberman.model.BombermanGame.RAND;

/**
 * The {@link Cell} class represents a cell on the Bomberman game map.
 * It can contain elements such as grass, indestructible walls, and destructible bricks.
 */
public final class Cell {

    /**
     * The row where this cell is located on the map.
     */
    private int row;

    /**
     * The column where this cell is located on the map.
     */
    private int column;

    /**
     * The property containing the sprite representing the content of this cell on the map.
     */
    private final ObjectProperty<Sprite> spriteProperty = new SimpleObjectProperty<>();

    /**
     * The property containing the wall present in this cell on the map.
     */
    private final ObjectProperty<Wall> wallProperty = new SimpleObjectProperty<>();

    /**
     * Indicates whether this cell contains a breakable brick.
     */
    private boolean isBrick;

    /**
     * Returns the width of this cell.
     *
     * @return The width of this cell.
     */
    public int getWidth() {
        return spriteProperty.get().getWidth();
    }

    /**
     * Returns the height of this cell.
     *
     * @return The height of this cell.
     */
    public int getHeight() {
        return spriteProperty.get().getHeight();
    }

    /**
     * Returns the row where this cell is located on the map.
     *
     * @return The row where this cell is located on the map.
     */
    public int getRow() {
        return row;
    }

    /**
     * Returns the column where this cell is located on the map.
     *
     * @return The column where this cell is located on the map.
     */
    public int getColumn() {
        return column;
    }

    /**
     * Creates a new instance of Cell.
     * The created cell is initially empty.
     *
     * @param row The row where the cell is located on the map.
     * @param column The column where the cell is located on the map.
     */
    public Cell(int row, int column) {
        this.row = row;
        this.column = column;
        this.isBrick = false;
    }

    /**
     * Creates a new instance of Cell with a sprite.
     *
     * @param sprite The representation of the initial content of the cell.
     */
    public Cell(Sprite sprite) {
        this.spriteProperty.set(sprite);
        this.isBrick = false;
    }

    /**
     * Creates a new instance of Cell with a sprite.
     *
     * @param sprite The representation of the cell's initial content.
     */
    public Cell(Sprite sprite, int row, int column) {
        this.row = row;
        this.column = column;
        this.spriteProperty.set(sprite);
        this.isBrick = false;
    }

    /**
     * Creates a new instance of Cell with a wall.
     *
     * @param wall The wall initially present in the cell.
     */
    public Cell(Wall wall) {
        this.wallProperty.set(wall);
        this.spriteProperty.set(wall.getSprite());
        this.isBrick = false;
    }

    public Cell(Wall wall, int row, int column) {
        this.row = row;
        this.column = column;
        this.wallProperty.set(wall);
        this.spriteProperty.set(wall.getSprite());
        this.isBrick = false;
    }

    /**
     * Checks if this cell is empty.
     * A cell is considered empty if it does not contain a wall or a destructible brick.
     *
     * @return true if the cell is empty.
     */
    public boolean isEmpty() {
        return wallProperty.get() == null && !isBrick;
    }

    /**
     * Returns the sprite representing the content of this cell on the map.
     *
     * @return The sprite representing the content of this cell on the map.
     */
    public Sprite getSprite() {
        return spriteProperty.get();
    }

    /**
     * Returns the property containing the sprite representing the content of this cell on the map.
     *
     * @return The property containing the sprite.
     */
    public ObjectProperty<Sprite> getSpriteProperty() {
        return spriteProperty;
    }

    /**
     * Returns the wall present on this cell on the map.
     *
     * @return The wall present on this cell on the map.
     */
    public Wall getWall() {
        return wallProperty.get();
    }

    /**
     * Replaces the content of this cell with the content of another cell.
     *
     * @param cell The cell whose content should be copied into this cell.
     */
    public void replaceBy(Cell cell) {
        spriteProperty.set(cell.getSprite());
        wallProperty.set(cell.getWall());
    }

    /**
     * Simulates the explosion of the cell, updating the sprite if it is a wall.
     */
    public void cellExplode(BombermanGame bg) {
        if (getWall() != null) {
            getWall().explode();
            if (getWall().isDestroyed()) {
                spriteProperty.set(SpriteStore.getInstance().getSprite("lawn"));
                wallProperty.set(null); // Removes the wall from the cell
                isBrick = false;
                int oddToAddBonus = RAND.nextInt(1,10);
                if (oddToAddBonus == 5 || oddToAddBonus == 6){
                    IMovable movable =  switch (RAND.nextInt(1,4)){
                        case 1 -> new BonusAddBomb(bg , getRow(), getColumn(), SpriteStore.getInstance().getSprite("bomb-bonus-add"));
                        case 2 -> new BonusInvincibility(bg, getRow(), getColumn(), SpriteStore.getInstance().getSprite("invincibility-bonus"));
                        case 3 -> new BonusLife(bg, getRow(), getColumn(), SpriteStore.getInstance().getSprite("life-bonus-add"));
                        default -> null;
                    };
                    if(movable != null){
                        movable.setX(getColumn() * SpriteStore.getInstance().getSpriteSize());
                        movable.setY(getRow() * SpriteStore.getInstance().getSpriteSize());
                        bg.addMovable(movable);
                    }
                }
            } else {
                spriteProperty.set(getWall().getSprite());
            }
        }
    }

}
