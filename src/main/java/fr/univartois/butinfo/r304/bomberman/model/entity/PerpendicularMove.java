package fr.univartois.butinfo.r304.bomberman.model.entity;

import fr.univartois.butinfo.r304.bomberman.model.movables.IStrategyMove;

import static fr.univartois.butinfo.r304.bomberman.model.BombermanGame.RAND;

/**
 * The {@code PerpendicularMove} class implements the {@link IStrategyMove} interface,
 * providing a movement strategy for an {@link Enemy} in the Bomberman game that moves
 * perpendicularly. The enemy's movement speed is randomly set upon creation, and it
 * changes direction if the enemy collides with a wall.
 */
public class PerpendicularMove implements IStrategyMove {

    private final Enemy enemy;

    /**
     * Creates a new instance of {@code PerpendicularMove} for the specified enemy.
     * The enemy's initial vertical and horizontal speed is set randomly to simulate
     * perpendicular movement.
     *
     * @param enemy The {@link Enemy} that this movement strategy will control.
     */
    public PerpendicularMove(Enemy enemy) {
        this.enemy = enemy;
        enemy.setStrategyMove(this);
        double rand = RAND.nextDouble();
        if (rand > 0.5){
            enemy.setVerticalSpeed(0);
            enemy.setHorizontalSpeed(rand > 0.75 ? -75 : 75);
        }else{
            enemy.setVerticalSpeed(rand > 0.25 ? -75 : 75);
            enemy.setHorizontalSpeed(0);
        }
    }

    /**
     * Updates the enemy's movement speed based on its collision state with a wall.
     * If the enemy is colliding with a wall, its movement direction (vertical or horizontal)
     * is randomized to simulate the change in direction.
     *
     * @param isOnWall Indicates whether the enemy is currently colliding with a wall.
     */
    public void move(boolean isOnWall) {
        if (isOnWall) {
            double rand = RAND.nextDouble();
            if (enemy.getVerticalSpeed() == 0) {
                enemy.setVerticalSpeed(rand > 0.5 ? -75 : 75);
                enemy.setHorizontalSpeed(0);
            } else {
                enemy.setHorizontalSpeed(rand > 0.5 ? -75 : 75);
                enemy.setVerticalSpeed(0);
            }
        }
    }
}