package com.trash.recyclablerecognition

/**
 * Created by kyle on 12/2/17.
 */

interface iDetectorAlgorithm {
    /**
     * Returns the probability of the object being recyclable. If the object is recyclable, it will
     * return a number closer to 1. If it is not recyclable, the number will be closer to 0.
     * @param detections A list of the objects detected by the classifier.
     * @param probabilities A list of the probabilities of the objects detected by the classifier,
     * has index correspondence with the objects in detections.
     * @return The probability that the object is recyclable between 0 and 1.
     */
    fun isRecyclable(detections: List<String>, probabilities: List<Double>, disposables: Disposables): Double

    /**
     * Returns the probability of the object being trash. If the object is trash, it will
     * return a number closer to 1. If it is not trash, the number will be closer to 0.
     * @param detections A list of the objects detected by the classifier.
     * @param probabilities A list of the probabilities of the objects detected by the classifier,
     * has index correspondence with the objects in detections.
     * @return The probability that the object is trash between 0 and 1.
     */
    fun isTrash(detections: List<String>, probabilities: List<Double>, disposables: Disposables): Double
}
