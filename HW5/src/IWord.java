import tester.*;
import javalib.funworld.WorldScene;
import javalib.worldimages.*;
import java.awt.Color;

// represents an IWord
interface IWord {

  // moves the word downward by a constant speed
  IWord move();

  // checks if the word reached the bottom
  boolean hasReachedBottom();

  // returns true if the word exactly matches the input
  boolean matchesInput(String input);

  // renders the word onto the scene
  WorldScene render(WorldScene scene);
}

// represents a single word
class Word implements IWord {
  String text;
  int x;
  int y;

  Word(String text, int x, int y) {
    this.text = text;
    this.x = x;
    this.y = y;
  }

  /* CLASS TEMPLATE
  FIELDS:
  ... this.text ...   -- String
  ... this.x ...      -- int
  ... this.y ...      -- int
  METHODS:
  ... this.move() ...                       -- IWord
  ... this.hasReachedBottom() ...           -- boolean
  ... this.matchesInput(String input) ...   -- boolean
  ... this.render(WorldScene scene) ...     -- WorldScene
  METHODS OF FIELDS:
  ... this.text.move() ...                    -- IWord
  ... this.text.hasReachedBottom() ...        -- boolean
  ... this.text.matchesInput(String input)    -- boolean
  ... this.text.render(WorldScene scene) ...  -- WorldScene
  ... this.x.move() ...                       -- IWord
  ... this.y.move() ...                       -- IWord
  ... this.x.render(WorldScene scene) ...     -- WorldScene
  ... this.y.render(WorldScene scene) ...     -- WorldScene
   */

  // moves the word downward by a constant speed
  public IWord move() {
    return new Word(this.text, this.x, this.y + Constants.WORD_SPEED);
  }

  // checks if the word reached the bottom
  public boolean hasReachedBottom() {
    return this.y >= Constants.HEIGHT;
  }

  // returns true if the word exactly matches the input
  public boolean matchesInput(String input) {
    return this.text.equals(input);
  }

  // renders the word onto the scene
  public WorldScene render(WorldScene scene) {
    return scene.placeImageXY(new TextImage(this.text, 20, Color.BLACK), this.x, this.y);

    /* Method Template:
     * everything in the class template, plus:
     ... this.text.render(WorldScene scene) ...   -- WorldScene
     */

  }
}

// Examples class
class ExamplesIWord {

  Word word1 = new Word("testing", 100, 50);
  Word word2 = new Word("project", 200, 300);
  Word bottomWord = new Word("bottom", 150, Constants.HEIGHT);

  // test move method
  boolean testMove(Tester t) {
    return t.checkExpect(word1.move(), new Word("testing", 100, 50 + Constants.WORD_SPEED))
        && t.checkExpect(word2.move(), new Word("project", 200, 300 + Constants.WORD_SPEED))
        && t.checkExpect(bottomWord.move(), 
            new Word("bottom", 150, Constants.HEIGHT + Constants.WORD_SPEED));
  }

  // test for hasReachedBottom method
  boolean testHasReachedBottom(Tester t) {
    return t.checkExpect(word1.hasReachedBottom(), false)
        && t.checkExpect(word2.hasReachedBottom(), false)
        && t.checkExpect(bottomWord.hasReachedBottom(), true);
  }

  // test for matchesInput method
  boolean testMatchesInput(Tester t) {
    return t.checkExpect(word1.matchesInput("testing"), true)
        && t.checkExpect(word1.matchesInput("tsting"), false)
        && t.checkExpect(word2.matchesInput("prject"), false)
        && t.checkExpect(word2.matchesInput("project"), true)
        && t.checkExpect(bottomWord.matchesInput("btm"), false)
        && t.checkExpect(bottomWord.matchesInput("bottom"), true);
  }

  // test for render method
  boolean testRender(Tester t) {
    WorldScene scene = new WorldScene(Constants.WIDTH, Constants.HEIGHT);
    WorldScene expectedScene = scene.placeImageXY(
        new TextImage("testing", 20, Color.BLACK), 100, 50);
    WorldScene expectedScene2 = scene.placeImageXY(
        new TextImage("project", 20, Color.BLACK), 200, 300);
    WorldScene expectedScene3 = scene.placeImageXY(
        new TextImage("bottom", 20, Color.BLACK), 150, Constants.HEIGHT);

    return t.checkExpect(word1.render(scene), expectedScene)
        && t.checkExpect(word2.render(scene), expectedScene2)
        && t.checkExpect(bottomWord.render(scene), expectedScene3);
  }
}
