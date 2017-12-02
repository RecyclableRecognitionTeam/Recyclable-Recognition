package com.trash.recyclablerecognition;

import java.util.List;

/**
 * Created by kyle on 12/2/17.
 */

public class TallyDetectorAlgorithm implements iDetectorAlgorithm {


    @Override
    public double isRecyclable(List<String> detections, List<Double> probabilities, Disposables disposables) {
        int recyclableCount = 0;
        int trashCount = 0;

        for (int i = 0; i < detections.size(); i++) {
            String item = detections.get(i);
            if (disposables.isRecyclable(item)){
                recyclableCount++;
            } else if(disposables.isTrash(item)){
                trashCount++;
            }
        }

        return recyclableCount != 0 ? recyclableCount / (double) (recyclableCount + trashCount) : 0;
    }

    @Override
    public double isTrash(List<String> detections, List<Double> probabilities, Disposables disposables) {
        int recyclableCount = 0;
        int trashCount = 0;

        for (int i = 0; i < detections.size(); i++) {
            String item = detections.get(i);
            if (disposables.isRecyclable(item)){
                recyclableCount++;
            } else if(disposables.isTrash(item)){
                trashCount++;
            }
        }

        return trashCount != 0 ? trashCount / (double) (recyclableCount + trashCount) : 0;
    }
}
