package com.sortingvisualizer.SortingVisualizer.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import org.springframework.stereotype.Service;

import com.sortingvisualizer.SortingVisualizer.dto.SortingRequest;
import com.sortingvisualizer.SortingVisualizer.dto.SortingResponse;

@Service
public class SortingService {

    public SortingResponse sortArray(SortingRequest request) {
        int[] array = request.getArray();
        if (array == null || array.length == 0) {
            array = generateRandomArray(request.getSize());
        }

        List<int[]> steps = new ArrayList<>();

        switch (request.getAlgorithm().toLowerCase()) {
            case "bubble":
                bubbleSort(array, request.getDelay(), steps);
                break;
            case "selection":
                selectionSort(array, request.getDelay(), steps);
                break;
            case "insertion":
                insertionSort(array, request.getDelay(), steps);
                break;
            case "quick":
                quickSort(array, 0, array.length - 1, request.getDelay(), steps);
                break;
            case "merge":
                mergeSort(array, 0, array.length - 1, request.getDelay(), steps);
                break;
            default:
                throw new IllegalArgumentException("Unsupported algorithm: " + request.getAlgorithm());
        }
        return new SortingResponse(array, steps);
    }

    private int[] generateRandomArray(int size) {
        Random random = new Random();
        int[] array = new int[size];
        for (int i = 0; i < size; i++) {
            array[i] = random.nextInt(100) + 1;
        }
        return array;
    }

    private void bubbleSort(int[] array, int delay, List<int[]> steps) {
        int n = array.length;
        for (int i = 0; i < n - 1; i++) {
            for (int j = 0; j < n - i - 1; j++) {
                if (array[j] > array[j + 1]) {
                    swap(array, j, j + 1);
                    steps.add(array.clone()); // Save current state after a swap
                }
            }
        }
    }

    private void selectionSort(int[] array, int delay, List<int[]> steps) {
        int n = array.length;
        for (int i = 0; i < n - 1; i++) {
            int minIndex = i;
            for (int j = i + 1; j < n; j++) {
                if (array[j] < array[minIndex]) {
                    minIndex = j;
                }
            }
            swap(array, i, minIndex);
            steps.add(array.clone()); // Save current state after swapping
        }
    }

    private void insertionSort(int[] array, int delay, List<int[]> steps) {
        int n = array.length;
        for (int i = 1; i < n; i++) {
            int key = array[i];
            int j = i - 1;
            while (j >= 0 && array[j] > key) {
                array[j + 1] = array[j];
                j--;
            }
            array[j + 1] = key;
            steps.add(array.clone()); // Save current state after insertion
        }
    }

    private void quickSort(int[] array, int low, int high, int delay, List<int[]> steps) {
        if (low < high) {
            int pi = partition(array, low, high, steps);
            quickSort(array, low, pi - 1, delay, steps); // Before pi
            quickSort(array, pi + 1, high, delay, steps); // After pi
        }
    }

    private int partition(int[] array, int low, int high, List<int[]> steps) {
        int pivot = array[high];
        int i = (low - 1);  // Index of smaller element
        for (int j = low; j < high; j++) {
            if (array[j] <= pivot) {
                i++;
                swap(array, i, j);
            }
        }
        swap(array, i + 1, high);
        steps.add(array.clone()); // Save current state after partitioning
        return i + 1;
    }

    private void mergeSort(int[] array, int left, int right, int delay, List<int[]> steps) {
        if (left < right) {
            int mid = (left + right) / 2;
            mergeSort(array, left, mid, delay, steps);  // Left half
            mergeSort(array, mid + 1, right, delay, steps);  // Right half
            merge(array, left, mid, right, steps);  // Merge the halves
        }
    }

    private void merge(int[] array, int left, int mid, int right, List<int[]> steps) {
        int n1 = mid - left + 1;
        int n2 = right - mid;

        int[] L = new int[n1];
        int[] R = new int[n2];

        System.arraycopy(array, left, L, 0, n1);
        System.arraycopy(array, mid + 1, R, 0, n2);

        int i = 0, j = 0;
        int k = left;
        while (i < n1 && j < n2) {
            if (L[i] <= R[j]) {
                array[k++] = L[i++];
            } else {
                array[k++] = R[j++];
            }
        }

        while (i < n1) {
            array[k++] = L[i++];
        }

        while (j < n2) {
            array[k++] = R[j++];
        }

        steps.add(array.clone()); // Save current state after merging
    }

    private void swap(int[] array, int i, int j) {
        int temp = array[i];
        array[i] = array[j];
        array[j] = temp;
    }
}
