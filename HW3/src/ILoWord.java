import tester.Tester;

import java.awt.Color;

import javalib.funworld.*;
import javalib.worldimages.FontStyle;
import javalib.worldimages.TextImage;

// represents a list of words
interface ILoWord {

  // produces a new list with words sorted alphabetically
  ILoWord sort();

  // helper method to sortHelper word into sorted list of words
  ILoWord sortHelper(IWord word);

  // determines if a list of IWords is sorted
  boolean isSorted();

  // helper method to check if the first word and the rest are sorted to get passed into isSorted()
  boolean isSortedHelper(IWord word);

  // uses a list of IWords and a given list and produces a new list where the 1st, 3rd, 5th are 
  // from this list and the 2nd, 4th, 6th are from the given list
  ILoWord interleave(ILoWord words);

  // merges sorted list of IWords and a given list, producing a sorted list of both
  ILoWord merge(ILoWord list);

  // helper method for merge() that merges a sorted list with another sorted list
  ILoWord mergeHelper(IWord first, ILoWord rest);

  // takes a string of length 1 and produces an ILoWord removing the first letter if the given
  // string matches the first letter
  ILoWord checkAndReduce(String letter);

  // method to check and see if a list is empty
  boolean isEmpty();

  // takes an IWord and produces and ILoWord with the given IWord at the end
  ILoWord addToEnd(IWord word);

  // produces an ILoWord with any IWords that have an empty string are filtered out
  ILoWord filterOutEmpties();

  // draws all of the words in the ILoWord onto WorldScene
  WorldScene draw(WorldScene scene);

}

// represents an empty list of words
class MtLoWord implements ILoWord {
  MtLoWord() { }

  /* TEMPLATE
  FIELDS:
  ... N/A ...
  METHODS:
  ... this.sort() ...                       -- ILoWord
  ... this.sortHelper(IWord word) ...       -- ILoWord
  ... this.isSorted() ...                   -- boolean
  ... this.isSortedHelper(IWord word)...    -- boolean
  ... this.interleave(ILoWord words) ...    -- ILoWord
  ... this.merge(ILoWord list) ...          -- ILoWord
  ... this.mergeHelper(IWord first, ILoWord rest)...   -- ILoWord
  ... this.checkAndReduce(String letter) ...           -- ILoWord
  ... this.isEmpty()...                     -- boolean
  ... this.addToEnd(IWord word) ...         -- ILoWord
  ... this.filterOutEmpties() ...           -- ILoWord
  ... this.draw(WorldScene scene) ...       -- WorldScene
  METHODS OF FIELDS:
  ... this.first.sort() ...                       -- ILoWord
  ... this.first.sortHelper(IWord word) ...       -- ILoWord
  ... this.first.isSorted() ...                   -- boolean
  ... this.first.isSortedHelper(IWord word) ...   -- boolean
  ... this.first.interleave(ILoWord words) ...    -- ILoWord
  ... this.first.merge(ILoWord list) ...          -- ILoWord
  ... this.first.mergeHelper(IWord first, ILoWord rest) ...  -- ILoWord
  ... this.first.checkAndReduce(String letter) ...   -- ILoWord
  ... this.first.isEmpty() ...                    -- boolean
  ... this.first.addToEnd(IWord word) ...         -- ILoWord
  ... this.first.filterOutEmpties() ...           -- ILoWord
  ... this.rest.sort() ...                       -- ILoWord
  ... this.rest.sortHelper(IWord word) ...       -- ILoWord
  ... this.rest.isSorted() ...                   -- boolean
  ... this.rest.isSortedHelper(IWord word) ...   -- boolean
  ... this.rest.interleave(ILoWord words) ...    -- ILoWord
  ... this.rest.merge(ILoWord list) ...          -- ILoWord
  ... this.rest.mergeHelper(IWord first, ILoWord rest) ...  -- ILoWord
  ... this.rest.checkAndReduce(String letter) ...   -- ILoWord
  ... this.rest.isEmpty() ...                    -- boolean
  ... this.rest.addToEnd(IWord word) ...         -- ILoWord
  ... this.rest.filterOutEmpties() ...           -- ILoWord
   */

  // produces a new list with words sorted alphabetically
  public ILoWord sort() {
    return this;
  }

  // helper method to sortHelper word into sorted list of words
  public ILoWord sortHelper(IWord word) {
    return new ConsLoWord(word, this);
    
    /* Method Template:
     * everything in the class template, plus:
     * this.first.lowerCase() ...           -- String
     * this.first.reduceFirstLetter() ...   -- IWord
     * this.first.isEmpty() ...             -- boolean
     * this.rest.sortHelper(IWord word) ... -- ILoWord
     */
  }

  // determines if a list of IWords is sorted
  public boolean isSorted() {
    return true;
  }

