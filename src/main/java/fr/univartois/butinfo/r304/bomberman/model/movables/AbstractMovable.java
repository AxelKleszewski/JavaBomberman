/**
 * This software is distributed for educational purposes.
 * <p>
 * It is provided "as is", without warranty of any kind, express or
 * implied, including but not limited to the warranties of merchantability,
 * fitness for a particular purpose and non-infringement.
 * In no event shall the authors or copyright holders be liable for any claim,
 * damages or other liability, whether in contract, tort or otherwise, arising
 * from, out of or in connection with the software or the use or other dealings
 * in the software.
 * <p>
 * (c) 2022-2024 Romain Wallon - University of Artois.
 * All rights reserved.
 */

package fr.univartois.butinfo.r304.bomberman.model.movables;

import fr.univartois.butinfo.r304.bomberman.model.BombermanGame;
import fr.univartois.butinfo.r304.bomberman.model.IMovable;
import fr.univartois.butinfo.r304.bomberman.view.Sprite;
import javafx.beans.property.BooleanProperty;
import javafx.beans.property.DoubleProperty;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.scene.shape.Rectangle;

/**
 * The class {@link AbstractMovable} provides a base implementation for all basic
 * objects that can move in the game.
 *
 * @author Romain Wallon
 *
 * @version 0.1.0
 */
public abstract class AbstractMovable implements IMovable {

    /**
     * The safety margin for obstacles (in pixels).
     */
    private static final int MARGIN = 5;

    /**
     * The game in which this object exists.
     */
    public final BombermanGame game;

    /**
     * The x-position of this object.
     */
    protected final SimpleDoubleProperty xPosition;

    /**
     * The y-position of this object.
     */
    protected final DoubleProperty yPosition;

    /**
     * Indicates if this object has been consumed.
     */
    protected final BooleanProperty consumed;

    /**
     * The current horizontal speed of this object (in pixels/s).
     */
    protected double horizontalSpeed;

    /**
     * The current vertical speed of this object (in pixels/s).
     */
    protected double verticalSpeed;

    /**
     * The instance of {@link Sprite} representing this object.
     */
    protected final ObjectProperty<Sprite> sprite;

    /**
     * Creates a new instance of AbstractMovable.
     *
     * @param game The game in which the object exists.
     * @param xPosition The initial x-position of the object.
     * @param yPosition The initial y-position of the object.
     * @param sprite The instance of {@link Sprite} representing the object.
     */
    protected AbstractMovable(BombermanGame game, double xPosition,
            double yPosition, Sprite sprite) {
        this.game = game;
        this.xPosition = new SimpleDoubleProperty(xPosition);
        this.yPosition = new SimpleDoubleProperty(yPosition);
        this.consumed = new SimpleBooleanProperty(false);
        this.sprite = new SimpleObjectProperty<>(sprite);
    }

    /**
     * Returns the width of the movable object based on its sprite.
     * The width is derived from the associated {@link Sprite} instance, which represents
     * the visual appearance of the object in the game.
     *
     * @return the width of the sprite representing the movable object.
     */
    @Override
    public int getWidth() {
        return sprite.get().getWidth();
    }

    /**
     * Returns the height of the movable object based on its sprite.
     * The height is derived from the associated {@link Sprite} instance, which represents
     * the visual appearance of the object in the game.
     *
     * @return the height of the sprite representing the movable object.
     */
    @Override
    public int getHeight() {
        return sprite.get().getHeight();
    }

    /**
     * Sets the horizontal position (x-coordinate) of the movable object.
     * This method updates the x-coordinate of the object, which is linked to the
     * position property, allowing it to reflect changes in the object's location
     * in the game world.
     *
     * @param xPosition the new x-coordinate to set for the movable object.
     */
    @Override
    public void setX(int xPosition) {
        this.xPosition.set(xPosition);
    }

    /**
     * Gets the horizontal position (x-coordinate) of the movable object.
     * This method returns the current x-coordinate of the object, which is
     * linked to the position property, reflecting the object's current location
     * in the game world.
     *
     * @return the current x-coordinate of the movable object.
     */
    @Override
    public int getX() {
        return xPosition.intValue();
    }

    /**
     * Gets the property representing the horizontal position (x-coordinate) of the movable object.
     * This method provides access to the `DoubleProperty` that is bound to the object's x-coordinate,
     * allowing listeners to track changes to its position.
     *
     * @return the `DoubleProperty` representing the x-coordinate of the movable object.
     */
    @Override
    public DoubleProperty getXProperty() {
        return xPosition;
    }

    /**
     * Sets the vertical position (y-coordinate) of the movable object.
     * This method updates the y-coordinate and notifies any listeners bound to the position property.
     *
     * @param yPosition the new vertical position (y-coordinate) of the movable object.
     */
    @Override
    public void setY(int yPosition) {
        this.yPosition.set(yPosition);
    }

