package findThePixel.picture;// Created by Mateusz Płuciennik on 08.11.16.


import javafx.scene.image.Image;
import javafx.scene.image.PixelReader;
import javafx.scene.paint.Color;

import java.util.Objects;
import java.util.concurrent.Callable;

public class PixelFinder implements Callable {

    private Color searchedColor;
    private Image image;
    private int pixelCounter;

    public PixelFinder(Color searchedColor, Image image) {
        this.searchedColor = searchedColor;
        this.image = image;
    }

    @Override
    public Integer call() throws Exception {

        int width = (int) image.getWidth();
        int height = (int) image.getHeight();
        PixelReader pixelReader = image.getPixelReader();

        for (int y = 0; y < height; y++) {
            for (int x = 0; x < width; x++) {
                if (Objects.equals(searchedColor, pixelReader.getColor(x, y))) {
                    pixelCounter++;
                }
            }
        }

        return pixelCounter;
    }
}
