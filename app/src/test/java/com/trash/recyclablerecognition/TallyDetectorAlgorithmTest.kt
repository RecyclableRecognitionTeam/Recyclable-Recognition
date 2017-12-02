package com.trash.recyclablerecognition

import org.junit.Test

import org.junit.Assert.*
import org.junit.Before

/**
 * Created by kyle on 12/2/17.
 */
class TallyDetectorAlgorithmTest {


    private lateinit var detector: TallyDetectorAlgorithm
    private lateinit var disposables: Disposables
    private val recyclableObjectDetections = mutableListOf("paper", "sheet", "writing", "person")
    private val recyclableObjectProbabilities = mutableListOf(0.995, 0.99, 0.94, 0.81)
    private val trashObjectDetections = mutableListOf("food", "plate", "paper", "person", "utensil")
    private val trashObjectProbabilities = mutableListOf(0.995, 0.99, 0.87, 0.81, 0.8)

    @Before
    fun setup(){
        detector = TallyDetectorAlgorithm()
        disposables = Disposables()
    }

    @Test
    fun isRecyclable() {
        val probability = detector.isRecyclable(recyclableObjectDetections, recyclableObjectProbabilities, disposables)
        assertEquals(probability, 1.0, 0.001)
        val probability2 = detector.isRecyclable(trashObjectDetections, trashObjectProbabilities, disposables)
        assertEquals(probability2, 0.25, 0.001)
    }

    @Test
    fun isTrash() {
        val probability = detector.isTrash(recyclableObjectDetections, recyclableObjectProbabilities, disposables)
        assertEquals(probability, 0.0, 0.001)
        val probability2 = detector.isTrash(trashObjectDetections, trashObjectProbabilities, disposables)
        assertEquals(probability2, 0.75, 0.001)
    }

}