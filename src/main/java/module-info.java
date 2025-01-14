/**
 * Le paquetage {@code fr.univartois.butinfo.r304.bomberman} fournit une implantation en
 * JavaFX du jeu Bomberman.
 *
 * @author Romain Wallon
 *
 * @version 0.1.0
 */

module fr.univartois.butinfo.r304.bomberman {
    exports fr.univartois.butinfo.r304.bomberman;

    opens fr.univartois.butinfo.r304.bomberman.controller to javafx.fxml;

    requires javafx.fxml;
    requires javafx.graphics;
    requires javafx.media;
    requires transitive javafx.controls;
}
