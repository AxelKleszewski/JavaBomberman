package fr.univartois.butinfo.r304.bomberman.model.bombs;

import fr.univartois.butinfo.r304.bomberman.model.BombermanGame;
import fr.univartois.butinfo.r304.bomberman.model.IMovable;
import fr.univartois.butinfo.r304.bomberman.model.movables.AbstractMovable;
import fr.univartois.butinfo.r304.bomberman.model.movables.IBomb;
import fr.univartois.butinfo.r304.bomberman.view.Sprite;

import java.util.Objects;

/**
 * The {@code Bombe} class represents a bomb in the Bomberman game.
 * The bomb is placed at a specific position and explodes after a certain delay.
 */
public class Bomb extends AbstractMovable {

    /**
     * The strategy for handling the bomb's explosion.
     * This defines how the explosion will be executed when the bomb explodes.
     */
    private final IBomb explosionStrategy;

    /**
     * Duration before the bomb explodes, in milliseconds.
     * This value determines how long the bomb will stay active before it explodes.
     */
    protected long duration = 3000;

    /**
     * The time at which the bomb was placed, in milliseconds.
     * This is used to track when the bomb was initially created and to check if it has expired.
     */
    protected long time;

    /**
     * Creates a new instance of {@code Bombe}.
     *
     * @param game      The {@link BombermanGame} instance in which the object exists.
     * @param xPosition The initial x-coordinate of the object.
     * @param yPosition The initial y-coordinate of the object.
     * @param sprite    The instance of {@link Sprite} representing the object.
     * @param explosionStrategy The strategy for handling the bomb's explosion.
     */
    public Bomb(BombermanGame game, double xPosition, double yPosition, Sprite sprite, IBomb explosionStrategy) {
        super(game, xPosition, yPosition, sprite);
        this.time = System.currentTimeMillis();
        this.explosionStrategy = explosionStrategy;
    }

    /**
     * Handles collisions with other movable objects.
     * This method is empty because the bomb does not react when colliding with another object.
     *
     * @param other The other {@link IMovable} object that this bomb collided with.
     */
    @Override
    public void collidedWith(IMovable other) {
        // This method is empty because the bomb does not react when colliding with another object.
    }

    /**
     * Triggers the explosion of the bomb by resetting the time.
     * This method is called to simulate the immediate explosion of the bomb.
     */
    @Override
    public void explode() {
        time = 0; // Resets the time to 0 to force the explosion.
    }

    /**
     * Positions the bomb on the field.
     * Sets the X and Y coordinates of the bomb and initializes the timer for the explosion.
     */
    public void positionBombe() {
        time = System.currentTimeMillis();
    }

    /**
     * Triggers the explosion using the specified explosion strategy.
     *
     * @param game The {@link BombermanGame} instance in which the explosion occurs.
     * @param xPosition The x-coordinate of the explosion's origin.
     * @param yPosition The y-coordinate of the explosion's origin.
     */
    public void explosion(BombermanGame game, double xPosition, double yPosition) {
        explosionStrategy.explosion(game, xPosition, yPosition);
    }

    /**
     * Manages the movement of the bomb and checks if it should explode after a certain delay.
     * If the bomb's time has expired, it explodes and creates Explosion objects around it.
     *
     * @param delta The time elapsed since the last game update.
     * @return Always true as the bomb does not move directly.
     */
    @Override
    public boolean move(long delta) {
        if (System.currentTimeMillis() >= time + duration) {
            explosion(game, this.xPosition.get(), this.yPosition.get());
            game.removeMovable(this);
        }
        return true;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        Bomb bomb = (Bomb) o;
        return duration == bomb.duration && time == bomb.time && Objects.equals(explosionStrategy, bomb.explosionStrategy);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), explosionStrategy, duration, time);
    }
}
