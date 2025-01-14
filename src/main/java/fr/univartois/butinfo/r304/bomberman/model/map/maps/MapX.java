package fr.univartois.butinfo.r304.bomberman.model.map.maps;

import fr.univartois.butinfo.r304.bomberman.model.map.Cell;
import fr.univartois.butinfo.r304.bomberman.model.map.GameMap;
import fr.univartois.butinfo.r304.bomberman.model.map.IMap;
import fr.univartois.butinfo.r304.bomberman.model.map.IStateWall;
import fr.univartois.butinfo.r304.bomberman.model.map.wall.UnbreakableWall;
import fr.univartois.butinfo.r304.bomberman.model.map.wall.Wall;
import fr.univartois.butinfo.r304.bomberman.model.map.wall.WallState1;
import fr.univartois.butinfo.r304.bomberman.model.map.wall.WallState2;
import fr.univartois.butinfo.r304.bomberman.view.Sprite;
import fr.univartois.butinfo.r304.bomberman.view.SpriteStore;

import static fr.univartois.butinfo.r304.bomberman.model.BombermanGame.RAND;

public class MapX implements IMap {

    /**
     * Chance to place a brick on the lawn.
     */
    public static final double CHANCE_TO_BE_BRICK = 0.35;

    /**
     * Initializes the given GameMap with the required map elements.
     * The map layer here includes a central cross of indestructible walls and random breakable bricks.
     *
     * @param gameMap The GameMap to be initialized.
     */
    public void initializeMap(GameMap gameMap) {
        IStateWall wall1 = new WallState1();
        IStateWall wall2 = new WallState2();
        Sprite wall = SpriteStore.getInstance().getSprite("wall");

        new MapGenerator(gameMap);
        int height = gameMap.getHeight();
        int width = gameMap.getWidth();

        // Create a cross-shaped arrangement of indestructible walls in the middle of the map
        for (int i = 6; i < height - 6; i++) {
            gameMap.setAt(i, width / 2, new Cell(new Wall(new UnbreakableWall(wall))));
        }
        for (int j = 6; j < width - 6; j++) {
            gameMap.setAt(height / 2, j, new Cell(new Wall(new UnbreakableWall(wall))));
        }

        // Randomly add breakable bricks on the lawn
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