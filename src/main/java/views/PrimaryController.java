package views;

import java.io.IOException;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import smartRobot.App;

public class PrimaryController {
    @FXML
    private Button button;
    @FXML
    private ImageView imgView;
    @FXML
    private void switchToSecondary() throws IOException {
        App.setRoot("secondary");
    }

}
