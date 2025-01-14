package com.sortingvisualizer.SortingVisualizer.dto;

import java.util.List;

public class SortingResponse {
    private int[] sortedArray;
    private List<int[]> steps;

    // Constructor to initialize fields
    public SortingResponse(int[] sortedArray, List<int[]> steps) {
        this.sortedArray = sortedArray; // Assign the sorted array
        this.steps = steps;            // Assign the sorting steps
    }

    // Getter and Setter for sortedArray
    public int[] getSortedArray() {
        return sortedArray;
    }

    public void setSortedArray(int[] sortedArray) {
        this.sortedArray = sortedArray;
    }

    // Getter and Setter for steps
    public List<int[]> getSteps() {
        return steps;
    }

    public void setSteps(List<int[]> steps) {
        this.steps = steps;
    }
}