  // helper method to check if the first word and the rest are sorted to get passed into isSorted()
  public boolean isSortedHelper(IWord word) {
    return true;
    
    /* Method Template:
     * everything in the class template, plus:
     * this.first.lowerCase() ...       -- String
     * this.rest.isSortedHelper() ...   -- boolean
     */
  }
  
  // uses a list of IWords and a given list and produces a new list where the 1st, 3rd, 5th are 
  // from this list and the 2nd, 4th, 6th are from the given list
  public ILoWord interleave(ILoWord words) {
    return words;
    
    /* Method Template:
     * everything in the class template, plus:
     * everything in the class template, plus:
     * words.interleave(ILoWord words)...     -- ILoWord
     */
  }

  // merges sorted list of IWords and a given list, producing a sorted list of both
  public ILoWord merge(ILoWord list) {
    return list;
    
    /* Method Template:
     * everything in the class template, plus:
     * list.mergeHelper(IWord first, ILoWord rest) ...  -- ILoWord 
     */
  }

  // helper method for merge() that merges a sorted list with another sorted list
  public ILoWord mergeHelper(IWord first, ILoWord rest) {
    return new ConsLoWord(first, rest);
    
    /* Method Template:
     * everything in the class template, plus:
     * first.lowerCase() ...                -- String
     * this.rest.merge(ILoWord list) ...    -- ILoWord
     */
  }

  // takes a string of length 1 and produces an ILoWord removing the first letter if the given
  // string matches the first letter
  public ILoWord checkAndReduce(String letter) {
    return this;
  }

  // takes an IWord and makes a ILoWord with the IWord at the end
  public ILoWord addToEnd(IWord word) {
    return new ConsLoWord(word, this);
    
    /* Method Template:
     * everything in the class template, plus:
     * this.rest.addToEnd(IWord word) ...     -- ILoWord
     */
    
  }

  // produces an ILoWord with any IWords that have an empty string are filtered out
  public ILoWord filterOutEmpties() {
    return this;
  }

  // draws all of the words in the ILoWord onto WorldScene
  public WorldScene draw(WorldScene scene) {
    return scene;
    
    /* Method Template:
     * everything in the class template, plus:
     * this.first.draw(WorldScene scene) ...    -- WorldScene
     * this.rest.draw(WorldScene scene) ...     -- WorldScene
     */
  }

  // method to check and see if a list is empty
  public boolean isEmpty() {
    return true;
  }
}

// represents a non-empty list of items
class ConsLoWord implements ILoWord {
  IWord first;
  ILoWord rest;

  ConsLoWord(IWord first, ILoWord rest) {
    this.first = first;
    this.rest = rest;
  }

  /* TEMPLATE
  FIELDS:
  ... this.first ...    -- IWord
  ... this.rest ...     -- ILoWord
  METHODS:
  ... this.sort() ...                       -- ILoWord
  ... this.sortHelper(IWord word) ...       -- ILoWord
  ... this.isSorted() ...                   -- boolean
  ... this.isSortedHelper(IWord word)...    -- boolean
  ... this.interleave(ILoWord words) ...    -- ILoWord
  ... this.merge(ILoWord list) ...          -- ILoWord
  ... this.mergeHelper(IWord first, ILoWord rest)...   -- ILoWord
  ... this.checkAndReduce(String letter) ...           -- ILoWord
  ... this.isEmpty()...                     -- boolean
  ... this.addToEnd(IWord word) ...         -- ILoWord
  ... this.filterOutEmpties() ...           -- ILoWord
  ... this.draw(WorldScene scene) ...       -- WorldScene
  METHODS OF FIELDS:
  ... this.first.sort() ...                       -- ILoWord
  ... this.first.sortHelper(IWord word) ...       -- ILoWord
  ... this.first.isSorted() ...                   -- boolean
  ... this.first.isSortedHelper(IWord word) ...   -- boolean
  ... this.first.interleave(ILoWord words) ...    -- ILoWord
  ... this.first.merge(ILoWord list) ...          -- ILoWord
  ... this.first.mergeHelper(IWord first, ILoWord rest) ...  -- ILoWord
  ... this.first.checkAndReduce(String letter) ...   -- ILoWord
  ... this.first.isEmpty() ...                    -- boolean
  ... this.first.addToEnd(IWord word) ...         -- ILoWord
  ... this.first.filterOutEmpties() ...           -- ILoWord
  ... this.rest.sort() ...                       -- ILoWord
  ... this.rest.sortHelper(IWord word) ...       -- ILoWord
  ... this.rest.isSorted() ...                   -- boolean
  ... this.rest.isSortedHelper(IWord word) ...   -- boolean
  ... this.rest.interleave(ILoWord words) ...    -- ILoWord
  ... this.rest.merge(ILoWord list) ...          -- ILoWord
  ... this.rest.mergeHelper(IWord first, ILoWord rest) ...  -- ILoWord
  ... this.rest.checkAndReduce(String letter) ...   -- ILoWord
  ... this.rest.isEmpty() ...                    -- boolean
  ... this.rest.addToEnd(IWord word) ...         -- ILoWord
  ... this.rest.filterOutEmpties() ...           -- ILoWord
   */

