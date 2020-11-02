import assignment2.Recursive;
import org.junit.Assert;
import org.junit.Test;

public class RecursiveTest {

    @Test
    public void exampleRecursiveTest() {
        int[] hourlyVolume =       {50,40,90,10,5,100,40,20,50};
        int[] fullServiceCapacity = {100,90,80,70,60,50,40,30,20,10};
        int[] regularServiceCapacity = {70,50,40,30,20,10};
        int[] minorServiceCapacity = {50,40,20,10};
        int expectedResult = 75;

        Assert.assertEquals(expectedResult, Recursive.optimalLossRecursive(hourlyVolume, fullServiceCapacity, regularServiceCapacity, minorServiceCapacity));
    }

    @Test
    public void exampleRecursiveTesBoundary() {
        int[] hourlyVolume =       {};
        int[] fullServiceCapacity = {100,90,80,70,60,50,40,30,20,10};
        int[] regularServiceCapacity = {70,50,40,30,20,10};
        int[] minorServiceCapacity = {50,40,20,10};
        int expectedResult = 0;

        Assert.assertEquals(expectedResult, Recursive.optimalLossRecursive(hourlyVolume, fullServiceCapacity, regularServiceCapacity, minorServiceCapacity));
    }

    @Test
    public void exampleRecursiveTest2() {
        int[] hourlyVolume =       {70,40,90,10,5,100,40,20,50};
        int[] fullServiceCapacity = {40,80,50,70,10,50,40,30,20,10};
        int[] regularServiceCapacity = {70,50,40,30,20,10};
        int[] minorServiceCapacity = {50,40,20,10};
        int expectedResult = 135;

        Assert.assertEquals(expectedResult, Recursive.optimalLossRecursive(hourlyVolume, fullServiceCapacity, regularServiceCapacity, minorServiceCapacity));
    }

    @Test
    public void exampleRecursiveTest3() {
        int[] hourlyVolume =       {20,40,100,10,5,100,50,20,50,20,40};
        int[] fullServiceCapacity = {90,65,20,30,50,50,30,30,20,10};
        int[] regularServiceCapacity = {50,50,45,30,20,10};
        int[] minorServiceCapacity = {60,30,20,20};
        int expectedResult = 185;

        Assert.assertEquals(expectedResult, Recursive.optimalLossRecursive(hourlyVolume, fullServiceCapacity, regularServiceCapacity, minorServiceCapacity));
    }

    @Test
    public void exampleRecursiveTest4() {
        int[] hourlyVolume =       {20,40,100,10,5,100,50,20,50,20,40};
        int[] fullServiceCapacity = {30,0,0,10};
        int[] regularServiceCapacity = {0,20};
        int[] minorServiceCapacity = {10};
        int expectedResult = 375;

        Assert.assertEquals(expectedResult, Recursive.optimalLossRecursive(hourlyVolume, fullServiceCapacity, regularServiceCapacity, minorServiceCapacity));
    }
}
