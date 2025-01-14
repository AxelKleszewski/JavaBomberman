package fr.univartois.butinfo.r304.bomberman.model.entity;

import fr.univartois.butinfo.r304.bomberman.model.IMovable;
import fr.univartois.butinfo.r304.bomberman.view.Sprite;
import javafx.beans.property.BooleanProperty;
import javafx.beans.property.DoubleProperty;
import javafx.beans.property.ObjectProperty;

/**
 * The {@code LivingMovableDecorator} class is an abstract decorator class that extends the functionality of an {@code IMovable} object.
 * It adds life management to any {@code IMovable} object, such as a player or an enemy in the Bomberman game.
 * This class handles the lives of the decorated object and provides implementations for basic movement and collision functionality.
 */
public abstract class LivingMovableDecorator implements IMovable {

    /**
     * The decorated object of type {@code IMovable}, such as a player or an enemy.
     */
    protected final IMovable movable;

    /**
     * The number of lives of the decorated object.
     */
    protected int lives;

    /**
     * Constructor for the {@code LivingMovableDecorator} class.
     * This constructor initializes the decorated object with an {@code IMovable} object and an initial number of lives.
     *
     * @param movable The object to be decorated, implementing {@code IMovable} (e.g., a player or an enemy).
     * @param lives The initial number of lives of the decorated object.
     */
    protected LivingMovableDecorator(IMovable movable, int lives) {
        this.movable = movable;
        this.lives = lives;
    }

    /**
     * Decreases the number of lives of the decorated object.
     * This method also prints the remaining lives to the console for tracking.
     */
    public void decreaseLives() {
        this.lives--;
    }

    /**
     * Increases the number of lives of the decorated object.
     * This method also prints the remaining lives to the console for tracking.
     */
    public void increaseLives() {
        this.lives++;
    }

    /**
     * Retrieves the width of the decorated object.
     *
     * @return The width of the decorated object.
     */
    @Override
    public int getWidth() {
        return movable.getWidth();
    }

    /**
     * Retrieves the height of the decorated object.
     *
     * @return The height of the decorated object.
     */
    @Override
    public int getHeight() {
        return movable.getHeight();
    }

    /**
     * Sets the X position of the decorated object.
     *
     * @param xPosition The X position to set for the decorated object.
     */
    @Override
    public void setX(int xPosition) {
        movable.setX(xPosition);
    }

    /**
     * Retrieves the X position of the decorated object.
     *
     * @return The X position of the decorated object.
     */
    @Override
    public int getX() {
        return movable.getX();
    }

    /**
     * Retrieves the X position property of the decorated object.
     *
     * @return The X position property of the decorated object.
     */
    @Override
    public DoubleProperty getXProperty() {
        return movable.getXProperty();
    }

    /**
     * Sets the Y position of the decorated object.
     *
     * @param yPosition The Y position to set for the decorated object.
     */
    @Override
    public void setY(int yPosition) {
        movable.setY(yPosition);
    }

    /**
     * Retrieves the Y position of the decorated object.
     *
     * @return The Y position of the decorated object.
     */
    @Override
    public int getY() {
        return movable.getY();
    }

    /**
     * Retrieves the Y position property of the decorated object.
     *
     * @return The Y position property of the decorated object.
     */
    @Override
    public DoubleProperty getYProperty() {
        return movable.getYProperty();
    }

    /**
     * Consumes the decorated object. The consumption is delegated to the decorated object.
     */
    @Override
    public void consume() {
        movable.consume();
    }

    /**
     * Checks if the decorated object has been consumed.
     *
     * @return {@code true} if the decorated object has been consumed, otherwise {@code false}.
     */
    @Override
    public boolean isConsumed() {
        return movable.isConsumed();
    }

    /**
     * Retrieves the consumed property of the decorated object.
     *
     * @return The {@code isConsumed} property of the decorated object.
     */
    @Override
    public BooleanProperty isConsumedProperty() {
        return movable.isConsumedProperty();
    }