    /**
     * Gets the vertical position (y-coordinate) of the movable object.
     * This method returns the current y-coordinate of the object.
     *
     * @return the current vertical position (y-coordinate) of the movable object.
     */
    @Override
    public int getY() {
        return yPosition.intValue();
    }

    /**
     * Gets the property that represents the vertical position (y-coordinate) of the movable object.
     * This method returns a {@link DoubleProperty} that can be used to bind or observe changes in the
     * y-coordinate of the object.
     *
     * @return the {@link DoubleProperty} representing the vertical position of the movable object.
     */
    @Override
    public DoubleProperty getYProperty() {
        return yPosition;
    }

    /**
     * Consumes the object, marking it as no longer active or visible in the game.
     * Once consumed, the object will disappear from the display and its state will be updated accordingly.
     */
    @Override
    public void consume() {
        consumed.set(true);
    }

    /**
     * Checks whether the object has been consumed.
     * A consumed object is no longer active or visible in the game.
     *
     * @return true if the object has been consumed, false otherwise.
     */
    @Override
    public boolean isConsumed() {
        return consumed.get();
    }

    /**
     * Provides the property that tracks whether the object has been consumed.
     * This property can be used to bind to other UI elements or properties in the game.
     *
     * @return The BooleanProperty that indicates whether the object has been consumed.
     */
    @Override
    public BooleanProperty isConsumedProperty() {
        return consumed;
    }

    /**
     * Sets the horizontal speed of the object.
     * This speed defines how fast the object moves along the x-axis (horizontal direction).
     *
     * @param speed The new horizontal speed of the object, in pixels per second.
     */
    @Override
    public void setHorizontalSpeed(double speed) {
        this.horizontalSpeed = speed;
    }

    /**
     * Returns the current horizontal speed of the object.
     * The horizontal speed defines how fast the object moves along the x-axis (horizontal direction).
     *
     * @return The horizontal speed of the object, in pixels per second.
     */
    @Override
    public double getHorizontalSpeed() {
        return horizontalSpeed;
    }

    /**
     * Sets the vertical speed of the object.
     * The vertical speed defines how fast the object moves along the y-axis (vertical direction).
     *
     * @param speed The new vertical speed of the object, in pixels per second.
     */
    @Override
    public void setVerticalSpeed(double speed) {
        this.verticalSpeed = speed;
    }

    /**
     * Retrieves the vertical speed of the object.
     * The vertical speed indicates how fast the object moves along the y-axis (vertical direction).
     *
     * @return The vertical speed of the object, in pixels per second.
     */
    @Override
    public double getVerticalSpeed() {
        return verticalSpeed;
    }

    /**
     * Sets the sprite for this object.
     * The sprite represents the visual appearance of the object in the game.
     * This method updates the sprite used to render the object on the screen.
     *
     * @param sprite The new {@link Sprite} to assign to this object.
     */
    @Override
    public void setSprite(Sprite sprite) {
        this.sprite.set(sprite);
    }

    /**
     * Gets the sprite representing this object.
     * The sprite is used to visually render the object in the game.
     *
     * @return The {@link Sprite} representing this object.
     */
    @Override
    public Sprite getSprite() {
        return sprite.get();
    }

    /**
     * Gets the property representing the sprite of this object.
     * This property allows binding the sprite to the visual representation of the object,
     * enabling dynamic updates in the user interface when the sprite changes.
     *
     * @return The {@link ObjectProperty} representing the sprite of this object.
     */
    @Override
    public ObjectProperty<Sprite> getSpriteProperty() {
        return sprite;
    }

    /**
     * Moves the object based on its current speed and the elapsed time since the last update.
     * The object’s new position is calculated and updated accordingly.
     * If the object reaches the boundaries of the game area or encounters an obstacle,
     * it will not move and will return {@code false}.
     *
     * @param delta The time elapsed since the last update, in milliseconds.
     *
     * @return {@code true} if the object has moved successfully to the new position,
     *         {@code false} if the object has encountered a boundary or obstacle.
     */
    @Override
    public boolean move(long delta) {
        // Updating the object's position on the x-axis.
        int limitMaxX = game.getWidth() - getWidth();
        double newX = xPosition.get() + (horizontalSpeed * delta) / 1000;
        if ((newX < 0) || (newX > limitMaxX)) {
            // The object has reached the limit on the x-axis.
            return false;
        }

        // Updating the object's position on the y-axis.
        int limitMaxY = game.getHeight() - getHeight();
        double newY = yPosition.get() + (verticalSpeed * delta) / 1000;
        if ((newY < 0) || (newY > limitMaxY)) {
            // The object has reached the limit on the y-axis.
            return false;
        }

        // Checking that there are no obstacles.
        if (isOnWall((int) newX, (int) newY)) {
            // The object has reached a wall.
            return false;
        }

        // The object has not reached any obstacles.
        xPosition.set(newX);
        yPosition.set(newY);
        return true;
    }

