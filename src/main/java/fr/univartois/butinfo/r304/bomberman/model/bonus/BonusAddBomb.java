package fr.univartois.butinfo.r304.bomberman.model.bonus;

import fr.univartois.butinfo.r304.bomberman.model.BombermanGame;
import fr.univartois.butinfo.r304.bomberman.model.IMovable;
import fr.univartois.butinfo.r304.bomberman.model.movables.AbstractMovable;
import fr.univartois.butinfo.r304.bomberman.view.Sprite;

/**
 * The {@code BonusAddBomb} class represents a bonus item in the Bomberman game.
 * This bonus allows the player to add an extra bomb to their inventory.
 * It interacts with other movable objects when they collide with it.
 */
public class BonusAddBomb extends AbstractMovable {

    /**
     * Creates a new instance of {@code BonusAddBomb}.
     *
     * @param game      The {@link BombermanGame} instance in which the object exists.
     * @param xPosition The initial x-coordinate of the object.
     * @param yPosition The initial y-coordinate of the object.
     * @param sprite    The instance of {@link Sprite} representing the object.
     */
    public BonusAddBomb(BombermanGame game, double xPosition, double yPosition, Sprite sprite) {
        super(game, xPosition, yPosition, sprite);
    }

    /**
     * Handles collisions with other movable objects.
     * In this case, the bonus adds a bomb to the player when collided.
     *
     * @param other The other {@link IMovable} object that this bonus collided with.
     */
    @Override
    public void collidedWith(IMovable other) {
        other.hitBonusAddBomb(this);
    }
}