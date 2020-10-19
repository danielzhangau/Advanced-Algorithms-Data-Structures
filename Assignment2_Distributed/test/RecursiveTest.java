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

        Assert.assertEquals(Recursive.optimalLossRecursive(hourlyVolume, fullServiceCapacity, regularServiceCapacity, minorServiceCapacity), expectedResult);
    }
}
