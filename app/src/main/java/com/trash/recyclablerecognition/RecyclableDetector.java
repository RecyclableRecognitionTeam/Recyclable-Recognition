package com.trash.recyclablerecognition;

import java.util.List;

/**
 * Created by kyle on 12/2/17.
 */

public class RecyclableDetector {

    private iDetectorAlgorithm detectorAlgorithm;
    private double thresh;


    /**
     * Create a Recyclable Detector which will determine of objects in an image are trash or recyclable.
     * @param detectorAlgorithm The algorithm to use to detect if something is trash or recycling.
     * @param thresh The threshold to determine if something is trash or recycling. From 0 to 1 inclusive.
     */
    public RecyclableDetector(iDetectorAlgorithm detectorAlgorithm, double thresh){
        this.detectorAlgorithm = detectorAlgorithm;
        this.thresh = thresh;
    }

    public boolean isRecyclable(List<String> detections, List<Double> probabilities){
        return detectorAlgorithm.isRecyclable(detections, probabilities) >= thresh;
    }

    public boolean isTrash(List<String> detections, List<Double> probabilities){
        return detectorAlgorithm.isTrash(detections, probabilities) >= thresh;
    }

}