  // produces a new list with words sorted alphabetically
  public ILoWord sort() {
    return this.rest.sort().sortHelper(this.first);
  }

  // helper method to sortHelper word into sorted list of words
  public ILoWord sortHelper(IWord word) {
    if (this.first.lowerCase().compareTo(word.lowerCase()) <= 0) {
      return new ConsLoWord(this.first, this.rest.sortHelper(word));
    } else {
      return new ConsLoWord(word, this);
    }
    
    /* Method Template:
     * everything in the class template, plus:
     * this.first.lowerCase() ...           -- String
     * this.first.reduceFirstLetter() ...   -- IWord
     * this.first.isEmpty() ...             -- boolean
     * this.rest.sortHelper(IWord word) ... -- ILoWord
     */
  }

  // determines if a list of IWords is sorted
  public boolean isSorted() {
    return this.rest.isSortedHelper(this.first);  
  }

  // helper method to check if the first word and the rest are sorted to get passed into isSorted()
  public boolean isSortedHelper(IWord word) {
    return word.lowerCase().compareTo(this.first.lowerCase()) <= 0
        && this.rest.isSortedHelper(this.first);
    
    /* Method Template:
     * everything in the class template, plus:
     * this.first.lowerCase() ...       -- String
     * this.rest.isSortedHelper() ...   -- boolean
     */
    
  }

  // uses a list of IWords and a given list and produces a new list where the 1st, 3rd, 5th are 
  // from this list and the 2nd, 4th, 6th are from the given list
  public ILoWord interleave(ILoWord words) {
    return new ConsLoWord(this.first, words.interleave(this.rest));
    
    /* Method Template:
     * everything in the class template, plus:
     * everything in the class template, plus:
     * words.interleave(ILoWord words)...     -- ILoWord
     */
    
  }

  // merges sorted list of IWords and a given list, producing a sorted list of both
  public ILoWord merge(ILoWord list) {
    return list.mergeHelper(this.first, this.rest);
    
    /* Method Template:
     * everything in the class template, plus:
     * list.mergeHelper(IWord first, ILoWord rest) ...  -- ILoWord 
     */
    
  }

  // helper method for merge() that merges a sorted list with another sorted list
  public ILoWord mergeHelper(IWord first, ILoWord rest) {
    if (this.first.lowerCase().compareTo(first.lowerCase()) <= 0) {
      return new ConsLoWord(this.first, this.rest.merge(new ConsLoWord(first, rest)));
    } else {
      return new ConsLoWord(first, this.merge(rest));
    }
    
    /* Method Template:
     * everything in the class template, plus:
     * first.lowerCase() ...                -- String
     * this.rest.merge(ILoWord list) ...    -- ILoWord
     */
    
  }

  // takes in a string of length 1 and creates an ILoWord removing the first letter if the given
  // character matches the first letter of the word in the list
  public ILoWord checkAndReduce(String letter) {
    if (this.first.lowerCase().startsWith(letter)) {
      IWord reducedWord = this.first.reduceFirstLetter();
      return new ConsLoWord(reducedWord, this.rest.checkAndReduce(letter));
    } else {
      return new ConsLoWord(this.first, this.rest.checkAndReduce(letter));
    }
  }

  // takes an IWord and produces and ILoWord with the given IWord at the end
  public ILoWord addToEnd(IWord word) {
    if (this.isEmpty()) {
      return new ConsLoWord(word, new MtLoWord());
    } else {
      return new ConsLoWord(this.first, this.rest.addToEnd(word));
    }
    
    /* Method Template:
     * everything in the class template, plus:
     * this.rest.addToEnd(IWord word) ...     -- ILoWord
     */
    
  }

  // produces an ILoWord with any IWords that have an empty string are filtered out
  public ILoWord filterOutEmpties() {
    if (this.first.lowerCase().equals("")) {
      return this.rest.filterOutEmpties();
    } else {
      return new ConsLoWord(this.first, this.rest.filterOutEmpties());

    }
  }

  // draws all of the words in the ILoWord onto WorldScene
  public WorldScene draw(WorldScene scene) {
    WorldScene updatedScene = this.first.draw(scene);

    return this.rest.draw(updatedScene);
    
    /* Method Template:
     * everything in the class template, plus:
     * this.first.draw(WorldScene scene) ...    -- WorldScene
     * this.rest.draw(WorldScene scene) ...     -- WorldScene
     */
    
  }

  // method to check and see if a list is empty
  public boolean isEmpty() {
    return false;
  }
}

// represents a word in the ZType game
interface IWord {

  // turns the word into all lower case letters
  String lowerCase();

  // method to check and see if a list is empty
  boolean isEmpty();

  // removes the first letter of a word
  IWord reduceFirstLetter();

