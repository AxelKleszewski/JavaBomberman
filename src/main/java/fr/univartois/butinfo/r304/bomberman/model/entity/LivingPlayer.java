package fr.univartois.butinfo.r304.bomberman.model.entity;

import fr.univartois.butinfo.r304.bomberman.model.BombermanGame;
import fr.univartois.butinfo.r304.bomberman.model.IMovable;
import fr.univartois.butinfo.r304.bomberman.model.bombs.Bomb;
import fr.univartois.butinfo.r304.bomberman.view.Sprite;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.collections.ObservableList;

/**
 * The {@code LivingPlayer} class represents a living player entity in the Bomberman game.
 * This class decorates a {@code Player} instance and provides additional attributes and methods related to the player's
 * health, score, inventory, and interactions with other game objects.
 */
public class LivingPlayer extends LivingMovableDecorator {

    /**
     * The underlying {@code Player} instance representing the player's state and behavior.
     */
    private final Player player;

    /**
     * Property representing the player's lives, allowing for property binding.
     */
    private final IntegerProperty livesProperty;

    /**
     * Constructs a new {@code LivingPlayer} with the specified game context, position, sprite, score, and lives.
     *
     * @param game The current {@code BombermanGame} instance.
     * @param xPosition The initial x-position of the player.
     * @param yPosition The initial y-position of the player.
     * @param sprite The visual representation of the player.
     * @param score The initial score of the player.
     * @param lives The initial number of lives of the player.
     */
    public LivingPlayer(BombermanGame game, double xPosition, double yPosition, Sprite sprite, int score, int lives) {
        super(new Player(game, xPosition, yPosition, sprite, score), lives);
        this.player = (Player) movable;
        this.livesProperty = new SimpleIntegerProperty(lives);
    }

    /**
     * Returns the player's lives property for data binding.
     *
     * @return The {@code IntegerProperty} representing the player's lives.
     */
    public IntegerProperty getLivesProperty() {
        return livesProperty;
    }

    /**
     * Gets the underlying {@code Player} instance.
     *
     * @return The {@code Player} instance representing the player.
     */
    public Player getPlayer() {
        return player;
    }

    /**
     * Decreases the player's lives and checks if the player has died.
     */
    @Override
    public void decreaseLives() {
        player.getPlayerState().decreaseLives(this);
        this.livesProperty.set(lives);
        if (lives <= 0) player.getGame().playerIsDead();
    }

    /**
     * Increases the player's lives.
     */
    @Override
    public void increaseLives() {
        player.getPlayerState().increaseLives(this);
        this.livesProperty.set(lives);
    }

    /**
     * Gets the player's current score.
     *
     * @return The player's score.
     */
    public int getScore() {
        return player.getScore();
    }

    /**
     * Gets the player's score as a property for data binding.
     *
     * @return The {@code IntegerProperty} representing the player's score.
     */
    public IntegerProperty getScoreProperty() {
        return player.scoreProperty();
    }

    /**
     * Sets the player's score.
     *
     * @param score The new score of the player.
     */
    public void setScore(int score) {
        player.setScore(score);
    }

    /**
     * Adds a bomb to the player's inventory.
     *
     * @param bomb The {@code Bombe} to add.
     */
    public void addBombs(Bomb bomb) {
        player.addBombs(bomb);
    }

    /**
     * Removes a bomb from the player's inventory.
     *
     * @param bomb The {@code Bombe} to remove.
     */
    public void removeBomb(Bomb bomb) {
        player.removeBomb(bomb);
    }

    /**
     * Gets the list of bombs in the player's inventory.
     *
     * @return An {@code ObservableList<Bombe>} of the player's bombs.
     */
    public ObservableList<Bomb> getListBombs() {
        return player.getListBombs();
    }

    /**
     * Sets the player's horizontal speed.
     *
     * @param speed The new horizontal speed.
     */
    @Override
    public void setHorizontalSpeed(double speed) {
        player.setHorizontalSpeed(speed);
    }

    /**
     * Sets the player's vertical speed.
     *
     * @param speed The new vertical speed.
     */
    @Override
    public void setVerticalSpeed(double speed) {
        player.setVerticalSpeed(speed);
    }

    /**
     * Moves the player based on the elapsed time.
     *
     * @param delta The time since the last movement update.
     * @return {@code true} if the player moved successfully, {@code false} otherwise.
     */
    @Override
    public boolean move(long delta) {
        return player.move(delta);
    }

    /**
     * Handles the collision between the player and another movable object.
     *
     * @param other The {@code IMovable} object the player collided with.
     */
    @Override
    public void collidedWith(IMovable other) {
        player.collidedWith(other);
    }

    /**
     * Handles actions when the player is hit by an enemy or an explosion.
     */
    @Override
    public void hitEnemy() {
        decreaseLives();
        player.hitEnemy();
    }

    /**
     * Handles the interaction when the player hits a bonus that adds a bomb.
     * This method delegates the action to the player's {@code hitBonusAddBomb} method.
     *
     * @param other The {@code IMovable} instance representing the object that the player collides with (e.g., a bonus).
     */
    @Override
    public void hitBonusAddBomb(IMovable other) {
        player.hitBonusAddBomb(other);
    }

    /**
     * Handles the interaction when the player hits a bonus that grants invincibility.
     * This method delegates the action to the player's {@code hitBonusInvincible} method.
     *
     * @param other The {@code IMovable} instance representing the object that the player collides with (e.g., a bonus).
     */
    @Override
    public void hitBonusInvincible(IMovable other) {
        player.hitBonusInvincible(other);
    }

    /**
     * Handles the interaction when the player hits a bonus that increases their life.
     * This method increases the player's life by calling {@code increaseLives()} and then delegates the action to
     * the player's {@code hitBonusLife} method.
     *
     * @param other The {@code IMovable} instance representing the object that the player collides with (e.g., a bonus).
     */
    @Override
    public void hitBonusLife(IMovable other) {
        increaseLives();
        player.hitBonusLife(other);
    }

    /**
     * Handles actions when the player is caught in an explosion.
     */
    @Override
    public void explode() {
        decreaseLives();
        movable.explode();
    }
}
