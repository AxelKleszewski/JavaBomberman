package fr.univartois.butinfo.r304.bomberman.model;

import fr.univartois.butinfo.r304.bomberman.model.map.Cell;
import fr.univartois.butinfo.r304.bomberman.model.map.GameMap;
import fr.univartois.butinfo.r304.bomberman.model.entity.LivingEnemy;
import fr.univartois.butinfo.r304.bomberman.model.entity.LivingPlayer;
import fr.univartois.butinfo.r304.bomberman.model.entity.Player;
import fr.univartois.butinfo.r304.bomberman.view.Sprite;

/**
 * The {@code LevelAbstractFactory} interface defines the contract for level-specific factories
 * in the Bomberman game. Each level factory implementation provides methods to create the
 * elements necessary for its level configuration, such as maps, enemies, and players.
 */
public interface LevelAbstractFactory {

    /**
     * Creates the map for the current level.
     *
     * @param gameMap The {@link GameMap} object to be initialized with the level's structure.
     */
    void createMap(GameMap gameMap);

    /**
     * Creates an enemy for the current level.
     *
     * @param cell   The {@link Cell} where the enemy will be placed.
     * @param sprite The {@link Sprite} representing the enemy's appearance.
     * @param game   The {@link BombermanGame} instance this enemy belongs to.
     * @param player The {@link Player} interacting with the game's mechanics.
     * @return A {@link LivingEnemy} instance configured for the current level.
     */
    LivingEnemy createEnemy(Cell cell, Sprite sprite, BombermanGame game, Player player);

    /**
     * Provides the factory for the next level.
     *
     * @return A {@link LevelAbstractFactory} instance representing the next level.
     */
    LevelAbstractFactory getNextLevelFactory();

    /**
     * Creates a player for the current level. By default, it provides a player with initial
     * attributes. This method can be overridden by implementing classes to customize player
     * creation.
     *
     * @param game   The {@link BombermanGame} instance this player belongs to.
     * @param sprite The {@link Sprite} representing the player's appearance.
     * @return A {@link LivingPlayer} instance with default attributes.
     */
    default LivingPlayer createPlayer(BombermanGame game, Sprite sprite) {
        return new LivingPlayer(game, 0, 0, sprite, 0, 5);
    }
}