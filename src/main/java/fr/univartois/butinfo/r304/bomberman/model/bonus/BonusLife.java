package fr.univartois.butinfo.r304.bomberman.model.bonus;

import fr.univartois.butinfo.r304.bomberman.model.BombermanGame;
import fr.univartois.butinfo.r304.bomberman.model.IMovable;
import fr.univartois.butinfo.r304.bomberman.model.movables.AbstractMovable;
import fr.univartois.butinfo.r304.bomberman.view.Sprite;

/**
 * The {@code BonusLife} class represents a bonus in the Bomberman game
 * that grants an extra life to the player when collected.
 * This class is a type of movable object and interacts with other entities
 * in the game when they collide with it.
 */
public class BonusLife extends AbstractMovable {

    /**
     * Creates a new instance of {@code BonusLife}.
     *
     * @param game      The {@link BombermanGame} instance in which the object exists.
     * @param xPosition The initial x-coordinate of the object.
     * @param yPosition The initial y-coordinate of the object.
     * @param sprite    The instance of {@link Sprite} representing the object.
     */
    public BonusLife(BombermanGame game, double xPosition, double yPosition, Sprite sprite) {
        super(game, xPosition, yPosition, sprite);
    }

    /**
     * Handles collisions with other movable objects. When a collision occurs,
     * this method calls {@code hitBonusLife} on the other object, which grants
     * the other object an extra life.
     *
     * @param other The other {@link IMovable} object that this bonus collided with.
     */
    @Override
    public void collidedWith(IMovable other) {
        other.hitBonusLife(this);
    }
}