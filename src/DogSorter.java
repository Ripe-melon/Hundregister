// Gustaf Ripe guri0112

import java.util.ArrayList;
import java.util.Comparator;

public class DogSorter {

    private static void swapDogs(ArrayList<Dog> dogs, int currentIndex, int minIndex) {
        Dog temp = dogs.get(currentIndex);
        dogs.set(currentIndex, dogs.get(minIndex));
        dogs.set(minIndex, temp);
    }

    private static int nextDog(Comparator<Dog> comparator, ArrayList<Dog> dogs, int minIndex) {
        for (int i = minIndex + 1; i < dogs.size(); i++) {
            if (comparator.compare(dogs.get(i), dogs.get(minIndex)) < 0) {
                minIndex = i;
            }
        }
        return minIndex;
    }

    public static int sortDogs(Comparator<Dog> comparator, ArrayList<Dog> dogs) {
        int swapCount = 0;

        for (int i = 0; i < dogs.size() - 1; i++) {
            int indexMinDog = nextDog(comparator, dogs, i);
            if (indexMinDog != i) {
                swapDogs(dogs, i, indexMinDog);
                swapCount++;
            }
        }
        return swapCount;
    }
}