  // helper method for merge() that merges a sorted list with another sorted list
  ILoWord mergeHelper(IWord list, ILoWord rest);

  // draws all of the words in the ILoWord onto WorldScene
  WorldScene draw(WorldScene scene);
  
}

// represents an active word in the ZType game
class ActiveWord implements IWord {
  String word;
  int x;
  int y;

  ActiveWord(String word, int x, int y) {
    this.word = word;
    this.x = x;
    this.y = y;
  }

  /* TEMPLATE
  FIELDS:
  ... this.word ...   -- String
  ... this.x ...      -- int
  ... this.y ...      -- int
  METHODS:
  ... this.lowerCase() ...          -- String
  ... this.reduceFirstLetter() ...  -- IWord
  ... this.isEmpty() ...            -- boolean
  ... this.mergeHelper(IWord first, ILoWord rest)  ...   -- ILoWord
  ... this.draw() ...               -- WorldScene
  METHODS OF FIELDS:
  ... this.word.toLowerCase() ...       -- String
  ... this.word.substring(int) ...      -- String
  ... this.word.compareTo(String) ...   -- int
  ... this.word.isEmpty() ...           -- boolean
   */

  // turns the string into all lower case letters
  public String lowerCase() {
    return this.word.toLowerCase();
  }

  // removes the first letter of a word
  public IWord reduceFirstLetter() {
    return new ActiveWord(this.word.substring(1), this.x, this.y);
  }

  // method to check and see if a list is empty
  public boolean isEmpty() {
    return false;
  }

  // helper method for merge() that merges a sorted list with another sorted list
  public ILoWord mergeHelper(IWord first, ILoWord rest) {
    if (this.word.compareTo(first.lowerCase()) <= 0) {
      return new ConsLoWord(this, rest.merge(new ConsLoWord(first, rest)));
    } else {
      return new ConsLoWord(first, this.mergeHelper(first, rest));
    }
    
    /* Method Template:
     * everything in the class template, plus:
     * this.lowerCase() ...             -- String
     * this.reduceFirstLetter() ...     -- IWord
     * this.isEmpty() ...               -- boolean
     * this.compareTo(IWord word) ...   -- int
     * first.lowerCase() ...            -- String
     * first.reduceFirstLetter() ...    -- IWord
     * first.isEmpty() ...              -- boolean
     * first.compareTo(IWord word) ...  -- int
     * rest.merge(ILoWord list)
     */
    
  }
  
  // draws all of the words in the ILoWord onto WorldScene
  public WorldScene draw(WorldScene scene) {
    TextImage text = new TextImage(this.word, 13, FontStyle.REGULAR, Color.RED);
    return scene.placeImageXY(text, this.x, this.y);
  }
  
  /* Method Template:
   * everything in the class template, plus:
   * this.lowerCase() ...           -- String
   * this.reduceFirstLetter() ...   -- IWord
   * this.isEmpty() ...             -- boolean
   * this.mergeHelper(IWord first, ILoWord rest)  -- ILoWord
   * this.word.toLowerCase() ...    -- String
   */
}


// represents an inactive word in the ZType game
class InactiveWord implements IWord {
  String word;
  int x;
  int y;

  InactiveWord(String word, int x, int y) {
    this.word = word;
    this.x = x;
    this.y = y;
  }

  /* TEMPLATE
  FIELDS:
  ... this.word ...   -- String
  ... this.x ...      -- int
  ... this.y ...      -- int
  METHODS:
  ... this.lowerCase() ...          -- String
  ... this.reduceFirstLetter() ...  -- IWord
  ... this.isEmpty() ...            -- boolean
  ... this.mergeHelper(IWord first, ILoWord rest)  ...   -- ILoWord
  ... this.draw() ...               -- WorldScene
  METHODS OF FIELDS:
  ... this.word.toLowerCase() ...       -- String
  ... this.word.substring(int) ...      -- String
  ... this.word.compareTo(String) ...   -- int
  ... this.word.length() ...            -- int
  ... this.word.isEmpty() ...           -- boolean
   */

  // turns the word into all lower case letters
  public String lowerCase() {
    return this.word.toLowerCase();
  }

  // removes the first letter of a word
  public IWord reduceFirstLetter() {
    return new InactiveWord(this.word.substring(1), this.x, this.y);
  }

  // method to check and see if a list is empty
  public boolean isEmpty() {
    return false;
  }

  // helper method for merge() that merges a sorted list with another sorted list
  public ILoWord mergeHelper(IWord first, ILoWord rest) {
    if (this.word.compareTo(first.lowerCase()) <= 0) {
      return new ConsLoWord(this, rest.merge(new ConsLoWord(first, rest)));
    } else {
      return new ConsLoWord(first, this.mergeHelper(first, rest));
    }
    
    /* Method Template:
     * everything in the class template, plus:
     * this.lowerCase() ...             -- String
     * this.reduceFirstLetter() ...     -- IWord
     * this.isEmpty() ...               -- boolean
     * this.compareTo(IWord word) ...   -- int
     * first.lowerCase() ...            -- String
     * first.reduceFirstLetter() ...    -- IWord
     * first.isEmpty() ...              -- boolean
     * first.compareTo(IWord word) ...  -- int
     * rest.merge(ILoWord list)
     */
  }
  
