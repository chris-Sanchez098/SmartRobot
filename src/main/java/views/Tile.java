package views;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.StackPane;

import java.util.Objects;


public class Tile extends StackPane {

    public Tile(Integer color) {
        ImageView imageView = new ImageView();
        switch (color) {
            case 0 -> {
                Image free = new Image(Objects.requireNonNull(getClass().getResourceAsStream("/images/free.png")));
                imageView.setImage(free);
            }
            case 1 -> {
                Image wall = new Image(Objects.requireNonNull(getClass().getResourceAsStream("/images/wall.png")));
                imageView.setImage(wall);
            }
            case 2 -> {
                Image bmo = new Image(Objects.requireNonNull(getClass().getResourceAsStream("/images/bmo.png")));
                imageView.setImage(bmo);
            }
            case 3 -> {
                Image ship = new Image(Objects.requireNonNull(getClass().getResourceAsStream("/images/ship.png")));
                imageView.setImage(ship);
            }
            case 4 -> {
                Image ship2 = new Image(Objects.requireNonNull(getClass().getResourceAsStream("/images/ship2.png")));
                imageView.setImage(ship2);
            }
            case 5 -> {
                Image reward = new Image(Objects.requireNonNull(getClass().getResourceAsStream("/images/reward.png")));
                imageView.setImage(reward);
            }
            case 6 -> {
                Image oil = new Image(Objects.requireNonNull(getClass().getResourceAsStream("/images/oil.png")));
                imageView.setImage(oil);
            }
        }
        getChildren().addAll(imageView);
    }
}
