# Hundregister - programmeringsuppgift i Prog 1

En liten konsolapplikation i Java där man kan registrera **hundar** och **ägare**, lista dem, samt koppla en hund till en ägare. Mest för skojs skull (och som övning i klasser, samlingar och sortering).

## Körning

Källkoden ligger i `src/`. Du kan köra via valfri IDE (t.ex. IntelliJ), eller via terminal:

```bash
# från projektroten
javac -d bin src/*.java
java -cp bin DogRegister
```

## Kommandon i programmet

När programmet startar kan du skriva kommandon (de är **inte** case-sensitive; programmet gör om till versaler).

- `REGISTER NEW OWNER`
- `REGISTER NEW DOG`
- `REMOVE OWNER`
- `REMOVE DOG`
- `LIST OWNERS`
- `LIST DOGS`
- `INCREASE AGE`
- `GIVE DOG TO OWNER`
- `REMOVE DOG FROM OWNER`
- `EXIT`

## Kort om mapparna

- `src/`: Java-källkod
- `bin/`: kompilerade `.class`-filer (om du kör kommandot ovan)
- `out/`: IDE-byggoutput (kan ignoreras)
