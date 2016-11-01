import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ColorPicker;
import javafx.scene.control.TextField;
import javafx.scene.image.*;
import javafx.scene.paint.Color;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.io.File;
import java.io.IOException;
import java.util.Objects;

public class MainViewController {
    @FXML
    private ImageView imageView;

    @FXML
    private ColorPicker colorPicker;

    @FXML
    private TextField textFieldOneThreadPixels;

    @FXML
    private TextField textFieldOneThreadTime;

    @FXML
    public void loadPicture() throws IOException {
        final FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Open picture");
        fileChooser.getExtensionFilters().addAll(
                new FileChooser.ExtensionFilter("Image Files", "*.png", "*jpg"));
        File selectedFile = fileChooser.showOpenDialog(null);

        if (selectedFile != null) {
            Image image = new Image(selectedFile.toURI().toString());
            imageView.setImage(image);
        }
    }

    @FXML
    public void findPixelWithOneThread() {
        int pixelCounter = 0;
        System.out.println("One thread here!");

        Image image = imageView.getImage();
        Color colorPickerValue = colorPicker.getValue();

        PixelReader pixelReader = image.getPixelReader();

        int widith = (int) image.getWidth();
        int height = (int) image.getHeight();

        WritableImage writableImage = new WritableImage(widith, height);
        PixelWriter pixelWriter = writableImage.getPixelWriter();

        double startTime = System.currentTimeMillis();

        for (int y = 0; y < height; y++) {
            for (int x = 0; x < widith; x++) {
                Color color = pixelReader.getColor(x,y);
                if (Objects.equals(colorPickerValue, color)) {
                    pixelCounter++;
                }
            }
        }

        double endTime = System.currentTimeMillis();
        double elapsedTime = endTime - startTime;

        textFieldOneThreadPixels.setText(String.valueOf(pixelCounter));
        textFieldOneThreadTime.setText(String.valueOf(elapsedTime));

        System.out.println(pixelCounter);
        System.out.println(elapsedTime + " ms");

    }

    @FXML
    public void  findPixelWithFourThreads() {
        System.out.println("Four threads here!");
    }

    @FXML
    public void showAbout() throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("helpView.fxml"));
        Scene scene = new Scene(root);
        Stage stage = new Stage();
        stage.setScene(scene);
        stage.setTitle("About");
        stage.setResizable(false);
        stage.show();
    }

}