    /**
     * Sets the horizontal speed of the decorated object.
     *
     * @param speed The horizontal speed to set.
     */
    @Override
    public void setHorizontalSpeed(double speed) {
        movable.setHorizontalSpeed(speed);
    }

    /**
     * Retrieves the horizontal speed of the decorated object.
     *
     * @return The horizontal speed of the decorated object.
     */
    @Override
    public double getHorizontalSpeed() {
        return movable.getHorizontalSpeed();
    }

    /**
     * Sets the vertical speed of the decorated object.
     *
     * @param speed The vertical speed to set.
     */
    @Override
    public void setVerticalSpeed(double speed) {
        movable.setVerticalSpeed(speed);
    }

    /**
     * Retrieves the vertical speed of the decorated object.
     *
     * @return The vertical speed of the decorated object.
     */
    @Override
    public double getVerticalSpeed() {
        return movable.getVerticalSpeed();
    }

    /**
     * Sets the sprite for the decorated object.
     *
     * @param sprite The sprite to set for the decorated object.
     */
    @Override
    public void setSprite(Sprite sprite) {
        movable.setSprite(sprite);
    }

    /**
     * Retrieves the sprite of the decorated object.
     *
     * @return The sprite of the decorated object.
     */
    @Override
    public Sprite getSprite() {
        return movable.getSprite();
    }

    /**
     * Retrieves the sprite property of the decorated object.
     *
     * @return The sprite property of the decorated object.
     */
    @Override
    public ObjectProperty<Sprite> getSpriteProperty() {
        return movable.getSpriteProperty();
    }

    /**
     * Moves the decorated object based on the time delta.
     *
     * @param timeDelta The time delta for movement.
     * @return {@code true} if the movement was successful, otherwise {@code false}.
     */
    @Override
    public boolean move(long timeDelta) {
        return movable.move(timeDelta);
    }

    /**
     * Performs an action when the decorated object collides with an enemy.
     */
    @Override
    public void hitEnemy() {
        decreaseLives();
        movable.hitEnemy();
    }

    /**
     * Checks if the decorated object collides with another {@code IMovable} object.
     *
     * @param other The other {@code IMovable} object to check for collision.
     * @return {@code true} if the objects collide, otherwise {@code false}.
     */
    @Override
    public boolean isCollidingWith(IMovable other) {
        return movable.isCollidingWith(other);
    }

    /**
     * Performs the action when a collision with another {@code IMovable} object occurs.
     *
     * @param other The other {@code IMovable} object with which the collision occurs.
     */
    @Override
    public void collidedWith(IMovable other) {
        movable.collidedWith(other);
    }

    /**
     * Retrieves the current instance of the decorated object.
     *
     * @return The current instance of the decorated object.
     */
    @Override
    public IMovable self() {
        return this.movable.self();
    }

    /**
     * Handles the event when the player hits a bonus that adds a bomb.
     * This method delegates the action to the {@code movable} object, which processes the bonus.
     *
     * @param other The {@code IMovable} instance representing the other object that triggered the bonus event.
     */
    @Override
    public void hitBonusAddBomb(IMovable other) {
        movable.hitBonusAddBomb(other);
    }

    /**
     * Handles the event when the player hits a bonus that grants invincibility.
     * This method delegates the action to the {@code movable} object, which processes the bonus.
     *
     * @param other The {@code IMovable} instance representing the other object that triggered the bonus event.
     */
    @Override
    public void hitBonusInvincible(IMovable other) {
        movable.hitBonusInvincible(other);
    }

    /**
     * Handles the event when the player hits a bonus that increases their lives.
     * This method delegates the action to the {@code movable} object, which processes the bonus and
     * applies the life increase.
     *
     * @param other The {@code IMovable} instance representing the other object that triggered the bonus event.
     */
    @Override
    public void hitBonusLife(IMovable other) {
        movable.hitBonusLife(other);
    }
}
