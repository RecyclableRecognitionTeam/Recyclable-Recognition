package com.trash.recyclablerecognition;

import java.util.List;

/**
 * Created by kyle on 12/2/17.
 */

public class RecyclableDetector {

    private iDetectorAlgorithm detectorAlgorithm;
    private Disposables disposables;
    private double thresh;


    /**
     * Create a Recyclable Detector which will determine of objects in an image are trash or recyclable.
     * @param detectorAlgorithm The algorithm to use to detect if something is trash or recycling.
     * @param thresh The threshold to determine if something is trash or recycling. From 0 to 1 inclusive.
     */
    public RecyclableDetector(iDetectorAlgorithm detectorAlgorithm, double thresh){
        this.detectorAlgorithm = detectorAlgorithm;
        this.disposables = new Disposables();
        this.thresh = thresh;
    }

    public boolean isRecyclable(List<String> detections, List<Double> probabilities){
        double trashProb = detectorAlgorithm.isTrash(detections, probabilities, disposables);
        double recyProb = detectorAlgorithm.isRecyclable(detections, probabilities, disposables);
        return  recyProb >= thresh && recyProb > trashProb;    }

    public boolean isTrash(List<String> detections, List<Double> probabilities){
        double trashProb = detectorAlgorithm.isTrash(detections, probabilities, disposables);
        double recyProb = detectorAlgorithm.isRecyclable(detections, probabilities, disposables);
        return  trashProb >= thresh && trashProb > recyProb;
    }

    public String getMostProbableDisposable(List<String> detections, List<Double> probabilities){
        boolean isTrash = isTrash(detections, probabilities);
        if(isTrash){
            for (String item : detections) {
                if(disposables.isTrash(item)){
                    return item;
                }
            }
        }
        boolean isRecyclable = isRecyclable(detections, probabilities);
        if(isRecyclable){
            for (String item : detections) {
                if(disposables.isRecyclable(item)){
                    return item;
                }
            }
        }

        return "unknown";
    }

}
