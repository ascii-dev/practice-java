package ng.com.asciidev.datastructures.binarysearch;

import java.util.List;

public class BinarySearch {
    public static int binarySearchLoops(int target, List<Integer> array) {
        int low = 0, high = array.size() - 1;
        while (low <= high) {
            int mid = (low + high) / 2;
            if (array.get(mid) < target)
                low = mid + 1;
            else if (array.get(mid) > target)
                high = mid - 1;
            else
                return mid;
        }
        return -1;
    }
}
