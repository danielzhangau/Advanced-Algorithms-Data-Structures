import assignment2.Dynamic;
import assignment2.Recursive;
import assignment2.Service;
import org.junit.Assert;
import org.junit.Test;

public class DynamicTestExtra {

    @Test
    public void exampleDynamicTest() {
        int[] hourlyVolume =       {50,40,90,10,5,100,40,20,50};
        int[] fullServiceCapacity = {100,90,80,70,60,50,40,30,20,10};
        int[] regularServiceCapacity = {70,50,40,30,20,10};
        int[] minorServiceCapacity = {50,40,20,10};
        int expectedResult = 75;
        Dynamic.optimalServicesDynamic(hourlyVolume, fullServiceCapacity, regularServiceCapacity, minorServiceCapacity);

        Assert.assertEquals(expectedResult, Dynamic.optimalLossDynamic(hourlyVolume, fullServiceCapacity, regularServiceCapacity, minorServiceCapacity));
    }

    @Test
    public void exampleDynamicServicesTest() {
        int[] hourlyVolume =       {50,40,90,10,5,100,40,20,50};
        int[] fullServiceCapacity = {100,90,80,70,60,50,40,30,20,10};
        int[] regularServiceCapacity = {70,50,40,30,20,10};
        int[] minorServiceCapacity = {50,40,20,10};
        int expectedResult = 75;

        checkServicesResult(hourlyVolume, fullServiceCapacity, regularServiceCapacity, minorServiceCapacity, expectedResult);
    }

    @Test
    public void test_2_dynamic() {
        int[] hourlyVolume =       {200,200,200,200,200,200,200,200,200,200,200,200,200,200,200};
        int[] fullServiceCapacity = {100,90,80,70,60,50,40,30,20,10};
        int[] regularServiceCapacity = {70,50,40,30,20,10};
        int[] minorServiceCapacity = {50,40,20,10};
        int expectedResult = 2150;
        Assert.assertEquals(Dynamic.optimalLossDynamic(hourlyVolume, fullServiceCapacity, regularServiceCapacity, minorServiceCapacity), expectedResult);
    }

    @Test
    public void test_2_recursive() {
        int[] hourlyVolume =       {200,200,200,200,200,200,200,200,200,200,200,200,200,200,200};
        int[] fullServiceCapacity = {100,90,80,70,60,50,40,30,20,10};
        int[] regularServiceCapacity = {70,50,40,30,20,10};
        int[] minorServiceCapacity = {50,40,20,10};
        int expectedResult = 2150;
        Assert.assertEquals(Recursive.optimalLossRecursive(hourlyVolume, fullServiceCapacity, regularServiceCapacity, minorServiceCapacity), expectedResult);
    }

    @Test
    public void test_2_schedule() {
        int[] hourlyVolume =       {200,200,200,200,200,200,200,200,200,200,200,200,200,200,200};
        int[] fullServiceCapacity = {100,90,80,70,60,50,40,30,20,10};
        int[] regularServiceCapacity = {70,50,40,30,20,10};
        int[] minorServiceCapacity = {50,40,20,10};
        int expectedResult = 2150;
        checkServicesResult(hourlyVolume, fullServiceCapacity, regularServiceCapacity, minorServiceCapacity, expectedResult);
    }

    @Test
    public void test_3_dynamic() {
        int[] hourlyVolume =       {2,2,2,2,2,2,2,2,2,2,2,2,2,2,2}; //15 * 2
        int[] fullServiceCapacity = {100,90,80,70,60,50,40,30,20,10};
        int[] regularServiceCapacity = {70,50,40,30,20,10};
        int[] minorServiceCapacity = {50,40,20,10};
        int expectedResult = 2;
        Assert.assertEquals(Dynamic.optimalLossDynamic(hourlyVolume, fullServiceCapacity, regularServiceCapacity, minorServiceCapacity), expectedResult);
    }

    @Test
    public void test_3_recursive() {
        int[] hourlyVolume =       {2,2,2,2,2,2,2,2,2,2,2,2,2,2,2}; //15 * 2
        int[] fullServiceCapacity = {100,90,80,70,60,50,40,30,20,10};
        int[] regularServiceCapacity = {70,50,40,30,20,10};
        int[] minorServiceCapacity = {50,40,20,10};
        int expectedResult = 2;
        Assert.assertEquals(Recursive.optimalLossRecursive(hourlyVolume, fullServiceCapacity, regularServiceCapacity, minorServiceCapacity), expectedResult);
    }

    @Test
    public void test_3_schedule() {
        int[] hourlyVolume =       {2,2,2,2,2,2,2,2,2,2,2,2,2,2,2}; //15 * 2
        int[] fullServiceCapacity = {100,90,80,70,60,50,40,30,20,10};
        int[] regularServiceCapacity = {70,50,40,30,20,10};
        int[] minorServiceCapacity = {50,40,20,10};
        int expectedResult = 2;
        checkServicesResult(hourlyVolume, fullServiceCapacity, regularServiceCapacity, minorServiceCapacity, expectedResult);
    }

    @Test
    public void test_4_dynamic() {
        int[] hourlyVolume =       {2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2}; //18 * 2
        int[] fullServiceCapacity = {100,90,80,70,60,50,40,30,20,10};
        int[] regularServiceCapacity = {70,50,40,30,20,10};
        int[] minorServiceCapacity = {50,40,20,10};
        int expectedResult = 4;
        Assert.assertEquals(Dynamic.optimalLossDynamic(hourlyVolume, fullServiceCapacity, regularServiceCapacity, minorServiceCapacity), expectedResult);
    }

