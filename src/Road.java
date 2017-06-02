/**
 * <h1>Max Heap Class</h1>
 * MaxHeap implements a Max Heap class with an Array based representation (Default size: 100)
 * <p></p>
 * This Heap Can:
 * <ul>
 * <li>Insert And Remove Values</li>
 * <li>Display All Values in Heap</li>
 * <li>Display Number of Swaps Used During Insertion</li>
 * </ul>
 * <p></p>
 * <b>Note:</b> There are 2 methods of insertion: Sequential and Optimal
 *
 * @author John Berkley
 *         CPP Class: CS 241
 *         Date Created: May 13, 2017
 */

public class Road {
    private int startingCity;
    private int endingCity;
    private int distance;

    public Road(int startingCity, int endingCity, int distance) {
        this.startingCity = startingCity;
        this.endingCity = endingCity;
        this.distance = distance;
    }

    public int getStartingCity() {
        return startingCity;
    }

    public int getEndingCity() {
        return endingCity;
    }

    public int getDistance() {
        return distance;
    }

    @Override
    public String toString() {
        return "Road{" +
                "startingCity = " + startingCity +
                ", endingCity = " + endingCity +
                ", distance = " + distance +
                '}';
    }
}
