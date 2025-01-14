package fr.univartois.butinfo.r304.bomberman.model.entity;

import fr.univartois.butinfo.r304.bomberman.model.movables.IPlayer;

import static fr.univartois.butinfo.r304.bomberman.Bomberman.MUSIC_PLAYER;
import static fr.univartois.butinfo.r304.bomberman.model.entity.Player.INVINCIBILITY_PLAYER;

/**
 * The {@code VulnerablePlayer} class represents the vulnerable state of a player in the Bomberman game.
 * In this state, the player can be harmed by enemies and explosions.
 */
public class VulnerablePlayer implements IPlayer {

    /**
     * Transitions the player from the vulnerable state to the invulnerable state.
     *
     * @return A new instance of {@code VulnerablePlayer}, representing the player's invulnerable state.
     */
    @Override
    public IPlayer nextState() {
        return null;
    }

    @Override
    public IPlayer nextState(int timeMillis) {
        return new InvulnerablePlayer(timeMillis);
    }

    /**
     * Inflicts damage on the player by changing their sprite to indicate injury and transitioning
     * the player to the next state.
     *
     * @param player The {@code Player} instance representing the player to be damaged.
     */
    public void damaged(Player player) {
        player.setSprite(player.game.getSpriteStore().getSprite("guy-hurt"));
        player.setPlayerState(player.getPlayerState().nextState(2000));
    }

    public void bonusInv(Player player) {
        player.setSprite(player.game.getSpriteStore().getSprite("guy-hurt"));
        player.setPlayerState(player.getPlayerState().nextState(10000));
        INVINCIBILITY_PLAYER.play();
        MUSIC_PLAYER.stop();
    }

    /**
     * No operation for removing invulnerability in this state, as the player is already vulnerable.
     *
     * @param player The {@code Player} instance representing the player.
     */
    public void removeInv(Player player) {
        MUSIC_PLAYER.play();
    }

    /**
     * Decreases the number of lives of the player.
     *
     * @param player The {@code LivingPlayer} instance representing the player whose lives are decreased.
     */
    @Override
    public void decreaseLives(LivingPlayer player) {
        player.lives--;
    }

    /**
     * Increases the number of lives of the player.
     *
     * @param player The {@code LivingPlayer} instance representing the player whose lives are increased.
     */
    @Override
    public void increaseLives(LivingPlayer player) {
        player.lives++;
    }
}
