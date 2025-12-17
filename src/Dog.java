// Gustaf Ripe guri0112
public class Dog {

    private Owner owner;
    private final String name;
    private final String breed;
    private int age;
    private final int weight;
    private final double dachshundTailLength = 3.7;
    private double tailLength;
    private String[] dachsNameList = {"TAX", "TECKEL",
            "DACHSHUND", "DACKEL",
            "GRAVHUND", "DACHS",
            "PERRO TEJONERO",
            "MÄYRÄKOIRA", "BASOTTO"};

    public Dog(String name, String breed, int age, int weight){
        this.name = name.toUpperCase();
        this.breed = breed.toUpperCase();
        this.age = age;
        this.weight = weight;
        this.tailLength = tailLength;
    }

    public String getName(){
        return this.name;
    }

    public String getBreed(){
        return this.breed;
    }

    public int getAge(){
        if (this.age<0) {
            this.age = 0;
        }
        return this.age;
    }

    public void increaseAge(int change) {
        if (change>0){
            if (Integer.MAX_VALUE - change >= this.age) {
                this.age += change;
            } else {
                this.age = Integer.MAX_VALUE;
            }
        }
    }

    public int getWeight(){
        return this.weight;

    }

    public double getTailLength(){
        boolean isTax = false;
        for (String s : dachsNameList) {
            if (s.equalsIgnoreCase(breed)) {
                isTax = true;
                break;
            }
        }
        if(isTax) {
            this.tailLength = dachshundTailLength;
        }else{
            this.tailLength = this.age * this.weight / 10.0;
        }
        return this.tailLength;
    }

    public String toString() {
        String ownerName = (owner != null) ? " Owner: " + owner.getName() : "";
        return String.format("%s | Breed: %s | Age: %d | Weight: %d | Tail Length: %.2f%s",
                name, breed, age, weight, getTailLength(), ownerName);
    }

    public boolean setOwner(Owner newOwner) {
        if (this.owner != null && this.owner == newOwner) {
            return false;
        }
        if (this.owner != null && newOwner != null){
            return false;
        }
        if (this.owner != null) {
            this.owner.removeDog(this);
        }
        this.owner = newOwner;
        if (newOwner != null){
            newOwner.addDog(this);
        }
        return true;
    }

    public Owner getOwner(){
        return this.owner;
    }
}

