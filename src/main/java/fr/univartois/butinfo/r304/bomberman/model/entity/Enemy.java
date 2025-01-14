package fr.univartois.butinfo.r304.bomberman.model.entity;

import fr.univartois.butinfo.r304.bomberman.model.BombermanGame;
import fr.univartois.butinfo.r304.bomberman.model.IMovable;
import fr.univartois.butinfo.r304.bomberman.model.movables.AbstractMovable;
import fr.univartois.butinfo.r304.bomberman.model.movables.IStrategyMove;
import fr.univartois.butinfo.r304.bomberman.view.Sprite;

import java.util.Objects;

/**
 * The {@code Enemy} class represents an enemy in the Bomberman game.
 * An enemy is a mobile entity capable of moving and interacting with other game objects.
 */
public class Enemy extends AbstractMovable {

    private IStrategyMove strategyMove;

    /**
     * Constructs an instance of {@code Enemy}.
     *
     * @param game       The Bomberman game to which the enemy is associated.
     * @param xPosition  The X position of the enemy in the game.
     * @param yPosition  The Y position of the enemy in the game.
     * @param sprite     The sprite that visually represents the enemy.
     */
    public Enemy(BombermanGame game, double xPosition, double yPosition, Sprite sprite) {
        super(game, xPosition, yPosition, sprite);
    }

    /**
     * Moves the enemy based on the elapsed time (delta).
     * The enemy has a random speed between 50 and 100 for both horizontal and vertical movement,
     * while the player's speed is fixed at 75.
     *
     * @param delta The time elapsed since the last update.
     * @return true if the movement was successfully performed, false otherwise.
     */
    @Override
    public boolean move(long delta) {
        boolean isOnWall = !super.move(delta);
        strategyMove.move(isOnWall);
        return isOnWall;
    }

    /**
     * Sets the movement strategy for this enemy.
     *
     * @param strategyMove The strategy that defines the enemy's movement behavior.
     */
    public void setStrategyMove(IStrategyMove strategyMove) {
        this.strategyMove = strategyMove;
    }

    /**
     * Handles the collision of this enemy with another movable object.
     * When a collision is detected, the {@code hitEnemy} method of the other object is called.
     *
     * @param other The movable object that this enemy collided with.
     */
    @Override
    public void collidedWith(IMovable other) {
        other.hitEnemy();
    }

    /**
     * Explodes this enemy. When it explodes, it is consumed (removed from the game).
     */
    @Override
    public void explode() {
        consume();
        game.enemyIsDead(this);
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        Enemy enemy = (Enemy) o;
        return Objects.equals(strategyMove, enemy.strategyMove);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), strategyMove);
    }
}
