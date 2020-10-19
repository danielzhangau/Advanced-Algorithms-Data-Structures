package assignment2;

public class Recursive {

    /**
     * Returns the least cost that can be incurred by your company over the
     * k = hourlyVolume.length hours (i.e hour 0 to hour k-1) that you are
     * in charge of the pump. Given that a full service concluded the hour
     * before you were placed in charge of the system (i.e finished one hour
     * before hour 0), given parameters hourlyVolume, fullServiceCapacity,
     * regularServiceCapacity and minorServiceCapacity
     *
     * (See handout for details)
     *
     * This method must be implemented using a recursive programming solution to
     * this problem. It is expected to have a worst-case running time that is
     * exponential in k
     *
     * @require the arrays hourlyVolume, fullServiceCapacity, regularServiceCapacity
     * and minorServiceCapacity are not null, and do not contain null values. Each
     * of the values in all arrays are non-negative (greater than or equal to 0).
     * fullServiceCapacity.length > 0, regularServiceCapacity.length > 0,
     * minorServiceCapacity.length > 0
     *
     * @ensure Returns the least cost that can be incurred by your company over the
     * k = hourlyVolume.length hours (i.e hour 0 to hour k-1) that you are
     * in charge of the pump. Given that a full service concluded the hour
     * before you were placed in charge of the system (i.e finished one hour
     * before hour 0), given parameters hourlyVolume, fullServiceCapacity,
     * regularServiceCapacity and minorServiceCapacity
     */
    public static int optimalLossRecursive(int[] hourlyVolume,
            int[] fullServiceCapacity, int [] regularServiceCapacity, int[] minorServiceCapacity) {
        // IMPLEMENT THIS METHOD BY IMPLEMENTING THE PRIVATE METHOD IN THIS
        // CLASS THAT HAS THE SAME NAME
        return optimalLossRecursive(hourlyVolume, fullServiceCapacity, regularServiceCapacity, minorServiceCapacity,
                0, Service.FULL_SERVICE, 0);
    }

    /**
     * Given parameters hourlyVolume, fullServiceCapacity, regularServiceCapacity and
     * minorServiceCapacity, return the least cost that can be incurred from hour
     * "currentHour" to hour "k-1" (inclusive) of the hours you are in charge of the pump
     * (where k = hourlyVolume.length), given that the last maintenance activity before hour
     * "currentHour" is given by parameter "lastService", and that it occurred
     * "hoursSinceService" hours before hour "currentHour".
     *
     * (See handout for details)
     *
     * This method must be implemented using a recursive programming solution to
     * this problem. It is expected to have a worst-case running time that is
     * exponential in k
     *
     * @require the arrays hourlyVolume, fullServiceCapacity, regularServiceCapacity
     * and minorServiceCapacity are not null, and do not contain null values. Each
     * of the values in all arrays are non-negative (greater than or equal to 0).
     * fullServiceCapacity.length > 0, regularServiceCapacity > 0, minorServiceCapacity > 0
     *
     * @ensure Returns the least cost that can be incurred by your company over the
     * k = hourlyVolume.length hours (i.e hour 0 to hour k-1) that you are
     * in charge of the pump. Given that a full service concluded the hour
     * before you were placed in charge of the system (i.e finished one hour
     * before hour 0), given parameters hourlyVolume, fullServiceCapacity,
     * regularServiceCapacity and minorServiceCapacity
     */
    private static int optimalLossRecursive(int[] hourlyVolume,
            int[] fullServiceCapacity, int [] regularServiceCapacity,
            int[] minorServiceCapacity, int currentHour, Service lastService, int hoursSinceService) {
        return -1; //REMOVE THIS LINE AND WRITE THIS METHOD
    }
}
