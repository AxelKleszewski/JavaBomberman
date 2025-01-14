package fr.univartois.butinfo.r304.bomberman.model.map;

/**
 * The {@code IMap} interface defines the contract for generating maps in the Bomberman game.
 * It provides a method for initialising the GameMap for the game.
 */
public interface IMap {

    /**
     * Initialises the given GameMap with the required map elements.
     *
     * @param gameMap The GameMap to be initialised.
     */
    void initializeMap(GameMap gameMap);

}
