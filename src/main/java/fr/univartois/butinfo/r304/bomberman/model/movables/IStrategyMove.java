package fr.univartois.butinfo.r304.bomberman.model.movables;

/**
 * The {@code IStrategyMove} interface defines the contract for movement strategies
 * that can be implemented by different movable entities in the Bomberman game.
 */
public interface IStrategyMove {

    /**
     * Moves the entity based on its current state and position.
     * The behavior of the movement may vary depending on whether the entity
     * is currently on a wall.
     *
     * @param isOnWall Indicates whether the entity is currently in contact with a wall.
     */
    void move(boolean isOnWall);
}