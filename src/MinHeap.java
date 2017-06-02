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

public class MinHeap {
    /**integer array containing heap data*/
    private int[] heapArray;
    /**current maximum size of the heap*/
    private int maxSize;
    /**current number of items in the heap array*/
    private int currentSize;

    /**
     * Default Constructor. Assigns maxSize to 100, current siz, and number of swaps to 0, and initializes heapArray
     */
    public MinHeap() {
        maxSize = 100;
        currentSize = 0;
        heapArray = new int[maxSize];
    }

    /**
     * Inserts a value into the max heap
     *
     * @param value to be inserted
     * @return true if inserted properly, false if insert failed
     */
    public boolean insert(int value) {
        if (currentSize == maxSize) {
            return false;
        }
        heapArray[currentSize] = value;
        trickleUp(currentSize++);
        return true;
    }

    /**
     * Trickles up value at given index
     *
     * @param index to be trickled up
     */
    private void trickleUp(int index) {
        int parent = (index - 1) / 2;
        int bottom = heapArray[index];

        while (index > 0 && heapArray[parent] > bottom) {
            heapArray[index] = heapArray[parent];
            index = parent;
            parent = (parent - 1) / 2;
        }
        heapArray[index] = bottom;
    }

    /**
     * Removes a value from the top of the heap
     *
     * @return value that was removed from heap
     */
    public int remove() {
        int root = heapArray[0];
        heapArray[0] = heapArray[--currentSize];
        trickleDown(0);
        return root;
    }

    /**
     * Trickles down value at given idex
     *
     * @param index to be trickled down
     */
    private void trickleDown(int index) {
        int largerChild;
        int top = heapArray[index];

        while (index < currentSize / 2) {
            int leftChild = 2 * index + 1;
            int rightChild = leftChild + 1;

            //determine which of the two is the larger child
            if (rightChild < currentSize && heapArray[leftChild] > heapArray[rightChild]) {
                largerChild = rightChild;
            } else {
                largerChild = leftChild;
            }

            if (top <= heapArray[largerChild]) {
                break;
            }

            heapArray[index] = heapArray[largerChild];
            index = largerChild;
        }

        heapArray[index] = top;
    }

    /**
     * Displays first 10 values in the heap
     */
    public void displayHeap() {
        for (int i = 0; i < currentSize; i++) {
            System.out.print(heapArray[i] + ", ");
        }
        System.out.println("...");
    }
}
