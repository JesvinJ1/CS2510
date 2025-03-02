import tester.*;

import java.awt.Color;

import javalib.funworld.WorldScene;
import javalib.worldimages.TextImage;

// interface representing a list of IWords
interface ILoWord {

  // moves every word in the list
  ILoWord move();

  // returns true/ends the game if any word reaches the bottom
  boolean hasReachedBottom();

  // removes the word if it matches the input
  ILoWord removeTyped(String input);

  // returns true if any word in the list exactly matches the input
  boolean isWordFullyTyped(String input);

  // renders the words onto the scene
  WorldScene render(WorldScene scene);
}

// represents an empty list of words
class MtLoWord implements ILoWord {

  /* CLASS TEMPLATE
  FIELDS:
  ... N/A ...
  METHODS:
  ... this.move() ...               -- IWord
  ... this.hasReachedBottom() ...   -- boolean
  ... this.removeTyped() ...        -- ILoWord
  ... this.isWordFullyTyped() ...   -- boolean
  ... this.render() ...             -- WorldScene
  METHODS OF FIELDS:
  ... N/A ...
   */

  // moves every word in the list
  public ILoWord move() {
    return this;
  }

  // returns true/ends the game if any word reaches the bottom
  public boolean hasReachedBottom() {
    return false;
  }

  // removes the word if it matches the input
  public ILoWord removeTyped(String input) {
    return this;
  }

  // returns true if any word in the list exactly matches the input
  public boolean isWordFullyTyped(String input) {
    return false;
  }

  // renders the words onto the scene
  public WorldScene render(WorldScene scene) {
    return scene;

    /* Method Template:
     * everything in the class template
     */

  }
}

// represents a non empty list of words
class ConsLoWord implements ILoWord {
  IWord first;
  ILoWord rest;

  ConsLoWord(IWord first, ILoWord rest) {
    this.first = first;
    this.rest = rest;
  }

  /* CLASS TEMPLATE
  FIELDS:
  ... this.first ...    -- IWord
  ... this.rest ...     -- ILoWord
  METHODS:
  ... this.move() ...               -- IWord
  ... this.hasReachedBottom() ...   -- boolean
  ... this.removeTyped() ...        -- ILoWord
  ... this.isWordFullyTyped() ...   -- boolean
  ... this.render() ...             -- WorldScene
  METHODS OF FIELDS:
  ... this.first.move() ...             -- ILoWord
  ... this.first.hasReachedBottom() ... -- boolean
  ... this.first.removeTyped() ...      -- ILoWord
  ... this.first.isWordFullyTyped() ... -- boolean
  ... this.first.render() ...           -- WorldScene
  ... this.rest.move() ...              -- ILoWord
  ... this.rest.hasReachedBottom() ...  -- boolean
  ... this.rest.removeTyped() ...       -- ILoWord
  ... this.rest.isWordFullyTyped() ...  -- boolean
  ... this.rest.render() ...            -- WorldScene
   */

  // moves every word in the list
  public ILoWord move() {
    return new ConsLoWord(this.first.move(), this.rest.move());
  }

  // returns true/ends the game if any word reaches the bottom
  public boolean hasReachedBottom() {
    return this.first.hasReachedBottom() || this.rest.hasReachedBottom();
  }

  // removes the word if it matches the input
  public ILoWord removeTyped(String input) {
    if (this.first.matchesInput(input)) {
      return this.rest;
    }
    return new ConsLoWord(this.first, this.rest.removeTyped(input));
  }

  // returns true if any word in the list exactly matches the input
  public boolean isWordFullyTyped(String input) {
    return this.first.matchesInput(input) || this.rest.isWordFullyTyped(input);
  }

  // renders the words onto the scene
  public WorldScene render(WorldScene scene) {
    return this.rest.render(this.first.render(scene));

    /* Method Template:
     * everything in the class template, plus:
     ... this.first.render(WorldScene scene) ...    -- WorldScene
     ... this.rest.render(WorldScene scene) ...     -- WorldScene
     */
  }
}

// Examples class
class ExamplesILoWord {

  Word word1 = new Word("testing", 100, 50);
  Word word2 = new Word("project", 200, 300);
  Word bottomWord = new Word("bottom", 150, Constants.HEIGHT);

  ILoWord mt = new MtLoWord();
  ILoWord list1 = new ConsLoWord(word1, mt);
  ILoWord list2 = new ConsLoWord(word1, new ConsLoWord(word2, mt));
  ILoWord listWithBottom = new ConsLoWord(bottomWord, new ConsLoWord(word1, mt));

  // tests for move method
  boolean testMove(Tester t) {
    return t.checkExpect(list1.move(), new ConsLoWord(
        new Word("testing", 100, 50 + Constants.WORD_SPEED), mt))
        && t.checkExpect(list2.move(), new ConsLoWord(
            new Word("testing", 100, 50 + Constants.WORD_SPEED), 
            new ConsLoWord(new Word("project", 200, 300 + Constants.WORD_SPEED), mt)));
  }

  // tests for hasReachedBottom method
  boolean testHasReachedBottom(Tester t) {
    return t.checkExpect(mt.hasReachedBottom(), false)
        && t.checkExpect(list1.hasReachedBottom(), false)
        && t.checkExpect(list2.hasReachedBottom(), false)
        && t.checkExpect(listWithBottom.hasReachedBottom(), true);
  }

  // tests for removeTyped method
  boolean testRemoveTyped(Tester t) {
    return t.checkExpect(list1.removeTyped("testing"), mt)
        && t.checkExpect(list1.removeTyped("ing"), list1)
        && t.checkExpect(list2.removeTyped("testing"), new ConsLoWord(word2, mt))
        && t.checkExpect(list2.removeTyped("project"), new ConsLoWord(word1, mt))
        && t.checkExpect(list2.removeTyped("huh"), list2)
        && t.checkExpect(listWithBottom.removeTyped("bottom"), new ConsLoWord(word1, mt));
  }

  // tests for isWordFullyTyped method
  boolean testIsWordFullyTyped(Tester t) {
    return t.checkExpect(mt.isWordFullyTyped(""), false)
        && t.checkExpect(list1.isWordFullyTyped("testing"), true)
        && t.checkExpect(list1.isWordFullyTyped("project"), false)
        && t.checkExpect(list2.isWordFullyTyped("project"), true)
        && t.checkExpect(list2.isWordFullyTyped("testing"), true)
        && t.checkExpect(list2.isWordFullyTyped("randomm"), false)
        && t.checkExpect(listWithBottom.isWordFullyTyped("bottom"), true)
        && t.checkExpect(listWithBottom.isWordFullyTyped("testing"), true)
        && t.checkExpect(listWithBottom.isWordFullyTyped("project"), false);
  }

  // tests for render method
  boolean testRender(Tester t) {

    WorldScene scene = new WorldScene(Constants.WIDTH, Constants.HEIGHT);
    WorldScene expectedScene = scene.placeImageXY(
        new TextImage("testing", 20, Color.BLACK), 100, 50);
    WorldScene expectedScene2 = expectedScene.placeImageXY(
        new TextImage("project", 20, Color.BLACK), 200, 300);

    return t.checkExpect(mt.render(scene), scene) 
        && t.checkExpect(list1.render(scene), expectedScene) 
        && t.checkExpect(list2.render(scene), expectedScene2);
  }
}
