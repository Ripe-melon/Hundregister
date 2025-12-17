// Gustaf Ripe guri0112

import java.util.ArrayList;
import java.util.Comparator;

public class DogCollection {
    private ArrayList<Dog> dogs = new ArrayList<>();

    public boolean addDog(Dog d) {
        if (containsDog(d.getName())) {
            return false;
        } else {
            dogs.add(d);
            return true;
        }
    }
    public boolean removeDog(Dog d) {
        if (d.getOwner() == null) {
            return dogs.remove(d);
        }else {
            return false;
        }
    }
    public boolean removeDog(String d) {
        for (int i = 0; i < dogs.size(); i++) {
            Dog dog = dogs.get(i);
            if (dog.getName().equals(d) && dog.getOwner() == null) {
                dogs.remove(i);
                return true;
            }
        }
        return false;
    }

    public boolean containsDog(Dog d) {
        return containsDog(d.getName());
    }

    public boolean containsDog(String d) {
        for(int i = 0; i < dogs.size(); i++) {
            if (dogs.get(i).getName().equals(d)) {
                return true;
            }
        }
        return false;
    }

    public Dog getDog(String d) {
        for (int i = 0; i < dogs.size(); i++) {
            if (dogs.get(i).getName().equals(d)) {
                return dogs.get(i);
            }
        }
        return null;
    }
    public ArrayList<Dog> getDogs(){
        ArrayList <Dog> sortedDogs = new ArrayList<>(dogs);
        Comparator<Dog> dogNameComparator = new DogNameComparator();
        DogSorter.sortDogs(dogNameComparator, sortedDogs);
        return sortedDogs;
    }
    public ArrayList<Dog> getDogsByTailLength(double d){
        ArrayList <Dog> minTailLength = new ArrayList<>();
        for(int i = 0; i < dogs.size(); i++) {
            if (dogs.get(i).getTailLength() >= d) {
                minTailLength.add(dogs.get(i));
            }
        }
        Comparator<Dog> dogTailComparator = new DogTailNameComparator();
        DogSorter.sortDogs(dogTailComparator, minTailLength);
        return minTailLength;
    }
}