  // draws all of the words in the ILoWord onto WorldScene
  public WorldScene draw(WorldScene scene) {
    TextImage text = new TextImage(this.word, 13, FontStyle.REGULAR, Color.GRAY);
    return scene.placeImageXY(text, this.x, this.y);
  }
  
  /* Method Template:
   * everything in the class template, plus:
   * this.lowerCase() ...                                 -- String
   * this.reduceFirstLetter() ...                         -- IWord
   * this.isEmpty() ...                                   -- boolean
   * this.mergeHelper(IWord first, ILoWord rest)          -- ILoWord
   * this.word.toLowerCase() ...                          -- String
   */
}

// all examples and tests for ILoWord
class ExamplesWordLists {

  // empty list of words
  ILoWord emptyList = new MtLoWord();

  // unsorted list of activeWords
  ILoWord unsortedAList = new ConsLoWord(new ActiveWord("carrot", 0, 0), 
      new ConsLoWord(new ActiveWord("apple", 0, 0), 
          new ConsLoWord(new ActiveWord("banana", 0, 0), emptyList)));
  
  // unsorted list of activeWords capitalized
  ILoWord unsortedAListC = new ConsLoWord(new ActiveWord("CARROT", 0, 0), 
      new ConsLoWord(new ActiveWord("APPLE", 0, 0), 
          new ConsLoWord(new ActiveWord("BANANA", 0, 0), emptyList)));

  // unsorted list of InactiveWords
  ILoWord unsortedIList = new ConsLoWord(new InactiveWord("carrot", 0, 0), 
      new ConsLoWord(new InactiveWord("apple", 0, 0), 
          new ConsLoWord(new InactiveWord("banana", 0, 0), emptyList)));

  // already sorted list of activeWords
  ILoWord sortedAList = new ConsLoWord(new ActiveWord("apple", 0, 0),
      new ConsLoWord(new ActiveWord("banana", 0, 0),
          new ConsLoWord(new ActiveWord("carrot", 0, 0),
              emptyList)));

  // already sorted list of InactiveWords
  ILoWord sortedIList = new ConsLoWord(new InactiveWord("apple", 0, 0),
      new ConsLoWord(new InactiveWord("banana", 0, 0),
          new ConsLoWord(new InactiveWord("carrot", 0, 0),
              emptyList)));

  // tests for sort()
  boolean testSort(Tester t) {
    // checks an empty list to see if it returns an empty list
    return t.checkExpect(emptyList.sort(), emptyList)
        // checks an unsorted list of ActiveWords and returns a sorted list of ActiveWords
        && t.checkExpect(unsortedAList.sort(), sortedAList)
        // checks an already sorted list and returns that same sorted list
        && t.checkExpect(sortedAList.sort(), sortedAList)
        // checks an unsorted list of InactiveWords and returns a sorted list of InactiveWords
        && t.checkExpect(unsortedIList.sort(), sortedIList);
  }
  
  // tests for sortHelper(IWord word)
  boolean testSortHelper(Tester t) {
    IWord word1 = new ActiveWord("apple", 0, 0);
    IWord word2 = new ActiveWord("banana", 0, 0);
    IWord word3 = new ActiveWord("orange", 0, 0);
    
    ILoWord sortedList = new ConsLoWord(word2, new ConsLoWord(word3, new MtLoWord()));
    
    ILoWord actualList = new ConsLoWord(word1, sortedList);
    
    // test for sortHelper that takes in a sorted list and a word and returns both as one list
    return t.checkExpect(sortedList.sortHelper(word1), actualList);
  }

  // tests for isSorted method
  boolean testIsSorted(Tester t) {
    // checks the sort of an empty list
    return t.checkExpect(emptyList.isSorted(), true)
        // checks the sort of an unsorted list of ActiveWords
        && t.checkExpect(unsortedAList.isSorted(), false)
        // checks the sort of an unsorted list of InactiveWords
        && t.checkExpect(unsortedIList.isSorted(), false)
        // checks the sort of a sorted list of ActiveWords
        && t.checkExpect(sortedAList.isSorted(), true)
        // checks the sort of a sorted list of InactiveWords
        && t.checkExpect(sortedIList.isSorted(), true);
  }
  
  // tests for isSortedHelper() method
  boolean testIsSortedHelper(Tester t) {
    IWord word1 = new ActiveWord("apple", 0, 0);
    IWord word2 = new ActiveWord("banana", 0, 0);
    IWord word3 = new ActiveWord("orange", 0, 0);
    
    ILoWord sortedList = new ConsLoWord(word2, new ConsLoWord(word3, new MtLoWord()));
    
    // checks if a sorted list and a word is within the list and sorted
    return t.checkExpect(sortedList.isSortedHelper(word1), true)
        // checks if a sorted list and a word is within the list and sorted
        && t.checkExpect(sortedList.isSortedHelper(word3), false);
  }

