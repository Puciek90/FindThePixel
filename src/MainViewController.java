import javafx.fxml.FXML;
import javafx.stage.FileChooser;

import java.io.File;

public class MainViewController {
    @FXML
    public void loadPicture() {
        final FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Open picture");
        fileChooser.getExtensionFilters().addAll(
                new FileChooser.ExtensionFilter("Image Files", "*.png", "*jpg"));
        File selectedFile = fileChooser.showOpenDialog(null);
    }
}
