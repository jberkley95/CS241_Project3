/**
 * <h1>Min Heap</h1>
 *
 * MaxHeap implements a Max Heap class with an Array based representation (Default size: 20)
 *
 * @author John Berkley
 *         CPP Class: CS 241
 *         Date Created: May 13, 2017
 */

public class MinHeap {
    /**HeapNode array containing heap data*/
    private HeapNode[] heapArray;
    /**current maximum size of the heap*/
    private int maxSize;
    /**current number of items in the heap array*/
    private int currentSize;

    /**
     * Default Constructor. Assigns maxSize to 100, current siz, and number of swaps to 0, and initializes heapArray
     */
    public MinHeap() {
        maxSize = 20;
        currentSize = 0;
        heapArray = new HeapNode[maxSize];
    }

    /**
     * Inserts a value into the max heap
     *
     * @param cityNumber to be inserted
     * @param distToNode to be inserted
     * @return true if inserted properly, false if insert failed
     */
    public boolean insert(int cityNumber, int distToNode) {
        if (currentSize == maxSize) {
            return false;
        }
        heapArray[currentSize] = new HeapNode();
        heapArray[currentSize].cityNumber = cityNumber;
        heapArray[currentSize].distToNode = distToNode;
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
        HeapNode bottom = heapArray[index];

        while (index > 0 && heapArray[parent].distToNode > bottom.distToNode) {
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
    public HeapNode remove() {
        HeapNode root = heapArray[0];
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
        HeapNode top = heapArray[index];

        while (index < currentSize / 2) {
            int leftChild = 2 * index + 1;
            int rightChild = leftChild + 1;

            //determine which of the two is the larger child
            if (rightChild < currentSize && heapArray[leftChild].distToNode > heapArray[rightChild].distToNode) {
                largerChild = rightChild;
            } else {
                largerChild = leftChild;
            }

            if (top.distToNode <= heapArray[largerChild].distToNode) {
                break;
            }

            heapArray[index] = heapArray[largerChild];
            index = largerChild;
        }

        heapArray[index] = top;
    }
}
