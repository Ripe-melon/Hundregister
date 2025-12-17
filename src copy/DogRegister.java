// Gustaf Ripe guri0112

public class DogRegister {
    private static final String REGISTER_NEW_OWNER_COMMAND = "REGISTER NEW OWNER";
    private static final String REGISTER_NEW_DOG_COMMAND = "REGISTER NEW DOG";
    private static final String REMOVE_OWNER_COMMAND = "REMOVE OWNER";
    private static final String LIST_OWNERS_COMMAND = "LIST OWNERS";
    private static final String LIST_DOGS_COMMAND = "LIST DOGS";
    private static final String REMOVE_DOG_COMMAND = "REMOVE DOG";
    private static final String REMOVE_DOG_FROM_OWNER_COMMAND = "REMOVE DOG FROM OWNER";
    private static final String INCREASE_AGE_COMMAND = "INCREASE AGE";
    private static final String GIVE_DOG_TO_OWNER_COMMAND = "GIVE DOG TO OWNER";
    private static final String EXIT_COMMAND = "EXIT";
    private OwnerCollection ownerCollection = new OwnerCollection();
    private DogCollection dogCollection = new DogCollection();

    private InputReader input = new InputReader();
    private void start(){
        initialize();
        runCommandLoop();
        shutDown();
    }

    private void runCommandLoop() {
        String command;
        do{
            command = readCommand().toUpperCase();
            handleCommand(command);
        }while (!command.equalsIgnoreCase(EXIT_COMMAND));
    }
    private void initialize() {
        System.out.println("Welcome to the dog register!");
        System.out.println("The following commands are available: ");
        System.out.println("* Register new dog");
        System.out.println("* Remove dog");
        System.out.println("* Remove owner");
        System.out.println("* Register new owner");
        System.out.println("* List dogs");
        System.out.println("* List owners");
        System.out.println("* Increase age");
        System.out.println("* Give dog to owner");
        System.out.println("* Remove dog from owner");
        System.out.println("* Exit");

    }
    private String readCommand() {
        return input.readLine("Enter command");
    }
    private void handleCommand(String command){
        switch (command){
            case REGISTER_NEW_OWNER_COMMAND:
                registerNewOwner();
                break;
            case REGISTER_NEW_DOG_COMMAND:
                registerNewDog();
                break;
            case REMOVE_DOG_COMMAND:
                removeDog();
                break;
            case REMOVE_DOG_FROM_OWNER_COMMAND:
                removeDogFromOwner();
                break;
            case LIST_OWNERS_COMMAND:
                listOwners();
                break;
            case INCREASE_AGE_COMMAND:
                increaseAge();
                break;
            case LIST_DOGS_COMMAND:
                listDogs();
                break;
            case GIVE_DOG_TO_OWNER_COMMAND:
                giveDogToOwner();
                break;
            case REMOVE_OWNER_COMMAND:
                removeOwner();
                break;
            case EXIT_COMMAND:
                break;
            default:
                System.out.println("Error: " + command + " is not a recognised command ");
        }

    }

    private void removeDogFromOwner() {
        if(checkForOwners()){
            return;
        }
        if(checkForDogs()){
            return;
        }
        String dogName = input.readLine("Enter dog name").toUpperCase();
        if(!dogCollection.containsDog(dogName)){
            System.out.println("Error: The dog " + dogName +  " does not exist");
            return;
        }
        Dog dog = dogCollection.getDog(dogName);
        dog.setOwner(null);
        System.out.println("The dog " + dogName + " has been removed from the owner");
    }

    private void increaseAge() {
        if(checkForDogs()){
            return;
        }
        String dogName = input.readLine("Enter dog name").toUpperCase();
        if(!dogCollection.containsDog(dogName)){
            System.out.println("Error: The dog " + dogName +  " does not exist");
            return;
        }
        Dog dog = dogCollection.getDog(dogName);
        dog.changeAge(1);
        System.out.println("The dog " + dogName + " is now one year older");
    }

    private void removeOwner() {
        if(checkForOwners()){
            return;
        }
        String ownerName = input.readLine("Enter owner name").toUpperCase();
        if(!ownerCollection.containsOwner(ownerName)){
            System.out.println("Error: The owner " + ownerName +  " does not exist");
            return;
        }
        Owner owner = ownerCollection.getOwner(ownerName);
        for(Dog dog : owner.getDogs()){
            dog.setOwner(null);
            dogCollection.removeDog(dog);
        }
        ownerCollection.removeOwner(ownerName);
        System.out.println("The owner " + ownerName + " has been removed from the register");
    }

    private void giveDogToOwner() {
        while(!dogCollection.getDogs().isEmpty() && !ownerCollection.getOwners().isEmpty()){
            String dogName = input.readLine("Enter dog name").toUpperCase();
            if(!dogCollection.containsDog(dogName)){
                System.out.println("Error: Dog does not exist");
                break;
            }
            Dog dog = dogCollection.getDog(dogName);
            if(dog.getOwner() != null){
                System.out.println("Error: The dog " + dogName + " already has an owner");
                break;
            }
            String ownerName = input.readLine("Enter owner name").toUpperCase();
            if(!ownerCollection.containsOwner(ownerName)){
                System.out.println("Error: Owner does not exist");
                break;
            }
            Owner owner = ownerCollection.getOwner(ownerName);
            dog.setOwner(owner);
            System.out.println("The dog " + dog.getName() + " is now owned by " +  owner.getName());
            break;
        }
        if(checkForOwners()){
            return;
        }
        if(checkForDogs()){
        }
    }

