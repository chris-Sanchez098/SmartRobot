package views;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.layout.Pane;

import java.io.FileNotFoundException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class SolutionController {
    @FXML
    private Pane paneSolution;
    private ArrayList<Tile> tiles;

    public void loadMap(ArrayList<Tile> tiles) {
        for (int i = 0; i < tiles.size(); i++) {
            Tile tile = tiles.get(i);
            tile.setTranslateX(50 * (i % 10));
            tile.setTranslateY(50 * (i / 10));
            paneSolution.getChildren().add(tile);
        }
    }

}
