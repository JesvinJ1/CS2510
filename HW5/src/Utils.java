import java.util.Random;

// class that handles making the random words
class Utils {
  Random rand;

  // constructor to use in the real games 
  Utils() {
    this(new Random());
  }

  // constructor for tester
  Utils(Random rand) {
    this.rand = rand;
  }

  // method makes a random 6 letter word
  String randomWord() {
    return randomWordHelper("", 6);
  }

  // helper method that makes the words
  String randomWordHelper(String current, int remaining) {
    if (remaining == 0) {
      return current;
    }

    String letters = "abcdefghijklmnopqrstuvwxyz";

    int randomIndex = rand.nextInt(letters.length());
    String randomChar = letters.substring(randomIndex, randomIndex + 1);

    return randomWordHelper(current + randomChar, remaining - 1);
  }
}
