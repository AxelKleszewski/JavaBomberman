package fr.univartois.butinfo.r304.bomberman.model.level;

import fr.univartois.butinfo.r304.bomberman.model.BombermanGame;
import fr.univartois.butinfo.r304.bomberman.model.LevelAbstractFactory;
import fr.univartois.butinfo.r304.bomberman.model.map.Cell;
import fr.univartois.butinfo.r304.bomberman.model.map.GameMap;
import fr.univartois.butinfo.r304.bomberman.model.map.IMap;
import fr.univartois.butinfo.r304.bomberman.model.map.maps.MapClassic;
import fr.univartois.butinfo.r304.bomberman.model.entity.IntelligentMove;
import fr.univartois.butinfo.r304.bomberman.model.entity.LivingEnemy;
import fr.univartois.butinfo.r304.bomberman.model.entity.Player;
import fr.univartois.butinfo.r304.bomberman.view.Sprite;

/**
 * This class implements the HardGameFactory, responsible for creating
 * the elements of a "hard" game level in the Bomberman game.
 * <p>
 * The factory adheres to the Singleton pattern, ensuring a single
 * instance is used throughout the game for consistent configuration.
 */
public class HardGameFactory implements LevelAbstractFactory {

    /**
     * The singleton instance of the HardGameFactory.
     * <p>
     * This attribute ensures that only one instance of the factory exists.
     * It provides a centralized creation logic for the "hard" level elements.
     */
    private static final LevelAbstractFactory INSTANCE = new HardGameFactory();

    private HardGameFactory(){}

    /**
     * Creates the map for the hard level.
     *
     * @param gameMap The GameMap object to be initialized with the map structure.
     *         This map typically has a more complex layout.
     */
    @Override
    public void createMap(GameMap gameMap) {
        IMap iMap = new MapClassic();
        iMap.initializeMap(gameMap);
    }

    /**
     * Creates an enemy for the hard level.
     *
     * @param cell   The Cell where the enemy is placed on the map.
     * @param sprite The Sprite representing the enemy's appearance in the game.
     * @param game   The BombermanGame instance to which this enemy belongs.
     * @param player   The player instance in the game.
     * @return A LivingEnemy instance configured with advanced attributes suitable
     *         for the hard difficulty level.
     *         The enemy has higher health points and is positioned based on
     *         the cell dimensions.
     */
    @Override
    public LivingEnemy createEnemy(Cell cell, Sprite sprite, BombermanGame game, Player player) {
        LivingEnemy enemy = new LivingEnemy(
                game,
                cell.getRow() * 1.0 * cell.getHeight(),
                cell.getRow() * 1.0 * cell.getHeight() * cell.getWidth(),
                sprite,
                3
        );
        new IntelligentMove(enemy.getEnemy(), player);
        return enemy;
    }

    /**
     * Provides the factory for the next level.
     * <p>
     * In the case of the hard level, there is no subsequent level,
     * so it returns the instance of HardGameFactory itself.
     *
     * @return The singleton instance of HardGameFactory.
     */
    @Override
    public LevelAbstractFactory getNextLevelFactory() {
        return HardGameFactory.getInstance();
    }

    /**
     * Provides the singleton instance of the HardGameFactory.
     * <p>
     * This method ensures access to the unique instance of this factory.
     *
     * @return The singleton HardGameFactory instance.
     */
    public static LevelAbstractFactory getInstance() {
        return INSTANCE;
    }
}