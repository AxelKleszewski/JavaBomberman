package fr.univartois.butinfo.r304.bomberman.model.entity;

import fr.univartois.butinfo.r304.bomberman.Bomberman;
import fr.univartois.butinfo.r304.bomberman.model.BombermanGame;
import fr.univartois.butinfo.r304.bomberman.model.IMovable;
import fr.univartois.butinfo.r304.bomberman.model.bombs.*;
import fr.univartois.butinfo.r304.bomberman.model.movables.AbstractMovable;
import fr.univartois.butinfo.r304.bomberman.model.movables.IBomb;
import fr.univartois.butinfo.r304.bomberman.model.movables.IPlayer;
import fr.univartois.butinfo.r304.bomberman.view.Sprite;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;

import java.util.Objects;

import static fr.univartois.butinfo.r304.bomberman.model.BombermanGame.RAND;

/**
 * The {@code Player} class represents a player in the Bomberman game.
 * A player has a score, lives, and a list of bombs in their inventory.
 * The player can move and interact with other game entities.
 */
public class Player extends AbstractMovable {

    /**
     * The player's score.
     */
    private final IntegerProperty score;

    /**
     * The list of bombs the player has in their inventory.
     */
    private final ObservableList<Bomb> listBombs;

    /**
     * The player's state.
     */
    private IPlayer playerState;

    /**
     * The music of invincibility.
     */
    private static final Media MUSIC_INVINCIBILITY = new Media(Objects.requireNonNull(Bomberman.class.getResource("view/sounds/mario_kart_star.wav")).toString());

    /**
     * The music player.
     */
    public static final MediaPlayer INVINCIBILITY_PLAYER = new MediaPlayer(MUSIC_INVINCIBILITY);


    /**
     * Creates a new instance of {@code Player}.
     *
     * @param game       The game instance in which the player operates.
     * @param xPosition  The initial x-position of the player.
     * @param yPosition  The initial y-position of the player.
     * @param sprite     The {@link Sprite} representing the player visually.
     * @param score      The initial score of the player.
     */
    public Player(BombermanGame game, double xPosition, double yPosition, Sprite sprite, int score) {
        super(game, xPosition, yPosition, sprite);
        this.score = new SimpleIntegerProperty(score);
        listBombs = FXCollections.observableArrayList();
        playerState = new VulnerablePlayer();
    }

    /**
     * Returns the player's score.
     *
     * @return The score of the player.
     */
    public int getScore() {
        return score.get();
    }

    /**
     * The property binding the player's score.
     *
     * @return The property binding the player's score.
     */
    public IntegerProperty scoreProperty() {
        return score;
    }

    /**
     * Updates the player's score.
     *
     * @param score The new score of the player.
     */
    public void setScore(int score) {
        this.score.set(score);
    }

    /**
     * Provides the list of bombs the player has in their inventory.
     *
     * @return The list of bombs in the player's inventory.
     */
    public ObservableList<Bomb> getListBombs() {
        return listBombs;
    }

    /**
     * Returns the current state of the player.
     *
     * @return The player's state.
     */
    public IPlayer getPlayerState() {
        return playerState;
    }

    /**
     * Sets the current state of the player.
     *
     * @param playerState The new state of the player.
     */
    public void setPlayerState(IPlayer playerState) {
        this.playerState = playerState;
    }

    /**
     * Retrieves the current instance of the Bomberman game associated with this player.
     *
     * @return The {@code BombermanGame} instance that the player is participating in.
     */
    public BombermanGame getGame() {return game;}

    /**
     * Adds a bomb to the list of bombs the player has in their inventory.
     *
     * @param bomb The bomb to add.
     */
    public void addBombs(Bomb bomb) {
        listBombs.add(bomb);
    }

    /**
     * Removes a bomb from the list of bombs the player has in their inventory.
     *
     * @param bomb The bomb to remove.
     */
    public void removeBomb(Bomb bomb) {
        listBombs.remove(bomb);
    }

    /**
     * Moves the player based on the time elapsed.
     *
     * @param delta The time elapsed since the last update.
     * @return true if the move was successful, false otherwise.
     */
    @Override
    public boolean move(long delta) {
        playerState.removeInv(this);
        return super.move(delta);
    }

    /**
     * Handles collision with another movable object.
     *
     * @param other The movable object that collided with the player.
     */
    @Override
    public void collidedWith(IMovable other) { /* nothing */ }

    /**
     * Called when the player is hit by a bomb or explosion.
     * Decreases the player's lives by one.
     */
    @Override
    public void explode() {
        playerState.damaged(this);
    }

    /**
     * Called when the player is hit by an enemy.
     * Decreases the player's lives by one.
     */
    @Override
    public void hitEnemy() {
        playerState.damaged(this);
    }

    /**
     * Handles the event when the player hits a bonus that adds a bomb.
     * The method randomly selects a type of bomb (Large, Simple, Row, or Column) and adds it to the player's collection of bombs.
     * Afterward, the bonus is consumed, meaning it is no longer available in the game.
     *
     * @param other The {@code IMovable} instance representing the other object (the bonus) that triggered the event.
     */
    @Override
    public void hitBonusAddBomb(IMovable other) {
        IBomb iBomb;
        switch (RAND.nextInt(4)) {
            case 0 -> iBomb = new LargeBomb();
            case 1 -> iBomb = new SimpleBomb();
            case 2 -> iBomb = new RowBomb();
            default -> iBomb = new ColumnBomb();
        }
        this.addBombs(new Bomb(game, getX(), getY(), game.getSpriteStore().getSprite("bomb"), iBomb));
        other.consume();
    }

    /**
     * Handles the event when the player hits a bonus that grants invincibility.
     * This method applies the invincibility effect to the player by invoking the {@code bonusInv} method on the player's state.
     * Afterward, the bonus is consumed, meaning it is no longer available in the game.
     *
     * @param other The {@code IMovable} instance representing the other object (the invincibility bonus) that triggered the event.
     */
    @Override
    public void hitBonusInvincible(IMovable other) {
        playerState.bonusInv(this);
        INVINCIBILITY_PLAYER.play();
        other.consume();
    }

    /**
     * Handles the event when the player hits a bonus that increases their lives.
     * This method simply consumes the bonus, meaning the player gains the benefit of the bonus without any additional action taken.
     * The method can be extended later to add specific logic for handling life bonuses.
     *
     * @param other The {@code IMovable} instance representing the other object (the life bonus) that triggered the event.
     */
    @Override
    public void hitBonusLife(IMovable other) {
        other.consume();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        Player player = (Player) o;
        return Objects.equals(score, player.score) && Objects.equals(listBombs, player.listBombs) && Objects.equals(playerState, player.playerState);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), score, listBombs, playerState);
    }
}