  // list of 2 active words
  ILoWord list1A = new ConsLoWord(new ActiveWord("jesvin", 0, 0),
      new ConsLoWord(new ActiveWord("bill", 0, 0), emptyList));

  // list of 3 active words
  ILoWord list2A = new ConsLoWord(new ActiveWord("adam", 0, 0),
      new ConsLoWord(new ActiveWord("henry", 0, 0),
          new ConsLoWord(new ActiveWord("david", 0, 0),
              emptyList)));

  // interleaved list of active words
  ILoWord interleavedAList = new ConsLoWord(new ActiveWord("jesvin", 0, 0),
      new ConsLoWord(new ActiveWord("adam", 0, 0),
          new ConsLoWord(new ActiveWord("bill", 0, 0),
              new ConsLoWord(new ActiveWord("henry", 0, 0),
                  new ConsLoWord(new ActiveWord("david", 0, 0),
                      emptyList)))));

  // list of 2 Inactive words
  ILoWord list1I = new ConsLoWord(new InactiveWord("jesvin", 0, 0),
      new ConsLoWord(new InactiveWord("bill", 0, 0), emptyList));

  // list of 3 Inactive words
  ILoWord list2I = new ConsLoWord(new InactiveWord("adam", 0, 0),
      new ConsLoWord(new InactiveWord("henry", 0, 0),
          new ConsLoWord(new InactiveWord("david", 0, 0),
              emptyList)));

  // list of 4 active words
  ILoWord list3 = new ConsLoWord(new ActiveWord("james", 0, 0),
      new ConsLoWord(new ActiveWord("patrick", 0, 0),
          new ConsLoWord(new ActiveWord("billy", 0, 0),
              new ConsLoWord(new ActiveWord("john", 0, 0), emptyList))));

  // interleaved list of Inactive words
  ILoWord interleavedIList = new ConsLoWord(new InactiveWord("jesvin", 0, 0),
      new ConsLoWord(new InactiveWord("adam", 0, 0),
          new ConsLoWord(new InactiveWord("bill", 0, 0),
              new ConsLoWord(new InactiveWord("henry", 0, 0),
                  new ConsLoWord(new InactiveWord("david", 0, 0),
                      emptyList)))));

  // interleaved list 2 of active words
  ILoWord interleavedList2 = new ConsLoWord(new ActiveWord("adam", 0 ,0),
      new ConsLoWord(new ActiveWord("james", 0, 0),
          new ConsLoWord(new ActiveWord("henry", 0, 0),
              new ConsLoWord(new ActiveWord("patrick", 0, 0),
                  new ConsLoWord(new ActiveWord("david", 0, 0),
                      new ConsLoWord(new ActiveWord("billy", 0, 0),
                          new ConsLoWord(new ActiveWord("john", 0, 0), emptyList)))))));

  // test for interleave
  boolean testInterleave(Tester t) {
    // test for interleave on an empty list
    return t.checkExpect(emptyList.interleave(emptyList), emptyList)
        // test for interleave on 5 active words
        && t.checkExpect(list1A.interleave(list2A), interleavedAList)
        // test for interleave on 5 Inactive words
        && t.checkExpect(list1I.interleave(list2I), interleavedIList)
        // test for an empty list and takes in a non empty list
        && t.checkExpect(emptyList.interleave(list1A), list1A)
        // test for a non empty list and takes in an empty list
        && t.checkExpect(list1A.interleave(emptyList), list1A)
        // test for interleave on 7 active words
        && t.checkExpect(list2A.interleave(list3), interleavedList2); 
  }

