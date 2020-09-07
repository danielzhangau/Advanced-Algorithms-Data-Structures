package assignment1;

import java.util.*;

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
    public static List<Interaction> findTransmissionPath(int startPerson, int endPerson,
                                                         List<Interaction> interactions) {

        return new ArrayList<>(); //REMOVE THIS LINE AND IMPLEMENT THIS METHOD
    }
}
