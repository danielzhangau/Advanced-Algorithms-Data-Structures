package assignment1.test;

import assignment1.*;
import java.util.*;

import org.junit.Assert;
import org.junit.Test;

public class IvanSuperTest {

    @Test
    public void defaultTest1() {

        //create the list of interactions
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

        //create the expected result
        List<Interaction> expectedResult = new ArrayList<>();
        expectedResult.add(new Interaction(1,3,7,0.8));
        expectedResult.add(new Interaction(3,5,7,0.5));
        expectedResult.add(new Interaction(5,6,14,0.9));

        //do the calculation and determine if the result was as expected
        List<Interaction> result = TransmissionFinder.findTransmissionPath(1, 6, interactionList);
        Assert.assertEquals(result, expectedResult);
    }

    @Test
    public void defaultTest2() {

        //create the list of interactions
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

        //do the calculation and determine if the result was as expected
        List<Interaction> result = TransmissionFinder.findTransmissionPath(1, 6, interactionList);
        Assert.assertNull(result);
    }

    @Test
    public void validStartConnectsToEnd1() {
        //create the list of interactions
        List<Interaction> interactionList = new ArrayList<>();
        interactionList.add(new Interaction(1,2,14,0.3));
        interactionList.add(new Interaction(2,1,8, 0.9));

        List<Interaction> expectedResult = new ArrayList<>();
        expectedResult.add(new Interaction(1,2,14,0.3));

        List<Interaction> result = TransmissionFinder.findTransmissionPath(1, 2, interactionList);
        Assert.assertEquals(result, expectedResult);
    }

    @Test
    public void validStartConnectsToEnd2() {
        //create the list of interactions
        List<Interaction> interactionList = new ArrayList<>();
        interactionList.add(new Interaction(1,2,14,0.7));
        interactionList.add(new Interaction(1,3,8, 0.8));
        interactionList.add(new Interaction(3,2,8, 0.8));

        List<Interaction> expectedResult = new ArrayList<>();
        expectedResult.add(new Interaction(1,2,14,0.7));

        List<Interaction> result = TransmissionFinder.findTransmissionPath(1, 2, interactionList);
        Assert.assertEquals(result, expectedResult);
    }

    @Test
    public void validStartConnectsToEnd3() {
        //create the list of interactions
        List<Interaction> interactionList = new ArrayList<>();
        interactionList.add(new Interaction(1,2,14,0.7));
        interactionList.add(new Interaction(1,3,8, 0.9));
        interactionList.add(new Interaction(3,2,8, 0.8));

        List<Interaction> expectedResult = new ArrayList<>();
        expectedResult.add(new Interaction(1,3,8, 0.9));
        expectedResult.add(new Interaction(3,2,8, 0.8));

        List<Interaction> result = TransmissionFinder.findTransmissionPath(1, 2, interactionList);
        Assert.assertEquals(result, expectedResult);
    }

    @Test
    public void validStartConnectsToEnd4() {
        //create the list of interactions
        List<Interaction> interactionList = new ArrayList<>();
        interactionList.add(new Interaction(1,2,14,0.7));
        interactionList.add(new Interaction(1,2,3,0.80));
        interactionList.add(new Interaction(1,2,50,0.7));
        interactionList.add(new Interaction(1,3,8, 0.9));
        interactionList.add(new Interaction(3,2,7, 0.9));

        List<Interaction> expectedResult = new ArrayList<>();
        expectedResult.add(new Interaction(1,2,3,0.80));

        List<Interaction> result = TransmissionFinder.findTransmissionPath(1, 2, interactionList);
        Assert.assertEquals(result, expectedResult);
    }

    @Test
    public void validStartConnectsToEnd5() {
        //create the list of interactions
        List<Interaction> interactionList = new ArrayList<>();
        interactionList.add(new Interaction(1,2,14,0.7));
        interactionList.add(new Interaction(1,2,3,0.80));
        interactionList.add(new Interaction(1,2,50,0.7));
        interactionList.add(new Interaction(1,3,8, 0.9));
        interactionList.add(new Interaction(3,2,8, 0.9));

        List<Interaction> expectedResult = new ArrayList<>();
        expectedResult.add(new Interaction(1,3,8, 0.9));
        expectedResult.add(new Interaction(3,2,8, 0.9));

        List<Interaction> result = TransmissionFinder.findTransmissionPath(1, 2, interactionList);
        Assert.assertEquals(result, expectedResult);
    }

