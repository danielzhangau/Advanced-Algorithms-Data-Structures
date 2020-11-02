import assignment1.*;
import java.util.*;
import java.lang.Math;

import org.junit.Assert;
import org.junit.Test;


public class TransmissionFinderTest {

    //start person 1, end person 6
    public static List<Interaction> test1List() {
        List<Interaction> interactionList = new ArrayList<>();
        interactionList.add(new Interaction(1,2,4,0.8));
        interactionList.add(new Interaction(1,3,7,0.8));
        interactionList.add(new Interaction(1,3,15,0.9));
        interactionList.add(new Interaction(1,4, 6, 0.2));
        interactionList.add(new Interaction(2,1,4,0.7));
        interactionList.add(new Interaction(3,1,15,0.9));
        interactionList.add(new Interaction(3,5,7,0.5));
        interactionList.add(new Interaction(3,6,6,0.9));
        interactionList.add(new Interaction(4,2,9,0.4));
        interactionList.add(new Interaction(4,6,18,0.7));
        interactionList.add(new Interaction(5,3,7,0.5));
        interactionList.add(new Interaction(5,6,14,0.9));
        interactionList.add(new Interaction(6,4,18,0.8));
        interactionList.add(new Interaction(6,5,14,0.9));
        return interactionList;
    }

    //start person 1, end person 4
    public static List<Interaction> test2List() {
        List<Interaction> interactionList = new ArrayList<>();
        interactionList.add(new Interaction(1,2,14,0.3));
        interactionList.add(new Interaction(1,3,8, 0.9));
        interactionList.add(new Interaction(2,1,14,0.4));
        interactionList.add(new Interaction(2,3,10,0.2));
        interactionList.add(new Interaction(2,4,3, 0.9));
        interactionList.add(new Interaction(3,1,8, 0.9));
        interactionList.add(new Interaction(3,2,10,0.3));
        interactionList.add(new Interaction(3,4,7, 0.8));
        interactionList.add(new Interaction(4,2,3, 0.9));
        interactionList.add(new Interaction(4,3,7, 0.7));
        return interactionList;
    }

    /**
     * Randomly generate a set of interactions, given a random seed, and a number of people, interactions and a timeframe
     */
    public static List<Interaction> generateInteractionList(long seed, int numPeople, int numInteractions, int timePeriod) {
        Random random = new Random(seed);
        List<Interaction> interactionList = new ArrayList<>();
        //generate a whole bunch of interactions
        for (int i = 0; i < numInteractions; i++) {
            int personFrom = random.nextInt(numPeople) + 1;
            //get a personTo which doesn't match the personFrom
            int personTo = random.nextInt(numPeople) + 1;
            while (personFrom == personTo) {
                personTo = random.nextInt(numPeople) + 1;
            }
            //get a random transmission probability to 3 decimal places, including 1, excluding 0
            double scale = Math.pow(10,3);
            double transmissionProbability = Math.floor(random.nextDouble() * scale + 1) / scale;
            interactionList.add(new Interaction(personFrom, personTo,
                    random.nextInt(timePeriod),transmissionProbability));
        }
        return interactionList;
    }

    /**
     * Generate a random list of interactions which forces the worst case performance of my best solution.
     * The optimal path generated will be from person 1 to numPeople, with a transmission probability of
     * 0.001*0.001*0.001*0.001
     */
    public static List<Interaction> generateForcingFullExecutionInteractionList(int seed, int numPeople, int numInteractions, int time) {
        List<Interaction> interactionList = new ArrayList<>();
        interactionList.add(new Interaction(1,2,1,0.01));
        interactionList.add(new Interaction(2,3,3,0.01));
        interactionList.add(new Interaction(3,numPeople - 1,5,0.01));
        interactionList.add(new Interaction(numPeople - 1, numPeople, time + 10,0.01));
        //generate a whole bunch of interactions
        Random random = new Random(seed);
        for (int i = 0; i < numInteractions; i++) {
            int personFrom = random.nextInt(numPeople - 2) + 1;
            //get a person to which doesn't match the person from
            int personTo = random.nextInt(numPeople - 2) + 1;
            while (personFrom == personTo) {
                personTo = random.nextInt(numPeople - 2) + 1;
            }
            double scale = Math.pow(10, 3);
            double transmissionProbability = Math.floor(random.nextDouble() * scale + 1) / scale;
            interactionList.add(new Interaction(personFrom, personTo,
                    random.nextInt(time)+6, transmissionProbability));
        }
        return interactionList;
    }

    //start person 1, end person 5, this has a probability one loop in it, so is an edge case
    public static List<Interaction> probabilityOneLoop() {
        //loop of probability 1 interactions at the same time
        List<Interaction> interactionList = new ArrayList<>();
        interactionList.add(new Interaction(1,2,5,0.8));
        interactionList.add(new Interaction(2,3,10,1));
        interactionList.add(new Interaction(3,4,10,1));
        interactionList.add(new Interaction(4,2,10,1));
        interactionList.add(new Interaction(4,5,18,0.2));
        return interactionList;
    }

