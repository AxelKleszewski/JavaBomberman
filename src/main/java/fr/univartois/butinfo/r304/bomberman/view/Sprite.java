/**
 * This software is distributed for educational purposes.
 * <p>
 * It is provided "as is", without any kind of warranty, either express or implied,
 * including but not limited to implied warranties of merchantability, fitness for a
 * particular purpose, and non-infringement.
 * In no event shall the authors or copyright holders be liable for any damage, claim,
 * or other liability, whether in an action of contract, tort, or otherwise, arising from,
 * out of, or in connection with the software or the use or other dealings in the software.
 * <p>
 * (c) 2022-2024 Romain Wallon - Université d'Artois.
 * All rights reserved.
 */

package fr.univartois.butinfo.r304.bomberman.view;

import javafx.scene.image.Image;

/**
 * The {@link Sprite} class represents a graphical element of the game.
 * It is an object that encapsulates an image without internal state and can be placed at
 * a specific location on the window.
 * This way, the same {@link Sprite} instance can be used to represent multiple similar
 * elements at the same time.
 *
 * @param image The image associated with this instance of {@link Sprite}.
 * @author Romain Wallon
 * @version 0.1.0
 */
public record Sprite(Image image) {

    /**
     * Returns the width of the associated image, measured in pixels.
     *
     * @return The width of the associated image.
     */
    public int getWidth() {
        return (int) image.getWidth();
    }

    /**
     * Returns the height of the associated image, measured in pixels.
     *
     * @return The height of the associated image.
     */
    public int getHeight() {
        return (int) image.getHeight();
    }

    /**
     * Returns the image associated with this instance of {@link Sprite}.
     *
     * @return The image associated with this instance of {@link Sprite}.
     */
    public Image getImage() {
        return image;
    }

}
