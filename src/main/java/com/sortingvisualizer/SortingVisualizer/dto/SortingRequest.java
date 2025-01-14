package com.sortingvisualizer.SortingVisualizer.dto;

public class SortingRequest {
    private String algorithm;
    private int[] array; // Input array for sorting
    private int delay;
    private int size; // Size to generate random array if array is null

    // Constructor
    public SortingRequest(String algorithm, int[] array, int delay) {
        this.algorithm = algorithm;
        this.array = array;
        this.delay = delay;
        this.size = (array == null || array.length == 0) ? 10 : array.length; // Default to 10 if array is null
    }

    public String getAlgorithm() {
        return algorithm;
    }

    public void setAlgorithm(String algorithm) {
        this.algorithm = algorithm;
    }

    public int[] getArray() {
        return array;
    }

    public void setArray(int[] array) {
        this.array = array;
    }

    public int getDelay() {
        return delay;
    }

    public void setDelay(int delay) {
        this.delay = delay;
    }

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }
}
