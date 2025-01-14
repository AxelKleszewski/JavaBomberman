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

package fr.univartois.butinfo.r304.bomberman.model.map;

import java.util.ArrayList;
import java.util.List;

/**
 * The {@link GameMap} class represents the game map of Bomberman, on which
 * characters move and can place bombs.
 *
 * @author Romain Wallon
 *
 * @version 0.1.0
 */
public final class GameMap {

    /**
     * The number of rows of cells in this map.
     */
    private final int height;

    /**
     * The number of columns of cells in this map.
     */
    private final int width;

    /**
     * The cells that make up this map.
     */
    private final Cell[][] cells;

    /**
     * Constructs a new instance of GameMap.
     *
     * @param width The number of rows of cells in the map.
     * @param height The number of columns of cells in the map.
     */
    public GameMap(int height, int width) {
        this.width = width;
        this.height = height;
        this.cells = new Cell[height][width];
        init();
    }

    /**
     * Creates the cells that make up this map.
     */
    private void init() {
        for (int i = 0; i < height; i++) {
            for (int j = 0; j < width; j++) {
                cells[i][j] = new Cell(i, j);
            }
        }
    }

    /**
     * Gives the number of rows of cells in this map.
     *
     * @return The number of rows of cells in this map.
     */
    public int getHeight() {
        return height;
    }

    /**
     * Gives the number of columns of cells in this map.
     *
     * @return The number of columns of cells in this map.
     */
    public int getWidth() {
        return width;
    }

    /**
     * Returns the cell at the given position.
     *
     * @param row The row of the cell.
     * @param column The column of the cell.
     *
     * @return The cell at the given position.
     *
     * @throws IllegalArgumentException If the position is outside the map.
     */
    public Cell getAt(int row, int column) {
        if ((row < 0) || (height <= row) || (column < 0) || (width <= column)) {
            throw new IllegalArgumentException("Incorrect cell location!");
        }
        return cells[row][column];
    }

    /**
     * Modifies the cell at the given position.
     *
     * @param row The row of the cell.
     * @param column The column of the cell.
     * @param cell The cell to place at the given position.
     *
     * @throws IllegalArgumentException If the position is outside the map.
     */
    public void setAt(int row, int column, Cell cell) {
        if ((row < 0) || (height <= row) || (column < 0) || (width <= column)) {
            throw new IllegalArgumentException("Incorrect cell location!");
        }
        cells[row][column].replaceBy(cell);
    }

    /**
     * Returns the list of cells that are empty on this map.
     *
     * @return The list of empty cells.
     *
     * @see Cell#isEmpty()
     */
    public List<Cell> getEmptyCells() {
        List<Cell> emptyTiles = new ArrayList<>();

        for (int i = 0; i < height; i++) {
            for (int j = 0; j < width; j++) {
                if (cells[i][j].isEmpty()) {
                    emptyTiles.add(cells[i][j]);
                }
            }
        }
        return emptyTiles;
    }
}
