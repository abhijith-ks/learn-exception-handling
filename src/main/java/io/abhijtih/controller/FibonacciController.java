package io.abhijtih.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("fibonacci")
public class FibonacciController {

    /**
     * Find nth fibonacci number
     *
     * @param n position
     * @return nth fibonacci number
     */
    @GetMapping("findNumber")
    public ResponseEntity<Integer> findFibonacciNumber(@RequestParam int n) {
        return ResponseEntity.ok(fibonacci(n));
    }

    /**
     * Find fibonacci number at the requested position in sequence
     *
     * @param position requested position in fibonacci sequence
     * @return fibonacci at position
     */
    private int fibonacci(int position) {
        if(position == 1 || position == 2) return 1; // Comment this to test the Stack Overflow Exception
        return fibonacci(position - 1) + fibonacci( position - 2);
    }
}
