package fr.univartois.butinfo.r304.bomberman.model.map.wall;

import fr.univartois.butinfo.r304.bomberman.model.map.IStateWall;
import fr.univartois.butinfo.r304.bomberman.view.Sprite;
import fr.univartois.butinfo.r304.bomberman.view.SpriteStore;

/**
 * The {@code WallState1} class represents the initial state of a destructible wall
 * in the Bomberman game. This state corresponds to the first visual representation of the wall.
 * As the wall is damaged, it transitions to the next state, represented by {@link WallState2}.
 */
public class WallState1 implements IStateWall {

    /**
     * Sets the next state of the wall.
     *
     * @return The next state of the wall.
     */
    @Override
    public IStateWall nextState() {
        return new WallState2();
    }

    /**
     * Returns the sprite associated with the current state of the wall.
     *
     * @return The sprite associated with the current state of the wall.
     */
    @Override
    public Sprite getSprite() {
        return SpriteStore.getInstance().getSprite("bricks");
    }
}
