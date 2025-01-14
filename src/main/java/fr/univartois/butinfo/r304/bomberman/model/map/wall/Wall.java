/**
 * This software is distributed for educational purposes.
 * <p>
 * It is provided "as is", without any warranty of any kind, express
 * or implied, including but not limited to warranties of merchantability,
 * fitness for a particular purpose, and non-infringement.
 * Under no circumstances shall the authors or copyright holders be liable
 * for any damage, claim, or other liability, whether in an action of contract,
 * tort, or otherwise, arising from, out of, or in connection with the software
 * or its use, or with other elements of the software.
 * <p>
 * (c) 2022-2024 Romain Wallon - Université d'Artois.
 * All rights reserved.
 */

package fr.univartois.butinfo.r304.bomberman.model.map.wall;

import fr.univartois.butinfo.r304.bomberman.model.map.IStateWall;
import fr.univartois.butinfo.r304.bomberman.view.Sprite;
import fr.univartois.butinfo.r304.bomberman.view.SpriteStore;

/**
 * The {@link Wall} class represents a brick wall on the game map.
 * It is an element that cannot be traversed but can be destroyed by an explosion.
 *
 * @author Romain Wallon
 *
 * @version 0.1.0
 */
public final class Wall {

    /**
     * The interface representing the current state of the wall.
     */
    private IStateWall stateWall;

    /**
     * Creates a new instance of Wall with a specific sprite for an indestructible wall.
     *
     * @param state The State of the creater wall.
     */
    public Wall(IStateWall state) {
        this.stateWall = state; // No state for an indestructible wall
    }

    /**
     * Returns the sprite representing this wall on the map.
     *
     * @return The sprite representing this wall on the map.
     */
    public Sprite getSprite() {
        if (stateWall != null) {
            return stateWall.getSprite();
        }
        return SpriteStore.getInstance().getSprite("wall"); // Sprite of an indestructible wall
    }

    /**
     * Makes the wall explode, transitioning it to the next state.
     */
    public void explode() {
        if (stateWall != null) {
            this.stateWall = stateWall.nextState();
        }
    }

    /**
     * Checks whether the wall is still present or not.
     * @return true if the wall is still present (state is not null), false otherwise.
     */
    public boolean isDestroyed() {
        return stateWall == null;
    }
}