    @Test
    public void test_4_recursive() {
        int[] hourlyVolume =       {2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2}; //18 * 2
        int[] fullServiceCapacity = {100,90,80,70,60,50,40,30,20,10};
        int[] regularServiceCapacity = {70,50,40,30,20,10};
        int[] minorServiceCapacity = {50,40,20,10};
        int expectedResult = 4;
        Assert.assertEquals(Recursive.optimalLossRecursive(hourlyVolume, fullServiceCapacity, regularServiceCapacity, minorServiceCapacity), expectedResult);
    }

    @Test
    public void test_4_schedule() {
        int[] hourlyVolume =       {2,2,2};
        int[] fullServiceCapacity = {100,90,80,70,60,50,40,30,20,10};
        int[] regularServiceCapacity = {70,50,40,30,20,10};
        int[] minorServiceCapacity = {50,40,20,10};
        int expectedResult = 0;
        checkServicesResult(hourlyVolume, fullServiceCapacity, regularServiceCapacity, minorServiceCapacity, expectedResult);
    }

    @Test
    public void test_5_dynamic() {
        int[] hourlyVolume =       {2,2,2};
        int[] fullServiceCapacity = {100,90,80,70,60,50,40,30,20,10};
        int[] regularServiceCapacity = {70,50,40,30,20,10};
        int[] minorServiceCapacity = {50,40,20,10};
        int expectedResult = 0;
        Assert.assertEquals(Dynamic.optimalLossDynamic(hourlyVolume, fullServiceCapacity, regularServiceCapacity, minorServiceCapacity), expectedResult);
    }

    @Test
    public void test_5_recursive() {
        int[] hourlyVolume =       {2,2,2};
        int[] fullServiceCapacity = {100,90,80,70,60,50,40,30,20,10};
        int[] regularServiceCapacity = {70,50,40,30,20,10};
        int[] minorServiceCapacity = {50,40,20,10};
        int expectedResult = 0;
        Assert.assertEquals(Recursive.optimalLossRecursive(hourlyVolume, fullServiceCapacity, regularServiceCapacity, minorServiceCapacity), expectedResult);
    }

    @Test
    public void test_5_schedule() {
        int[] hourlyVolume =       {2,2,2};
        int[] fullServiceCapacity = {100,90,80,70,60,50,40,30,20,10};
        int[] regularServiceCapacity = {70,50,40,30,20,10};
        int[] minorServiceCapacity = {50,40,20,10};
        int expectedResult = 0;
        checkServicesResult(hourlyVolume, fullServiceCapacity, regularServiceCapacity, minorServiceCapacity, expectedResult);
    }

    @Test
    public void test_6_dynamic() {
        int[] hourlyVolume =       {2,200,2};
        int[] fullServiceCapacity = {100,90,80,70,60,50,40,30,20,10};
        int[] regularServiceCapacity = {70,50,40,30,20,10};
        int[] minorServiceCapacity = {50,40,20,10};
        int expectedResult = 110;
        Assert.assertEquals(Dynamic.optimalLossDynamic(hourlyVolume, fullServiceCapacity, regularServiceCapacity, minorServiceCapacity), expectedResult);
    }

    @Test
    public void test_6_recursive() {
        int[] hourlyVolume =       {2,200,2};
        int[] fullServiceCapacity = {100,90,80,70,60,50,40,30,20,10};
        int[] regularServiceCapacity = {70,50,40,30,20,10};
        int[] minorServiceCapacity = {50,40,20,10};
        int expectedResult = 110;
        Assert.assertEquals(Recursive.optimalLossRecursive(hourlyVolume, fullServiceCapacity, regularServiceCapacity, minorServiceCapacity), expectedResult);
    }

    @Test
    public void test_6_schedule() {
        int[] hourlyVolume =       {2,200,2};
        int[] fullServiceCapacity = {100,90,80,70,60,50,40,30,20,10};
        int[] regularServiceCapacity = {70,50,40,30,20,10};
        int[] minorServiceCapacity = {50,40,20,10};
        int expectedResult = 110;
        checkServicesResult(hourlyVolume, fullServiceCapacity, regularServiceCapacity, minorServiceCapacity, expectedResult);
    }

    @Test
    public void test_7_recursive() {
        int[] hourlyVolume =       {50,40,90,10,5,100,40,20,50, 50,40,90,10,5,100,40,20,50};
        int[] fullServiceCapacity = {100,90,80,70,60,50,40,30,20,10};
        int[] regularServiceCapacity = {70,50,40,30,20,10};
        int[] minorServiceCapacity = {50,40,20,10};
        int expectedResult = 230;
        Assert.assertEquals(Recursive.optimalLossRecursive(hourlyVolume, fullServiceCapacity, regularServiceCapacity, minorServiceCapacity), expectedResult);
    }

    @Test
    public void test_7_dynamic() {
        int[] hourlyVolume =       {50,40,90,10,5,100,40,20,50, 50,40,90,10,5,100,40,20,50};
        int[] fullServiceCapacity = {100,90,80,70,60,50,40,30,20,10};
        int[] regularServiceCapacity = {70,50,40,30,20,10};
        int[] minorServiceCapacity = {50,40,20,10};
        int expectedResult = 230;
        Assert.assertEquals(Dynamic.optimalLossDynamic(hourlyVolume, fullServiceCapacity, regularServiceCapacity, minorServiceCapacity), expectedResult);
    }