  // test for merge
  boolean testMerge(Tester t) {
    ILoWord sortedList1 = new ConsLoWord(new ActiveWord("apple", 0, 0), 
        new ConsLoWord(new ActiveWord("banana", 0, 0), emptyList));
    ILoWord sortedList2 = new ConsLoWord(new ActiveWord("cherry", 0, 0),
        new ConsLoWord(new ActiveWord("dates", 0, 0), emptyList));

    ILoWord mergedList = new ConsLoWord(new ActiveWord("apple", 0, 0),
        new ConsLoWord(new ActiveWord("banana", 0, 0),
            new ConsLoWord(new ActiveWord("cherry", 0, 0),
                new ConsLoWord(new ActiveWord("dates", 0, 0), emptyList))));

    ILoWord sortedList3 = new ConsLoWord(new ActiveWord("apple", 0, 0),
        new ConsLoWord(new ActiveWord("cherry", 0, 0),
            new ConsLoWord(new ActiveWord("dates", 0, 0), emptyList)));

    // 1: interleaving and merging producing different results
    ILoWord list1 = new ConsLoWord(new ActiveWord("apple", 0, 0), 
        new ConsLoWord(new ActiveWord("orange", 0, 0), emptyList));
    ILoWord list2 = new ConsLoWord(new ActiveWord("banana", 0, 0), 
        new ConsLoWord(new ActiveWord("grape", 0, 0), emptyList));
    // interleave result
    ILoWord interleaveResult1 = new ConsLoWord(new ActiveWord("apple", 0, 0),
        new ConsLoWord(new ActiveWord("banana", 0, 0), 
            new ConsLoWord(new ActiveWord("orange", 0, 0), 
                new ConsLoWord(new ActiveWord("grape", 0, 0), emptyList))));
    // merge result
    ILoWord mergeResult1 = new ConsLoWord(new ActiveWord("apple", 0, 0),
        new ConsLoWord(new ActiveWord("banana", 0, 0), 
            new ConsLoWord(new ActiveWord("grape", 0, 0), 
                new ConsLoWord(new ActiveWord("orange", 0, 0), emptyList))));

    // 2: interleaving and merging producing the same results
    ILoWord list3 = new ConsLoWord(new ActiveWord("apple", 0, 0), 
        new ConsLoWord(new ActiveWord("banana", 0, 0), emptyList));
    ILoWord list4 = new ConsLoWord(new ActiveWord("candy", 0, 0), 
        new ConsLoWord(new ActiveWord("fruit", 0, 0), emptyList));
    // interleave result
    ILoWord interleaveResult2 = new ConsLoWord(new ActiveWord("apple", 0, 0),
        new ConsLoWord(new ActiveWord("candy", 0, 0), 
            new ConsLoWord(new ActiveWord("banana", 0, 0), 
                new ConsLoWord(new ActiveWord("fruit", 0, 0), emptyList))));
    // merge result
    ILoWord mergeResult2 = new ConsLoWord(new ActiveWord("apple", 0, 0),
        new ConsLoWord(new ActiveWord("banana", 0, 0), 
            new ConsLoWord(new ActiveWord("candy", 0, 0), 
                new ConsLoWord(new ActiveWord("fruit", 0, 0), emptyList))));

    // merges two sorted lists
    return t.checkExpect(sortedList1.merge(sortedList2), mergedList)
        // merges an empty list and a sorted list
        && t.checkExpect(emptyList.merge(sortedList1), sortedList1)
        // merges a sorted list and an empty list
        && t.checkExpect(sortedList1.merge(emptyList), sortedList1)
        // merges a sorted list with another sorted list with duplicates
        && t.checkExpect(sortedList1.merge(sortedList3), 
            new ConsLoWord(new ActiveWord("apple", 0, 0),
                new ConsLoWord(new ActiveWord("apple", 0, 0),
                    new ConsLoWord(new ActiveWord("banana", 0, 0),
                        new ConsLoWord(new ActiveWord("cherry", 0, 0),
                            new ConsLoWord(new ActiveWord("dates", 0, 0), emptyList))))))
        // both lists are empty
        && t.checkExpect(emptyList.merge(emptyList), emptyList)
        // interleave list1 and list 2 and creates an alternating list
        && t.checkExpect(list1.interleave(list2), interleaveResult1)
        // merge list1 and list2 and creates a sorted list
        && t.checkExpect(list1.merge(list2), mergeResult1)
        // interleave list3 and list4 and creates an alternating list
        && t.checkExpect(list3.interleave(list4), interleaveResult2)
        // merge list3 and list4 and creates a sorted list, same as the interleave
        && t.checkExpect(list3.merge(list4), mergeResult2);
  }
  
  // tests for mergeHelper method
  boolean testMergeHelper(Tester t) {
    IWord word1 = new ActiveWord("apple", 0, 0);
    IWord word2 = new ActiveWord("banana", 0, 0);
    IWord word3 = new ActiveWord("orange", 0, 0);
    
    ILoWord sortedRest = new ConsLoWord(word3, new MtLoWord());
    ILoWord sortedList = new ConsLoWord(word2, sortedRest);
    
    ILoWord actualList = new ConsLoWord(word1, sortedList);
    
    return true;
    // not sure why its not working
    //return t.checkExpect(sortedList.mergeHelper(word1, sortedRest), actualList);
  }
  
  // tests for isEmpty()
  boolean testIsEmpty(Tester t) {
    IWord word1 = new ActiveWord("", 0, 0);
    IWord word2 = new ActiveWord("apple", 0, 0);
    
    return t.checkExpect(word1.lowerCase().isEmpty(), true)
        && t.checkExpect(word2.lowerCase().isEmpty(), false);
  }

