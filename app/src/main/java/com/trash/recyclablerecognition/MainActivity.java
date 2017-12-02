package com.trash.recyclablerecognition;

import android.Manifest;
import android.app.Activity;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.drawable.Drawable;
import android.media.Image;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v4.content.FileProvider;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import java.io.File;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;

import clarifai2.dto.model.output.ClarifaiOutput;
import clarifai2.dto.prediction.Concept;

/**
 * Created by kyle on 12/2/17.
 */

public class MainActivity extends Activity {
    Button button;
    ImageView imageView;
    TextView itemName, itemAction;
    static final int CAM_REQUEST = 1;
    CallClarifai Clarifai = new CallClarifai();
    List<ClarifaiOutput<Concept>> clarifaiOutput;
    List<String> objects = new ArrayList<String>();
    List<Double> probabilities = new ArrayList<Double>();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        if (ContextCompat.checkSelfPermission(this,
                Manifest.permission.WRITE_EXTERNAL_STORAGE)
                != PackageManager.PERMISSION_GRANTED) {

                ActivityCompat.requestPermissions(this,
                        new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE},
                        0);
        }
        button = (Button) findViewById(R.id.button);
        imageView = (ImageView)findViewById(R.id.image_view);
        itemAction = findViewById(R.id.item_action);
        itemName = findViewById(R.id.item_name);
        button.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                Intent camera_intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                File file = getFile();
                camera_intent.putExtra(MediaStore.EXTRA_OUTPUT, Uri.fromFile(file));
                camera_intent.putExtra(MediaStore.EXTRA_OUTPUT, FileProvider.getUriForFile(MainActivity.this,
                        BuildConfig.APPLICATION_ID + ".provider",
                        file));
                startActivityForResult(camera_intent,CAM_REQUEST);
            }
        });
    }

    private File getFile()
    {
        File folder = new File("sdcard/camera_app");

        if(!folder.exists())
        {
            folder.mkdir();
        }

        File image_file = new File(folder,"cam_image.jpg");
        return image_file;
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data){
        String path = "sdcard/camera_app/cam_image.jpg";
        imageView.setImageDrawable(Drawable.createFromPath(path));
        runClarifai();
    }

    private void runClarifai(){
        clarifaiOutput = Clarifai.getClarifai(getFile());
        List<Concept> data = clarifaiOutput.get(0).data();
        for (int i = 0; i < data.size(); i++) {
            objects.add(data.get(i).name());
            probabilities.add((double) data.get(i).value());
        }

        System.out.println(objects);

        RecyclableDetector detector = new RecyclableDetector(new TallyDetectorAlgorithm(), 0.6);
        String disposable = detector.getMostProbableDisposable(objects, probabilities);
        System.out.println("Most likely disposable: " + disposable);
        boolean isRecyclable = detector.isRecyclable(objects, probabilities);
        boolean isTrash = detector.isTrash(objects, probabilities);
        String objectType = "I'm not sure what this is, give me a hint or check your local waste management site";

        if(isRecyclable){
            objectType = "Recycle this!";
        }

        if(isTrash){
            objectType = "Throw it in the trash!";
        }

        System.out.println(objectType);

        itemName.setText(disposable);
        itemAction.setText(objectType);
        objects.clear();
        probabilities.clear();

    }
}
