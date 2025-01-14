package fr.univartois.butinfo.r304.bomberman.model.bombs;

import fr.univartois.butinfo.r304.bomberman.model.BombermanGame;
import fr.univartois.butinfo.r304.bomberman.model.IMovable;
import fr.univartois.butinfo.r304.bomberman.model.movables.AbstractMovable;
import fr.univartois.butinfo.r304.bomberman.view.Sprite;

import java.util.Objects;

/**
 * The {@code Explosion} class represents an explosion in the game.
 * The explosion has a limited duration and disappears after a certain time.
 */
public class Explosion extends AbstractMovable {

    /**
     * Duration for which the explosion remains visible in milliseconds.
     */
    protected long duration = 2000;

    /**
     * The time when the explosion was created, in milliseconds.
     */
    protected long currentTime;

    /**
     * Constructor to initialize the explosion at a given position with a specific sprite.
     *
     * @param game The instance of the Bomberman game in which the explosion occurs.
     * @param xPosition The X position of the explosion.
     * @param yPosition The Y position of the explosion.
     * @param sprite The graphical sprite representing the explosion.
     */
    protected Explosion(BombermanGame game, double xPosition, double yPosition, Sprite sprite) {
        super(game, xPosition, yPosition, sprite);
        this.currentTime = System.currentTimeMillis(); // Initializes the creation time of the explosion.
    }

    /**
     * Manages the behavior of the explosion.
     * Removes the explosion from the game when its duration has elapsed.
     *
     * @param delta The time elapsed since the last game update.
     * @return Always true as the explosion does not move.
     */
    @Override
    public boolean move(long delta) {
        // Checks if the explosion time has elapsed and removes it from the game.
        if (System.currentTimeMillis() >= currentTime + duration) {
            game.removeMovable(this); // Removes the explosion from the game after its duration has elapsed.
        }
        return true; // The explosion does not move, so it always returns true.
    }

    /**
     * Handles the collision with another object.
     * When the explosion comes into contact with another object, it triggers that object's explosion.
     *
     * @param other The object with which the explosion collides.
     */
    @Override
    public void collidedWith(IMovable other) {
        other.explode(); // Triggers the explosion of the other object.
    }

    /**
     * Handles the interaction with a bonus that grants an additional bomb.
     * Currently, this method has no implementation.
     *
     * @param other The movable entity that triggered the interaction.
     */
    @Override
    public void hitBonusAddBomb(IMovable other) {
        // No action is performed for this interaction.
    }

    /**
     * Handles the interaction with a bonus that grants invincibility.
     * Currently, this method has no implementation.
     *
     * @param other The movable entity that triggered the interaction.
     */
    @Override
    public void hitBonusInvincible(IMovable other) {
        // No action is performed for this interaction.
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        Explosion explosion = (Explosion) o;
        return duration == explosion.duration && currentTime == explosion.currentTime;
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), duration, currentTime);
    }
}