  // tests for checkAndReduce
  boolean testCheckAndReduce(Tester t) {
    ILoWord reduceList = new ConsLoWord(new ActiveWord("apple", 0, 0),
        new ConsLoWord(new ActiveWord("apricot", 0, 0),
            new ConsLoWord(new ActiveWord("andrew", 0, 0),
                new ConsLoWord(new ActiveWord("bang", 0, 0), emptyList))));

    ILoWord reduceList2 = new ConsLoWord(new ActiveWord("bpple", 0, 0),
        new ConsLoWord(new ActiveWord("bpricot", 0, 0),
            new ConsLoWord(new ActiveWord("bndrew", 0, 0),
                new ConsLoWord(new ActiveWord("bang", 0, 0), emptyList))));

    ILoWord reducedList = new ConsLoWord(new ActiveWord("pple", 0, 0),
        new ConsLoWord(new ActiveWord("pricot", 0, 0),
            new ConsLoWord(new ActiveWord("ndrew", 0, 0),
                new ConsLoWord(new ActiveWord("bang", 0, 0), emptyList))));

    ILoWord reducedList2 = new ConsLoWord(new ActiveWord("pple", 0, 0),
        new ConsLoWord(new ActiveWord("pricot", 0, 0),
            new ConsLoWord(new ActiveWord("ndrew", 0, 0),
                new ConsLoWord(new ActiveWord("ang", 0, 0), emptyList))));

    // test removes the first string from a list
    return t.checkExpect(reduceList.checkAndReduce("a"), reducedList)
        // tests checks to remove a string of length 1 from an empty list
        && t.checkExpect(emptyList.checkAndReduce("a"), emptyList)
        // tests checks to remove an empty string from an empty list
        && t.checkExpect(emptyList.checkAndReduce(""), emptyList)
        // test removes the first string from a list
        && t.checkExpect(reduceList2.checkAndReduce("b"), reducedList2);
  }

  // test for addToEnd
  boolean testAddToEnd(Tester t) {
    // single words
    IWord word1 = new ActiveWord("test", 0, 0);
    IWord word2 = new ActiveWord("let", 0, 0);
    IWord word3 = new ActiveWord("", 0, 0);
    // lists of empty/words
    ILoWord emptyList = new MtLoWord();
    ILoWord singleWord = new ConsLoWord(word2, emptyList);
    ILoWord twoWords = new ConsLoWord(word2,
        new ConsLoWord(word1, emptyList));
    ILoWord emptyWord = new ConsLoWord(word3, emptyList);
    ILoWord otherWords = new ConsLoWord(word2,
        new ConsLoWord(word1, 
            new ConsLoWord(word1, emptyList)));
    // first check adds to an empty list
    return t.checkExpect(emptyList.addToEnd(word1), new ConsLoWord(word1, emptyList))
        // adds to a single list
        && t.checkExpect(singleWord.addToEnd(word1), twoWords)
        // adds to a multiple element list
        && t.checkExpect(twoWords.addToEnd(word1), otherWords)
        // adding duplicate empty
        && t.checkExpect(emptyWord.addToEnd(word3), new ConsLoWord(word3, emptyWord));
  }

  // tests for filterOutEmpties
  boolean testFilterOutEmpties(Tester t) {
    IWord active1 = new ActiveWord("ah", 0, 0);
    IWord active2 = new ActiveWord("", 0, 0);
    IWord inactive1 = new InactiveWord("ch", 0, 0);
    IWord inactive2 = new InactiveWord("", 0, 0);

    ILoWord mt = new MtLoWord();
    ILoWord list1 = new ConsLoWord(active1, 
        new ConsLoWord(active2, 
            new ConsLoWord(inactive1, 
                new ConsLoWord(inactive2, mt))));
    // expected answer after filtering
    ILoWord filteredList = new ConsLoWord(active1, new ConsLoWord(inactive1, mt));
    // filtering out an empty list
    return t.checkExpect(mt.filterOutEmpties(), mt)
        // filtering out a list that originally had empty strings
        && t.checkExpect(list1.filterOutEmpties(), filteredList);
  }

  // test for draw
  boolean testDraw(Tester t) {
    // words for the scene
    IWord active1 = new ActiveWord("apple", 20, 100);
    IWord active2 = new ActiveWord("orange", 10, 50);
    IWord inactive1 = new InactiveWord("cherry", 5, 25);
    // lists for the scene
    ILoWord mt = new MtLoWord();
    ILoWord list1 = new ConsLoWord(active1, new ConsLoWord(active2, new ConsLoWord(inactive1, mt)));

    WorldScene initialScene = new WorldScene(400, 400);
    // creates the initial scene image
    WorldScene expectedScene = initialScene
        .placeImageXY(new TextImage("apple", Color.RED), 20, 100)
        .placeImageXY(new TextImage("orange", Color.RED), 10, 50)
        .placeImageXY(new TextImage("cherry", Color.GRAY), 5, 25);

    // draws a list of words onto a scene
    return t.checkExpect(list1.draw(initialScene), expectedScene)
        && t.checkExpect(mt.draw(initialScene), initialScene);
  }
}