package com.trash.recyclablerecognition;

import android.app.Activity;
import android.os.AsyncTask;
import android.os.Bundle;

import java.io.File;
import java.util.List;
import java.util.concurrent.ExecutionException;

import clarifai2.api.ClarifaiBuilder;
import clarifai2.api.ClarifaiClient;
import clarifai2.dto.input.ClarifaiInput;
import clarifai2.dto.model.output.ClarifaiOutput;
import clarifai2.dto.prediction.Concept;
import okhttp3.OkHttpClient;

/**
 * Created by Tarek on 12/2/2017.
 */

public class CallClarifai extends Activity {
    private ClarifaiClient client = new ClarifaiBuilder("afbbcc64fee745ec96634074bb4df6d7").client(new OkHttpClient()).buildSync();

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //client = new ClarifaiBuilder("afbbcc64fee745ec96634074bb4df6d7").client(new OkHttpClient()).buildSync();
    }

    private class imageAsyncTask extends AsyncTask<File, Void, List<ClarifaiOutput<Concept>>> {

        List<ClarifaiOutput<Concept>> predictionResults = null;

        @Override
        protected List<ClarifaiOutput<Concept>> doInBackground(File... files) {
            File file = files[0];
            System.out.println(file.getAbsoluteFile());
            predictionResults = client.getDefaultModels().generalModel().predict()
                    .withInputs(ClarifaiInput.forImage(file)).executeSync().get();
            return predictionResults;
        }

        @Override
        protected void onPostExecute(List<ClarifaiOutput<Concept>> result){
            super.onPostExecute(result);
        }
    }
    public List<ClarifaiOutput<Concept>> getClarifai(File file) {

        List<ClarifaiOutput<Concept>> clarifaiOutput = null;
        imageAsyncTask task = new imageAsyncTask();

        try {
            clarifaiOutput = task.execute(new File[]{file}).get();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }

        return clarifaiOutput;
    }
}
