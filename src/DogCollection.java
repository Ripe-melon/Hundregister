// Gustaf Ripe guri0112

import java.util.ArrayList;
import java.util.Comparator;

public class DogCollection {

    private ArrayList<Dog> dogs = new ArrayList<>();

    public boolean addDog(Dog dog) {
        if (containsDog(dog.getName())) {
            return false;
        } else {
            dogs.add(dog);
            return true;
        }
    }

    public boolean removeDog(Dog dog) {
        if (dog.getOwner() == null) {
            return dogs.remove(dog);
        }
        return false;
    }

    public boolean removeDog(String dogName) {
        for (int i = 0; i < dogs.size(); i++) {
            Dog dog = dogs.get(i);
            if (dog.getName().equals(dogName) && dog.getOwner() == null) {
                dogs.remove(i);
                return true;
            }
        }
        return false;
    }

    public boolean containsDog(Dog dogName) {
        return getDog(dogName.getName()) != null;
    }

    public boolean containsDog(String dogName) {
        return getDog(dogName) != null;
    }

    public Dog getDog(String dogName) {
        if(dogName != null) {
            for (int i = 0; i < dogs.size(); i++) {
                if (dogs.get(i).getName().equals(dogName)) {
                    return dogs.get(i);
                }
            }
            return  null;
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
        Comparator<Dog> dogTailNameComparator = new DogTailNameComparator();
        DogSorter.sortDogs(dogTailNameComparator, minTailLength);
        return minTailLength;
    }
}