    @Test
    public void test_7_schedule() {
        int[] hourlyVolume =       {50,40,90,10,5,100,40,20,50, 50,40,90,10,5,100,40,20,50};
        int[] fullServiceCapacity = {100,90,80,70,60,50,40,30,20,10};
        int[] regularServiceCapacity = {70,50,40,30,20,10};
        int[] minorServiceCapacity = {50,40,20,10};
        int expectedResult = 230;
        checkServicesResult(hourlyVolume, fullServiceCapacity, regularServiceCapacity, minorServiceCapacity, expectedResult);
    }

    @Test
    public void test_8_recursive() {
        int[] hourlyVolume =       {50,40,90,10,5,100,40,20,50, 50,40,90,10,5,100,40,20,50,30,90,50,100};
        int[] fullServiceCapacity = {100,90,80,70,60,50,40,30,20,10,5};
        int[] regularServiceCapacity = {70,50,40,30,20,10,10};
        int[] minorServiceCapacity = {50,40,20,10};
        int expectedResult = 350;
        Assert.assertEquals(Recursive.optimalLossRecursive(hourlyVolume, fullServiceCapacity, regularServiceCapacity, minorServiceCapacity), expectedResult);
    }

    @Test
    public void test_8_dynamic() {
        int[] hourlyVolume =       {50,40,90,10,5,100,40,20,50, 50,40,90,10,5,100,40,20,50,30,90,50,100};
        int[] fullServiceCapacity = {100,90,80,70,60,50,40,30,20,10,5};
        int[] regularServiceCapacity = {70,50,40,30,20,10,10};
        int[] minorServiceCapacity = {50,40,20,10};
        int expectedResult = 350;
        Assert.assertEquals(Dynamic.optimalLossDynamic(hourlyVolume, fullServiceCapacity, regularServiceCapacity, minorServiceCapacity), expectedResult);
    }

    @Test
    public void test_8_schedule() {
        int[] hourlyVolume =       {50,40,90,10,5,100,40,20,50, 50,40,90,10,5,100,40,20,50,30,90,50,100};
        int[] fullServiceCapacity = {100,90,80,70,60,50,40,30,20,10,5};
        int[] regularServiceCapacity = {70,50,40,30,20,10,10};
        int[] minorServiceCapacity = {50,40,20,10};
        int expectedResult = 350;
        checkServicesResult(hourlyVolume, fullServiceCapacity, regularServiceCapacity, minorServiceCapacity, expectedResult);
    }

    @Test
    public void test_9_recursive() {
        int[] hourlyVolume =       {50,40,90,10,5,100,40,20,50, 100,40,90,10,5,100,40,40,50,30,90,50,100};
        int[] fullServiceCapacity = {100,90,80,70,60,50,40,30,20,10,5,20,50};
        int[] regularServiceCapacity = {70,50,40,30,20,10,20,10,5,1};
        int[] minorServiceCapacity = {50,40,20,40,50,10,20};
        int expectedResult = 390;
        Assert.assertEquals(Recursive.optimalLossRecursive(hourlyVolume, fullServiceCapacity, regularServiceCapacity, minorServiceCapacity), expectedResult);
    }

    @Test
    public void test_9_dynamic() {
        int[] hourlyVolume =       {50,40,90,10,5,100,40,20,50, 100,40,90,10,5,100,40,40,50,30,90,50,100};
        int[] fullServiceCapacity = {100,90,80,70,60,50,40,30,20,10,5,20,50};
        int[] regularServiceCapacity = {70,50,40,30,20,10,20,10,5,1};
        int[] minorServiceCapacity = {50,40,20,40,50,10,20};
        int expectedResult = 390;
        Assert.assertEquals(Dynamic.optimalLossDynamic(hourlyVolume, fullServiceCapacity, regularServiceCapacity, minorServiceCapacity), expectedResult);
    }

    @Test
    public void test_9_schedule() {
        int[] hourlyVolume =       {50,40,90,10,5,100,40,20,50, 100,40,90,10,5,100,40,40,50,30,90,50,100};
        int[] fullServiceCapacity = {100,90,80,70,60,50,40,30,20,10,5,20,50};
        int[] regularServiceCapacity = {70,50,40,30,20,10,20,10,5,1};
        int[] minorServiceCapacity = {50,40,20,40,50,10,20};
        int expectedResult = 390;
        checkServicesResult(hourlyVolume, fullServiceCapacity, regularServiceCapacity, minorServiceCapacity, expectedResult);
    }

    @Test
    public void large_test_1_dynamic() {
        int[] hourlyVolume =       {50,40,90,10,5,100,40,20,50, 100,40,90,10,5,100,40,40,50,30,90,50,100,
                50,40,90,10,5,100,40,20,50, 100,40,90,10,5,100,40,40,50,30,90,50,100};
        int[] fullServiceCapacity = {100,90,80,70,60,50,40,30,20,10,5,20,50};
        int[] regularServiceCapacity = {70,50,40,30,20,10,20,10,5,1};
        int[] minorServiceCapacity = {50,40,20,40,50,10,20};
        Dynamic.optimalLossDynamic(hourlyVolume, fullServiceCapacity, regularServiceCapacity, minorServiceCapacity);
    }

