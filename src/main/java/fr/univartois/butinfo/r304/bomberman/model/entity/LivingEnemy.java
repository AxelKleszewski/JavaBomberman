package fr.univartois.butinfo.r304.bomberman.model.entity;

import fr.univartois.butinfo.r304.bomberman.model.BombermanGame;
import fr.univartois.butinfo.r304.bomberman.model.IMovable;
import fr.univartois.butinfo.r304.bomberman.view.Sprite;

/**
 * The {@code LivingEnemy} class represents an enemy in the Bomberman game that has a limited number of lives.
 * This class is a decorator for the {@code Enemy} class, adding functionality to manage the health of the enemy,
 * as well as handling interactions with explosions or other damaging events.
 * <p>
 * A {@code LivingEnemy} can be damaged by explosions, losing lives and eventually being destroyed when all lives are lost.
 * This class also incorporates an invulnerability period after the enemy is hit, during which it cannot be damaged again.
 */
public class LivingEnemy extends LivingMovableDecorator {

    /**
     * Constant defining the invulnerability period in milliseconds.
     */
    private static final int INVULNERABLE_TIME_MILLIS = 1500;

    /**
     * Timestamp for the last time the enemy was hit (in milliseconds).
     */
    private long timeLastHit;

    /**
     * Constructs a {@code LivingEnemy} with the specified game context, position, sprite, and life count.
     * This constructor initializes the enemy with the specified attributes and sets up an invulnerability timer.
     *
     * @param game The current {@code BombermanGame} instance representing the game state.
     * @param xPosition The initial x-position of the enemy on the game grid.
     * @param yPosition The initial y-position of the enemy on the game grid.
     * @param sprite The visual representation of the enemy, used for rendering.
     * @param life The initial number of lives of the enemy.
     */
    public LivingEnemy(BombermanGame game, double xPosition, double yPosition, Sprite sprite, int life) {
        super(new Enemy(game, xPosition, yPosition, sprite), life);
        this.timeLastHit = 0;
    }

    /**
     * Retrieves the {@code Enemy} object associated with this instance.
     * This method casts the `movable` object to an {@code Enemy} type, assuming that the
     * `movable` instance is indeed of type {@code Enemy}.
     *
     * @return The {@code Enemy} object associated with this instance.
     */
    public Enemy getEnemy() {
        return (Enemy) movable;
    }

    /**
     * Decreases the enemy's lives if it is not currently in the invulnerable state.
     * The invulnerability state is checked by ensuring the elapsed time since the last hit
     * is greater than or equal to the invulnerability period.
     * <p>
     * If the enemy is not invulnerable, its lives are decreased, and the time of the last hit is updated.
     */
    @Override
    public void decreaseLives() {
        if (System.currentTimeMillis() - timeLastHit >= INVULNERABLE_TIME_MILLIS) {
            super.decreaseLives();
            timeLastHit = System.currentTimeMillis();
        }
    }

    /**
     * Increases the number of lives for the entity.
     * Currently, this method has no implementation.
     */
    @Override
    public void increaseLives() {
        // No action is performed for this method.
    }

    /**
     * Handles the enemy being caught in an explosion. This decreases the enemy's lives, and if the enemy's
     * lives drop to zero or below, the enemy is destroyed by triggering its {@code explode()} method.
     */
    @Override
    public void explode() {
        decreaseLives();
        if (lives <= 0) {
            movable.explode();
        }
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

    /**
     * Handles the interaction with a bonus that grants an extra life.
     * Currently, this method has no implementation.
     *
     * @param other The movable entity that triggered the interaction.
     */
    @Override
    public void hitBonusLife(IMovable other) {
        // No action is performed for this interaction.
    }
}
