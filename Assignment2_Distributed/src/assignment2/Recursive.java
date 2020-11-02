package assignment2;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.Objects;

public class Recursive {

    /**
     * Returns the capacity of the system i days after the last maintenance
     * service, given that the the last maintenance service has the given
     * capacity array.
     */
    private static int getCurrentCapacity(int[] capacity, int hoursSinceService) {
        if (hoursSinceService < capacity.length) {
            return capacity[hoursSinceService];
        } else {
            return 0;
        }
    }

    /**
     * State class to store each possible node
     */
    static class State {
        int currentHour;
        Service lastService;
        int hoursSinceService;

        public State(int currentHour, Service lastService, int hoursSinceService) {
            this.currentHour = currentHour;
            this.lastService = lastService;
            this.hoursSinceService = hoursSinceService;
        }

        public State performActivity(Service service) {
            if (service == null) {
                return new State(
                    currentHour,
                    lastService, // persist the lastService
                    hoursSinceService
                );
            } else if (service == Service.FULL_SERVICE) {
                return new State(
                    currentHour,
                    Service.FULL_SERVICE, // perform new activity
                    0 // reset hoursSinceService
                );
            } else if (service == Service.REGULAR_SERVICE) {
                return new State(
                    currentHour,
                    Service.REGULAR_SERVICE, // perform new activity
                    0 // reset hoursSinceService
                );
            } else if (service == Service.MINOR_SERVICE){
                return new State(
                    currentHour,
                    Service.MINOR_SERVICE, // perform new activity
                    0 // reset hoursSinceService
                );
            } else {
                System.out.println("error not equal");
                return null;
            }
        }

        public int loss(int[] fullServiceCapacity, int[] regularServiceCapacity,
            int[] minorServiceCapacity, int[] hourlyVolume) {
            int capacity = 0;
            if (lastService == Service.FULL_SERVICE) {
                capacity = getCurrentCapacity(fullServiceCapacity, hoursSinceService);
            } else if (lastService == Service.REGULAR_SERVICE) {
                capacity = getCurrentCapacity(regularServiceCapacity, hoursSinceService);
            } else if (lastService == Service.MINOR_SERVICE) {
                capacity = getCurrentCapacity(minorServiceCapacity, hoursSinceService);
            }

            int demand = hourlyVolume[currentHour];
            // avoid negative number
            return Math.max(0, demand - capacity);
        }

        public State incrementHour() {
            return new State(
                currentHour + 1,
                lastService,
                hoursSinceService + 1
            );
        }
    }

    static class Entry {
        int loss;
        Service service;

        public Entry(int loss, Service service) {
            this.loss = loss;
            this.service = service;
        }

        public int getLoss() {
            return loss;
        }
    }

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
        // initialize three int arrays which has its service time (start use after 1 hr)
        int[] newFull = new int[fullServiceCapacity.length + 4];
        for (int i = 0; i < 4; i++) newFull[i] = 0; // {0,0,0,0,...}
        System.arraycopy(fullServiceCapacity, 0, newFull, 4, fullServiceCapacity.length);
        int[] newRegular = new int[regularServiceCapacity.length + 2];
        for (int i = 0; i < 2; i++) newRegular[i] = 0; // {0,0,...}
        System.arraycopy(regularServiceCapacity, 0, newRegular, 2, regularServiceCapacity.length);
        int[] newMinor = new int[minorServiceCapacity.length + 1];
        for (int i = 0; i < 1; i++) newMinor[i] = 0; // {0,...}
        System.arraycopy(minorServiceCapacity, 0, newMinor, 1, minorServiceCapacity.length);

        return optimalLossRecursive(hourlyVolume, newFull, newRegular, newMinor,
            0, Service.FULL_SERVICE, 4);
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
        int k = hourlyVolume.length;
        // termination condition
        if (currentHour == k) return 0;

        // current state
        State state = new State(currentHour, lastService, hoursSinceService);

        ArrayList<Service> activities = new ArrayList<>();
        activities.add(Service.FULL_SERVICE);
        activities.add(Service.REGULAR_SERVICE);
        activities.add(Service.MINOR_SERVICE);
        activities.add(null);

//        // calculate current loss (for hr0 always full service)
//        int currentLoss = state.loss(fullServiceCapacity,
//            regularServiceCapacity, minorServiceCapacity, hourlyVolume);

        ArrayList<Entry> candidates = new ArrayList<>();
        for (Service activity : activities) {
            // calculate current loss (for hr0 always full service)
            int currentLoss = state.performActivity(activity).loss(fullServiceCapacity,
                regularServiceCapacity, minorServiceCapacity, hourlyVolume);

            // next four possible service nodes
            State successor = state
                .performActivity(activity)
                .incrementHour();
            /* recursive here to process further nodes */
            int successorLoss = optimalLossRecursive(hourlyVolume, fullServiceCapacity,
                regularServiceCapacity, minorServiceCapacity,
                successor.currentHour, successor.lastService, successor.hoursSinceService);

            candidates.add(new Entry(currentLoss + successorLoss, activity));
        }

        // each time we get the smallest possible loss
        return Objects.requireNonNull(candidates
            .stream()
            .min(Comparator.comparing(Entry::getLoss))
            .orElse(null))
            .loss;
    }
}

