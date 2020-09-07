package assignment1;

public class Interaction {

    //the identifier for the transmitting person
    private final int personFrom;
    //the identifier for the receiving person
    private final int personTo;
    //the time of the interaction
    private final int timeOfInteraction;
    //the probability of transmission during this interaction
    private final double transmissionProbability;

    /*
     * class invariant: personFrom != personTo && timeOfInteraction >= 0 &&
     * 0 < transmissionProbability <= 1
     */

    /**
     * @require personFrom != personTo && timeOfInteraction >= 0 && 0 < transmissionProbability <= 1
     * @ensure creates a new Interaction with the given personFrom, personTo, timeOfInteraction and
     * transmission probability.
     */
    public Interaction(int personFrom, int personTo, int timeOfInteraction,
                       double transmissionProbability) {
        if (personFrom == personTo ) {
            throw new IllegalArgumentException(
                    "Interaction must be between different people");
        }
        if (timeOfInteraction < 0) {
            throw new IllegalArgumentException("Time must be non-negative");
        }
        if (transmissionProbability <= 0 || transmissionProbability > 1) {
            throw new IllegalArgumentException("Transmission probability must be between 0 (non-inclusive) and 1");
        }

        this.personFrom = personFrom;
        this.personTo = personTo;
        this.timeOfInteraction = timeOfInteraction;
        this.transmissionProbability = transmissionProbability;
    }

    /**
     * @ensure Returns the transmitting person identifier
     */
    public int getPersonFrom() {
        return personFrom;
    }

    /**
     * @ensure Return the receiving person identifier
     */
    public int getPersonTo() {
        return personTo;
    }

    /**
     * @ensure Return the time of the interaction
     */
    public int getTimeOfInteraction() {
        return timeOfInteraction;
    }

    /**
     * @ensure Returns the probability of transmission
     */
    public double getTransmissionProbability() {
        return transmissionProbability;
    }

    @Override
    public String toString() {
        return String.format("(P%d,P%d,%d,%.3f)\n", personFrom, personTo, timeOfInteraction, transmissionProbability);
    }

    @Override
    public boolean equals(Object o) {

        //if the object is being compared with itself then return true
        if (o == this) {
            return true;
        }

        //next check the object is an instance of an interaction
        if (!(o instanceof Interaction)) {
            return false;
        }

        //then cast the object to be an interaction
        Interaction i = (Interaction) o;

        //then return whether all fields are the same
        return this.personFrom == i.personFrom &&
                this.personTo == i.personTo &&
                this.timeOfInteraction == i.timeOfInteraction &&
                this.transmissionProbability == i.transmissionProbability;
    }
}
