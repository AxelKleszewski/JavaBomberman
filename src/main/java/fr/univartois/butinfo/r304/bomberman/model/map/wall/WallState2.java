package fr.univartois.butinfo.r304.bomberman.model.map.wall;

import fr.univartois.butinfo.r304.bomberman.model.map.IStateWall;
import fr.univartois.butinfo.r304.bomberman.view.Sprite;
import fr.univartois.butinfo.r304.bomberman.view.SpriteStore;

/**
 * The {@code WallState2} class represents the second and final state of a destructible wall
 * in the Bomberman game. In this state, the wall appears cracked, indicating it is about to be destroyed.
 * Once damaged further, the wall is removed from the game, as indicated by the {@code null} next state.
 */
public class WallState2 implements IStateWall {

    /**
     * Sets the next state of the wall.
     *
     * @return The next state of the wall.
     */
    @Override
    public IStateWall nextState() {
        return null;
    }

    /**
     * Returns the sprite associated with the current state of the wall.
     *
     * @return The sprite associated with the current state of the wall.
     */
    @Override
    public Sprite getSprite() {
        return SpriteStore.getInstance().getSprite("cracked-bricks");
    }
}
