/**
 * <h1>Road</h1>
 *
 * Class to model a road object read from Road.dat file
 *
 * @author John Berkley
 *         CPP Class: CS 241
 *         Date Created: June 1, 2017
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
