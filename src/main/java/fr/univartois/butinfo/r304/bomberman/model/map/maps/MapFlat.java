package fr.univartois.butinfo.r304.bomberman.model.map.maps;

import fr.univartois.butinfo.r304.bomberman.model.map.Cell;
import fr.univartois.butinfo.r304.bomberman.model.map.GameMap;
import fr.univartois.butinfo.r304.bomberman.model.map.IMap;
import fr.univartois.butinfo.r304.bomberman.model.map.IStateWall;
import fr.univartois.butinfo.r304.bomberman.model.map.wall.Wall;
import fr.univartois.butinfo.r304.bomberman.model.map.wall.WallState1;
import fr.univartois.butinfo.r304.bomberman.model.map.wall.WallState2;
import fr.univartois.butinfo.r304.bomberman.view.SpriteStore;

import static fr.univartois.butinfo.r304.bomberman.model.BombermanGame.RAND;


public class MapFlat implements IMap {
    /**
     * chance to place a brick on lawn
     */
    public static final double CHANCE_TO_BE_BRICK = 0.2;

    /**
     * Initialises the given GameMap with the required map elements.
     * Here the layer of the map is the classic one
     *
     *
     * @param gameMap The GameMap to be initialised.
     */
    public void initializeMap(GameMap gameMap) {
        IStateWall wall1 = new WallState1();
        IStateWall wall2 = new WallState2();
        new MapGenerator(gameMap);
        int height = gameMap.getHeight();
        int width = gameMap.getWidth();

        // add breakable wall
        for (int i = 0; i < height; i++) {
            for (int j = 0; j < width; j++) {
                if (RAND.nextDouble() < CHANCE_TO_BE_BRICK && gameMap.getAt(i, j).getSprite().equals(SpriteStore.getInstance().getSprite("lawn"))) {
                    if (RAND.nextDouble() < 0.1) {
                        gameMap.setAt(i, j, new Cell(new Wall(wall2), i, j));
                    } else {
                        gameMap.setAt(i, j, new Cell(new Wall(wall1), i, j));
                    }
                }
            }
        }
    }



}
