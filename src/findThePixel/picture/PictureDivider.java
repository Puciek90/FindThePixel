package findThePixel.picture;// Created by Mateusz Płuciennik on 11.11.16.

import javafx.scene.image.Image;
import javafx.scene.image.WritableImage;

import java.util.ArrayList;
import java.util.List;

public class PictureDivider {
    private Image image;
    private int pieces;

    public PictureDivider(Image image, int pieces) {
        this.image = image;
        this.pieces = pieces;
    }

    public List<Image> dividePicture() {
        int width = (int) image.getWidth();
        int height = (int) image.getHeight();
        List<Image> dividedImage = new ArrayList<>();

        int dividedPictureWidth = width/pieces;

        for (int i=0; i<pieces; i++) {
            //todo: think how to optimize this?
            if (i == pieces-1) {
                Image piece = new WritableImage(image.getPixelReader(), i * dividedPictureWidth, 0, dividedPictureWidth + (width % pieces), height);
                dividedImage.add(piece);
            } else {
                Image piece = new WritableImage(image.getPixelReader(), i * dividedPictureWidth, 0, dividedPictureWidth + (width % pieces), height);
                dividedImage.add(piece);
            }
        }
        return dividedImage;
    }
}
