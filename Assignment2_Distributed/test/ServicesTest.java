import assignment2.Dynamic;
import assignment2.Service;
import org.junit.Assert;
import org.junit.Test;

import java.util.Arrays;
import java.util.Random;

public class ServicesTest {

    /**
     * the example test in the handout
     */
    @Test(timeout = 500)
    public void dynamicServicesTest1() {
        int[] hourlyVolume =       {50,40,90,10,5,100,40,20,50};
        int[] fullServiceCapacity = {100,90,80,70,60,50,40,30,20,10};
        int[] regularServiceCapacity = {70,50,40,30,20,10};
        int[] minorServiceCapacity = {50,40,20,10};
        int expectedResult = 75;

        checkServicesResult(hourlyVolume, fullServiceCapacity, regularServiceCapacity, minorServiceCapacity, expectedResult);
    }

    @Test(timeout = 500)
    public void dynamicServicesTest2() {
        dynamicServicesTest2a();
        dynamicServicesTest2b();
    }

    /**
     * test that requires not performing a service at all
     */
    @Test(timeout = 500)
    public void dynamicServicesTest2a() {
        int[] hourlyVolume = {80, 80, 70, 80, 50, 42, 39, 24, 40, 10};
        int[] fullServiceCapacity = {110,100,80,60,50,50,50,30,20,10};
        int[] regularServiceCapacity = {80,40,40,30,20,20,10,10};
        int[] minorServiceCapacity = {40,20,10};
        int expectedResult = 40;
        checkServicesResult(hourlyVolume, fullServiceCapacity, regularServiceCapacity, minorServiceCapacity, expectedResult);
    }

    /**
     * test that requires a full service
     */
    @Test(timeout = 500)
    public void dynamicServicesTest2b() {
        int[] hourlyVolume = {10, 12, 6, 40, 120, 130, 100, 50, 42, 10, 80};
        int[] fullServiceCapacity = {100,100,80,60,60,50,40,30,20,10};
        int[] regularServiceCapacity = {80,50,40,30,20,10};
        int[] minorServiceCapacity = {50,50,40,40};
        int expectedResult = 178;
        checkServicesResult(hourlyVolume, fullServiceCapacity, regularServiceCapacity, minorServiceCapacity, expectedResult);
    }

    @Test(timeout = 500)
    public void dynamicServicesTest3() {
        dynamicServicesTest3a();
        dynamicServicesTest3b();
    }

    /**
     * test with some weird capacity arrays
     */
    @Test(timeout = 500)
    public void dynamicServicesTest3a() {
        int[] hourlyVolume = {40, 80, 30, 120, 100, 90, 30, 0, 70, 30, 60};
        int[] fullServiceCapacity = {40, 40, 40, 40, 40, 40, 40, 40, 40, 50};
        int[] regularServiceCapacity = {70, 40, 50, 70, 30, 50};
        int[] minorServiceCapacity = {10, 50, 100, 100};
        int expectedResult = 210;
        checkServicesResult(hourlyVolume, fullServiceCapacity, regularServiceCapacity, minorServiceCapacity, expectedResult);
    }

    /**
     * test to perform a service in the last hour
     */
    @Test(timeout = 500)
    public void dynamicServicesTest3b() {
        int[] hourlyVolume = {80, 80, 70, 80, 50, 42, 39, 69, 40, 10};
        int[] fullServiceCapacity = {110,100,80,70,50,50,50,30,20};
        int[] regularServiceCapacity = {80,80,60,30,20,20,10,10};
        int[] minorServiceCapacity = {80,60};
        int expectedResult = 59;
        checkServicesResult(hourlyVolume, fullServiceCapacity, regularServiceCapacity, minorServiceCapacity, expectedResult);
    }

    /**
     * larger size input, should fail with any recursive solution but any dynamic solution should pass comfortably
     */
    @Test(timeout = 5000)
    public void dynamicServicesTest4() {
        int[] hourlyVolume = generateRandomHourlyVolume(1000, 100, "seed".hashCode());
        int[] fullServiceCapacity = {100,90,80,70,60,50,40,30,20,10};
        int[] regularServiceCapacity = {70,50,40,30,20,10};
        int[] minorServiceCapacity = {50,40,20,10};
        int expectedResult = 16874;
        checkServicesResult(hourlyVolume, fullServiceCapacity, regularServiceCapacity, minorServiceCapacity, expectedResult);
    }

    /**
     * larger size input, should fail with any recursive solution but any dynamic solution should pass comfortably
     */
    @Test(timeout = 5000)
    public void dynamicServicesTest5() {
        int[] hourlyVolume = generateRandomHourlyVolume(3000, 80, "are you going to maths camp?".hashCode());
        int[] fullServiceCapacity = {100,90,80,70,60,50,50,20,20,10};
        int[] regularServiceCapacity = {80,40,40,20,20,60};
        int[] minorServiceCapacity = {20, 40, 60, 60};
        int expectedResult = 33005;
        checkServicesResult(hourlyVolume, fullServiceCapacity, regularServiceCapacity, minorServiceCapacity, expectedResult);
    }

