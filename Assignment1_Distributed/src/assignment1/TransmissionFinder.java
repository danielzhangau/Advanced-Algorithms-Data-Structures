package assignment1;

import java.util.*;
import java.util.stream.Collectors;

public class TransmissionFinder {

    /**
     * @require The list of interactions is not null and does not contain any null elements.
     *
     * @ensure The state of all interactions are NOT modified in any way
     *
     *         This method should return either:
     *
     *         a) a non-null list of interactions such that
     *          (i) each interaction is non-null and is in the input list
     *          (ii) the first interaction in the list has a personFrom identifier matching the
     *              startPerson
     *          (iii) the last interaction in the list has a personTo identifier
     *              matching the endPerson
     *          (iv) for all interactions in the list, the personTo
     *              identifier of one entry matches the personFrom identifier of the next entry
     *          (v) the time of all interactions is non-decreasing
     *          (vi) there are no other paths from the startPerson to the endPerson such
     *              that the product of their transmissionProbabilities is greater than the
     *              product of transmissionProbabilities along the returned path
     *
     *         OR
     *
     *         b) the value null if and only if no such list of interactions as described
     *         in part (a) exists
     *
     *         (See the assignment handout for details and examples)
     */

//     List<Interaction> FindTransmissionPath(int startPerson, int endPerson, List<Interaction> interactions) {
//     1     personToInteractions: HashMap<Integer, HashSet<Interaction>> = new HashMap().onLookupFail(new HashSet())
//     2     endInteractions: HashSet<Interaction> = new HashSet()
//     3     // I (number of interactions) iterations with O(1) loop body
//     4     // O(P) (number of person) Hashset construction cost, due to handshake lemma
//     5     // Overall: O(I + P)
//     6     // Worst-case: |P| = 2 * |I|, if each interaction transmit between two unique person
//     7     // 3 * |I| give us Overall O(I)
//     8     for interaction in interactions:
//     9         startInteractions: HashSet<Interaction> = personToInteractions[interaction.PersonFrom] // O(1)
//     10        startInteractions.add(interaction) // O(1)
//     11        if interaction.personTo == endPerson -> endInteractions.add(interaction)
//     12    // O(I) due to implementation limitations
//     13    sources: HashSet<Interaction> = personToInteractions[startPerson]
//
//     14    adjacency: HashMap<Interaction, HashSet<Interaction>> = new HashMap().onLookupFail(new HashSet())
//     15    // I iterations with worst-case: O(I) loop body, if successors' time >= predecessors' time
//     16    //     and successors' personFrom don't equal to predecessors' personTo
//     17    // Overall: O(I^2)
//     18    for interaction in interactions:
//     19        neighbors: HashSet<Interaction> = personToInteractions[interaction.personTo]
//     20            .filter(i -> interaction.time <= i.time && interaction.PersonFrom != i.PersonTo) // O(I)
//     21        adjacency[interaction] = neighbors // O(1)
//
//     22    // Running time for Dijkstra's algorithm using a Java Heap as a priority queue is
//     23    // O((|E| + |V|) * log|V|).
//     24    // Worst-case: |E| = |V^2|, we get O((|V^2| + |V|) * log|V|).
//     25    // as we use Interaction I as our Vertex
//     26    // so O((|V^2| + |V|) * log|V|) give us Overall O(I^2 * logI)
//     27    Dijkstra(adjacency, sources);
//     28    maximumProb: Double = personToInteractions[endPerson].
//     29        .filter(v -> v.prob != Double.MAX_VALUE).maxBy(v -> v.prob).prob
//     30    finalInteractionList: List<Interaction> = new ArrayList<>();
//     31    finalInteractionList.add(highestProbVertex.get().element);
//     32    while True // O(I)
//     33        if head is not null
//     34            finalInteractionList.add(0, head.element);
//     35            head = head.predecessor;
//     36        else // head is null (finish)
//     37            return finalInteractionList;


