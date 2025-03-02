import tester.Tester;

// to represent a pet owner
class Person {
    String name;
    IPet pet;
    int age;
 
    Person(String name, IPet pet, int age) {
        this.name = name;
        this.pet = pet;
        this.age = age;
    }
    
    /* TEMPLATE
    FIELDS:
    ... this.name ...                -- String
    ... this.pet ...                 -- IPet
    ... this.age ...                 -- int
    METHODS
    ... this.isOlder(Person other) ...        -- boolean
    ... this.samePetName(String petName) ...  -- boolean
    .. this.perish() ...                 -- Person
 */
    
    //is this Person older than the given Person?
    boolean isOlder(Person other) {
      return this.age > other.age;
    }
    
    // does the name of this person's pet match the given name?
    boolean samePetName(String petName) {
      return this.pet.hasName(petName);
    }
    
    //creates a person who's pet has perished
    Person perish() {
      return new Person(this.name, new NoPet(), this.age);
    }
}



// to represent a pet
interface IPet { 
  // does the IPet's name match the actual name?
  boolean hasName(String name);
}
 
// to represent a pet cat
class Cat implements IPet {
    String name;
    String kind;
    boolean longhaired;
 
    Cat(String name, String kind, boolean longhaired) {
        this.name = name;
        this.kind = kind;
        this.longhaired = longhaired;
    }
    
    /* TEMPLATE
    FIELDS:
    ... this.name ...                -- String
    ... this.kind ...                -- String
    ... this.longhaired ...          -- boolean
    METHODS
    ... this.hasName(String name) ...     -- boolean
 */
    
    // does the pet's name match the actual name?
    public boolean hasName(String name) {
      return this.name.equals(name);
    }
}
 
// to represent a pet dog
class Dog implements IPet {
    String name;
    String kind;
    boolean male;
 
    Dog(String name, String kind, boolean male) {
        this.name = name;
        this.kind = kind;
        this.male = male;
    }
    
    /* TEMPLATE
    FIELDS:
    ... this.name ...                -- String
    ... this.kind ...                -- String
    ... this.male ...                -- boolean
    METHODS
    ... this.hasName(String name) ...     -- boolean
 */
    
    // does the pet's name match the actual name?
    public boolean hasName(String name) {
      return this.name.equals(name);
    }
}

// to represent a no pet owner
class NoPet implements IPet {
  NoPet() {
    
  }
  
  /* TEMPLATE
  FIELDS:
  ... n/a ...
  METHODS
  ... this.hasName(String name) ...     -- boolean
*/
  
  //does the pet's name match the actual name?
  public boolean hasName(String name) {
    return false;
  }
}

class ExamplesPets {
  // Examples of pets
  IPet pet1 = new Cat ("Bill", "Birman", true);
  IPet pet2 = new Cat ("Billy", "Persian", false);
  IPet pet3 = new Dog ("David", "German", true);
  IPet pet4 = new Dog ("Tim", "Beagle", false);
  
  // Examples of people
  Person person1 = new Person("John", pet1, 12);
  Person person2 = new Person("Jerry", pet2, 30);
  Person person3 = new Person("Tom", pet3, 21);
  Person person4 = new Person("Bill", pet4, 8);
  
  // Examples of no pets
  IPet noPet1 = new NoPet();
  IPet noPet2 = new NoPet();
  IPet noPet3 = new NoPet();
  
  // tests for isOlder
  boolean testIsOlder(Tester t) {
    return t.checkExpect(person1.isOlder(person2), false) &&
        t.checkExpect(person2.isOlder(person1), true) &&
        t.checkExpect(person3.isOlder(person1), true);
  }
  
  // tests for samePetName
  boolean testSamePetName(Tester t) {
    return t.checkExpect(person1.samePetName("John"), false) &&
        t.checkExpect(person1.samePetName("Bill"), true) &&
        t.checkExpect(person3.samePetName("David"), true);
  }
  
  // tests for perish
  boolean testPerish(Tester t) {
    return t.checkExpect(person1.perish(), new Person("John", noPet1, 12))
        && t.checkExpect(person2.perish(), new Person("Jerry", noPet3, 30));
  }
}

