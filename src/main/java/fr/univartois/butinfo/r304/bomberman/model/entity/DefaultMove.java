package fr.univartois.butinfo.r304.bomberman.model.entity;

import fr.univartois.butinfo.r304.bomberman.model.movables.IStrategyMove;

import static fr.univartois.butinfo.r304.bomberman.model.BombermanGame.RAND;

/**
 * The {@code DefaultMove} class implements the {@link IStrategyMove} interface,
 * providing a default movement strategy for an {@link Enemy} in the Bomberman game.
 * The enemy's movement speed is randomized upon creation and updated when it encounters a wall.
 */
public class DefaultMove implements IStrategyMove {

    private final Enemy enemy;

    /**
     * Creates a new instance of {@code DefaultMove} for the specified enemy.
     *
     * @param enemy The {@link Enemy} that this movement strategy will control.
     */
    public DefaultMove(Enemy enemy) {
        this.enemy = enemy;
        enemy.setStrategyMove(this);
        enemy.setVerticalSpeed((RAND.nextDouble() * 50 + 50) * (RAND.nextDouble() > 0.5 ? 1 : -1));
        enemy.setHorizontalSpeed((RAND.nextDouble() * 50 + 50) * (RAND.nextDouble() > 0.5 ? 1 : -1));
    }

    /**
     * Updates the enemy's movement speed based on its collision state.
     * If the enemy is currently on a wall, its vertical and horizontal speeds are randomized again.
     *
     * @param isOnWall Indicates whether the enemy is currently colliding with a wall.
     */
    public void move(boolean isOnWall) {
        if(isOnWall) {
            enemy.setVerticalSpeed((RAND.nextDouble() * 50 + 50) * (RAND.nextDouble() > 0.5 ? 1 : -1));
            enemy.setHorizontalSpeed((RAND.nextDouble() * 50 + 50) * (RAND.nextDouble() > 0.5 ? 1 : -1));
        }
    }
}