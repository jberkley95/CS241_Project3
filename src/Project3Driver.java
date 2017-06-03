import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * <h1>Project 3 Driver</h1>
 *
 * Menu and Implementation of Djikstras for pathfinding between 2 city nodes
 *
 * Uses a min heap as a priority queue for implementation.
 *
 * @author John Berkley
 *         CPP Class: CS 241
 *         Date Created: June 1, 2017
 */

public class Project3Driver {
    public static void main(String[] args) throws FileNotFoundException {
        //set up city and road array lists
        Scanner inputScanner = new Scanner(System.in);
        ArrayList<City> cityList = new ArrayList<>();
        ArrayList<Road> roadList = new ArrayList<>();
        //starting point is row, ending is column
        boolean[][] adjacencyMatrix = new boolean[20][20];
        String menuChoice, userInput;
        String[] temp;

        //read in city data
        Scanner fileIn = new Scanner(new File("src/City.dat"));
        while (fileIn.hasNextLine()) {
            temp = fileIn.nextLine().split("\\s{2,}");
            cityList.add(new City(Integer.parseInt(temp[0].trim()), temp[1], temp[2], Integer.parseInt(temp[3].trim()), Integer.parseInt(temp[4].trim())));
        }

        //read in road data
        fileIn = new Scanner(new File("src/Road.dat"));
        while (fileIn.hasNextLine()) {
            temp = fileIn.nextLine().split("\\s{2,}");
            roadList.add(new Road(Integer.parseInt(temp[0].trim()), Integer.parseInt(temp[1].trim()), Integer.parseInt(temp[2].trim())));
        }

        for (Road r : roadList) {
            adjacencyMatrix[r.getStartingCity() - 1][r.getEndingCity() - 1] = true;
        }

        //process menu options
        do {
            System.out.print("Command: ");
            menuChoice = inputScanner.nextLine();
            switch (menuChoice.toUpperCase().charAt(0)) {
                case 'Q':
                    //display city query
                    System.out.print("City code: ");
                    if (!findAndDisplayCity(cityList, inputScanner.nextLine().toUpperCase())) System.out.println("Invalid City Code");
                    break;
                case 'D':
                    //display shortest path
                    System.out.print("City codes: ");
                    temp = inputScanner.nextLine().split("\\s+");
                    if (getCityFromList(cityList, temp[0]) != null && getCityFromList(cityList, temp[0]) != null) {
                        City startingCity = getCityFromList(cityList, temp[0]);
                        City endingCity = getCityFromList(cityList, temp[1]);
                        displayShortestPath(cityList, roadList, startingCity.getNumber(), endingCity.getNumber());
                    } else {
                        System.out.println("Invalid city choices.");
                    }
                    break;
                case 'I':
                    //allow user to insert new road
                    System.out.print("City codes and distance: ");
                    temp = inputScanner.nextLine().split("\\s+");
                    if (getCityFromList(cityList, temp[0]) != null && getCityFromList(cityList, temp[0]) != null) {
                        City startingCity = getCityFromList(cityList, temp[0]);
                        City endingCity = getCityFromList(cityList, temp[1]);
                        if (!adjacencyMatrix[startingCity.getNumber() - 1][endingCity.getNumber() - 1]) {
                            adjacencyMatrix[startingCity.getNumber() - 1][endingCity.getNumber() - 1] = true;
                            roadList.add(new Road(startingCity.getNumber(), endingCity.getNumber(), Integer.parseInt(temp[2].trim())));
                            System.out.println("You have inserted a road from " + startingCity.getCityName() + " to "
                                                + endingCity.getCityName() + " with a distance of " + temp[2] + " miles.");
                        } else {
                            System.out.println("The road from " + startingCity.getCityName() + " to "
                                    + endingCity.getCityName() + " already exists.");
                        }
                    } else {
                        System.out.println("Invalid city choices.");
                    }
                    break;
                case 'R':
                    //allow user to remove road
                    System.out.print("City codes: ");
                    temp = inputScanner.nextLine().split("\\s+");
                    if (getCityFromList(cityList, temp[0]) != null && getCityFromList(cityList, temp[0]) != null) {
                        City startingCity = getCityFromList(cityList, temp[0]);
                        City endingCity = getCityFromList(cityList, temp[1]);
                        if (adjacencyMatrix[startingCity.getNumber() - 1][endingCity.getNumber() - 1]) {
                            adjacencyMatrix[startingCity.getNumber() - 1][endingCity.getNumber() - 1] = false;
                            for (int i = 0; i < roadList.size(); i++) {
                                if (roadList.get(i).getStartingCity() == startingCity.getNumber() &&
                                        roadList.get(i).getEndingCity() == endingCity.getNumber()) {
                                    roadList.remove(i);
                                }
                            }
                            System.out.println("You have removed the road from " + startingCity.getCityName() + " to "
                                    + endingCity.getCityName() + ".");
                        } else {
                            System.out.println("The road from " + startingCity.getCityName() + " to "
                                                + endingCity.getCityName() + " doesn't exist.");
                        }
                    } else {
                        System.out.println("Invalid city choices.");
                    }
                    break;
                case 'H':
                    //display menu
                    System.out.println("Q Query the city information by entering the city code.");
                    System.out.println("D Find the minimum distance between two cities.");
                    System.out.println("I Insert a road by entering two city codes and distance.");
                    System.out.println("R Remove an existing road by entering two city codes.");
                    System.out.println("H Display this message.");
                    System.out.println("E Exit.");
                    break;
                case 'E':
                    menuChoice = "E";
                    break;
                default:
                    //show invalid input error
                    System.out.println("That is not a vaid input, please try again.");
            }

        } while (!menuChoice.equals("E"));
    }

