package fr.univartois.butinfo.r304.bomberman.model.level;

import fr.univartois.butinfo.r304.bomberman.model.BombermanGame;
import fr.univartois.butinfo.r304.bomberman.model.LevelAbstractFactory;
import fr.univartois.butinfo.r304.bomberman.model.entity.LivingEnemy;
import fr.univartois.butinfo.r304.bomberman.model.entity.PerpendicularMove;
import fr.univartois.butinfo.r304.bomberman.model.entity.Player;
import fr.univartois.butinfo.r304.bomberman.model.map.Cell;
import fr.univartois.butinfo.r304.bomberman.model.map.GameMap;
import fr.univartois.butinfo.r304.bomberman.model.map.IMap;
import fr.univartois.butinfo.r304.bomberman.model.map.maps.MapFlat;
import fr.univartois.butinfo.r304.bomberman.view.Sprite;

/**
 * This class implements the EasyGameFactory, which is responsible for creating
 * the elements of an "easy" game level in the Bomberman game.
 * <p>
 * The factory uses the Singleton pattern to ensure a single instance is used.
 */
public class EasyGameFactory implements LevelAbstractFactory {

    /**
     * The singleton instance of the EasyGameFactory.
     * <p>
     * This ensures only one instance of the factory exists, which
     * allows for consistent configuration of the "easy" level elements.
     */
    private static final LevelAbstractFactory INSTANCE = new EasyGameFactory();

    private EasyGameFactory(){}

    /**
     * Creates the map for the easy level.
     *
     * @param gameMap The GameMap object to be initialized with the map structure.
     */
    @Override
    public void createMap(GameMap gameMap) {
        IMap iMap = new MapFlat();
        iMap.initializeMap(gameMap);
    }

    /**
     * Creates an enemy for the easy level.
     *
     * @param cell   The cell in which the enemy is placed.
     * @param sprite The sprite used for the enemy's visual representation.
     * @param game   The BombermanGame instance the enemy belongs to.
     * @param player   The player instance in the game.
     * @return A LivingEnemy instance with basic attributes for this level.
     */
    @Override
    public LivingEnemy createEnemy(Cell cell, Sprite sprite, BombermanGame game, Player player) {
        LivingEnemy enemy = new LivingEnemy(
                game,
                cell.getRow() * 1.0 * cell.getHeight(),
                cell.getRow() * 1.0 * cell.getHeight() * cell.getWidth(),
                sprite,
                1
        );
        new PerpendicularMove(enemy.getEnemy());
        return enemy;
    }

    /**
     * Provides the factory for the next level (MediumGameFactory).
     *
     * @return The LevelAbstractFactory instance for the medium level.
     */
    @Override
    public LevelAbstractFactory getNextLevelFactory() {
        return MediumGameFactory.getInstance();
    }

    /**
     * Provides the singleton instance of the EasyGameFactory.
     *
     * @return The unique EasyGameFactory instance.
     */
    public static LevelAbstractFactory getInstance() {
        return INSTANCE;
    }
}