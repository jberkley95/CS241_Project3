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

public class City {
    private int number;
    private String cityCode;
    private String cityName;
    private int population;
    private int elevation;

    public City(int number, String cityCode, String cityName, int population, int elevation) {
        this.number = number;
        this.cityCode = cityCode;
        this.cityName = cityName;
        this.population = population;
        this.elevation = elevation;
    }

    public int getNumber() {
        return number;
    }

    public String getCityCode() {
        return cityCode;
    }

    public String getCityName() {
        return cityName;
    }

    public int getPopulation() {
        return population;
    }

    public int getElevation() {
        return elevation;
    }

    @Override
    public String toString() {
        return "City{" +
                "number = " + number +
                ", cityCode = '" + cityCode + '\'' +
                ", cityName = '" + cityName + '\'' +
                ", population = " + population +
                ", elevation = " + elevation +
                '}';
    }
}