    public static List<Interaction> findTransmissionPath(int startPerson, int endPerson,
                                                         List<Interaction> interactions) {
        HashMap<Integer, HashSet<Interaction>> personToInteractions = new HashMap<>();
        HashMap<Interaction, Vertex<Interaction>> interactionToVertex = new HashMap<>();
        HashSet<Interaction> endInteractions = new HashSet<>();

        // I (number of interactions) iterations with O(1) loop body
        // with O(P) (number of person) Hashset construction cost
        // Overall: O(I + P)
        // Worst-case: |P| = 2 * |I|, if each interaction transmit between two unique person
        // 3 * |I| give us Overall O(I)
        for (Interaction interaction: interactions) {
            // provide value for new key which is absent using computeIfAbsent method
            // Returns: current (existing or computed) value associated with the specified key,
            // or null if map returns null.
            // we create a hashset for each possible of interaction.getPersonFrom
            HashSet<Interaction> startInteractions = personToInteractions
                .computeIfAbsent(interaction.getPersonFrom(), (k) -> new HashSet<>());
            startInteractions.add(interaction);

            interactionToVertex.put(interaction, new Vertex<>(interaction));
            // add all correct endPerson interactions to HashSet
            if (interaction.getPersonTo() == endPerson) {
                endInteractions.add(interaction);
            }
        }

        // O(I) worst-case
        HashSet<Vertex<Interaction>> sources = personToInteractions
            .computeIfAbsent(startPerson, (k) -> new HashSet<>())
            .stream()
            .map(interactionToVertex::get)
            .collect(Collectors.toCollection(HashSet::new));

        HashMap<Vertex<Interaction>, HashSet<Vertex<Interaction>>> adjacency = new HashMap<>();
        // I iterations with worst-case: O(I) loop body, if successors' time >= predecessors' time
        //     and successors' personFrom don't equal to predecessors' personTo
        // Overall: O(I^2)
        for (Interaction interaction: interactions) {
            HashSet<Vertex<Interaction>> neighbors = personToInteractions
                .computeIfAbsent(interaction.getPersonTo(), (k) -> new HashSet<>())
                .stream()
                .filter(i -> i.getTimeOfInteraction() >= interaction.getTimeOfInteraction() &&
                    i.getPersonTo() != interaction.getPersonFrom()) // otherwise it'll be a circle
                .map(interactionToVertex::get)
                .collect(Collectors.toCollection(HashSet::new));

            adjacency.put(interactionToVertex.get(interaction), neighbors);
        }

        // Running time for Dijkstra's algorithm using a Java Heap as a priority queue is
        // O((|E| + |V|) * log|V|).
        // Worst-case: |E| = |V^2|, we get O((|V^2| + |V|) * log|V|).
        // as we use Interaction I as our Vertex
        // so O((|V^2| + |V|) * log|V|) give us Overall O(I^2 * logI)
        Dijkstra(adjacency, sources);

        // O(I)
        // note: the prob could probably be the Double.MAX_VALUE if not visited, so filter them
        Optional<Vertex<Interaction>> highestProbVertex = endInteractions
            .stream()
            .map(interactionToVertex::get)
            .filter(v -> v.prob != Double.MAX_VALUE)
            .max(Comparator.comparingDouble(v -> v.prob));

        // O(I)
        if (!highestProbVertex.isPresent()) {
            return null;
        } else {
            List<Interaction> finalInteractionList = new ArrayList<>();
            double prob = highestProbVertex.get().prob;
//            System.out.println(prob);
//            System.out.println(highestProbVertex.get().element);
            finalInteractionList.add(highestProbVertex.get().element);
            Vertex<Interaction> head = highestProbVertex.get().predecessor;
            while (true) {
                if (head != null) {
//                    System.out.println(head.element);
                    finalInteractionList.add(0, head.element);
                    head = head.predecessor;
                } else {
//                    System.out.println(finalInteractionList);
                    return finalInteractionList;
                }
            }
        }
    }

