//Gustaf Ripe guri0112

import java.util.ArrayList;
import java.util.Comparator;

public class Owner implements Comparable<Owner> {
    private ArrayList <Dog> ownerDogs = new ArrayList<>();
    private String name;
    public Owner(String name) {
        this.name = name.toUpperCase();
    }
    public String getName() {
        return this.name;
    }
    public int compareTo(Owner other) {
        return name.compareTo(other.name);
        }
    public String toString() {
        return this.name + dogListToString();

    }
    public boolean addDog(Dog newDog) {
        if (newDog.getOwner() != null && newDog.getOwner() != this) {
            return false;
        }
        if (!ownerDogs.contains(newDog)) {
            newDog.setOwner(this);
            return ownerDogs.add(newDog);
        }
        return false;
    }
    public boolean removeDog(Dog dog) {
        for (int i = 0; i < ownerDogs.size(); i++) {
            if (ownerDogs.get(i) == dog) {
                return ownerDogs.remove(dog);
            }
        }
        return false;
    }
    public ArrayList<Dog> getDogs(){
        ArrayList <Dog> sortedOwnerDogs = new ArrayList<>(ownerDogs);
        Comparator <Dog> comparator = new DogTailNameComparator();
        DogSorter.sortDogs(comparator, sortedOwnerDogs);
        return sortedOwnerDogs;
    }
    private ArrayList <String> dogListToString () {
        ArrayList<String> ownerDogsToString = new ArrayList<>();
        ArrayList<Dog> getDogNames = getDogs();
        for (int i = 0; i < getDogNames.size(); i++) {
            ownerDogsToString.add(getDogNames.get(i).getName());
        }
        return ownerDogsToString;
    }
}