    @Test
    public void large_test_1_schedule() {
        int[] hourlyVolume =       {50,40,90,10,5,100,40,20,50, 100,40,90,10,5,100,40,40,50,30,90,50,100,
                50,40,90,10,5,100,40,20,50, 100,40,90,10,5,100,40,40,50,30,90,50,100};
        int[] fullServiceCapacity = {100,90,80,70,60,50,40,30,20,10,5,20,50};
        int[] regularServiceCapacity = {70,50,40,30,20,10,20,10,5,1};
        int[] minorServiceCapacity = {50,40,20,40,50,10,20};
        checkServicesResult(hourlyVolume, fullServiceCapacity, regularServiceCapacity, minorServiceCapacity,
                Dynamic.optimalLossDynamic(hourlyVolume, fullServiceCapacity, regularServiceCapacity, minorServiceCapacity));

    }

    @Test
    public void large_test_2_dynamic() {
        int[] hourlyVolume =       {50,40,90,10,5,100,40,20,50, 100,40,90,10,5,100,40,40,50,30,90,50,100,
                50,40,90,10,5,100,40,20,50, 100,40,90,10,5,100,40,40,50,30,90,50,100, 50,40,90,10,5,100,40,20,50, 100,40,90,10,5,100,40,40,50,30,90,50,100,
                50,40,90,10,5,100,40,20,50, 100,40,90,10,5,100,40,40,50,30,90,50,100, 50,40,90,10,5,100,40,20,50, 100,40,90,10,5,100,40,40,50,30,90,50,100,
                50,40,90,10,5,100,40,20,50, 100,40,90,10,5,100,40,40,50,30,90,50,100};
        int[] fullServiceCapacity = {100,90,80,70,60,50,40,30,20,10,5,20,50};
        int[] regularServiceCapacity = {70,50,40,30,20,10,20,10,5,1};
        int[] minorServiceCapacity = {50,40,20,40,50,10,20};
        Dynamic.optimalLossDynamic(hourlyVolume, fullServiceCapacity, regularServiceCapacity, minorServiceCapacity);
    }

    @Test
    public void large_test_2_schedule() {
        int[] hourlyVolume =       {50,40,90,10,5,100,40,20,50, 100,40,90,10,5,100,40,40,50,30,90,50,100,
                50,40,90,10,5,100,40,20,50, 100,40,90,10,5,100,40,40,50,30,90,50,100, 50,40,90,10,5,100,40,20,50, 100,40,90,10,5,100,40,40,50,30,90,50,100,
                50,40,90,10,5,100,40,20,50, 100,40,90,10,5,100,40,40,50,30,90,50,100, 50,40,90,10,5,100,40,20,50, 100,40,90,10,5,100,40,40,50,30,90,50,100,
                50,40,90,10,5,100,40,20,50, 100,40,90,10,5,100,40,40,50,30,90,50,100};
        int[] fullServiceCapacity = {100,90,80,70,60,50,40,30,20,10,5,20,50};
        int[] regularServiceCapacity = {70,50,40,30,20,10,20,10,5,1};
        int[] minorServiceCapacity = {50,40,20,40,50,10,20};
        checkServicesResult(hourlyVolume, fullServiceCapacity, regularServiceCapacity, minorServiceCapacity,
                Dynamic.optimalLossDynamic(hourlyVolume, fullServiceCapacity, regularServiceCapacity, minorServiceCapacity));
    }