    @Test
    public void validAllEdgesNonDecreasing1() {

        List<Interaction> interactionList = new ArrayList<>();

        interactionList.add(new Interaction(1,2,1,0.9));
        interactionList.add(new Interaction(1,3,1,0.8));
        interactionList.add(new Interaction(1,4,1,0.7));

        interactionList.add(new Interaction(2,3,2,0.9));
        interactionList.add(new Interaction(2,3,2,0.8));
        interactionList.add(new Interaction(2,4,2,0.8));

        interactionList.add(new Interaction(3,4,3,0.9));

        //interactionList.add(new Interaction(4,1,3,0.9));
        //interactionList.add(new Interaction(4,3,3,0.9));


        List<Interaction> expectedResult = new ArrayList<>();
        expectedResult.add(new Interaction(1,2,1,0.9));
        expectedResult.add(new Interaction(2,3,2,0.9));
        expectedResult.add(new Interaction(3,4,3,0.9));

        List<Interaction> result = TransmissionFinder.findTransmissionPath(1, 4, interactionList);
        Assert.assertEquals(result, expectedResult);
    }

    @Test
    public void validAllEdgesNonDecreasing2() {

        List<Interaction> interactionList = new ArrayList<>();

        interactionList.add(new Interaction(1,2,1,0.9));
        interactionList.add(new Interaction(1,3,1,0.8));
        interactionList.add(new Interaction(1,4,1,0.7));

        interactionList.add(new Interaction(2,3,2,0.9));
        interactionList.add(new Interaction(2,3,2,0.8));
        interactionList.add(new Interaction(2,4,2,0.8));

        interactionList.add(new Interaction(3,4,3,0.9));

        interactionList.add(new Interaction(4,1,4,1));
        interactionList.add(new Interaction(4,3,4,1));

        List<Interaction> expectedResult = new ArrayList<>();
        expectedResult.add(new Interaction(1,2,1,0.9));
        expectedResult.add(new Interaction(2,3,2,0.9));
        expectedResult.add(new Interaction(3,4,3,0.9));

        List<Interaction> result = TransmissionFinder.findTransmissionPath(1, 4, interactionList);
        Assert.assertEquals(result, expectedResult);
    }

    @Test
    public void validSomeEdgesDecreasing1() {

        List<Interaction> interactionList = new ArrayList<>();

        interactionList.add(new Interaction(1,2,15,0.9));
        interactionList.add(new Interaction(1,2,10,0.8));
        interactionList.add(new Interaction(1,2,2,0.7));
        interactionList.add(new Interaction(1,5,1,0.5));

        interactionList.add(new Interaction(2,3,20,0.85));
        interactionList.add(new Interaction(2,4,12,1));
        interactionList.add(new Interaction(2,5,12,0.8));
        interactionList.add(new Interaction(2,5,3,0.9));

        interactionList.add(new Interaction(3,4,20,0.9));
        interactionList.add(new Interaction(3,5,20,0.9));

        interactionList.add(new Interaction(4,3,15,1));
        interactionList.add(new Interaction(4,5,2,1));

        List<Interaction> expectedResult = new ArrayList<>();
        expectedResult.add(new Interaction(1,2,10,0.8));
        expectedResult.add(new Interaction(2,4,12,1));
        expectedResult.add(new Interaction(4,3,15,1));
        expectedResult.add(new Interaction(3,5,20,0.9));


        List<Interaction> result = TransmissionFinder.findTransmissionPath(1, 5, interactionList);
        Assert.assertEquals(result, expectedResult);
    }

    @Test
    public void validSomeEdgesDecreasing2() {

        List<Interaction> interactionList = new ArrayList<>();

        interactionList.add(new Interaction(1,2,15,0.9));
        interactionList.add(new Interaction(1,2,10,0.8));
        interactionList.add(new Interaction(1,2,2,0.75));
        interactionList.add(new Interaction(1,5,1,0.5));

        interactionList.add(new Interaction(2,3,20,0.85));
        interactionList.add(new Interaction(2,4,12,1));
        interactionList.add(new Interaction(2,5,12,0.8));
        interactionList.add(new Interaction(2,5,3,1));

        interactionList.add(new Interaction(3,4,20,0.9));
        interactionList.add(new Interaction(3,5,20,0.9));

        interactionList.add(new Interaction(4,3,15,1));
        interactionList.add(new Interaction(4,5,2,1));

        List<Interaction> expectedResult = new ArrayList<>();
        expectedResult.add(new Interaction(1,2,2,0.75));
        expectedResult.add(new Interaction(2,5,3,1));

        List<Interaction> result = TransmissionFinder.findTransmissionPath(1, 5, interactionList);
        Assert.assertEquals(result, expectedResult);
    }

