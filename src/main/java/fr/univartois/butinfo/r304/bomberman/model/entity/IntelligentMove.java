package fr.univartois.butinfo.r304.bomberman.model.entity;

import fr.univartois.butinfo.r304.bomberman.model.movables.IStrategyMove;

import static fr.univartois.butinfo.r304.bomberman.model.BombermanGame.RAND;

/**
 * The {@code IntelligentMove} class implements the {@link IStrategyMove} interface
 * to define the movement behavior of an enemy that intelligently follows a player.
 */
public class IntelligentMove implements IStrategyMove {

    /**
     * The enemy that will use this movement strategy.
     * This attribute stores the reference to the enemy that follows the player
     * based on the intelligent movement strategy.
     */
    private final Enemy enemy;

    /**
     * The player that the enemy will follow.
     * This attribute stores the reference to the player whose position is used
     * to calculate the movement direction and speed of the enemy.
     */
    private final Player player;

    /**
     * Constructs an instance of {@code IntelligentMove} for the specified enemy and player.
     *
     * @param enemy The enemy that will use this movement strategy.
     * @param player The player that the enemy will follow.
     */
    public IntelligentMove(Enemy enemy, Player player) {
        this.enemy = enemy;
        this.player = player;
        enemy.setStrategyMove(this);
    }

    /**
     * Moves the enemy based on its proximity to the player.
     * If the enemy is on a wall, it will adjust its speed to attempt to move toward the player.
     * If not on a wall, the enemy calculates the distance to the player and sets its speed
     * to move towards the player.
     *
     * @param isOnWall Indicates whether the enemy is currently on a wall.
     */
    public void move(boolean isOnWall) {
        double xPlayer = player.getX();
        double yPlayer = player.getY();
        double speed = 35;

        if (isOnWall) {
            if (Math.abs(xPlayer - enemy.getX()) < Math.abs(yPlayer - enemy.getY())) {
                enemy.setHorizontalSpeed(50);
                enemy.setVerticalSpeed(5 * RAND.nextDouble() > 0.5 ? 1 : -1);
            } else {
                enemy.setHorizontalSpeed(5 * RAND.nextDouble() > 0.5 ? 1 : -1);
                enemy.setVerticalSpeed(50);
            }
            return;
        }

        double distance = Math.sqrt((xPlayer - enemy.getX()) * (xPlayer - enemy.getX()) +
                (yPlayer - enemy.getY()) * (yPlayer - enemy.getY()));
        enemy.setHorizontalSpeed(speed * ((xPlayer - enemy.getX()) / distance));
        enemy.setVerticalSpeed(speed * ((yPlayer - enemy.getY()) / distance));
    }
}