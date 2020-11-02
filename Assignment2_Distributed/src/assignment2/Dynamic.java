package assignment2;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;

public class Dynamic {

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
            } else if (service.equals(Service.FULL_SERVICE)) {
                return new State(
                    currentHour,
                    Service.FULL_SERVICE, // perform new activity
                    0 // reset hoursSinceService
                );
            } else if (service.equals(Service.REGULAR_SERVICE)) {
                return new State(
                    currentHour,
                    Service.REGULAR_SERVICE, // perform new activity
                    0 // reset hoursSinceService
                );
            } else {
                return new State(
                    currentHour,
                    Service.MINOR_SERVICE, // perform new activity
                    0 // reset hoursSinceService
                );
            }
        }

        public int loss(int[] fullServiceCapacity, int[] regularServiceCapacity,
            int[] minorServiceCapacity, int[] hourlyVolume) {
            int capacity = 0;
            if (lastService.equals(Service.FULL_SERVICE)) {
                capacity = getCurrentCapacity(fullServiceCapacity, hoursSinceService);
            } else if (lastService.equals(Service.REGULAR_SERVICE)) {
                capacity = getCurrentCapacity(regularServiceCapacity, hoursSinceService);
            } else if (lastService.equals(Service.MINOR_SERVICE)) {
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

        @Override
        public int hashCode() {
            int result = currentHour;
            result = 22 * result + lastService.hashCode();
            result = 22 * result + hoursSinceService;
            return result;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;

            State state = (State) o;

            if (currentHour != state.currentHour) return false;
            if (hoursSinceService != state.hoursSinceService) return false;
            return lastService == state.lastService;
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

    public static HashMap<State, Entry> buildMap(int[] hourlyVolume, int[] fullServiceCapacity,
            int [] regularServiceCapacity, int[] minorServiceCapacity) {
        // values mapped are the minimal costs
        HashMap<State, Entry> map = new HashMap<>();

        ArrayList<Service> services = new ArrayList<>();
        services.add(Service.FULL_SERVICE);
        services.add(Service.REGULAR_SERVICE);
        services.add(Service.MINOR_SERVICE);

        int k = hourlyVolume.length;
        // build bottom up, find the least cost from end
        for (int currentHour = k - 1; currentHour >= 0; currentHour--) {
            // we +4 since the add the 4 hr task time
            for (int hoursSinceService = 0; hoursSinceService <= currentHour + 4; hoursSinceService++) {
                for (Service lastService : services) {
                    // current state
                    State state = new State(currentHour, lastService, hoursSinceService);

                    ArrayList<Service> activities = new ArrayList<>();
                    activities.add(Service.FULL_SERVICE);
                    activities.add(Service.REGULAR_SERVICE);
                    activities.add(Service.MINOR_SERVICE);
                    activities.add(null);

                    ArrayList<Entry> candidates = new ArrayList<>();
                    for (Service activity: activities) {
                        // calculate current loss
                        int currentLoss = state.performActivity(activity).loss(fullServiceCapacity,
                            regularServiceCapacity, minorServiceCapacity, hourlyVolume);

                        // if (currentHour == k - 1) => return 0
                        Entry defaultValue = new Entry(0, null);
                        // next four possible service nodes
                        int successorLoss = map.getOrDefault(
                            state.performActivity(activity).incrementHour(),
                            defaultValue)
                            .loss;

                        candidates.add(new Entry(currentLoss + successorLoss, activity));
                    }
                    // each time we get the smallest possible loss
                    Entry minEntry = candidates
                        .stream()
                        .min(Comparator.comparing(Entry::getLoss))
                        .orElse(null);

                    map.put(state, minEntry);
                }
            }
        }
        return map;
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
        if (hourlyVolume.length == 0) {
            return 0;
        }
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

        return buildMap(hourlyVolume, newFull, newRegular, newMinor).get(
            new State(0, Service.FULL_SERVICE, 4)
        ).loss;
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

        HashMap<State, Entry> map = buildMap(hourlyVolume, newFull, newRegular, newMinor);
        Service[] services = new Service[hourlyVolume.length];

        State state = new State(0, Service.FULL_SERVICE, 4);
        Entry entry = map.get(state);
        state = state.performActivity(entry.service).incrementHour();

        int k = hourlyVolume.length;

        // USED TO TEST TESTCASE
        // services = new Service[]{null, null, null, Service.REGULAR_SERVICE, Service.REGULAR_SERVICE, null,
        //    null, Service.MINOR_SERVICE, null};

        for (int hr = 1; hr <= k + 1; hr++) {
            if (!map.containsKey(state)) {
                break;
            }
            Entry entry2 = map.get(state);
            // get next activity to perform from entry
            services[hr] = entry2.service;
            // perform and increment hour
            state = state.performActivity(entry2.service).incrementHour();
        }

        // modify services array to correct form
        int hour = 0;
        while (hour < services.length) {
            if (services[hour] == null || services[hour] == Service.MINOR_SERVICE) {
                hour += 1;
            } else if (services[hour] == Service.FULL_SERVICE) {
                for (int extra = 1; extra < 4; extra++) {
                    services[hour + extra] = Service.FULL_SERVICE;
                }
                hour += 4; //skip over the full services
            } else if (services[hour] == Service.REGULAR_SERVICE) {
                services[hour + 1] = Service.REGULAR_SERVICE;
                hour += 2; //skip over the next hour
            }
        }
        return services;
    }

}

