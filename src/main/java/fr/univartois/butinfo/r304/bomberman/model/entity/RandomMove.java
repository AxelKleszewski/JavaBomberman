package fr.univartois.butinfo.r304.bomberman.model.entity;

import fr.univartois.butinfo.r304.bomberman.model.movables.IStrategyMove;

import static fr.univartois.butinfo.r304.bomberman.model.BombermanGame.RAND;

/**
 * The {@code RandomMove} class implements a random movement strategy for an enemy in the Bomberman game.
 * This strategy randomly changes the direction and speed of the enemy, giving it an unpredictable movement pattern.
 */
public class RandomMove implements IStrategyMove {

    /**
     * The enemy that will use this random movement strategy.
     */
    private final Enemy enemy;

    /**
     * Constructs a new {@code RandomMove} strategy for the specified enemy.
     * This also sets the strategy in the enemy instance.
     *
     * @param enemy The {@code Enemy} instance that will use this random movement strategy.
     */
    public RandomMove(Enemy enemy) {
        this.enemy = enemy;
        enemy.setStrategyMove(this);
    }

    /**
     * Moves the enemy in a random direction. If the enemy collides with a wall (or if a random condition is met),
     * it changes direction and speed to a new random value.
     *
     * @param isOnWall Indicates whether the enemy has collided with a wall, which forces a direction change.
     */
    public void move(boolean isOnWall) {
        if (RAND.nextDouble() < 0.02 || isOnWall) {
            enemy.setHorizontalSpeed((RAND.nextDouble() * 50 + 50) * (RAND.nextDouble() > 0.5 ? 1 : -1));
            enemy.setVerticalSpeed((RAND.nextDouble() * 50 + 50) * (RAND.nextDouble() > 0.5 ? 1 : -1));
        }
    }
}