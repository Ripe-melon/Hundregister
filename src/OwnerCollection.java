// Gustaf Ripe guri0112

import java.util.ArrayList;
import java.util.Collections;

public class OwnerCollection {

    private Owner[] owners = new Owner[5];
    private int count;

    public boolean addOwner(Owner owner){
        if(containsOwner(owner)){
            return false;
        }if(count == owners.length){
            expandArray();
        }
        owners[count++] = owner;
        return true;
    }

    public boolean removeOwner(String ownerName) {
        return removeOwnerByName(ownerName);
    }

    public boolean removeOwner(Owner owner) {
        return removeOwnerByName(owner.getName());
    }


    public boolean containsOwner(String ownerName){
        return getOwner(ownerName) != null;
    }

    public boolean containsOwner(Owner owner){
        return getOwner(owner.getName()) != null;
    }

    public Owner getOwner(String ownerName){
        if(ownerName != null) {
            for (int i = 0; i < count; i++) {
                if (owners[i].getName().equals(ownerName)) {
                    return owners[i];
                }
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

    private boolean removeOwnerByName(String ownerName) {
        for (int i = 0; i < count; i++) {
            Owner o = owners[i];
            if (o.getName().equals(ownerName) && o.getDogs().isEmpty()) {
                removeOwnerAtIndex(i);
                return true;
            }
        }
        return false;
    }

    private void removeOwnerAtIndex(int index) {
        System.arraycopy(owners, index + 1, owners, index, count - index - 1);
        owners[count - 1] = null;
        count--;
    }

    private void expandArray(){
        Owner[] newOwners = new Owner[owners.length * 2];
        System.arraycopy(owners,0, newOwners, 0, count);
        owners = newOwners;

    }
}
