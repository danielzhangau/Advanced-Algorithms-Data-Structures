package assignment1.test;

import assignment1.*;
import java.util.*;

import org.junit.Assert;
import org.junit.Test;


public class TransmissionFinderTest {

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


}
