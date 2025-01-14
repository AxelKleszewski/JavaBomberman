package fr.univartois.butinfo.r304.bomberman.model.map;

import fr.univartois.butinfo.r304.bomberman.view.Sprite;

/**
 * The {@code IMap} interface defines the contract for generating maps in the Bomberman game.
 * It provides a method for initialising the GameMap for the game.
 */
public interface IStateWall {

    /**
     * Sets the next state of the wall.
     *
     * @return The next state of the wall.
     */
    IStateWall nextState();

    /**
     * Returns the sprite associated with the current state of the wall.
     *
     * @return The sprite associated with the current state of the wall.
     */
    Sprite getSprite();
}