    /**
     * Checks if the object's new position is on a wall.
     *
     * @param x The new x position of the object.
     * @param y The new y position of the object.
     *
     * @return Whether the object's new position is on a wall.
     */
    private boolean isOnWall(int x, int y) {
        if (game.getCellAt(x, y).getWall() != null) {
            // The top-left corner of the object has reached a wall.
            return true;
        }

        if (game.getCellAt(x, y + getHeight() - MARGIN).getWall() != null) {
            // The bottom-left corner of the object has reached a wall.
            return true;
        }

        if (game.getCellAt(x + getWidth() - MARGIN, y).getWall() != null) {
            // The top-right corner of the object has reached a wall.
            return true;
        }

        // The bottom-right corner of the object has reached a wall.
        return game.getCellAt(x + getWidth() - MARGIN, y + getHeight() - MARGIN).getWall() != null;
    }

    /**
     * Checks if this object is colliding with another object.
     * The collision is determined based on the bounding rectangles of both objects.
     * If either object has been consumed (i.e., is no longer active in the game),
     * the method returns {@code false} because no collision can occur.
     *
     * @param other The other object to check for collision.
     *
     * @return {@code true} if this object is colliding with the specified object,
     *         {@code false} if there is no collision or if either object has been consumed.
     */
    @Override
    public boolean isCollidingWith(IMovable other) {
        if (isConsumed() || other.isConsumed()) {
            // At least one of the two objects has already been consumed.
            // Therefore, there can be no collision.
            return false;
        }

        Rectangle rectangle = new Rectangle(getX(), getY(), getWidth(), getHeight());
        return rectangle.intersects(other.getX(), other.getY(), other.getWidth(), other.getHeight());
    }

    /**
     * Handles the interaction with a bonus that grants an additional bomb.
     *
     * @param other The movable entity that triggered the interaction.
     */
    @Override
    public void hitBonusAddBomb(IMovable other) {
        // No action is performed for this interaction.
    }

    /**
     * Handles the interaction with a bonus that grants invincibility.
     *
     * @param other The movable entity that triggered the interaction.
     */
    @Override
    public void hitBonusInvincible(IMovable other) {
        // No action is performed for this interaction.
    }

    /**
     * Handles the interaction with a bonus that grants an extra life.
     *
     * @param other The movable entity that triggered the interaction.
     */
    @Override
    public void hitBonusLife(IMovable other) {
        // No action is performed for this interaction.
    }

    /**
     * Method called when this enemy is hit by another enemy.
     * Currently, this method does nothing.
     */
    @Override
    public void hitEnemy() {
        // This method is empty because the enemy does not react when colliding with another enemy.
    }

    /**
     * This method is called when the bonus is supposed to explode.
     * so this method does nothing.
     */
    @Override
    public void explode() {
        // This method is empty because the bonus does not react when hitting an explosion.
    }

    /**
     * Returns the current instance of this object, which implements the {@link IMovable} interface.
     * This method is typically used when an object needs to refer to itself,
     * especially in contexts where the object is passed around as a reference.
     *
     * @return The current instance of this object implementing {@link IMovable}.
     */
    @Override
    public IMovable self() {
        return this;
    }

    /**
     * Returns a hash code value for the object. This implementation calls the
     * {@code hashCode()} method of the superclass to obtain the hash code.
     * <p>
     * Note that the hash code is used for object comparison, particularly
     * in hash-based collections such as {@link java.util.HashMap} or
     * {@link java.util.HashSet}.
     *
     * @return The hash code value for the object.
     */
    @Override
    public int hashCode() {
        return super.hashCode();
    }

    /**
     * Compares this object with the specified object for equality. This method checks
     * whether the two objects are identical, or if they represent the same "real object."
     * <p>
     * The comparison is performed as follows:
     * - If the given object is {@code null}, the objects are not equal.
     * - If the given object is the same as this object (same reference), they are equal.
     * - If the given object is an instance of {@link IMovable}, it compares the "real objects"
     *   by calling the {@link #self()} method to check if they represent the same entity.
     * - If the given object is not of a compatible class, the objects are not equal.
     *
     * @param obj The object to compare this object with.
     * @return {@code true} if the objects are equal, {@code false} otherwise.
     */
    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            // The two objects are definitely different.
            return false;
        }

        if (obj == this) {
            // The two objects are strictly identical.
            return true;
        }

        if (obj instanceof IMovable other) {
            // We compare the "real objects."
            return other.self() == self();
        }

        // The given object is not of a compatible class.
        return false;
    }
}
