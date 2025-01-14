package fr.univartois.butinfo.r304.bomberman.model.level;

import fr.univartois.butinfo.r304.bomberman.model.BombermanGame;
import fr.univartois.butinfo.r304.bomberman.model.LevelAbstractFactory;
import fr.univartois.butinfo.r304.bomberman.model.entity.DefaultMove;
import fr.univartois.butinfo.r304.bomberman.model.entity.LivingEnemy;
import fr.univartois.butinfo.r304.bomberman.model.entity.Player;
import fr.univartois.butinfo.r304.bomberman.model.entity.RandomMove;
import fr.univartois.butinfo.r304.bomberman.model.map.Cell;
import fr.univartois.butinfo.r304.bomberman.model.map.GameMap;
import fr.univartois.butinfo.r304.bomberman.model.map.IMap;
import fr.univartois.butinfo.r304.bomberman.model.map.maps.MapX;
import fr.univartois.butinfo.r304.bomberman.view.Sprite;

import static fr.univartois.butinfo.r304.bomberman.model.BombermanGame.RAND;

/**
 * This class implements the MediumGameFactory, which is responsible for creating
 * the elements of a "medium" game level in the Bomberman game.
 * <p>
 * The factory uses the Singleton pattern to ensure a single instance is used.
 */
public class MediumGameFactory implements LevelAbstractFactory {

    /**
     * The singleton instance of the MediumGameFactory.
     * <p>
     * This ensures only one instance of the factory exists, which
     * allows for consistent configuration of the "medium" level elements.
     */
    private static final LevelAbstractFactory INSTANCE = new MediumGameFactory();

    private MediumGameFactory(){}

    /**
     * Creates the map for the medium level.
     *
     * @param gameMap The GameMap object to be initialized with the map structure.
     */
    @Override
    public void createMap(GameMap gameMap) {
        IMap iMap = new MapX();
        iMap.initializeMap(gameMap);
    }

    /**
     * Creates an enemy for the medium level.
     *
     * @param cell   The cell in which the enemy is placed.
     * @param sprite The sprite used for the enemy's visual representation.
     * @param game   The BombermanGame instance the enemy belongs to.
     * @param player   The player instance in the game.
     * @return A LivingEnemy instance with moderate attributes for this level.
     */
    @Override
    public LivingEnemy createEnemy(Cell cell, Sprite sprite, BombermanGame game, Player player) {
        LivingEnemy enemy = new LivingEnemy(
                game,
                cell.getRow() * 1.0 * cell.getHeight(),
                cell.getRow() * 1.0 * cell.getHeight() * cell.getWidth(),
                sprite,
                2
        );
        if(RAND.nextDouble() < 0.5) new DefaultMove(enemy.getEnemy());
        else new RandomMove(enemy.getEnemy());
        return enemy;
    }

    /**
     * Provides the factory for the next level (HardGameFactory).
     *
     * @return The LevelAbstractFactory instance for the hard level.
     */
    @Override
    public LevelAbstractFactory getNextLevelFactory() {
        return HardGameFactory.getInstance();
    }

    /**
     * Singleton implementation to ensure only one instance of the MediumGameFactory exists.
     * This is necessary for consistent game state and level configuration.
     */
    public static LevelAbstractFactory getInstance() {
        return INSTANCE;
    }
}