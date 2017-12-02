package com.trash.recyclablerecognition;

import java.util.List;

/**
 * Created by kyle on 12/2/17.
 */

public class TallyDetectorAlgorithm implements iDetectorAlgorithm {

    private Disposables disposables;

    public TallyDetectorAlgorithm(Disposables disposables){
        this.disposables = disposables;
    }

    @Override
    public double isRecyclable(List<String> detections, List<Double> probabilities) {

        int recyclableCount = 0;
        int trashCount = 0;

        for (int i = 0; i < detections.size(); i++) {
            String item = detections.get(i);
            
        }
        
        return 0;
    }

    @Override
    public double isTrash(List<String> detections, List<Double> probabilities) {
        return 0;
    }
}
