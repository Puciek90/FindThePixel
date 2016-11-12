package findThePixel.mainView;

import findThePixel.picture.PictureDivider;
import findThePixel.threads.ThreadsRunner;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ColorPicker;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.paint.Color;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.io.File;
import java.io.IOException;
import java.util.concurrent.ExecutionException;

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
    private TextField textFieldMultiThreadPixels;

    @FXML
    private TextField textFieldMultiThreadTime;

    @FXML
    private TextField threadsNumber;

    @FXML
    public void loadPicture() throws IOException {
        final FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Open findThePixel.picture");
        fileChooser.getExtensionFilters().addAll(
                new FileChooser.ExtensionFilter("Image Files", "*.png", "*jpg"));
        File selectedFile = fileChooser.showOpenDialog(null);

        if (selectedFile != null) {
            Image image = new Image(selectedFile.toURI().toString());
            imageView.setImage(image);
        }
    }

    @FXML
    public void findPixelWithOneThread() throws ExecutionException, InterruptedException {
        Color colorPickerValue = colorPicker.getValue();
        Image image = imageView.getImage();

        ThreadsRunner threadsRunner = new ThreadsRunner(colorPickerValue, image, 1);

        double startTime = System.currentTimeMillis();

        int pixels = threadsRunner.runThreads();

        double endTime = System.currentTimeMillis();
        double elapsedTime = endTime - startTime;

        textFieldOneThreadTime.setText(String.valueOf(elapsedTime));
        textFieldOneThreadPixels.setText(String.valueOf(pixels));
    }

    @FXML
    public void  findPixelWithFourThreads() throws ExecutionException, InterruptedException {
        Color colorPickerValue = colorPicker.getValue();
        Image image = imageView.getImage();
        PictureDivider pictureDivider = new PictureDivider(image, 4);

        ThreadsRunner threadsRunner = new ThreadsRunner(colorPickerValue, pictureDivider.dividePicture(), 5);

        double startTime = System.currentTimeMillis();

        int pixels = threadsRunner.runThreads();

        double endTime = System.currentTimeMillis();
        double elapsedTime = endTime - startTime;

        textFieldMultiThreadTime.setText(String.valueOf(elapsedTime));
        textFieldMultiThreadPixels.setText(String.valueOf(pixels));

    }

    @FXML
    public void showAbout() throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("findThePixel/aboutView/aboutView.fxml"));
        Scene scene = new Scene(root);
        Stage stage = new Stage();
        stage.setScene(scene);
        stage.setTitle("About");
        stage.setResizable(false);
        stage.show();
    }

}
