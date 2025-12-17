// Gustaf Ripe guri0112

import java.util.ArrayList;
import java.util.Collections;

public class OwnerCollection {
    private Owner[] owners = new Owner[5];
    private int count;

    private void expandArray(){
        Owner[] newOwners = new Owner[owners.length * 2];
        System.arraycopy(owners,0, newOwners, 0, count);
        owners = newOwners;

    }
    public boolean addOwner(Owner o){
        if(containsOwner(o)){
            return false;
        }if(count == owners.length){
            expandArray();
        }
        owners[count++] = o;
        return true;
    }
    public boolean removeOwner(String name) {
        for (int i = 0; i < count; i++) {
            Owner o = owners[i];
            if (o.getName().equals(name) && o.getDogs().isEmpty()) {
                System.arraycopy(owners, i + 1, owners, i, count - i - 1);
                owners[count - 1] = null;
                count--;
                return true;
            }
        }
        return false;
    }
    public boolean removeOwner(Owner o) {
        if (!o.getDogs().isEmpty()) {
            return false;
        }
        for (int i = 0; i < count; i++) {
            if (owners[i].equals(o)) {
                ArrayList<Dog> dogs = o.getDogs();
                for (Dog dog : dogs) {
                    dog.setOwner(null);
                    o.removeDog(dog);
                }
                System.arraycopy(owners, i + 1, owners, i, count - i - 1);
                owners[count - 1] = null;
                count--;
                return true;
            }
        }
        return false;
    }
    public boolean containsOwner(String o){
        for (int i = 0; i < count; i++){
            if (owners[i].getName().equals(o)){
                return true;
            }
        }
        return false;
    }
    public boolean containsOwner(Owner o){
        for (int i = 0; i < count; i++){
            if(owners[i].compareTo(o) == 0){
                return true;
            }
        }
        return false;
    }
    public Owner getOwner(String o){
        for (int i = 0; i < count; i++){
            if (owners[i].getName().equals(o)){
                return owners[i];
            }
        }
        return null;
    }
    public ArrayList<Owner> getOwners(){
        ArrayList<Owner> ownersList = new ArrayList<>();
        for (int i = 0; i < count; i ++){
            if(owners[i] != null){
                ownersList.add(owners[i]);
            }
        }
        Collections.sort(ownersList);
        return ownersList;
    }



}
