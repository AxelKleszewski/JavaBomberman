/**
 * This software is distributed for educational purposes.
 * <p>
 * It is provided "as is", without any kind of warranty, either express or implied,
 * including but not limited to implied warranties of merchantability, fitness for a
 * particular purpose, and non-infringement.
 * In no event shall the authors or copyright holders be liable for any damage, claim,
 * or other liability, whether in an action of contract, tort, or otherwise, arising from,
 * out of, or in connection with the software or the use or other dealings in the software.
 * <p>
 * (c) 2022-2024 Romain Wallon - Université d'Artois.
 * All rights reserved.
 */

package fr.univartois.butinfo.r304.bomberman.model;

import fr.univartois.butinfo.r304.bomberman.view.Sprite;
import javafx.beans.property.BooleanProperty;
import javafx.beans.property.DoubleProperty;
import javafx.beans.property.ObjectProperty;

/**
 * The {@link IMovable} interface defines the contract for game elements capable
 * of moving.
 *
 * @author Romain Wallon
 *
 * @version 0.1.0
 */
public interface IMovable {

    /**
     * Returns the width of this object.
     *
     * @return The width of this object.
     */
    int getWidth();

    /**
     * Returns the height of this object.
     *
     * @return The height of this object.
     */
    int getHeight();

    /**
     * Sets the x position of this object.
     *
     * @param xPosition The new x position of this object.
     */
    void setX(int xPosition);

    /**
     * Returns the x position of this object.
     *
     * @return The x position of this object.
     */
    int getX();

    /**
     * Returns the property associated with the x position of this object.
     *
     * @return The property associated with the x position of this object.
     */
    DoubleProperty getXProperty();

    /**
     * Sets the y position of this object.
     *
     * @param yPosition The new y position of this object.
     */
    void setY(int yPosition);

    /**
     * Returns the y position of this object.
     *
     * @return The y position of this object.
     */
    int getY();

    /**
     * Returns the property associated with the y position of this object.
     *
     * @return The property associated with the y position of this object.
     */
    DoubleProperty getYProperty();

    /**
     * Consumes this object so that it is no longer displayed in the game.
     */
    void consume();

    /**
     * Checks if this object has been consumed.
     * A consumed object disappears from the display.
     *
     * @return True if the object has been consumed.
     */
    boolean isConsumed();

    /**
     * Returns the property indicating whether this object has been consumed.
     *
     * @return The property indicating whether the object has been consumed.
     *
     * @see #isConsumed()
     */
    BooleanProperty isConsumedProperty();

    /**
     * Sets the horizontal speed of this object.
     *
     * @param speed The new horizontal speed of this object (in pixels per second).
     */
    void setHorizontalSpeed(double speed);

    /**
     * Returns the horizontal speed of this object.
     *
     * @return The horizontal speed of this object (in pixels per second).
     */
    double getHorizontalSpeed();

    /**
     * Sets the vertical speed of this object.
     *
     * @param speed The new vertical speed of this object (in pixels per second).
     */
    void setVerticalSpeed(double speed);

    /**
     * Returns the vertical speed of this object.
     *
     * @return The vertical speed of this object (in pixels per second).
     */
    double getVerticalSpeed();

    /**
     * Sets the {@link Sprite} instance representing this object.
     *
     * @param sprite The new {@link Sprite} instance representing this object.
     */
    void setSprite(Sprite sprite);

    /**
     * Returns the {@link Sprite} instance representing this object.
     *
     * @return The {@link Sprite} instance representing this object.
     */
    Sprite getSprite();

    /**
     * Returns the property associated with the {@link Sprite} representing this object.
     *
     * @return The property associated with the {@link Sprite}.
     */
    ObjectProperty<Sprite> getSpriteProperty();

    /**
     * Moves this object to its new position, calculated based on the time elapsed
     * since its last movement and its current speed.
     *
     * @param timeDelta The time elapsed since the last movement of this object (in
     *        milliseconds).
     *
     * @return True if the object was successfully moved.
     *         If false, the object has reached the edge of the window and is blocked.
     */
    boolean move(long timeDelta);

    /**
     * Checks if this object has collided with another {@link IMovable} instance.
     *
     * @param other The object with which the collision should be checked.
     *
     * @return True if this object collided with {@code other}.
     */
    boolean isCollidingWith(IMovable other);

    /**
     * Notifies this object that it has collided with another {@link IMovable} instance.
     *
     * @param other The object with which this object collided.
     */
    void collidedWith(IMovable other);

    /**
     * Causes this object to explode.
     */
    void explode();

    /**
     * Notifies this object that it has hit an enemy.
     */
    void hitEnemy();

    /**
     * Informs this object that it has touched a bonus that adds a bomb.
     */
    void hitBonusAddBomb(IMovable other);

    /**
     * Informs this object that it has touched a bonus making it invincible.
     */
    void hitBonusInvincible(IMovable other);

    /**
     * Informs this object that it has touched a bonus restoring one life point.
     */
    void hitBonusLife(IMovable other);

    /**
     * Returns the actual object that implements this interface.
     *
     * @return The actual object.
     */
    IMovable self();

}
