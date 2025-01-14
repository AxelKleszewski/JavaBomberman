package fr.univartois.butinfo.r304.bomberman.model.movables;

import fr.univartois.butinfo.r304.bomberman.model.BombermanGame;

/**
 * The {@code IBombe} interface defines the contract for bomb objects in the Bomberman game.
 * It provides a method for handling the explosion of a bomb.
 */
public interface IBomb {

    /**
     * Triggers the explosion of the bomb at a specified position in the game.
     *
     * @param game The instance of the Bomberman game where the explosion occurs.
     * @param xPosition The X coordinate where the explosion takes place.
     * @param yPosition The Y coordinate where the explosion takes place.
     */
    void explosion(BombermanGame game, double xPosition, double yPosition);
}