    public static void Dijkstra(HashMap<Vertex<Interaction>, HashSet<Vertex<Interaction>>> adjacency,
                                HashSet<Vertex<Interaction>> sources) {
        // Java Priority Queue is implemented using Heap Data Structures
        // and Heap has O(log(n)) time complexity to insert and delete element.
        PriorityQueue<Priority<Vertex<Interaction>>> queue = new PriorityQueue<>();
        // I iterations with worst-case: O(I) loop body,
        // initialize the prob and add the new priority class
        for (Vertex<Interaction> source: sources) {
            source.prob = source.element.getTransmissionProbability();
            queue.add(new Priority<>(source, source.prob));
        }

        // Overall O((|E|+|V|)log|V|)
        // |I| times
        while (!queue.isEmpty()) {
            // the total time required to execute the main loop itself is O(V lg V)
            // O(log V)
            Vertex<Interaction> current = queue.poll().element;

            // use array list to store exiting interaction person to avoid loop

            // degree(neighbor) times
            // run though its neighbors, update its prob if new prob it's lower
            for (Vertex<Interaction> neighbor: adjacency.computeIfAbsent(current, (k) -> new HashSet<>())) {
                ArrayList<Integer> aList = new ArrayList<>();
                Vertex<Interaction> predecessor = current.predecessor;
                while (predecessor != null) {
                    aList.add(predecessor.element.getPersonFrom());
                    aList.add(predecessor.element.getPersonTo());
                    predecessor = predecessor.predecessor;
                }
                int personTo = neighbor.element.getPersonTo();
                int personFrom = neighbor.element.getPersonFrom();
                if (aList.contains(personTo)) {
                    continue;
                } else {
                    aList.add(personFrom);
                    aList.add(personTo);
                }

                double newProb = current.prob * neighbor.element.getTransmissionProbability();

                if (newProb > neighbor.prob || neighbor.prob == Double.MAX_VALUE) {
                    neighbor.prob = newProb;
                    // add to queue with the updated distance
                    queue.add(new Priority<>(neighbor, neighbor.prob)); // O(log V)
                    neighbor.predecessor = current;
                }
            }
        }
    }


    static class Vertex<T> {
        /**
         * Vertex class use to store the person number and its prob from startPerson
         */
        public T element;
        public double prob;
        public Vertex<T> predecessor;

        // we initialize the prob as the with infinity
        public Vertex(T element) {
            this.element = element;
            this.prob = Double.MAX_VALUE;
            this.predecessor = null;
        }
    }


    static class Priority<T> implements Comparable<Priority<T>> {
        /**
         * Priority class use to store the person number and its prob from startPerson
         */
        public T element;
        public double priority;

        public Priority(T element, double priority) {
            this.element = element;
            this.priority = priority;
        }

        @Override
        // 0: if d1 is numerically equal to d2.
        // Negative value: if d1 is numerically less than d2.
        // Positive value: if d1 is numerically greater than d2.
        public int compareTo(Priority<T> other) {
            return Double.compare(priority, other.priority);
        }
    }
}

//    public static List<Interaction> findTransmissionPathBruteForce(int startPerson, int endPerson,
//                                                         List<Interaction> interactions) {
//        // List<Interaction> result = TransmissionFinder.findTransmissionPath(1, 6, interactionList);
//        List<Interaction> finalInteractionList = new ArrayList<>();
//        ArrayList<List<Interaction>> listOLists = new ArrayList<List<Interaction>>();
//        for (Iterator<Interaction> iter = interactions.listIterator(); iter.hasNext(); ) {
//            Interaction interaction = iter.next();
//            // add all interaction with personFrom equal to startPerson
//            if (interaction.getPersonFrom() == startPerson) {
//                List<Interaction> interactionList = new ArrayList<>();
//                interactionList.add(interaction);
//                listOLists.add(interactionList);
//                // remove the interaction after add to new list
//                iter.remove();
//            }
//        }
//
//        for (Interaction interaction: interactions) {
//            for (List<Interaction> list: listOLists) {
//                if (list.get(list.size() - 1).getPersonTo() == interaction.getPersonFrom() &&
//                        interaction.getPersonFrom() < interaction.getPersonTo() &&
//                        list.get(list.size() - 1).getTimeOfInteraction() <= interaction.getTimeOfInteraction()) {
//                    list.add(interaction);
//                }
//            }
//        }
//
//        double highest_prob = 0;
//        for (List<Interaction> list: listOLists) {
//            if (list.size() < 2 || list.get(list.size() - 1).getPersonTo() != endPerson) {
//                continue;
//            }
//            double prob = 1;
//            for (Interaction interaction : list) {
//                prob *= interaction.getTransmissionProbability();
//            }
//            if (prob > highest_prob) {
//                highest_prob = prob;
//                finalInteractionList = list;
//            }
//        }
//
////        System.out.println(finalInteractionList);
//        if (finalInteractionList.isEmpty()) {
//            return null;
//        }
//
//        return finalInteractionList;
//    }
