package assignment1.test;

import assignment1.*;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class PerformanceTest {
    private List<Interaction> interactions;
    private List<Interaction> expectedResult;

    public void loadTestCase(String filename) throws IOException {
        BufferedReader reader = new BufferedReader(new FileReader(filename));
        interactions = new ArrayList<>();
        String line = reader.readLine();

        while (line != null) {
            String[] parts = line.split("\t");
            int fromPerson = Integer.parseInt(parts[0].trim());
            int toPerson = Integer.parseInt(parts[1].trim());
            int time = Integer.parseInt(parts[2].trim());
            Double probability = Double.parseDouble(parts[3].trim());
            interactions.add(new Interaction(fromPerson, toPerson, time, probability));
            line = reader.readLine();
        }

        reader.close();
    }

    @Before
    public void setup() {
        try {
            loadTestCase("C:/Users/Daniel Zhang/Desktop/COMP4500/Assignment1_Distributed/test/test-4000.txt");
        } catch (IOException e) {
            System.out.println("can't read file");
        }
    }

    int startPerson = 5;
    int endPerson = 388;


//    @Test
//    public void testPerformanceBruteForce() {
//        List<Interaction> result = TransmissionFinder.findTransmissionPathBruteForce(944, 17, interactions);
//        System.out.println("Brute force result:");
//        System.out.println(result);
//    }
//
    @Test
    public void testPerformanceA() {
        List<Interaction> result = TransmissionFinder.findTransmissionPath(startPerson, endPerson, interactions);
        System.out.println("result: ");
        System.out.println(result);
    }
}
