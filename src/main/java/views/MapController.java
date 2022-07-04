package views;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.layout.Pane;
import javafx.stage.FileChooser;
import javafx.stage.Modality;
import javafx.stage.Stage;
import model.*;

import java.io.File;
import java.io.FileNotFoundException;
import java.net.URL;
import java.util.*;

public class MapController implements Initializable {
    @FXML
    private Pane pane;
    @FXML
    private ComboBox<String> cbSelect;
    @FXML
    private Button bStart;
    private Integer[][] map;
    private final Integer[] place = new Integer[2];

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        initCB();
    }

    /**
     * Read file and insert number in matriz integer[10][10]
     */
    public void loadMatriz() {
        File file = selectFile();
        Integer[][] matriz = new Integer[10][10];
        try {
            Scanner sc = new Scanner(file);
            while (sc.hasNext()) {
                for (int i = 0; i < 10; i++) {
                    for (int j = 0; j < 10; j++) {
                        int temp = Integer.parseInt(sc.next());
                        if (temp == 2) {
                            place[0] = i;
                            place[1] = j;
                        }
                        matriz[i][j] = temp;
                    }
                }
            }
        } catch (FileNotFoundException e) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setHeaderText(null);
            alert.setTitle("Select correct file");
            alert.setContentText("Please verify that you have selected an correct file. ");
            alert.showAndWait();
        }
        map = matriz;
    }

    /**
     * Convert Integer[][] to ArrayList<Tile>
     * @param map Integer[][]
     * @return ArrayList<Tile>
     * @throws FileNotFoundException exception
     */
    public ArrayList<Tile> loadTile(Integer[][] map) throws FileNotFoundException {
        ArrayList<Tile> temp = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            for (int j = 0; j < 10; j++) {
                Integer item = map[i][j];
                temp.add(new Tile(item));
            }
        }
        return temp;
    }

    /**
     * Update map
     * @throws FileNotFoundException exception
     */
    public void updateMap() throws FileNotFoundException {
        loadMatriz();
        ArrayList<Tile> tiles = loadTile(map);
        for (int i = 0; i < tiles.size(); i++) {
            Tile tile = tiles.get(i);
            tile.setTranslateX(50 * (i % 10));
            tile.setTranslateY(50 * (i / 10));
            pane.getChildren().add(tile);
        }
    }

    /**
     * Open file chooser
     */
    public File selectFile() {
        File file;
        Stage stage = new Stage();
        FileChooser fileChooser = new FileChooser();
        stage.setTitle("Select map file");
        file = fileChooser.showOpenDialog(stage);
        return file;
    }

    /**
     * Init values of combobox
     */
    public void initCB() {
        ObservableList<String> option =
                FXCollections.observableArrayList("Selection", "Amplitud", "Costo uniforme",
                        "Profundidad evitando ciclos","Avara","A*");
        cbSelect.setItems(option);
        cbSelect.getSelectionModel().selectFirst();
    }

    public ArrayList<Object> solution() {
        Node node;
        ArrayList<Object> temp = new ArrayList<>();
        int selection = cbSelect.getSelectionModel().getSelectedIndex();
        switch (selection){
            case 0 -> {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setHeaderText(null);
                alert.setTitle("Select algorithm");
                alert.setContentText("Please verify that you have selected an algorithm. ");
                alert.showAndWait();
            }
            case 1 -> {
                Breadth breadth = new Breadth(map);
                node = breadth.getSolution();
                temp.add(node);
                temp.add("Meta - Amplitud");
                return temp;
            }
            case 2 -> {
                Cost cost = new Cost(map);
                node = cost.getSolution();
                temp.add(node);
                temp.add("Meta - Costo");
                return temp;
            }
            case 3 -> {
                Deep deep = new Deep(map);
                node = deep.getSolution();
                temp.add(node);
                temp.add("Meta - Profundidad evitando ciclos");
                return temp;
            }
            case 4 -> {
                Greedy greedy = new Greedy(map);
                node = greedy.getSolution();
                temp.add(node);
                temp.add("Meta - Avara");
                return temp;
            }
            case 5 -> {
                AStart aStart = new AStart(map);
                node = aStart.getSolution();
                temp.add(node);
                temp.add("Meta - A*");
                return temp;
            }
        }
        return null;
    }


    /**
     *
     * @param node goal node
     * @return String with all steps to reach the goal
     */
    public String steps(Node node) {
        StringBuilder out = new StringBuilder();
        ArrayList<Integer[]> arrPlaces = new ArrayList<>();
        arrPlaces.add(node.getPlace());
        while (node.getParent() != null) {
            arrPlaces.add(node.getParent().getPlace());
            node = node.getParent();
        }
        Collections.reverse(arrPlaces);
        Integer[] temPlace = place;
        for (Integer[] step : arrPlaces) {
            if((Objects.equals(temPlace[0] , step[0])) && (Objects.equals(temPlace[1], step[1]))) {
                out.append("Inicio casilla ").append(temPlace[0]).append(" ").append(temPlace[1]).append("\n");
            }
            if (temPlace[0] + 1 == step[0]) { // down
                out.append("Down ");
                temPlace = step;
            }
            if (temPlace[0] - 1 == step[0]) { // up
                out.append("Up ");
                temPlace = step;
            }
            if (temPlace[1] + 1 == step[1]) { // right
                out.append("Right ");
                temPlace = step;
            }
            if (temPlace[1] - 1 == step[1]) { // left
                out.append("Left ");
                temPlace = step;
            }
        }
        return out.toString();
    }

    @FXML
    public void onClick(ActionEvent event) throws FileNotFoundException {
        ArrayList<Object> temp = solution();
        Node node = (Node) temp.get(0);
        Integer deep = node.getDeep();
        Integer cost = node.getCost();
        Double time = node.getTime();
        String steps = steps(node);
        Integer nodes = node.getNodes();
        String title = temp.get(1).toString();
        Integer[][] mapMeta = node.getMap();
        if (event.getSource() == bStart && temp != null) {
            ArrayList<Tile> tiles = loadTile(mapMeta);
            try {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("/views/solution.fxml"));
                Parent root = loader.load();
                SolutionController solutionController = loader.getController();
                solutionController.loadMap(tiles);
                solutionController.updateLabel(deep + "", cost + "", time + " seg",steps, nodes+"");
                Scene scene = new Scene(root);
                Stage stage = new Stage();
                stage.setTitle(title);
                stage.initModality(Modality.APPLICATION_MODAL);
                stage.setScene(scene);
                stage.setResizable(false);
                stage.showAndWait();
            } catch (Exception e) {
                e.printStackTrace();
            }
        } else {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setHeaderText(null);
            alert.setTitle("Error");
            alert.setContentText("No solution was found with the algorithm used " +
                    "or verify that you have selected an algorithm. ");
            alert.showAndWait();
        }
    }
    public void onClickChange() throws FileNotFoundException {
        updateMap();
    }
}