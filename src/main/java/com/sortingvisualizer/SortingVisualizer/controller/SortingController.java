package com.sortingvisualizer.SortingVisualizer.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.sortingvisualizer.SortingVisualizer.dto.SortingRequest;
import com.sortingvisualizer.SortingVisualizer.dto.SortingResponse;
import com.sortingvisualizer.SortingVisualizer.service.SortingService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@RestController
@RequestMapping("/api")
@CrossOrigin(origins = "http://localhost:3000", allowedHeaders = "*")
public class SortingController {

    @Autowired
    private SortingService sortingService;

    // @GetMapping("/sort")
    // public ResponseEntity<?> sortArray(
    //         @RequestParam String algorithm,
    //         @RequestParam String array,
    //         @RequestParam(defaultValue = "100") int delay) {
    //     try {
    //         int[] inputArray = Arrays.stream(array.split(","))
    //                 .mapToInt(Integer::parseInt)
    //                 .toArray();
    //         SortingRequest request = new SortingRequest(algorithm, inputArray, delay);

    //         SortingResponse response = sortingService.sortArray(request);

    //         return ResponseEntity.ok(response);
    //     } catch (IllegalArgumentException e) {
    //         return ResponseEntity.badRequest().body(e.getMessage());
    //     } catch (Exception e) {
    //         return ResponseEntity.status(500).body("Internal Server Error: " + e.getMessage());
    //     }
    // }

    @GetMapping("/sort")
    public ResponseEntity<?> sortArray(
            @RequestParam String algorithm,
            @RequestParam int size,
            @RequestParam(defaultValue = "100") int delay) {

        try {
            // Create SortingRequest object based on the incoming request
            SortingRequest request = new SortingRequest(algorithm, null, delay);
            request.setSize(size);  // Set the size of the array to be generated

            // Call the sorting service to sort the array and get the steps
            SortingResponse response = sortingService.sortArray(request);

            // Return the sorted array and the steps taken during sorting
            return ResponseEntity.ok(response);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.status(500).body("Internal Server Error: " + e.getMessage());
        }
    }

    @PostMapping("/sort")
    public ResponseEntity<?> sortArrayPost(@RequestBody SortingRequest request) {
        try {
            SortingResponse response = sortingService.sortArray(request);
            return ResponseEntity.ok(response);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.status(500).body("Internal Server Error: " + e.getMessage());
        }
    }
}
