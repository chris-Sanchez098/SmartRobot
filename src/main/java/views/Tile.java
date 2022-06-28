package views;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.StackPane;

import java.io.FileInputStream;
import java.io.FileNotFoundException;


public class Tile extends StackPane {

    private final Image premio1 = new Image(new FileInputStream("src/main/resources/images/premio1.png"));

    public Tile(Integer color) throws FileNotFoundException {
        ImageView imageView = new ImageView();
        switch (color) {
            case 0 -> {
                Image free = new Image(new FileInputStream("src/main/resources/images/free.png"));
                imageView.setImage(free);
            }
            case 1 -> {
                Image wall = new Image(new FileInputStream("src/main/resources/images/wall.png"));
                imageView.setImage(wall);
            }
            case 2 -> {
                Image bmo = new Image(new FileInputStream("src/main/resources/images/bmo.png"));
                imageView.setImage(bmo);
            }
            case 3 -> {
                Image ship = new Image(new FileInputStream("src/main/resources/images/ship.png"));
                imageView.setImage(ship);
            }
            case 4 -> {
                Image ship2 = new Image(new FileInputStream("src/main/resources/images/ship2.png"));
                imageView.setImage(ship2);
            }
            case 5 -> {
                Image premio2 = new Image(new FileInputStream("src/main/resources/images/premio2.png"));
                imageView.setImage(premio2);
            }
            case 6 -> {
                Image oil = new Image(new FileInputStream("src/main/resources/images/oil.png"));
                imageView.setImage(oil);
            }
        }
        getChildren().addAll(imageView);
    }
}
