package fr.univartois.butinfo.r304.bomberman.model.bonus;

import fr.univartois.butinfo.r304.bomberman.model.BombermanGame;
import fr.univartois.butinfo.r304.bomberman.model.IMovable;
import fr.univartois.butinfo.r304.bomberman.model.movables.AbstractMovable;
import fr.univartois.butinfo.r304.bomberman.view.Sprite;

/**
 * The {@code BonusInvincibility} class represents a bonus in the Bomberman game
 * that grants invincibility to the player when collected.
 * This class is a type of movable object and is used to interact with other entities
 * in the game when they collide with it.
 */
public class BonusInvincibility extends AbstractMovable {

    /**
     * Creates a new instance of {@code BonusInvincibility}.
     *
     * @param game      The {@link BombermanGame} instance in which the object exists.
     * @param xPosition The initial x-coordinate of the object.
     * @param yPosition The initial y-coordinate of the object.
     * @param sprite    The instance of {@link Sprite} representing the object.
     */
    public BonusInvincibility(BombermanGame game, double xPosition, double yPosition, Sprite sprite) {
        super(game, xPosition, yPosition, sprite);
    }

    /**
     * Handles collisions with other movable objects. This method calls
     * {@code hitBonusInvincible} on the other object when a collision occurs,
     * which grants invincibility to the object that collided with the bonus.
     *
     * @param other The other {@link IMovable} object that this bonus collided with.
     */
    @Override
    public void collidedWith(IMovable other) {
        other.hitBonusInvincible(this);
    }
}
