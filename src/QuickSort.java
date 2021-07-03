import java.util.Arrays;
import java.util.Random;

public class QuickSort {
    public static void main(String[] args) {
        int[] dataSet = new int[100];
        Random random = new Random();
        for (int x = 0; x < dataSet.length; x++) {
            int randomNum = random.nextInt(100) + 1;
            dataSet[x] = randomNum;
        }
        System.out.println("Unsorted Array:" + Arrays.toString(dataSet));
        System.out.println("Sorted Array:" + Arrays.toString(quickSort(dataSet)));
    }

    public static int[] quickSort(int[] unsortedArray) {
        if (unsortedArray.length <= 1) {
            return unsortedArray;
        }
        if (unsortedArray.length == 2) {
            if (unsortedArray[0] > unsortedArray[1]) {
                int tempNum = unsortedArray[0];
                unsortedArray[0] = unsortedArray[1];
                unsortedArray[1] = tempNum;
            }
            return unsortedArray;
        }
        int pivotIndex = unsortedArray.length / 2;
        int pivotValue = unsortedArray[pivotIndex];
        int iPointer = -1;
        for (int x = 0; x < unsortedArray.length; x++) {
            if (x == pivotIndex) x++;
            if (x < unsortedArray.length && unsortedArray[x] < pivotValue) {
                iPointer++;
                if (iPointer == pivotIndex) iPointer++;
                int tempNum = unsortedArray[x];
                unsortedArray[x] = unsortedArray[iPointer];
                unsortedArray[iPointer] = tempNum;
            }
        }
        int offset = 0;
        int[] tempArray = new int[unsortedArray.length];
        if (iPointer + 1 > pivotIndex) {
            iPointer--;
        }
        for (int x = 0; x < unsortedArray.length; x++) {
            if (x == iPointer + 1 && pivotIndex != iPointer + 1) {
                tempArray[x] = pivotValue;
                offset--;
            } else if (x + offset == pivotIndex && pivotIndex != iPointer + 1) {
                offset++;
                tempArray[x] = unsortedArray[x + offset];
            } else {
                tempArray[x] = unsortedArray[x + offset];
            }
        }
        int firstHalfSize = iPointer + 2;
        int[] firstHalf = new int[firstHalfSize];
        int[] secondHalf = new int[unsortedArray.length - firstHalfSize];
        System.arraycopy(tempArray, 0, firstHalf, 0, firstHalfSize);
        System.arraycopy(tempArray, firstHalfSize, secondHalf, 0, secondHalf.length);
        firstHalf = quickSort(firstHalf);
        secondHalf = quickSort(secondHalf);
        for (int x = 0; x < firstHalfSize; x++) tempArray[x] = firstHalf[x];
        for (int x = firstHalfSize; x < unsortedArray.length; x++) tempArray[x] = secondHalf[x - firstHalfSize];
        return tempArray;
    }
}