    @Test
    public void large_test_3_dynamic() {
        int[] hourlyVolume =       {50,40,90,10,5,100,40,20,50, 100,40,90,10,5,100,40,40,50,30,90,50,100,
                50,40,90,10,5,100,40,20,50, 100,40,90,10,5,100,40,40,50,30,90,50,100, 50,40,90,10,5,100,40,20,50, 100,40,90,10,5,100,40,40,50,30,90,50,100,
                50,40,90,10,5,100,40,20,50, 100,40,90,10,5,100,40,40,50,30,90,50,100, 50,40,90,10,5,100,40,20,50, 100,40,90,10,5,100,40,40,50,30,90,50,100,
                50,40,90,10,5,100,40,20,50, 100,40,90,10,5,100,40,40,50,30,90,50,100,50,40,90,10,5,100,40,20,50, 100,40,90,10,5,100,40,40,50,30,90,50,100,
                50,40,90,10,5,100,40,20,50, 100,40,90,10,5,100,40,40,50,30,90,50,100, 50,40,90,10,5,100,40,20,50, 100,40,90,10,5,100,40,40,50,30,90,50,100,
                50,40,90,10,5,100,40,20,50, 100,40,90,10,5,100,40,40,50,30,90,50,100, 50,40,90,10,5,100,40,20,50, 100,40,90,10,5,100,40,40,50,30,90,50,100,
                50,40,90,10,5,100,40,20,50, 100,40,90,10,5,100,40,40,50,30,90,50,100,50,40,90,10,5,100,40,20,50, 100,40,90,10,5,100,40,40,50,30,90,50,100,
                50,40,90,10,5,100,40,20,50, 100,40,90,10,5,100,40,40,50,30,90,50,100, 50,40,90,10,5,100,40,20,50, 100,40,90,10,5,100,40,40,50,30,90,50,100,
                50,40,90,10,5,100,40,20,50, 100,40,90,10,5,100,40,40,50,30,90,50,100, 50,40,90,10,5,100,40,20,50, 100,40,90,10,5,100,40,40,50,30,90,50,100,
                50,40,90,10,5,100,40,20,50, 100,40,90,10,5,100,40,40,50,30,90,50,100,50,40,90,10,5,100,40,20,50, 100,40,90,10,5,100,40,40,50,30,90,50,100,
                50,40,90,10,5,100,40,20,50, 100,40,90,10,5,100,40,40,50,30,90,50,100, 50,40,90,10,5,100,40,20,50, 100,40,90,10,5,100,40,40,50,30,90,50,100,
                50,40,90,10,5,100,40,20,50, 100,40,90,10,5,100,40,40,50,30,90,50,100, 50,40,90,10,5,100,40,20,50, 100,40,90,10,5,100,40,40,50,30,90,50,100,
                50,40,90,10,5,100,40,20,50, 100,40,90,10,5,100,40,40,50,30,90,50,100,50,40,90,10,5,100,40,20,50, 100,40,90,10,5,100,40,40,50,30,90,50,100,
                50,40,90,10,5,100,40,20,50, 100,40,90,10,5,100,40,40,50,30,90,50,100, 50,40,90,10,5,100,40,20,50, 100,40,90,10,5,100,40,40,50,30,90,50,100,
                50,40,90,10,5,100,40,20,50, 100,40,90,10,5,100,40,40,50,30,90,50,100, 50,40,90,10,5,100,40,20,50, 100,40,90,10,5,100,40,40,50,30,90,50,100,
                50,40,90,10,5,100,40,20,50, 100,40,90,10,5,100,40,40,50,30,90,50,100,50,40,90,10,5,100,40,20,50, 100,40,90,10,5,100,40,40,50,30,90,50,100,
                50,40,90,10,5,100,40,20,50, 100,40,90,10,5,100,40,40,50,30,90,50,100, 50,40,90,10,5,100,40,20,50, 100,40,90,10,5,100,40,40,50,30,90,50,100,
                50,40,90,10,5,100,40,20,50, 100,40,90,10,5,100,40,40,50,30,90,50,100, 50,40,90,10,5,100,40,20,50, 100,40,90,10,5,100,40,40,50,30,90,50,100,
                50,40,90,10,5,100,40,20,50, 100,40,90,10,5,100,40,40,50,30,90,50,100,50,40,90,10,5,100,40,20,50, 100,40,90,10,5,100,40,40,50,30,90,50,100,
                50,40,90,10,5,100,40,20,50, 100,40,90,10,5,100,40,40,50,30,90,50,100, 50,40,90,10,5,100,40,20,50, 100,40,90,10,5,100,40,40,50,30,90,50,100,
                50,40,90,10,5,100,40,20,50, 100,40,90,10,5,100,40,40,50,30,90,50,100, 50,40,90,10,5,100,40,20,50, 100,40,90,10,5,100,40,40,50,30,90,50,100,
                50,40,90,10,5,100,40,20,50, 100,40,90,10,5,100,40,40,50,30,90,50,100,50,40,90,10,5,100,40,20,50, 100,40,90,10,5,100,40,40,50,30,90,50,100,
                50,40,90,10,5,100,40,20,50, 100,40,90,10,5,100,40,40,50,30,90,50,100, 50,40,90,10,5,100,40,20,50, 100,40,90,10,5,100,40,40,50,30,90,50,100,
                50,40,90,10,5,100,40,20,50, 100,40,90,10,5,100,40,40,50,30,90,50,100, 50,40,90,10,5,100,40,20,50, 100,40,90,10,5,100,40,40,50,30,90,50,100,
                50,40,90,10,5,100,40,20,50, 100,40,90,10,5,100,40,40,50,30,90,50,100,50,40,90,10,5,100,40,20,50, 100,40,90,10,5,100,40,40,50,30,90,50,100,
                50,40,90,10,5,100,40,20,50, 100,40,90,10,5,100,40,40,50,30,90,50,100, 50,40,90,10,5,100,40,20,50, 100,40,90,10,5,100,40,40,50,30,90,50,100,
                50,40,90,10,5,100,40,20,50, 100,40,90,10,5,100,40,40,50,30,90,50,100, 50,40,90,10,5,100,40,20,50, 100,40,90,10,5,100,40,40,50,30,90,50,100,
                50,40,90,10,5,100,40,20,50, 100,40,90,10,5,100,40,40,50,30,90,50,100,50,40,90,10,5,100,40,20,50, 100,40,90,10,5,100,40,40,50,30,90,50,100,
                50,40,90,10,5,100,40,20,50, 100,40,90,10,5,100,40,40,50,30,90,50,100, 50,40,90,10,5,100,40,20,50, 100,40,90,10,5,100,40,40,50,30,90,50,100,
                50,40,90,10,5,100,40,20,50, 100,40,90,10,5,100,40,40,50,30,90,50,100, 50,40,90,10,5,100,40,20,50, 100,40,90,10,5,100,40,40,50,30,90,50,100,
                50,40,90,10,5,100,40,20,50, 100,40,90,10,5,100,40,40,50,30,90,50,100,50,40,90,10,5,100,40,20,50, 100,40,90,10,5,100,40,40,50,30,90,50,100,
                50,40,90,10,5,100,40,20,50, 100,40,90,10,5,100,40,40,50,30,90,50,100, 50,40,90,10,5,100,40,20,50, 100,40,90,10,5,100,40,40,50,30,90,50,100,
                50,40,90,10,5,100,40,20,50, 100,40,90,10,5,100,40,40,50,30,90,50,100, 50,40,90,10,5,100,40,20,50, 100,40,90,10,5,100,40,40,50,30,90,50,100,
                50,40,90,10,5,100,40,20,50, 100,40,90,10,5,100,40,40,50,30,90,50,100,50,40,90,10,5,100,40,20,50, 100,40,90,10,5,100,40,40,50,30,90,50,100,
                50,40,90,10,5,100,40,20,50, 100,40,90,10,5,100,40,40,50,30,90,50,100, 50,40,90,10,5,100,40,20,50, 100,40,90,10,5,100,40,40,50,30,90,50,100,
                50,40,90,10,5,100,40,20,50, 100,40,90,10,5,100,40,40,50,30,90,50,100, 50,40,90,10,5,100,40,20,50, 100,40,90,10,5,100,40,40,50,30,90,50,100,
                50,40,90,10,5,100,40,20,50, 100,40,90,10,5,100,40,40,50,30,90,50,100,50,40,90,10,5,100,40,20,50, 100,40,90,10,5,100,40,40,50,30,90,50,100,
                50,40,90,10,5,100,40,20,50, 100,40,90,10,5,100,40,40,50,30,90,50,100, 50,40,90,10,5,100,40,20,50, 100,40,90,10,5,100,40,40,50,30,90,50,100,
                50,40,90,10,5,100,40,20,50, 100,40,90,10,5,100,40,40,50,30,90,50,100, 50,40,90,10,5,100,40,20,50, 100,40,90,10,5,100,40,40,50,30,90,50,100,
                50,40,90,10,5,100,40,20,50, 100,40,90,10,5,100,40,40,50,30,90,50,100,50,40,90,10,5,100,40,20,50, 100,40,90,10,5,100,40,40,50,30,90,50,100,
                50,40,90,10,5,100,40,20,50, 100,40,90,10,5,100,40,40,50,30,90,50,100, 50,40,90,10,5,100,40,20,50, 100,40,90,10,5,100,40,40,50,30,90,50,100,
                50,40,90,10,5,100,40,20,50, 100,40,90,10,5,100,40,40,50,30,90,50,100, 50,40,90,10,5,100,40,20,50, 100,40,90,10,5,100,40,40,50,30,90,50,100,
                50,40,90,10,5,100,40,20,50, 100,40,90,10,5,100,40,40,50,30,90,50,100,50,40,90,10,5,100,40,20,50, 100,40,90,10,5,100,40,40,50,30,90,50,100,
                50,40,90,10,5,100,40,20,50, 100,40,90,10,5,100,40,40,50,30,90,50,100, 50,40,90,10,5,100,40,20,50, 100,40,90,10,5,100,40,40,50,30,90,50,100,
                50,40,90,10,5,100,40,20,50, 100,40,90,10,5,100,40,40,50,30,90,50,100, 50,40,90,10,5,100,40,20,50, 100,40,90,10,5,100,40,40,50,30,90,50,100,
                50,40,90,10,5,100,40,20,50, 100,40,90,10,5,100,40,40,50,30,90,50,100};
        int[] fullServiceCapacity = {100,90,80,70,60,50,40,30,20,10,5,20,50,100,90,80,70,60,50,40,30,20,10,5,20,50,100,90,80,70,60,50,40,30,20,10,5,20,50};
        int[] regularServiceCapacity = {70,50,70,50,40,30,20,10,20,10,5,1,70,50,40,30,20,10,20,10,5,140,30,20,10,20,10,5,1};
        int[] minorServiceCapacity = {50,40,20,40,50,10,20,50,40,20,40,50,10,20};
        Dynamic.optimalLossDynamic(hourlyVolume, fullServiceCapacity, regularServiceCapacity, minorServiceCapacity);
    }

