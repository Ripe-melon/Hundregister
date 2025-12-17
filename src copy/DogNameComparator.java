// Gustaf Ripe guri0112

import java.util.Comparator;
public class DogNameComparator implements Comparator<Dog>{
    public int compare(Dog first, Dog second) {
        return first.getName().compareTo(second.getName());
    }
}