    //display city data after finding inside list
    public static boolean findAndDisplayCity(ArrayList<City> list, String queriedCity) {
        for (City c: list) {
            if (c.getCityCode().equals(queriedCity)) {
                System.out.println(c.toString());
                return true;
            }
        }
        return false;
    }

    //return city from list, return null if not found
    public static City getCityFromList(ArrayList<City> list, String queriedCity) {
        for (City c: list) {
            if (c.getCityCode().equals(queriedCity)) {
                return c;
            }
        }
        return null;
    }

    //Find and display shortest path using djikstras
    public static void displayShortestPath(ArrayList<City> cityList, ArrayList<Road> roadList, int startingCity, int endingCity) {
        ArrayList<Integer> remainingCities = new ArrayList<>();
        MinHeap minHeap = new MinHeap();
        int[] distanceArray = new int[20];
        int[] precedingCityArray = new int[20];
        //for each vertex, set distance to max int, and treat int min as undefined
        for (int i = 1; i < 21; i++) {
            remainingCities.add(i);
            distanceArray[i - 1] = Integer.MAX_VALUE;
            precedingCityArray[i - 1] = Integer.MIN_VALUE;
        }

        //set source distance to 0
        distanceArray[startingCity - 1] = 0;
        //insert initial node to min heap (given city index and distance, sorting is based on distance)
        minHeap.insert(startingCity, 0);

        //while remaining cities list is not empty
        while (remainingCities.size() > 0) {
            //assign u to city with smallest distance
            int u = minHeap.remove().cityNumber;
            //remove u from cities list
            if (remainingCities.indexOf(u) != -1)
                remainingCities.remove(remainingCities.indexOf(u));
            ArrayList<Road> roadsFromU = new ArrayList<>();
            for (Road temp : roadList) {
                if (temp.getStartingCity() == u) roadsFromU.add(temp);
            }
            //for each neighbor v of u
            for (Road v : roadsFromU) {
                //let path weight equal distance between the two + distance to u
                int pathWeight = distanceArray[u - 1] + v.getDistance();
                //if the new path weight is less than previous, set value in distance array and preceding city array
                if (pathWeight < distanceArray[v.getEndingCity() - 1]) {
                    distanceArray[v.getEndingCity() - 1] = pathWeight;
                    precedingCityArray[v.getEndingCity() - 1] = u - 1;
                }
                //if the endpoint v is inside our remaining cities, add to the heap to be processed next
                if (remainingCities.indexOf(v.getEndingCity()) != -1)
                    minHeap.insert(v.getEndingCity(), pathWeight);
            }
        }

        //display path
        System.out.print("The minimum distance between " + cityList.get(startingCity  - 1).getCityName() + " and "
                            + cityList.get(endingCity - 1).getCityName() + " is " + distanceArray[endingCity - 1]
                            + " through the path: ");
        ArrayList<String> codeList = new ArrayList<>();
        int currentCity = endingCity - 1;
        while (currentCity >= 0) {
            codeList.add(cityList.get(currentCity).getCityCode());
            currentCity = precedingCityArray[currentCity];
        }
        for (int i = codeList.size() - 1; i > 0; i--) {
            System.out.print(codeList.get(i) + ", ");
        }
        System.out.println(codeList.get(0) + ".");
    }
}
