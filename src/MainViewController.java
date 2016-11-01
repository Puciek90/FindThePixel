import javafx.fxml.FXML;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.FileChooser;

import java.io.File;
import java.io.IOException;

public class MainViewController {
    @FXML
    private ImageView imageView;

    @FXML
    public void loadPicture() throws IOException {
        final FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Open picture");
        fileChooser.getExtensionFilters().addAll(
                new FileChooser.ExtensionFilter("Image Files", "*.png", "*jpg"));
        File selectedFile = fileChooser.showOpenDialog(null);
        Image image = new Image(selectedFile.toURI().toString());
        imageView.setImage(image);
    }
}