    /**
     * assert whether a path provided is valid, i.e contains all interactions in the supplied list
     * and goes from the startPerson to the endPerson
     */
    public static void assertValidPath(int startPerson, int endPerson, List<Interaction> interactions,
                             List<Interaction> result) {
        if (result.get(0).getPersonFrom() != startPerson) {
            Assert.fail("Does not start at the startPerson");
        }
        for (int i = 1; i < result.size(); i++) {
            Interaction interaction = result.get(i);
            Interaction previousInteraction = result.get(i-1);
            //check that the interaction is actually in the list
            if (!interactions.contains(interaction)) {
                Assert.fail("One of the returned interactions was not given");
            }
            if (previousInteraction.getPersonTo() != interaction.getPersonFrom()) {
                Assert.fail("One of the links is invalid");
            }
            if (previousInteraction.getTimeOfInteraction() > interaction.getTimeOfInteraction()) {
                Assert.fail("The times in the path decrease");
            }
        }
        //check that we end at the end person
        if (result.get(result.size()-1).getPersonTo() != endPerson) {
            Assert.fail("Does not end on the correct person");
        }
    }

    /**
     * Calculates the probability of transmission along a path
     */
    public static double calculatePathProbability(List<Interaction> path) {
        double probability = 1;
        for (Interaction i : path) {
            probability *= i.getTransmissionProbability();
        }
        return probability;
    }

    /**
     * Assert whether a generated solution is a valid solution, i.e is a valid path from the startPerson
     * to the endPerson and has a probability as expected.
     */
    public void checkSolution(int startPerson, int endPerson, List<Interaction> interactions,
                               List<Interaction> result, double expectedResult) {
        //expected result is zero if there is no solution
        if (expectedResult == 0) {
            Assert.assertNull(result);
        } else {
            Assert.assertNotNull(result); //assert not null
            assertValidPath(startPerson, endPerson, interactions, result);
            double pathProbability = calculatePathProbability(result);
            Assert.assertEquals(pathProbability, expectedResult, 0.0001);
        }
    }

    /**
     * run a full test with a supplied set of interactions and an expected probability.
     */
    public void runTest(int startPerson, int endPerson, List<Interaction> interactions, double expectedResult) {
        //first create a deep copy of the interactions list
        List<Interaction> interactionCopy = new ArrayList<>();
        interactionCopy.addAll(interactions);
        //next actually get the result
        List<Interaction> result = TransmissionFinder.findTransmissionPath(startPerson, endPerson, interactionCopy);
        //then check the solution works
        //System.out.print(result.toString());
        checkSolution(startPerson,endPerson,interactions,result,expectedResult);
    }

    @Test(timeout = 1000)
    public void defaultTest1() {
        runTest(1,6,test1List(), 0.36);
    }

    @Test(timeout = 1000)
    public void defaultTest2() {
        runTest(1,4,test2List(),0);
    }

    @Test(timeout = 1000)
    public void smallTestCases() {
        runTest(1,6,generateInteractionList(69,6,15,30),0.287*0.759);
        runTest(3,1,generateInteractionList(11,4,24,18),0.264);
        runTest(1,5,generateInteractionList(121,7,19,10),0);
    }

    @Test(timeout = 1000)
    public void smallTestCases2() {
        //this one has some people with interactions to but no interactions from
        runTest(6,9,generateInteractionList(42,15,50,100),0.684*0.781*0.283*0.762);
        runTest(5,4,generateInteractionList("rocket league is life".hashCode(),6,30,300),0.169);
        runTest(1,4,generateInteractionList("Pat Mahomes, take me home".hashCode(), 10,35,32),0);
    }

    @Test(timeout = 1000)
    public void edgeCaseTests() {
        //has a probability 1 loop in it
        runTest(1,5,probabilityOneLoop(),0.16);
        //all the same time
        runTest(1,8, generateInteractionList(100, 10,20,1), 0.525*0.470*0.350);
        //target person not in the list of interactions
        runTest(1, 42, generateInteractionList(420,4, 15, 100),0);
    }

    @Test(timeout = 5000)
    public void stressTest1() {
        runTest(1,8,generateInteractionList(100,2,25,1),0);
        runTest(1,6,generateForcingFullExecutionInteractionList(20,6,70, 300),0.01*0.01*0.01*0.01);
    }

    @Test(timeout = 5000)
    public void stressTest2() {
        runTest(5,12,generateInteractionList(57,14,120,10),0.16484);
        runTest(1,12,generateForcingFullExecutionInteractionList(20,12,800, 100),0.01*0.01*0.01*0.01);
    }

    @Test(timeout = 5000)
    public void stressTest3() {
        runTest(4,81,generateInteractionList(144,100,1000,15),0.422556);
        runTest(1,25,generateForcingFullExecutionInteractionList(20,25,5000, 1000),0.01*0.01*0.01*0.01);
    }

    @Test(timeout = 5000)
    public void stressTest4() {
        runTest(2, 365, generateInteractionList(9000, 400, 10000,9),0.812*0.951*0.858*0.976*0.776);
        runTest(1,35,generateForcingFullExecutionInteractionList(20,35,25000, 10000),0.01*0.01*0.01*0.01);
    }

    @Test(timeout = 5000)
    public void stressTest5() {
        runTest(42,6900, generateInteractionList("friends?".hashCode(), 7000, 100000, 100), 0.293*0.633*0.809*0.336*0.789*0.985*0.622);
        runTest(1,90,generateForcingFullExecutionInteractionList(20,90,320000, 10000),0.01*0.01*0.01*0.01);
        //my code can handle 2,000,000 interactions within the 5s limit
    }


}
