package assignment2;

import java.util.*;

public class Dynamic {

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
     * This method must be implemented using an efficient bottom-up dynamic programming
     * solution to the problem (not memoised)
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
    public static int optimalLossDynamic(int[] hourlyVolume,
            int[] fullServiceCapacity, int [] regularServiceCapacity, int[] minorServiceCapacity) {
        return -1; // REMOVE THIS LINE AND WRITE THIS METHOD
    }


    /**
     * Returns a schedule of the services that should take place on each of the k
     * = hourlyVolume.length hours that you are in charge of the pump, that guarantees
     * that the least possible cost will be incurred by your company over these k
     * hours (given parameters hourlyVolume, fullServiceCapacity, regularServiceCapacity
     * and minorServiceCapacity)
     *
     * The schedule should be an array of services of length k, where for each array index
     * i, for 0 <= i < k, the value of the array at index i should be the service that is in
     * progress at that hour (Service.FULL_SERVICE, Service.REGULAR_SERVICE, Service.MINOR_SERVICE)
     * if there is a service or null if there is no service taking place at that time.
     *
     * For example, with a k value of 8, the return value
     * [null, null, REGULAR_SERVICE, REGULAR_SERVICE, null, null, MINOR_SERVICE, null]
     * represents a schedule where a regular service is conducted that takes place through the
     * third and fourth hours (hours 2 and 3) and a minor service is conducted in the seventh hour
     * (hour 6) and no services are conducted during the other hours.
     *
     * You should assume that a full service was completed the hour before you took control
     * of the pump (i.e 1 hour before hour 0)
     *
     * (See handout for details.)
     *
     * This method must be implemented using an efficient bottom-up dynamic programming solution
     * to the problem (not memoised)
     *
     * @require the arrays hourlyVolume, fullServiceCapacity, regularServiceCapacity
     * and minorServiceCapacity are not null, and do not contain null values. Each
     * of the values in all arrays are non-negative (greater than or equal to 0).
     * fullServiceCapacity.length > 0, regularServiceCapacity.length > 0,
     * minorServiceCapacity.length > 0
     *
     * @ensure Returns a schedule of the services that should take place on each of the k
     * = hourlyVolume.length hours that you are in charge of the pump, that guarantees
     * that the least possible cost will be incurred by your company over these k
     * hours (given parameters hourlyVolume, fullServiceCapacity, regularServiceCapacity
     * and minorServiceCapacity)
     */
    public static Service[] optimalServicesDynamic(int[] hourlyVolume,
            int[] fullServiceCapacity, int [] regularServiceCapacity, int[] minorServiceCapacity) {
        return null; // REMOVE THIS LINE AND WRITE THIS METHOD
    }

}
