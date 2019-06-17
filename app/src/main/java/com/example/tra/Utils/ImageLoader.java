package com.example.tra.Utils;

import java.util.ArrayList;

/**
 * Created by ua on 9/18/2018.
 */


public class ImageLoader {

    public static ArrayList<Image> loadedImages = new ArrayList<Image>();

    public ImageLoader(String path){
        loadImage(path);
    }

    public ImageLoader(){

    }

    public Image loadImage(String path){
        boolean duplicate = false;
        if(loadedImages != null) {
            for (Image image : loadedImages) {
                if (path != null && image.location != null && image.location.equals((path))) {
                    return image;
                }
            }
        }
        Image image = new Image(path);
        loadedImages.add(image);
        return image;
    }
}