    /**
     * generate a random hourly volume array using the given seed with the given length and a maximum value of upperBound
     */
    private int[] generateRandomHourlyVolume(int length, int upperBound, int seed) {
        Random random = new Random(seed);
        int[] hourlyVolume = new int[length];
        for (int i = 0; i < length; i++) {
            hourlyVolume[i] = random.nextInt(upperBound);
        }
        return hourlyVolume;
    }

    /**
     * Check that Dynamic.optimalActivitiesDynamic produces a valid list of services that would
     * produce the expectedResult
     */
    private static void checkServicesResult(int[] hourlyVolume, int[] fullServiceCapacity, int[] regularServiceCapacity,
                                            int[] minorServiceCapacity, int expectedResult) {
        Service[] actualServices = Dynamic.optimalServicesDynamic(hourlyVolume, fullServiceCapacity,
                regularServiceCapacity, minorServiceCapacity);
        //System.out.println(Arrays.toString(actualServices)); //print the result, uncomment to see the result
        checkSolutionValidity(actualServices, hourlyVolume);
        int solutionCost = getCost(hourlyVolume, fullServiceCapacity, regularServiceCapacity,
                minorServiceCapacity, actualServices);
        Assert.assertEquals(expectedResult, solutionCost);
    }

    /**
     * Checks for basic validity of a solution, checks that it has the correct length and all full and
     * regular services come in appropriately sized blocks
     */
    private static void checkSolutionValidity(Service[] services, int[] hourlyVolume) {

        Assert.assertEquals(services.length, hourlyVolume.length); //they should be the same length

        //check that full services come in blocks of 4 and regular services come in blocks of 2
        int hour = 0;
        while (hour < services.length) {
            if (services[hour] == null || services[hour] == Service.MINOR_SERVICE) {
                hour += 1;
            } else if (services[hour] == Service.FULL_SERVICE) {
                if (hour > services.length - 4) {
                    Assert.fail("performing a full service too late");
                }
                for (int extra = 1; extra < 4; extra++) {
                    Assert.assertEquals(Service.FULL_SERVICE, services[hour + extra]);
                }
                hour += 4; //skip over the full services
            } else if (services[hour] == Service.REGULAR_SERVICE) {
                if (hour > services.length - 2) {
                    Assert.fail("performing a regular service too late");
                }
                Assert.assertEquals(Service.REGULAR_SERVICE, services[hour + 1]);
                hour += 2; //skip over the next hour
            }
        }
    }

    /**
     * Returns the cost associated with the array of services returned. This determines the total cost
     * incurred by the company if they take the strategy listed in services for the problem described
     * by hourlyVolume, fullServiceCapacity, regularServiceCapacity and minorServiceCapacity
     */
    private static int getCost(int[] hourlyVolume, int[] fullServiceCapacity, int[] regularServiceCapacity,
                               int[] minorServiceCapacity, Service[] services) {

        Service lastService = Service.FULL_SERVICE;
        int cost = 0;
        int hoursSinceService = 0;

        for (int currentHour = 0; currentHour < hourlyVolume.length; currentHour++) {
            if (services[currentHour] == null) {
                cost += getHourlyCost(hourlyVolume, fullServiceCapacity, regularServiceCapacity, minorServiceCapacity,
                        currentHour, lastService, hoursSinceService);
                hoursSinceService++; //another hour since a service
            } else {
                cost += hourlyVolume[currentHour]; //forfeit all liquid in this hour
                hoursSinceService = 0; //reset the counter
                lastService = services[currentHour]; //update the last service type
            }
        }
        return cost;
    }

    /**
     * Returns the hourly cost for the current hour given that the last service was of type 'lastService' and
     * it has been 'hoursSinceService' hours since that service.
     */
    private static int getHourlyCost(int[] hourlyVolume, int[] fullServiceCapacity, int[] regularServiceCapacity,
                                     int[] minorServiceCapacity, int currentHour, Service lastService, int hoursSinceService) {

        int[] ServiceCapacity = getServiceArray(fullServiceCapacity, regularServiceCapacity, minorServiceCapacity, lastService);
        if (hoursSinceService >= ServiceCapacity.length) {
            Assert.fail("Solution does not perform a service in time");
        }
        return Math.max(hourlyVolume[currentHour] - ServiceCapacity[hoursSinceService], 0);
    }

    /**
     * Returns the volume array that is relevant given the last service
     */
    private static int[] getServiceArray(int[] fullServiceCapacity, int[] regularServiceCapacity, int[] minorServiceCapacity,
                                         Service lastService) {
        switch (lastService) {
            case FULL_SERVICE:
                return fullServiceCapacity;
            case REGULAR_SERVICE:
                return regularServiceCapacity;
            case MINOR_SERVICE:
            default:
                return minorServiceCapacity;
        }
    }
}
