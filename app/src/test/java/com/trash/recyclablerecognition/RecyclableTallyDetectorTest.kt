package com.trash.recyclablerecognition

import org.junit.Assert
import org.junit.Before
import org.junit.Test

/**
 * Created by kyle on 12/2/17.
 */
class RecyclableTallyDetectorTest {


    private lateinit var detector: RecyclableDetector
    private lateinit var disposables: Disposables
    private val recyclableObjectDetections = mutableListOf("paper", "sheet", "writing", "person")
    private val recyclableObjectProbabilities = mutableListOf(0.995, 0.99, 0.94, 0.81)
    private val trashObjectDetections = mutableListOf("food", "plate", "paper", "person", "utensil")
    private val trashObjectProbabilities = mutableListOf(0.995, 0.99, 0.87, 0.81, 0.8)

    @Before
    fun setup(){
        detector = RecyclableDetector(TallyDetectorAlgorithm(), 0.5)
        disposables = Disposables()
    }

    @Test
    fun isRecyclable() {
        val probability = detector.isRecyclable(recyclableObjectDetections, recyclableObjectProbabilities)
        Assert.assertTrue(probability)
        val probability2 = detector.isRecyclable(trashObjectDetections, trashObjectProbabilities)
        Assert.assertFalse(probability2)
    }

    @Test
    fun isTrash() {
        val probability = detector.isTrash(recyclableObjectDetections, recyclableObjectProbabilities)
        Assert.assertFalse(probability)
        val probability2 = detector.isTrash(trashObjectDetections, trashObjectProbabilities)
        Assert.assertTrue(probability2)
    }

    @Test
    fun getMostProbableDisposable(){
        val disposable = detector.getMostProbableDisposable(recyclableObjectDetections, recyclableObjectProbabilities)
        Assert.assertEquals(disposable, "paper")
        val disposable2 = detector.getMostProbableDisposable(trashObjectDetections, trashObjectProbabilities)
        Assert.assertEquals(disposable2, "food")
    }

}