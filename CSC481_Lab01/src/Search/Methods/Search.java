package Search.Methods;

import java.util.Random;

public class Search {

    public static int LinearSearch(int[] searchArray, int findNum) {
        for (int i = 0; i < searchArray.length; i++) {
            if (findNum == searchArray[i]) {
                return i;
            }
        }
        return -1;
    }

    public static int BinarySearch(int[] sortedArray, int findNum) {
        int min = 0;
        int max = sortedArray.length - 1;
        while (min <= max) {
            int median = min + (max - min) / 2;
            if (findNum < sortedArray[median]) {
                max = (median - 1);
            } else if (findNum > sortedArray[median]) {
                min = (median + 1);
            } else if (findNum == sortedArray[median]) {
                return median;
            }
        }
        return -1;
    }

    public static int[] SimpleSort(int[] searchArray) {
        int swap;
        for (int i = 0; i < searchArray.length - 1; i++) {
            for (int j = 0; j < searchArray.length - i - 1; j++) {
                if (searchArray[j] > searchArray[j + 1]) {
                    swap = searchArray[j];
                    searchArray[j] = searchArray[j + 1];
                    searchArray[j + 1] = swap;
                }
            }
        }
        return searchArray;
    }

    public static int[] EfficientSort(int[] searchArray, int min, int max) {

        if (max <= min) {
            return searchArray;
        }

        int j = partition(searchArray, min, max);
        EfficientSort(searchArray, min, j - 1);
        EfficientSort(searchArray, j + 1, max);

        return searchArray;
    }

    public static int partition(int[] searchArray, int min, int max) {
        int i = min - 1;
        int temp = searchArray[max];

        for (int j = min; j < max; j++) {
            if (searchArray[j] <= temp) {
                i++;
                int temp2 = searchArray[i];
                searchArray[i] = searchArray[j];
                searchArray[j] = temp2;
            }
        }
        int temp2 = searchArray[i + 1];
        searchArray[i + 1] = searchArray[max];
        searchArray[max] = temp2;
        return i + 1;
    }


    public static void printTable(long[] tempArray, int[] nArray, int factor) {
        System.out.println("------------------------------------------------------------------------------------------------------");
        System.out.printf("%10s %25s %30s %30s", "List of Size N", "Avg Experimental Time us", "Experimental Doubling Ratio", "Theoretical Doubling Ratio");
        System.out.println();
        System.out.println("------------------------------------------------------------------------------------------------------");
        System.out.printf("%10s %25s %30s %30s", nArray[0], tempArray[0], "n/a", "n/a");
        System.out.println();
        for (int i = 1; i < 10; i++) {
            System.out.printf("%10s %25s %30.3f %30s", nArray[i], tempArray[i], (double) tempArray[i] / (double) tempArray[i - 1], factor);
            System.out.println();
        }
    }

    public static void printTable2(long[] tempArray, int[] nArray, String factor) {
        System.out.println("------------------------------------------------------------------------------------------------------");
        System.out.printf("%10s %25s %30s %30s", "List of Size N", "Avg Experimental Time us", "Experimental Doubling Ratio", "Theoretical Doubling Ratio");
        System.out.println();
        System.out.println("------------------------------------------------------------------------------------------------------");
        System.out.printf("%10s %25s %30s %30s", nArray[0], tempArray[0], "n/a", "n/a");
        System.out.println();
        for (int i = 1; i < 6; i++) {
            System.out.printf("%10s %25s %30.3f %30s", nArray[i], tempArray[i], (double) tempArray[i] / (double) tempArray[i - 1], factor);
            System.out.println();
        }
    }


    public static void printResult(int findNum, int result, int[] searchArray) {
        if (result == -1) {
            System.out.println(findNum + " is not in the Array");
        } else {
            System.out.println(findNum + " is located at index " + result);
        }
    }

    public static void main(String[] args) {
        int N = 1000;
        int[] nArray = new int[N];
        nArray[0] = N;
        for (int i = 1; i < 10; i++) {
            nArray[i] = (nArray[i - 1] * 2);
        }
        int findNum = 5;
        int result;
        long[] linearArray = new long[N];
        long[] bubbleArray = new long[N];
        long[] binaryArray = new long[N];
        long[] quickArray = new long[N];

        Random rand = new Random();
        int[] searchArray = new int[N];
        int[] sortedArray = new int[N];
        for (int i = 0; i < N; i++) {
            searchArray[i] = rand.nextInt();
        }

        int linearFactor = 2;
        // Linear Search
        for (int i = 0; i < 10; i++) {
            long startTime = System.nanoTime();
            result = LinearSearch(searchArray, findNum);
            long endTime = System.nanoTime();
            long duration = (endTime - startTime);
            linearArray[i] = (duration);
        }
        System.out.println();
        System.out.println("Linear Regression Table");
        printTable(linearArray, nArray, linearFactor);

        // Bubble Sort
        int bubbleFactor = 4;
        for (int i = 0; i < 10; i++) {
            long startTime3 = System.nanoTime();
            sortedArray = SimpleSort(searchArray);
            long endTime3 = System.nanoTime();
            long duration3 = (endTime3 - startTime3);
            bubbleArray[i] = duration3;
        }

        System.out.println();
        System.out.println("Bubble Sort Table");
        printTable(bubbleArray, nArray, bubbleFactor);


        String binaryFactor = ("Log(N)");
        // Binary Search
        for (int i = 0; i < 6; i++) {
            long startTime2 = System.nanoTime();
            result = BinarySearch(sortedArray, findNum);
            long endTime2 = System.nanoTime();
            long duration2 = (endTime2 - startTime2);
            binaryArray[i] = duration2;
        }
        System.out.println();
        System.out.println("Binary Sort Table");
        printTable2(binaryArray, nArray, binaryFactor);

        String quickFactor = ("Log(N)");
        // Quick Sort
        for (int i = 0; i < 6; i++) {
            long startTime4 = System.nanoTime();
            searchArray = EfficientSort(searchArray, 0, searchArray.length - 1);
            long endTime4 = System.nanoTime();
            long duration4 = (endTime4 - startTime4);
            quickArray[i] = duration4;
        }
        System.out.println("Quick Sort Table");
        printTable2(quickArray, nArray, quickFactor);

    }

}
