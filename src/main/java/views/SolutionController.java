package views;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.layout.Pane;
import java.util.ArrayList;

public class SolutionController {
    @FXML
    private Pane paneSolution;
    @FXML
    private Label lDeep;
    @FXML
    private Label lCost;
    @FXML
    private Label lTime;
    @FXML
    private Label lNodos;
    @FXML
    private TextArea textArea;

    public void loadMap(ArrayList<Tile> tiles) {
        for (int i = 0; i < tiles.size(); i++) {
            Tile tile = tiles.get(i);
            tile.setTranslateX(50 * (i % 10));
            tile.setTranslateY(50 * (i / 10));
            paneSolution.getChildren().add(tile);
        }
    }

    public void updateLabel(String deep, String cost, String time, String path, String nodos) {
        lDeep.setText(deep);
        lCost.setText(cost);
        lTime.setText(time);
        lNodos.setText(nodos);
        textArea.setText(path);
        textArea.setWrapText(true);
    }

}