    @Test
    public void large_test_3_schedule() {
        int[] hourlyVolume =       {50,40,90,10,5,100,40,20,50, 100,40,90,10,5,100,40,40,50,30,90,50,100,
                50,40,90,10,5,100,40,20,50, 100,40,90,10,5,100,40,40,50,30,90,50,100, 50,40,90,10,5,100,40,20,50, 100,40,90,10,5,100,40,40,50,30,90,50,100,
                50,40,90,10,5,100,40,20,50, 100,40,90,10,5,100,40,40,50,30,90,50,100, 50,40,90,10,5,100,40,20,50, 100,40,90,10,5,100,40,40,50,30,90,50,100,
                50,40,90,10,5,100,40,20,50, 100,40,90,10,5,100,40,40,50,30,90,50,100,50,40,90,10,5,100,40,20,50, 100,40,90,10,5,100,40,40,50,30,90,50,100,
                50,40,90,10,5,100,40,20,50, 100,40,90,10,5,100,40,40,50,30,90,50,100, 50,40,90,10,5,100,40,20,50, 100,40,90,10,5,100,40,40,50,30,90,50,100,
                50,40,90,10,5,100,40,20,50, 100,40,90,10,5,100,40,40,50,30,90,50,100, 50,40,90,10,5,100,40,20,50, 100,40,90,10,5,100,40,40,50,30,90,50,100,
                50,40,90,10,5,100,40,20,50, 100,40,90,10,5,100,40,40,50,30,90,50,100,50,40,90,10,5,100,40,20,50, 100,40,90,10,5,100,40,40,50,30,90,50,100,
                50,40,90,10,5,100,40,20,50, 100,40,90,10,5,100,40,40,50,30,90,50,100, 50,40,90,10,5,100,40,20,50, 100,40,90,10,5,100,40,40,50,30,90,50,100,
                50,40,90,10,5,100,40,20,50, 100,40,90,10,5,100,40,40,50,30,90,50,100, 50,40,90,10,5,100,40,20,50, 100,40,90,10,5,100,40,40,50,30,90,50,100,
                50,40,90,10,5,100,40,20,50, 100,40,90,10,5,100,40,40,50,30,90,50,100,50,40,90,10,5,100,40,20,50, 100,40,90,10,5,100,40,40,50,30,90,50,100,
                50,40,90,10,5,100,40,20,50, 100,40,90,10,5,100,40,40,50,30,90,50,100, 50,40,90,10,5,100,40,20,50, 100,40,90,10,5,100,40,40,50,30,90,50,100,
                50,40,90,10,5,100,40,20,50, 100,40,90,10,5,100,40,40,50,30,90,50,100, 50,40,90,10,5,100,40,20,50, 100,40,90,10,5,100,40,40,50,30,90,50,100,
                50,40,90,10,5,100,40,20,50, 100,40,90,10,5,100,40,40,50,30,90,50,100,50,40,90,10,5,100,40,20,50, 100,40,90,10,5,100,40,40,50,30,90,50,100,
                50,40,90,10,5,100,40,20,50, 100,40,90,10,5,100,40,40,50,30,90,50,100, 50,40,90,10,5,100,40,20,50, 100,40,90,10,5,100,40,40,50,30,90,50,100,
                50,40,90,10,5,100,40,20,50, 100,40,90,10,5,100,40,40,50,30,90,50,100, 50,40,90,10,5,100,40,20,50, 100,40,90,10,5,100,40,40,50,30,90,50,100,
                50,40,90,10,5,100,40,20,50, 100,40,90,10,5,100,40,40,50,30,90,50,100,50,40,90,10,5,100,40,20,50, 100,40,90,10,5,100,40,40,50,30,90,50,100,
                50,40,90,10,5,100,40,20,50, 100,40,90,10,5,100,40,40,50,30,90,50,100, 50,40,90,10,5,100,40,20,50, 100,40,90,10,5,100,40,40,50,30,90,50,100,
                50,40,90,10,5,100,40,20,50, 100,40,90,10,5,100,40,40,50,30,90,50,100, 50,40,90,10,5,100,40,20,50, 100,40,90,10,5,100,40,40,50,30,90,50,100,
                50,40,90,10,5,100,40,20,50, 100,40,90,10,5,100,40,40,50,30,90,50,100,50,40,90,10,5,100,40,20,50, 100,40,90,10,5,100,40,40,50,30,90,50,100,
                50,40,90,10,5,100,40,20,50, 100,40,90,10,5,100,40,40,50,30,90,50,100, 50,40,90,10,5,100,40,20,50, 100,40,90,10,5,100,40,40,50,30,90,50,100,
                50,40,90,10,5,100,40,20,50, 100,40,90,10,5,100,40,40,50,30,90,50,100, 50,40,90,10,5,100,40,20,50, 100,40,90,10,5,100,40,40,50,30,90,50,100,
                50,40,90,10,5,100,40,20,50, 100,40,90,10,5,100,40,40,50,30,90,50,100,50,40,90,10,5,100,40,20,50, 100,40,90,10,5,100,40,40,50,30,90,50,100,
                50,40,90,10,5,100,40,20,50, 100,40,90,10,5,100,40,40,50,30,90,50,100, 50,40,90,10,5,100,40,20,50, 100,40,90,10,5,100,40,40,50,30,90,50,100,
                50,40,90,10,5,100,40,20,50, 100,40,90,10,5,100,40,40,50,30,90,50,100, 50,40,90,10,5,100,40,20,50, 100,40,90,10,5,100,40,40,50,30,90,50,100,
                50,40,90,10,5,100,40,20,50, 100,40,90,10,5,100,40,40,50,30,90,50,100,50,40,90,10,5,100,40,20,50, 100,40,90,10,5,100,40,40,50,30,90,50,100,
                50,40,90,10,5,100,40,20,50, 100,40,90,10,5,100,40,40,50,30,90,50,100, 50,40,90,10,5,100,40,20,50, 100,40,90,10,5,100,40,40,50,30,90,50,100,
                50,40,90,10,5,100,40,20,50, 100,40,90,10,5,100,40,40,50,30,90,50,100, 50,40,90,10,5,100,40,20,50, 100,40,90,10,5,100,40,40,50,30,90,50,100,
                50,40,90,10,5,100,40,20,50, 100,40,90,10,5,100,40,40,50,30,90,50,100,50,40,90,10,5,100,40,20,50, 100,40,90,10,5,100,40,40,50,30,90,50,100,
                50,40,90,10,5,100,40,20,50, 100,40,90,10,5,100,40,40,50,30,90,50,100, 50,40,90,10,5,100,40,20,50, 100,40,90,10,5,100,40,40,50,30,90,50,100,
                50,40,90,10,5,100,40,20,50, 100,40,90,10,5,100,40,40,50,30,90,50,100, 50,40,90,10,5,100,40,20,50, 100,40,90,10,5,100,40,40,50,30,90,50,100,
                50,40,90,10,5,100,40,20,50, 100,40,90,10,5,100,40,40,50,30,90,50,100,50,40,90,10,5,100,40,20,50, 100,40,90,10,5,100,40,40,50,30,90,50,100,
                50,40,90,10,5,100,40,20,50, 100,40,90,10,5,100,40,40,50,30,90,50,100, 50,40,90,10,5,100,40,20,50, 100,40,90,10,5,100,40,40,50,30,90,50,100,
                50,40,90,10,5,100,40,20,50, 100,40,90,10,5,100,40,40,50,30,90,50,100, 50,40,90,10,5,100,40,20,50, 100,40,90,10,5,100,40,40,50,30,90,50,100,
                50,40,90,10,5,100,40,20,50, 100,40,90,10,5,100,40,40,50,30,90,50,100,50,40,90,10,5,100,40,20,50, 100,40,90,10,5,100,40,40,50,30,90,50,100,
                50,40,90,10,5,100,40,20,50, 100,40,90,10,5,100,40,40,50,30,90,50,100, 50,40,90,10,5,100,40,20,50, 100,40,90,10,5,100,40,40,50,30,90,50,100,
                50,40,90,10,5,100,40,20,50, 100,40,90,10,5,100,40,40,50,30,90,50,100, 50,40,90,10,5,100,40,20,50, 100,40,90,10,5,100,40,40,50,30,90,50,100,
                50,40,90,10,5,100,40,20,50, 100,40,90,10,5,100,40,40,50,30,90,50,100,50,40,90,10,5,100,40,20,50, 100,40,90,10,5,100,40,40,50,30,90,50,100,
                50,40,90,10,5,100,40,20,50, 100,40,90,10,5,100,40,40,50,30,90,50,100, 50,40,90,10,5,100,40,20,50, 100,40,90,10,5,100,40,40,50,30,90,50,100,
                50,40,90,10,5,100,40,20,50, 100,40,90,10,5,100,40,40,50,30,90,50,100, 50,40,90,10,5,100,40,20,50, 100,40,90,10,5,100,40,40,50,30,90,50,100,
                50,40,90,10,5,100,40,20,50, 100,40,90,10,5,100,40,40,50,30,90,50,100,50,40,90,10,5,100,40,20,50, 100,40,90,10,5,100,40,40,50,30,90,50,100,
                50,40,90,10,5,100,40,20,50, 100,40,90,10,5,100,40,40,50,30,90,50,100, 50,40,90,10,5,100,40,20,50, 100,40,90,10,5,100,40,40,50,30,90,50,100,
                50,40,90,10,5,100,40,20,50, 100,40,90,10,5,100,40,40,50,30,90,50,100, 50,40,90,10,5,100,40,20,50, 100,40,90,10,5,100,40,40,50,30,90,50,100,
                50,40,90,10,5,100,40,20,50, 100,40,90,10,5,100,40,40,50,30,90,50,100,50,40,90,10,5,100,40,20,50, 100,40,90,10,5,100,40,40,50,30,90,50,100,
                50,40,90,10,5,100,40,20,50, 100,40,90,10,5,100,40,40,50,30,90,50,100, 50,40,90,10,5,100,40,20,50, 100,40,90,10,5,100,40,40,50,30,90,50,100,
                50,40,90,10,5,100,40,20,50, 100,40,90,10,5,100,40,40,50,30,90,50,100, 50,40,90,10,5,100,40,20,50, 100,40,90,10,5,100,40,40,50,30,90,50,100,
                50,40,90,10,5,100,40,20,50, 100,40,90,10,5,100,40,40,50,30,90,50,100};
        int[] fullServiceCapacity = {100,90,80,70,60,50,40,30,20,10,5,20,50,100,90,80,70,60,50,40,30,20,10,5,20,50,100,90,80,70,60,50,40,30,20,10,5,20,50};
        int[] regularServiceCapacity = {70,50,70,50,40,30,20,10,20,10,5,1,70,50,40,30,20,10,20,10,5,140,30,20,10,20,10,5,1};
        int[] minorServiceCapacity = {50,40,20,40,50,10,20,50,40,20,40,50,10,20};
        checkServicesResult(hourlyVolume, fullServiceCapacity, regularServiceCapacity, minorServiceCapacity,
                Dynamic.optimalLossDynamic(hourlyVolume, fullServiceCapacity, regularServiceCapacity, minorServiceCapacity));
    }

    @Test
    public void simplerTest() {
        int previousHour = 2 - 1;
        String a = "";
        a = previousHour+ "-0-3%n";
        System.out.printf(a);
    }


    /**
     * Check that Dynamic.optimalActivitiesDynamic produces a valid list of services that would
     * produce the expectedResult
     */
    public static void checkServicesResult(int[] hourlyVolume, int[] fullServiceCapacity, int[] regularServiceCapacity,
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

        Assert.assertEquals(hourlyVolume.length, services.length); //they should be the same length

        //check that full services come in blocks of 4 and regular services come in blocks of 2
        int hour = 0;
        while (hour < services.length) {
            if (services[hour] == null || services[hour] == Service.MINOR_SERVICE) {
                hour += 1;
            } else if (services[hour] == Service.FULL_SERVICE) {
                for (int extra = 1; extra < 4; extra++) {
                    Assert.assertEquals(services[hour + extra], Service.FULL_SERVICE);
                }
                hour += 4; //skip over the full services
            } else if (services[hour] == Service.REGULAR_SERVICE) {
                Assert.assertEquals(services[hour + 1], Service.REGULAR_SERVICE);
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
