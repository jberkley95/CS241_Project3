/**
 * <h1>City</h1>
 *
 * Object used to model a city node that is read from City.dat file
 *
 * @author John Berkley
 *         CPP Class: CS 241
 *         Date Created: June 1, 2017
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
