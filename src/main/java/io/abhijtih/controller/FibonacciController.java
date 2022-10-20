package io.abhijtih.controller;

import io.abhijtih.exception.FibonacciOutOfRangeException;
import io.abhijtih.utilities.FibonacciUtilites;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("fibonacci")
public class FibonacciController {

    @Autowired
    FibonacciUtilites utilites;

    /**
     * Find nth fibonacci number
     *
     * @param n position
     * @return nth fibonacci number
     */
    @GetMapping("findNumber")
    public ResponseEntity<String> findFibonacciNumber(@RequestParam int n) {
        Integer number;
        try {
            number = utilites.fibonacci(n);
        } catch (FibonacciOutOfRangeException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
        return ResponseEntity.ok(String.valueOf(number));
    }

    /**
     * Store the first nth numbers int the fibonacci sequence in a text file.
     *
     * @param n position in fibonacci sequence
     * @return name of the file created
     */
    @PostMapping("createSequence")
    public ResponseEntity<String> generateFibonacciSequence(@RequestParam int n) {
        List<Integer> sequence = utilites.getSequence(n);
        String fileName;
        try {
            fileName = utilites.storeSequence(sequence);
        } catch (IOException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        }
        return ResponseEntity.ok(fileName);
    }


    /**
     * Gets the sequence stored in fileName
     *
     * @param fileName which contains fibonacci sequence
     * @return fibonacci sequence as a string
     */
    @GetMapping("getSequence")
    public ResponseEntity<String> retrieveFibonacciSequence(@RequestParam String fileName) throws IOException {
        String sequence;
        try {
            sequence = utilites.getSequence(fileName);
        } catch (FileNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("File not available. Please check request and try again " + e.getMessage());
        }
        return ResponseEntity.ok(sequence);
    }


}