    private void listDogs() {
        if(checkForDogs()){
            return;
        }
        double tailLength = input.readDouble("Enter tail length");
        int maxNameLength = "Name   ".length();
        int maxBreedLength = "Breed".length();
        int maxAgeLength = "Age".length();
        int maxWeightLength = "Weight".length();
        int maxTailLength = "Tail".length();
        int maxOwnerLength = "Owner".length();
        for (Dog dog : dogCollection.getDogsByTailLength(tailLength)) {
            maxNameLength = Math.max(maxNameLength, dog.getName().length());
            maxBreedLength = Math.max(maxBreedLength, dog.getBreed().length());
            maxAgeLength = Math.max(maxAgeLength, Integer.toString(dog.getAge()).length());
            maxWeightLength = Math.max(maxWeightLength, Integer.toString(dog.getWeight()).length());
            maxTailLength = Math.max(maxTailLength, Double.toString(dog.getTailLength()).length());
            String ownerName = (dog.getOwner() != null) ? dog.getOwner().getName() : "";
            maxOwnerLength = Math.max(maxOwnerLength, ownerName.length());
        }
        String headerFormat = "%" + maxNameLength + "s  %-" + maxBreedLength + "s  %-" + maxAgeLength + "s  %-" + maxWeightLength + "s  %-" + maxTailLength + "s  %-" + maxOwnerLength + "s\n";
        System.out.printf(headerFormat, "Name", "Breed", "Age", "Weight", "Tail", "Owner");
        for (Dog dog : dogCollection.getDogsByTailLength(tailLength)) {
            String ownerName = (dog.getOwner() != null) ? dog.getOwner().getName() : "";
            System.out.printf(headerFormat, dog.getName(), dog.getBreed(), dog.getAge(), dog.getWeight(), dog.getTailLength(), ownerName);
        }
    }
    private void registerNewDog() {
        while (true) {
            String dogName = input.readLine("Enter dog name").toUpperCase().trim();
            while (dogName.isEmpty() || dogName.isBlank()) {
                System.out.println("Error: A blank string is not allowed, try again");
                dogName = input.readLine("Enter dog name").trim();
            }
            if (dogCollection.containsDog(dogName)) {
                System.out.println("Error: Dog " + dogName + " already exists");
                break;
            }
            String breed = input.readLine("Enter dog breed").trim();
            while (breed.isEmpty() || breed.isBlank()) {
                System.out.println("Error: A blank string is not allowed, try again");
                breed = input.readLine("Enter dog breed").trim();
            }
            int age = input.readInteger("Enter age");
            int weight = input.readInteger("Enter weight");
            Dog dog = new Dog(dogName, breed, age, weight);
            dogCollection.addDog(dog);
            System.out.println("The dog " + dog.getName() + " has been added to the register");
            break;
        }
    }
    private void listOwners() {
        if(checkForOwners()){
            return;
        }
        int maxOwnerLength = "Name ".length();
        int maxDogsLength = "Dogs".length();
        for (Owner owner : ownerCollection.getOwners()) {
            maxOwnerLength = Math.max(maxOwnerLength, owner.getName().length());
            String dogsList = "";
            for (Dog dog : owner.getDogs()) {
                if (!dogsList.isEmpty()) {
                    dogsList += ", ";
                }
                dogsList += dog.getName();
            }
            maxDogsLength = Math.max(maxDogsLength, dogsList.toString().length());
        }
        String header = "%-" + maxOwnerLength + "s  %-" + maxDogsLength + "s\n";
        System.out.printf(header, "Name", "Dogs");
        for (Owner owner : ownerCollection.getOwners()) {
            String dogsList = "";
            for (Dog dog : owner.getDogs()) {
                if (!dogsList.isEmpty()) {
                    dogsList += ", ";
                }
                dogsList += dog.getName();
            }
            System.out.printf(header, owner.getName(), dogsList);
        }
    }
    private void removeDog() {
        if(checkForDogs()){
            return;
        }
        String dogName = input.readLine("Enter dog name").toUpperCase();
        if(!dogCollection.containsDog(dogName)){
            System.out.println("Error: The dog " + dogName +  " does not exist");
            return;
        }
        Dog dog = dogCollection.getDog(dogName);
        dog.setOwner(null);
        dogCollection.removeDog(dog);
        System.out.println("The dog " + dog.getName() + " has been removed from the register");
    }
    private void registerNewOwner() {
        while(true) {
                String ownerName = input.readLine("Enter owner name").trim();
                while (ownerName.isEmpty() || ownerName.isBlank()) {
                    System.out.println("Error: A blank string is not allowed, try again");
                    ownerName = input.readLine("Enter owner name").trim();
                }
                Owner owner = new Owner(ownerName.trim());
                if (ownerCollection.containsOwner(owner)) {
                    System.out.println("Error: Owner " + owner.getName() + " already exists");
                    break;
                }
                ownerCollection.addOwner(owner);
                System.out.println("The owner " + owner.getName() + " has been added to the register");
                break;
        }
    }
    private void shutDown() {
        System.out.println("The register shut down. ");
        input.close();
    }
    private boolean checkForOwners(){
        if(ownerCollection.getOwners().isEmpty()){
            System.out.println("Error: No owners in the register");
            return true;
        }
        return false;
    }
    private boolean checkForDogs(){
        if(dogCollection.getDogs().isEmpty()){
            System.out.println("Error: No dogs in the register");
            return true;
        }
        return false;
    }
    public static void main ( String[] args){
        new DogRegister().start();
    }
}
