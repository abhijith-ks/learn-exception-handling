package io.abhijtih.utilities;

import io.abhijtih.exception.FibonacciInputException;
import io.abhijtih.exception.FibonacciOutOfRangeException;
import org.springframework.stereotype.Component;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class FibonacciUtilites {

    /**
     * Find fibonacci number at the requested position in sequence
     *
     * @param position requested position in fibonacci sequence
     * @return fibonacci at position
     */
    public int fibonacci(int position) throws FibonacciOutOfRangeException {
        if(position > 8)
            throw new FibonacciOutOfRangeException("Fibonacci sequence more than 8 is not supported");
        if(position == 0) return 0;
        if(position == 1 || position == 2) return 1; // Comment this to test the Stack Overflow Exception
        return fibonacci(position - 1) + fibonacci( position - 2);
    }


    /**
     * Generate fibonacci sequence without recursion
     *
     * @param str total number of fibonacci sequence
     * @return list of integers in sequence
     */
    public List<Integer> getSequence(String str, List<Integer> sequence) throws FibonacciInputException {
        int n;
        try {
            n = Integer.parseInt(str);
        } catch (NumberFormatException e) {
            throw new FibonacciInputException("Invalid input. Please provide a valid number");
        }
        if(sequence == null) {
            sequence = new ArrayList<>();
        }
        sequence.add(0);
        int prev = 0;
        int curr = 1;
        int index = 1;
        while(index <= n) {
            sequence.add(curr);
            prev = curr;
            curr = curr + prev;
            index += 1;
        }
        return sequence;
    }


    /**
     * Save fibonacci sequence in a file
     *
     * @param sequence list of ints in the fibonacci sequence
     * @return String name of the file saved
     */
    public String storeSequence(List<Integer> sequence) throws IOException {
        String name = "fibonacci.txt";
        File file  = new File(name);
        file.createNewFile();
        FileWriter writer = new FileWriter(file);
        writer.write(sequence.toString());
        writer.flush();
        writer.close();
        return name;
    }

    /**
     * Get the fibonacci sequence in string format
     *
     * @param filename which contains fibonacci sequence
     * @return fibonacci sequence as a string
     */
    public String getSequenceFromFile(String filename) throws FileNotFoundException {
        BufferedReader reader = new BufferedReader(new FileReader(filename));
        return reader.lines().collect(Collectors.joining());
    }

}