    @Test
    public void validSomeEdgesDecreasing3() {

        List<Interaction> interactionList = new ArrayList<>();

        interactionList.add(new Interaction(1,2,15,0.9));
        interactionList.add(new Interaction(1,2,10,0.8));
        interactionList.add(new Interaction(1,2,2,0.75));
        interactionList.add(new Interaction(1,5,1,0.9));

        interactionList.add(new Interaction(2,3,20,0.85));
        interactionList.add(new Interaction(2,4,12,1));
        interactionList.add(new Interaction(2,5,12,0.8));
        interactionList.add(new Interaction(2,5,3,1));

        interactionList.add(new Interaction(3,4,20,0.9));
        interactionList.add(new Interaction(3,5,20,0.9));

        interactionList.add(new Interaction(4,3,15,1));
        interactionList.add(new Interaction(4,5,2,1));

        List<Interaction> expectedResult = new ArrayList<>();
        expectedResult.add(new Interaction(1,5,1,0.9));


        List<Interaction> result = TransmissionFinder.findTransmissionPath(1, 5, interactionList);
        Assert.assertEquals(result, expectedResult);
    }

    @Test
    public void validSomeEdgesDecreasing4() {

        List<Interaction> interactionList = new ArrayList<>();

        interactionList.add(new Interaction(1,2,15,0.9));
        interactionList.add(new Interaction(1,2,10,0.8));
        interactionList.add(new Interaction(1,2,2,0.75));
        interactionList.add(new Interaction(1,5,1,0.7));

        interactionList.add(new Interaction(2,3,20,0.85));
        interactionList.add(new Interaction(2,4,12,1));
        interactionList.add(new Interaction(2,5,12,0.8));
        interactionList.add(new Interaction(2,5,3,1));

        interactionList.add(new Interaction(3,4,20,0.9));
        interactionList.add(new Interaction(3,5,20,0.9));

        interactionList.add(new Interaction(4,3,15,1));
        interactionList.add(new Interaction(4,5,12,0.95));

        List<Interaction> expectedResult = new ArrayList<>();
        expectedResult.add(new Interaction(1,2,10,0.8));
        expectedResult.add(new Interaction(2,4,12,1));
        expectedResult.add(new Interaction(4,5,12,0.95));

        List<Interaction> result = TransmissionFinder.findTransmissionPath(1, 5, interactionList);
        Assert.assertEquals(result, expectedResult);
    }

    @Test
    public void validManyEdges1() {

        List<Interaction> interactionList = new ArrayList<>();

        interactionList.add(new Interaction(1,2,1,0.9));
        interactionList.add(new Interaction(1,2,2,0.9));
        interactionList.add(new Interaction(1,2,4,0.95));
        interactionList.add(new Interaction(1,2,6,0.9));
        interactionList.add(new Interaction(1,2,4,0.9));
        interactionList.add(new Interaction(1,2,7,0.9));
        interactionList.add(new Interaction(1,2,89,0.9));
        interactionList.add(new Interaction(1,2,24,1));
        interactionList.add(new Interaction(1,2,45,0.9));
        interactionList.add(new Interaction(1,2,5,0.9));

        interactionList.add(new Interaction(2,3,3,0.9));
        interactionList.add(new Interaction(2,3,6,0.9));
        interactionList.add(new Interaction(2,3,7,0.9));
        interactionList.add(new Interaction(2,3,4,0.9));
        interactionList.add(new Interaction(2,3,5,0.9));
        interactionList.add(new Interaction(2,3,12,1));
        interactionList.add(new Interaction(2,3,56,0.9));
        interactionList.add(new Interaction(2,3,32,0.9));
        interactionList.add(new Interaction(2,3,28,0.9));
        interactionList.add(new Interaction(2,3,60,0.9));

        interactionList.add(new Interaction(1,3,43,0.9));
        interactionList.add(new Interaction(1,3,56,0.9));
        interactionList.add(new Interaction(1,3,6,0.9));
        interactionList.add(new Interaction(1,3,4,0.9));
        interactionList.add(new Interaction(1,3,2,0.9));
        interactionList.add(new Interaction(1,3,12,0.9));
        interactionList.add(new Interaction(1,3,15,0.9));
        interactionList.add(new Interaction(1,3,18,0.9));
        interactionList.add(new Interaction(1,3,24,0.9));
        interactionList.add(new Interaction(1,3,34,0.9));

        List<Interaction> expectedResult = new ArrayList<>();
        expectedResult.add(new Interaction(1,2,4,0.95));
        expectedResult.add(new Interaction(2,3,12,1));

        List<Interaction> result = TransmissionFinder.findTransmissionPath(1, 3, interactionList);
        Assert.assertEquals(result, expectedResult);
    }

