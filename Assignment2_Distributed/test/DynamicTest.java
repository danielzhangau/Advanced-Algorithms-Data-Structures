import assignment2.Dynamic;
import org.junit.Assert;
import org.junit.Test;
import java.util.Random;

public class DynamicTest {

    /**
     * the example test in the handout
     */
    @Test(timeout = 500)
    public void dynamicTest1() {
        int[] hourlyVolume =       {50,40,90,10,5,100,40,20,50};
        int[] fullServiceCapacity = {100,90,80,70,60,50,40,30,20,10};
        int[] regularServiceCapacity = {70,50,40,30,20,10};
        int[] minorServiceCapacity = {50,40,20,10};
        int expectedResult = 75;

        Assert.assertEquals(expectedResult, Dynamic.optimalLossDynamic(hourlyVolume, fullServiceCapacity, regularServiceCapacity, minorServiceCapacity));
    }

    @Test(timeout = 500)
    public void dynamicTest2() {
        dynamicTest2a();
        dynamicTest2b();
    }

    /**
     * test that requires not performing a service at all
     */
    @Test(timeout = 500)
    public void dynamicTest2a() {
        int[] hourlyVolume = {80, 80, 70, 80, 50, 42, 39, 24, 40, 10};
        int[] fullServiceCapacity = {110,100,80,60,50,50,50,30,20,10};
        int[] regularServiceCapacity = {80,40,40,30,20,20,10,10};
        int[] minorServiceCapacity = {40,20,10};
        int expectedResult = 40;
        Assert.assertEquals(expectedResult, Dynamic.optimalLossDynamic(hourlyVolume, fullServiceCapacity, regularServiceCapacity, minorServiceCapacity));
    }

    /**
     * test that requires a full service
     */
    @Test(timeout = 500)
    public void dynamicTest2b() {
        int[] hourlyVolume = {10, 12, 6, 40, 120, 130, 100, 50, 42, 10, 80};
        int[] fullServiceCapacity = {100,100,80,60,60,50,40,30,20,10};
        int[] regularServiceCapacity = {80,50,40,30,20,10};
        int[] minorServiceCapacity = {50,50,40,40};
        int expectedResult = 178;
        Assert.assertEquals(expectedResult, Dynamic.optimalLossDynamic(hourlyVolume, fullServiceCapacity, regularServiceCapacity, minorServiceCapacity));
    }

    @Test(timeout = 500)
    public void dynamicTest3() {
        dynamicTest3a();
        dynamicTest3b();
    }

    /**
     * test with some weird capacity arrays
     */
    @Test(timeout = 500)
    public void dynamicTest3a() {
        int[] hourlyVolume = {40, 80, 30, 120, 100, 90, 30, 0, 70, 30, 60};
        int[] fullServiceCapacity = {40, 40, 40, 40, 40, 40, 40, 40, 40, 50};
        int[] regularServiceCapacity = {70, 40, 50, 70, 30, 50};
        int[] minorServiceCapacity = {10, 50, 100, 100};
        int expectedResult = 210;
        Assert.assertEquals(expectedResult, Dynamic.optimalLossDynamic(hourlyVolume, fullServiceCapacity, regularServiceCapacity, minorServiceCapacity));
    }

    /**
     * test to perform a service in the last hour
     */
    @Test(timeout = 500)
    public void dynamicTest3b() {
        int[] hourlyVolume = {80, 80, 70, 80, 50, 42, 39, 69, 40, 10};
        int[] fullServiceCapacity = {110,100,80,70,50,50,50,30,20};
        int[] regularServiceCapacity = {80,80,60,30,20,20,10,10};
        int[] minorServiceCapacity = {80,60};
        int expectedResult = 59;
        Assert.assertEquals(expectedResult, Dynamic.optimalLossDynamic(hourlyVolume, fullServiceCapacity, regularServiceCapacity, minorServiceCapacity));
    }

    /**
     * larger size input, should fail with any recursive solution but any dynamic solution should pass comfortably
     */
    @Test(timeout = 5000)
    public void dynamicTest4() {
        int[] hourlyVolume = generateRandomHourlyVolume(1000, 100, "seed".hashCode());
        int[] fullServiceCapacity = {100,90,80,70,60,50,40,30,20,10};
        int[] regularServiceCapacity = {70,50,40,30,20,10};
        int[] minorServiceCapacity = {50,40,20,10};
        int expectedResult = 16874;
        Assert.assertEquals(expectedResult, Dynamic.optimalLossDynamic(hourlyVolume, fullServiceCapacity, regularServiceCapacity, minorServiceCapacity));
    }

    /**
     * larger size input, should fail with any recursive solution but any dynamic solution should pass comfortably
     */
    @Test(timeout = 5000)
    public void dynamicTest5() {
        int[] hourlyVolume = generateRandomHourlyVolume(3000, 80, "are you going to maths camp?".hashCode());
        int[] fullServiceCapacity = {100,90,80,70,60,50,50,20,20,10};
        int[] regularServiceCapacity = {80,40,40,20,20,60};
        int[] minorServiceCapacity = {20, 40, 60, 60};
        int expectedResult = 33005;
        Assert.assertEquals(expectedResult, Dynamic.optimalLossDynamic(hourlyVolume, fullServiceCapacity, regularServiceCapacity, minorServiceCapacity));
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

}
