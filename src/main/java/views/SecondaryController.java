package views;

import java.io.IOException;
import javafx.fxml.FXML;
import smartRobot.App;

public class SecondaryController {

    @FXML
    private void switchToPrimary() throws IOException {
        App.setRoot("primary");
    }
}