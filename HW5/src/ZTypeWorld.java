import tester.*;

import java.awt.Color;
import javalib.worldimages.*;

import javalib.funworld.*;

// class represents ZTypeWorld, game handler
class ZTypeWorld extends World {

  ILoWord words;
  Utils utils;
  int tickCounter;
  String typedInput;

  ZTypeWorld(ILoWord words, int tickCounter, String typedInput) {
    this.words = words;
    this.utils = new Utils();
    this.tickCounter = tickCounter;
    this.typedInput = typedInput;
  }

  /* CLASS TEMPLATE
  FIELDS:
  ... this.words ...        -- ILoWord
  ... this.utils ...        -- Utils
  ... this.tickCounter ...  -- int
  ... this.typedInput ...   -- String
  METHODS:
  ... this.makeScene() ...    -- WorldScene
  ... this.onTick() ...       -- World
  ... this.onKeyEvent() ...   -- World
  METHODS OF FIELDS:
  ... this.words.makeScene() ...  -- WorldScene
  ... this.words.onTick() ...     -- World
  ... this.words.onKeyEvent() ... -- World
  ... this.typedInput.makeScene() ...  -- WorldScene
  ... this.typedInput.onTick() ...     -- World
  ... this.typedInput.onKeyEvent() ... -- World
   */

  // renders the scene of the game
  public WorldScene makeScene() {
    WorldScene scene = new WorldScene(Constants.WIDTH, Constants.HEIGHT);
    WorldImage background = new RectangleImage(Constants.WIDTH, Constants.HEIGHT, 
        OutlineMode.SOLID, Color.gray);
    scene = scene.placeImageXY(background, Constants.WIDTH / 2, Constants.HEIGHT / 2);

    WorldImage currentTyped = new TextImage(
        "Typed: " + this.typedInput, 20, FontStyle.REGULAR, Color.CYAN);
    scene = scene.placeImageXY(currentTyped, Constants.WIDTH / 2, Constants.HEIGHT - 30);

    return this.words.render(scene);
  }


  // updates the game for every 'frame'/tick
  public World onTick() {
    int newTickCounter = this.tickCounter + 1;
    ILoWord newWords = this.words.move();

    if (newTickCounter % 50 == 0) {
      String newWord = utils.randomWord();
      IWord newWordObj = new Word(newWord, Constants.WIDTH / 2, 0);
      newWords = new ConsLoWord(newWordObj, newWords);
    }

    if (newWords.hasReachedBottom()) {
      return this.endOfWorld("Game Over :(");
    }

    return new ZTypeWorld(newWords, newTickCounter, this.typedInput);
  }

  // tracks what letters the user types
  public World onKeyEvent(String key) {
    String newTypedInput = this.typedInput;
    if (key.equals("backspace") && !newTypedInput.isEmpty()) {
      newTypedInput = newTypedInput.substring(0, newTypedInput.length() - 1);
    } 

    else if (key.length() == 1 && ("abcdefghijklmnopqrstuvwxyz".contains(key))) { 
      newTypedInput += key;
    }

    ILoWord newWords = this.words.removeTyped(newTypedInput);
    if (this.words.isWordFullyTyped(newTypedInput)) {
      newTypedInput = "";
    }
    return new ZTypeWorld(newWords, this.tickCounter, newTypedInput);
  }


  // the game over screen when word reaches the bottom
  public WorldScene lastScene(String msg) {
    WorldScene scene = new WorldScene(Constants.WIDTH, Constants.HEIGHT);
    WorldImage background = new RectangleImage(
        Constants.WIDTH, Constants.HEIGHT, OutlineMode.SOLID, Color.GRAY);
    WorldImage text = new TextImage(msg, 50, FontStyle.BOLD, Color.RED);

    scene = scene.placeImageXY(background, Constants.WIDTH / 2, Constants.HEIGHT / 2);
    scene = scene.placeImageXY(text, Constants.WIDTH / 2, Constants.HEIGHT / 2);

    return scene;
  }
}

// Examples class
class ExamplesZType {

  // starts the game
  boolean testStartGame(Tester t) {
    ZTypeWorld game = new ZTypeWorld(new MtLoWord(), 0, "");
    game.bigBang(Constants.WIDTH, Constants.HEIGHT, 0.04);

    return true;
  }

  Word word1 = new Word("testing", 100, 50);
  Word word2 = new Word("project", 200, 300);
  Word bottomWord = new Word("bottom", 150, Constants.HEIGHT);

  ILoWord mt = new MtLoWord();
  ILoWord list1 = new ConsLoWord(word1, mt);
  ILoWord list2 = new ConsLoWord(word1, new ConsLoWord(word2, mt));
  ILoWord listWithBottom = new ConsLoWord(bottomWord, new ConsLoWord(word1, mt));

  ZTypeWorld game = new ZTypeWorld(mt, 0, "");
  ZTypeWorld game2 = new ZTypeWorld(list1, 0, "");
  ZTypeWorld game3 = new ZTypeWorld(listWithBottom, 0, "");

  // tests for makeScene method
  boolean testMakeScene(Tester t) {

    WorldScene defaultScene = new WorldScene(Constants.WIDTH, Constants.HEIGHT);
    WorldImage background = new RectangleImage(
        Constants.WIDTH, Constants.HEIGHT, OutlineMode.SOLID, Color.GRAY);
    defaultScene = defaultScene.placeImageXY(background, Constants.WIDTH / 2, Constants.HEIGHT / 2);

    WorldImage currentTyped = new TextImage("Typed: ", 20, FontStyle.REGULAR, Color.CYAN);
    defaultScene = defaultScene.placeImageXY(
        currentTyped, Constants.WIDTH / 2, Constants.HEIGHT - 30);

    defaultScene = defaultScene.placeImageXY(
        new TextImage("testing", 20, FontStyle.REGULAR, Color.BLACK), 100, 50);

    return t.checkExpect(game2.makeScene(), defaultScene);
  }

  // tests for onTick method
  boolean testOnTick(Tester t) {
    ZTypeWorld movedGame = new ZTypeWorld(list1.move(), 1, "");

    return t.checkExpect(game2.onTick(), movedGame); 
  }

  // tests for onKey method
  boolean testOnKeyEvent(Tester t) {
    return t.checkExpect(game2.onKeyEvent("h"), new ZTypeWorld(list1, 0, "h")) 
        && t.checkExpect(game2.onKeyEvent("testing"), new ZTypeWorld(list1, 0, ""))
        && t.checkExpect(game2.onKeyEvent("backspace"), new ZTypeWorld(list1, 0, ""));
  }
}
