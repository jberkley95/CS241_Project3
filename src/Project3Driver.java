import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

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

public class Project3Driver {
    public static void main(String[] args) throws FileNotFoundException {
        Scanner inputScanner = new Scanner(System.in);
        ArrayList<City> cityList = new ArrayList<>();
        ArrayList<Road> roadList = new ArrayList<>();
        //starting point is row, ending is column
        boolean[][] adjacencyMatrix = new boolean[20][20];
        String menuChoice, userInput;
        String[] temp;

        Scanner fileIn = new Scanner(new File("src/City.dat"));
        while (fileIn.hasNextLine()) {
            temp = fileIn.nextLine().split("\\s{2,}");
            cityList.add(new City(Integer.parseInt(temp[0].trim()), temp[1], temp[2], Integer.parseInt(temp[3].trim()), Integer.parseInt(temp[4].trim())));
        }

        fileIn = new Scanner(new File("src/Road.dat"));
        while (fileIn.hasNextLine()) {
            temp = fileIn.nextLine().split("\\s{2,}");
            roadList.add(new Road(Integer.parseInt(temp[0].trim()), Integer.parseInt(temp[1].trim()), Integer.parseInt(temp[2].trim())));
        }

        for (Road r : roadList) {
            adjacencyMatrix[r.getStartingCity() - 1][r.getEndingCity() - 1] = true;
        }

        do {
            System.out.print("Command: ");
            menuChoice = inputScanner.nextLine();
            switch (menuChoice.toUpperCase().charAt(0)) {
                case 'Q':
                    System.out.print("City code: ");
                    if (!findAndDisplayCity(cityList, inputScanner.next().toUpperCase())) System.out.println("Invalid City Code");
                    break;
                case 'D':
                    //TODO: Implement the shortest path using Djikstras
                    break;
                case 'I':
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
                    System.out.println("That is not a vaid input, please try again.");
            }

        } while (!menuChoice.equals("E"));
    }

    public static boolean findAndDisplayCity(ArrayList<City> list, String queriedCity) {
        for (City c: list) {
            if (c.getCityCode().equals(queriedCity)) {
                System.out.println(c.toString());
                return true;
            }
        }
        return false;
    }

    public static City getCityFromList(ArrayList<City> list, String queriedCity) {
        for (City c: list) {
            if (c.getCityCode().equals(queriedCity)) {
                return c;
            }
        }
        return null;
    }
}
