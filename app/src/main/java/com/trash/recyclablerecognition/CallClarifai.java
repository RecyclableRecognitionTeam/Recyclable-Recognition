package com.trash.recyclablerecognition;

import java.io.File;

import clarifai2.api.ClarifaiBuilder;
import clarifai2.api.ClarifaiClient;
import okhttp3.OkHttpClient;

/**
 * Created by Tarek on 12/2/2017.
 */

public class CallClarifai {
    private ClarifaiClient client;

    public void imageThing() {
        client = new ClarifaiBuilder("afbbcc64fee745ec96634074bb4df6d7").client(new OkHttpClient()).buildSync();

        File f = new File("C:/Users/Tarek/Documents/GitHub/Recyclable-Recognition/app/src/main/res/image.jpg");
        boolean fileExists = f.getAbsoluteFile().exists();
        System.out.println(fileExists);
    }
}