    @Test
    public void validManyEdges2() {

        List<Interaction> interactionList = new ArrayList<>();

        interactionList.add(new Interaction(1,2,1,0.9));
        interactionList.add(new Interaction(1,2,2,0.9));
        interactionList.add(new Interaction(1,2,4,0.95));
        interactionList.add(new Interaction(1,2,6,0.9));
        interactionList.add(new Interaction(1,2,4,0.9));
        interactionList.add(new Interaction(1,2,7,0.9));
        interactionList.add(new Interaction(1,2,89,0.9));
        interactionList.add(new Interaction(1,2,24,1));
        interactionList.add(new Interaction(1,2,45,0.9));
        interactionList.add(new Interaction(1,2,5,0.9));

        interactionList.add(new Interaction(2,3,3,0.9));
        interactionList.add(new Interaction(2,3,6,0.9));
        interactionList.add(new Interaction(2,3,7,0.9));
        interactionList.add(new Interaction(2,3,4,0.9));
        interactionList.add(new Interaction(2,3,5,0.9));
        interactionList.add(new Interaction(2,3,12,1));
        interactionList.add(new Interaction(2,3,56,0.9));
        interactionList.add(new Interaction(2,3,32,0.9));
        interactionList.add(new Interaction(2,3,28,0.9));
        interactionList.add(new Interaction(2,3,60,0.9));

        interactionList.add(new Interaction(1,3,43,0.9));
        interactionList.add(new Interaction(1,3,56,0.9));
        interactionList.add(new Interaction(1,3,6,0.9));
        interactionList.add(new Interaction(1,3,4,0.9));
        interactionList.add(new Interaction(1,3,2,0.9));
        interactionList.add(new Interaction(1,3,12,0.9));
        interactionList.add(new Interaction(1,3,15,0.9));
        interactionList.add(new Interaction(1,3,18,0.96));
        interactionList.add(new Interaction(1,3,24,0.9));
        interactionList.add(new Interaction(1,3,34,0.9));

        List<Interaction> expectedResult = new ArrayList<>();
        expectedResult.add(new Interaction(1,3,18,0.96));

        List<Interaction> result = TransmissionFinder.findTransmissionPath(1, 3, interactionList);
        Assert.assertEquals(result, expectedResult);
    }

    @Test
    public void invalidStartDNE() {

        List<Interaction> interactionList = new ArrayList<>();

        interactionList.add(new Interaction(1,2,1,0.9));
        interactionList.add(new Interaction(1,3,1,0.8));
        interactionList.add(new Interaction(1,4,1,0.7));

        interactionList.add(new Interaction(2,3,2,0.9));
        interactionList.add(new Interaction(2,3,2,0.8));
        interactionList.add(new Interaction(2,4,2,0.8));

        interactionList.add(new Interaction(3,4,3,0.9));

        interactionList.add(new Interaction(4,1,4,1));
        interactionList.add(new Interaction(4,3,4,1));

        List<Interaction> result = TransmissionFinder.findTransmissionPath(5, 4, interactionList);
        Assert.assertNull(result);
    }

    @Test
    public void invalidEndDNE() {

        List<Interaction> interactionList = new ArrayList<>();

        interactionList.add(new Interaction(1,2,1,0.9));
        interactionList.add(new Interaction(1,3,1,0.8));
        interactionList.add(new Interaction(1,4,1,0.7));

        interactionList.add(new Interaction(2,3,2,0.9));
        interactionList.add(new Interaction(2,3,2,0.8));
        interactionList.add(new Interaction(2,4,2,0.8));

        interactionList.add(new Interaction(3,4,3,0.9));

        interactionList.add(new Interaction(4,1,4,1));
        interactionList.add(new Interaction(4,3,4,1));

        List<Interaction> result = TransmissionFinder.findTransmissionPath(2, 0, interactionList);
        Assert.assertNull(result);
    }

