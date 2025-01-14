package fr.univartois.butinfo.r304.bomberman.model.entity;

import fr.univartois.butinfo.r304.bomberman.model.movables.IPlayer;

import static fr.univartois.butinfo.r304.bomberman.Bomberman.MUSIC_PLAYER;
import static fr.univartois.butinfo.r304.bomberman.model.entity.Player.INVINCIBILITY_PLAYER;

/**
 * The {@code InvulnerablePlayer} class represents the invulnerable state of a player in the Bomberman game.
 * While in this state, the player is immune to damage from enemies and explosions.
 */
public class InvulnerablePlayer implements IPlayer {

    /**
     * The timestamp when the invulnerability period started.
     */
    private final long time;

    /**
     * The duration of the invulnerability in milliseconds.
     */
    private final int invulnerableTimeMillis;

    /**
     * Constructs a new {@code InvulnerablePlayer} instance, setting the starting time for the invulnerability.
     */
    public InvulnerablePlayer(int invulnerableTimeMillis) {
        time = System.currentTimeMillis();
        this.invulnerableTimeMillis = invulnerableTimeMillis;
    }

    /**
     * Transitions the player from the invulnerable state to the vulnerable state.
     *
     * @return A new instance of {@code InvulnerablePlayer}, representing the player's vulnerable state.
     */
    @Override
    public IPlayer nextState() {
        return new VulnerablePlayer();
    }

    /**
     * Transitions the player from the vulnerable state to the invulnerable state.
     *
     * @return A new instance of {@code VulnerablePlayer}, representing the player's vulnerable state.
     */
    @Override
    public IPlayer nextState(int timeMillis) {
        return new InvulnerablePlayer(timeMillis);
    }

    /**
     * Handles the damaged event while the player is invulnerable.
     * This method does nothing as the player is immune to damage in this state.
     *
     * @param player The {@code Player} instance representing the player.
     */
    public void damaged(Player player) { /* nothing */}

    /**
     * Applies the invincibility bonus to the player. This method changes the player's sprite to indicate
     * that they are invincible (such as showing a "hurt" or special sprite) and transitions their state to
     * an invincible state, lasting for a specific period (10 seconds in this case).
     *
     * <p>This method is called when the player collects a bonus that grants them temporary invincibility,
     * which prevents them from taking damage during the invincibility period.</p>
     *
     * @param player The {@code Player} instance representing the player who will receive the invincibility bonus.
     */
    public void bonusInv(Player player) {
        player.setSprite(player.game.getSpriteStore().getSprite("guy-hurt"));
        player.setPlayerState(player.getPlayerState().nextState(10000));
        INVINCIBILITY_PLAYER.play();
    }

    /**
     * Removes the player's invulnerability if the invulnerability period has expired.
     * Once expired, the player's state is set to vulnerable, and their visual representation is updated.
     *
     * @param player The {@code Player} instance representing the player whose invulnerability is removed.
     */
    public void removeInv(Player player) {
        if (System.currentTimeMillis() >= invulnerableTimeMillis + time) {
            player.setSprite(player.game.getSpriteStore().getSprite("guy"));
            player.setPlayerState(nextState());
            INVINCIBILITY_PLAYER.stop();
            MUSIC_PLAYER.play();
        }
    }

    /**
     * Reduces the player's life count, though in this invulnerable state, the player cannot lose lives.
     *
     * @param player The {@code LivingPlayer} instance representing the player whose lives are decreased.
     */
    @Override
    public void decreaseLives(LivingPlayer player) {/* nothing */}

    /**
     * Increases the player's life count.
     * This method increments the life count of the {@code LivingPlayer} by 1.
     * In the invulnerable state, the player is allowed to increase their life count
     * without any restrictions.
     *
     * @param player The {@code LivingPlayer} instance whose life count will be increased.
     */
    @Override
    public void increaseLives(LivingPlayer player) {
        player.lives++;
    }
}
