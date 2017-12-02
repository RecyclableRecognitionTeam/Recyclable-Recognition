package com.trash.recyclablerecognition;

import java.util.List;

/**
 * Created by kyle on 12/2/17.
 */

public interface iDetectorAlgorithm {
    /**
     * Returns the probability of the object being recyclable. If the object is recyclable, it will
     * return a number closer to 1. If it is not recyclable, the number will be closer to 0.
     * @param detections A list of the objects detected by the classifier.
     * @param probabilities A list of the probabilities of the objects detected by the classifier,
     *                      has index correspondence with the objects in detections.
     * @return The probability that the object is recyclable between 0 and 1.
     */
    double isRecyclable(List<String> detections, List<Double> probabilities);

    /**
     * Returns the probability of the object being trash. If the object is trash, it will
     * return a number closer to 1. If it is not trash, the number will be closer to 0.
     * @param detections A list of the objects detected by the classifier.
     * @param probabilities A list of the probabilities of the objects detected by the classifier,
     *                      has index correspondence with the objects in detections.
     * @return The probability that the object is trash between 0 and 1.
     */
    double isTrash(List<String> detections, List<Double> probabilities);
}
