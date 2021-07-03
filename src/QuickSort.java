import java.util.Arrays;
import java.util.Random;

public class QuickSort {
    public static void main(String[] args) {
        //Used to store unsorted data
        int[] dataSet = new int[100];
        //Used to generate random numbers
        Random random = new Random();
        //Fills "dataSet" with random numbers
        for (int x = 0; x < dataSet.length; x++) {
            int randomNum = random.nextInt(100) + 1;
            dataSet[x] = randomNum;
        }
        //Prints out "dataSet" unsorted and "dataSet" after sorting
        System.out.println("Unsorted Array:" + Arrays.toString(dataSet));
        System.out.println("Sorted Array:" + Arrays.toString(quickSort(dataSet)));
    }

    public static int[] quickSort(int[] unsortedArray) {
        //If the array is only one long it is returned
        if (unsortedArray.length <= 1) {
            return unsortedArray;
        }
        //If the array is two long it is sorted and returned
        if (unsortedArray.length == 2) {
            if (unsortedArray[0] > unsortedArray[1]) {
                int tempNum = unsortedArray[0];
                unsortedArray[0] = unsortedArray[1];
                unsortedArray[1] = tempNum;
            }
            return unsortedArray;
        }
        //Finds the index to use as the pivot
        int pivotIndex = unsortedArray.length / 2;
        //Gets the pivot value
        int pivotValue = unsortedArray[pivotIndex];
        //Sets the starting position of the iPointer
        int iPointer = -1;
        //Loops through the unsorted array
        for (int x = 0; x < unsortedArray.length; x++) {
            //Used to skip the pivotIndex
            if (x == pivotIndex) x++;
            //Checks that x is within bounds and whether the current value is less than the pivot
            if (x < unsortedArray.length && unsortedArray[x] < pivotValue) {
                //Increments i pointer
                iPointer++;
                //If i pointer is equal to pivotIndex, increment it again
                if (iPointer == pivotIndex) iPointer++;
                //Swaps the current value with the value at the iPosition
                int tempNum = unsortedArray[x];
                unsortedArray[x] = unsortedArray[iPointer];
                unsortedArray[iPointer] = tempNum;
            }
        }
        //Sets the offset
        int offset = 0;
        //Creates a temporary array which is used to make a modified copy of the original
        int[] tempArray = new int[unsortedArray.length];
        //If the correct index of the pivot is greater than it's current index, iPointer is moved back
        if (iPointer + 1 > pivotIndex) {
            iPointer--;
        }
        //Loops through the array again
        for (int x = 0; x < unsortedArray.length; x++) {
            //If the current location is where the pivot should be, and the pivot isn't already there, the code runs
            if (x == iPointer + 1 && pivotIndex != iPointer + 1) {
                //Sets the current value to the pivot value
                tempArray[x] = pivotValue;
                //Updates the offset
                offset--;
            }
            //Runs if the current location on the unsorted array is the pivot and the pivot needs to be moved
            else if (x + offset == pivotIndex && pivotIndex != iPointer + 1) {
                //Updates the offset and adds a element to the temp array
                offset++;
                tempArray[x] = unsortedArray[x + offset];
            } else {
                //Otherwise just adds an element to the tempArray
                tempArray[x] = unsortedArray[x + offset];
            }
        }
        //Splits it into two arrays
        int firstHalfSize = iPointer + 2;
        int[] firstHalf = new int[firstHalfSize];
        int[] secondHalf = new int[unsortedArray.length - firstHalfSize];
        System.arraycopy(tempArray, 0, firstHalf, 0, firstHalfSize);
        System.arraycopy(tempArray, firstHalfSize, secondHalf, 0, secondHalf.length);
        //Calls quicksort on both arrays
        firstHalf = quickSort(firstHalf);
        secondHalf = quickSort(secondHalf);
        //Combines them into tempArray
        for (int x = 0; x < firstHalfSize; x++) tempArray[x] = firstHalf[x];
        for (int x = firstHalfSize; x < unsortedArray.length; x++) tempArray[x] = secondHalf[x - firstHalfSize];
        
        return tempArray;
    }
}