package fr.univartois.butinfo.r304.bomberman.model.movables;

import fr.univartois.butinfo.r304.bomberman.model.entity.LivingPlayer;
import fr.univartois.butinfo.r304.bomberman.model.entity.Player;

/**
 * The {@code IPlayer} interface defines the contract for the states of players in the Bomberman game.
 * It includes methods for managing state transitions and actions on a player, such as taking damage,
 * removing invincibility, and decreasing lives.
 *
 * <p>Classes implementing this interface represent different possible states for a player,
 * providing flexibility to model state changes throughout the game.</p>
 */
public interface IPlayer {

    /**
     * Transitions the player to the next state. This method is used to handle state transitions
     * for a player, such as from a normal state to an invincible or damaged state.
     *
     * @return The next state of the player as an {@code IPlayer} instance.
     */
    IPlayer nextState();

    /**
     * Transitions the player to the next state. This method is used to handle state transitions
     * for a player, such as from a normal state to an invincible or damaged state.
     *
     * @param timeMillis The duration of the next state in milliseconds.
     * @return The next state of the player as an {@code IPlayer} instance.
     */
    IPlayer nextState(int timeMillis);

    /**
     * Applies damage to the player. This method should adjust the player's state
     * accordingly when they are hit or damaged during the game.
     *
     * @param player The {@code Player} instance representing the player to damage.
     */
    void damaged(Player player);

    /**
     * Removes the player's invincibility. This method is used to disable any
     * invincibility effect on the player, returning them to a normal state.
     *
     * @param player The {@code Player} instance representing the player whose invincibility is removed.
     */
    void removeInv(Player player);

    /**
     * Applies the invincibility bonus to the player. This method is used to make the player invincible
     * for a certain period, protecting them from taking damage during that time.
     *
     * @param player The {@code Player} instance representing the player who receives the invincibility bonus.
     */
    void bonusInv(Player player);

    /**
     * Decreases the player's life count. This method reduces the player's remaining lives,
     * potentially leading to a game-over state if their lives reach zero.
     *
     * @param player The {@code LivingPlayer} instance representing the player whose lives are decreased.
     */
    void decreaseLives(LivingPlayer player);

    /**
     * Increases the player's life count. This method is used when the player collects a bonus that increases
     * their lives, giving them additional chances to continue in the game.
     *
     * @param player The {@code LivingPlayer} instance representing the player whose lives are increased.
     */
    void increaseLives(LivingPlayer player);
}