    @Test
    public void invalidBothDNE() {

        List<Interaction> interactionList = new ArrayList<>();

        interactionList.add(new Interaction(1,2,1,0.9));
        interactionList.add(new Interaction(1,3,1,0.8));
        interactionList.add(new Interaction(1,4,1,0.7));

        interactionList.add(new Interaction(2,3,2,0.9));
        interactionList.add(new Interaction(2,3,2,0.8));
        interactionList.add(new Interaction(2,4,2,0.8));

        interactionList.add(new Interaction(3,4,3,0.9));

        interactionList.add(new Interaction(4,1,4,1));
        interactionList.add(new Interaction(4,3,4,1));

        List<Interaction> result = TransmissionFinder.findTransmissionPath(5, 6, interactionList);
        Assert.assertNull(result);
    }

    @Test
    public void invalidNoEdgeFromStartToEnd1() {

        List<Interaction> interactionList = new ArrayList<>();

        interactionList.add(new Interaction(1,2,1,0.9));
        interactionList.add(new Interaction(1,3,1,0.8));
        interactionList.add(new Interaction(1,4,1,0.7));

        interactionList.add(new Interaction(2,3,2,0.9));
        interactionList.add(new Interaction(2,3,2,0.8));
        interactionList.add(new Interaction(2,4,2,0.8));

        interactionList.add(new Interaction(4,1,4,1));
        interactionList.add(new Interaction(4,3,4,1));

        List<Interaction> result = TransmissionFinder.findTransmissionPath(3, 1, interactionList);
        Assert.assertNull(result);
    }

    @Test
    public void invalidNoEdgeFromStartToEnd2() {

        List<Interaction> interactionList = new ArrayList<>();

        interactionList.add(new Interaction(1,2,1,0.9));
        interactionList.add(new Interaction(1,3,1,0.8));
        interactionList.add(new Interaction(1,4,1,0.7));

        interactionList.add(new Interaction(2,3,2,0.9));
        interactionList.add(new Interaction(2,3,2,0.8));
        interactionList.add(new Interaction(2,4,2,0.8));

        interactionList.add(new Interaction(4,1,4,1));
        interactionList.add(new Interaction(4,3,4,1));
        interactionList.add(new Interaction(4,6,4,1));

        interactionList.add(new Interaction(5,6,4,1));

        List<Interaction> result = TransmissionFinder.findTransmissionPath(1, 8, interactionList);
        Assert.assertNull(result);
    }

    @Test
    public void invalidDecreasing1() {

        List<Interaction> interactionList = new ArrayList<>();

        interactionList.add(new Interaction(1,2,2,0.9));
        interactionList.add(new Interaction(1,2,3,0.9));

        interactionList.add(new Interaction(2,3,1,0.8));

        List<Interaction> result = TransmissionFinder.findTransmissionPath(1, 8, interactionList);
        Assert.assertNull(result);
    }

    @Test
    public void invalidDecreasing2() {

        List<Interaction> interactionList = new ArrayList<>();

        interactionList.add(new Interaction(1,2,2,0.9));
        interactionList.add(new Interaction(1,2,3,0.9));
        interactionList.add(new Interaction(1,3,100,0.01));

        interactionList.add(new Interaction(2,3,1,0.8));

        List<Interaction> expectedResult = new ArrayList<>();
        expectedResult.add(new Interaction(1,3,100,0.01));

        List<Interaction> result = TransmissionFinder.findTransmissionPath(1, 3, interactionList);
        Assert.assertEquals(result, expectedResult);
    }

    @Test
    public void invalidDecreasing3() {

        List<Interaction> interactionList = new ArrayList<>();

        interactionList.add(new Interaction(1,2,2,0.9));
        interactionList.add(new Interaction(1,2,3,0.9));

        interactionList.add(new Interaction(2,3,1,0.8));
        interactionList.add(new Interaction(2,3,2,0.8));

        List<Interaction> expectedResult = new ArrayList<>();
        expectedResult.add(new Interaction(1,2,2,0.9));
        expectedResult.add(new Interaction(2,3,2,0.8));

        List<Interaction> result = TransmissionFinder.findTransmissionPath(1, 3, interactionList);
        Assert.assertEquals(result, expectedResult);
    }

//    @Test
//    public void test() {
//        List<Interaction> interactionList = new ArrayList<>();
//
//        interactionList.add(new Interaction(1,2,2,0.9));
//        interactionList.add(new Interaction(1,2,2,0.8));
//        interactionList.add(new Interaction(1,2,2,0.5));
//
//        interactionList.add(new Interaction(2,3,1,0.8));
//        interactionList.add(new Interaction(2,3,2,0.8));
//
//        System.out.println(interactionList);
//        interactionList = TransmissionFinder.preprocessInteractions(interactionList);
//        System.out.println(interactionList);
//
//    }
}
