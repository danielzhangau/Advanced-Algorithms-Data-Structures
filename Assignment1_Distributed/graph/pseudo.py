def findMinimumCost(locations, source, ts, destination, td,  deliveries):
    sourceToDeliveries: HashMap<Location, HashSet<Delivery>> = 
        new HashMap().onLookupFail(new HashSet())
    destinationDeliveries: HashSet<Delivery> = new HashSet()

    # D iterations
    # O(1) loop body
    # O(P) HashSet construction cost, due to handshake lemma
    # Overall: O(D + P)
    # Worst-case: |P| = 2 * |D|, 
    #   if each delivery bridges two unique locations
    # Overall: O(D)
    for delivery in deliveries:
        # foreach P, we construct a HashSet, if the P lookup fails
        sourceDeliveries: HashSet<Delivery> = 
            sourceToDeliveries[delivery.source] # O(1)

        sourceDeliveries.add(delivery) # O(1)
        if delivery.destination == destination:
            destinationDeliveries.add(delivery) # O(1)

    adjacency: HashMap<Delivery, HashSet<Delivery>> = 
        new HashMap().onLookupFail(new HashSet())

    # D iterations
    # Worst-case: O(D) loop body, 
    #   if successive locations always depart after predecessor arrival
    # Overall: O(D^2)
    for delivery in deliveries:
        candidateNeighbours: HashSet<Delivery> = sourceToDeliveries[delivery.destination]
                # worst-case O(D)
                .filter(d -> delivery.arrival() <= d.departure())

        adjacency[delivery] = candidateNeighbours # O(1)

    # O(D) due to implementation constraints
    sources: HashSet<Delivery> = sourceToDeliveries[source]

    # djikstras is \Theta(V * lg V + E * lg V),
    # As E is worst-case O(V^2), substituting, we get
    # \Theta(V * lg V + V^2 * lg V), which is
    # \Theta(V^2)
    # As our Ds are vertexes in our use of Djikstras algorithm, we get
    # Overall: \Theta(D^2)
    dijkstra(G = adjacency, sources)

    # O(D)
    minimumCost: Int = destinationToDeliveries[destination].minBy(d -> d.d).d

    return cost == inf ? -1 : cost