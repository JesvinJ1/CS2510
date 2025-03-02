import tester.Tester;

// class representing a perfect bagel recipe
class BagelRecipe {

  double flour;
  double water;
  double yeast;
  double salt;
  double malt;

  /* CLASS TEMPLATE:
   FIELDS:
   * this.flour ...   -- double
   * this.water ...   -- double
   * this.yeast ...   -- double
   * this.salt ...    -- double
   * this.malt ...    -- double
   METHODS:
   * this.sameRecipe(BagelRecipe other) ...   -- boolean
   METHODS OF FIELDS:
   * N/A
   */

  BagelRecipe(double flour, double water, double yeast, double salt, double malt) {

    // if condition for constructor if the flour does not equal the water
    if (flour != water) {
      throw new IllegalArgumentException(
          "the weight of the flour should be equal to the weight of the water");
    }
    // if condition for constructor if the yeast does not equal the malt
    if (yeast != malt) {
      throw new IllegalArgumentException(
          "the weight of the yeast should be equal the weight of the malt");
    }
    // if condition for constructor if the salt + yeast does not equal 1/20th the weight of flour
    if (salt + yeast != flour / 20) {
      throw new IllegalArgumentException(
          "the weight of the salt + yeast should be 1/20th the weight of the flour");
    }

    this.flour = flour;
    this.water = water;
    this.yeast = yeast;
    this.salt = salt;
    this.malt = malt;

  }

  // constructor that only needs the weights of flour and yeast to create a perfect recipe
  BagelRecipe(double flour, double yeast) {
    this(flour, flour, yeast, (flour / 20) - yeast, yeast);
  }

  // constructor that takes flour, yeast, and salt as volumes to create a perfect recipe
  BagelRecipe(double flourC, double yeastT, double saltT) {
    double flourWeight = flourC * 4.25;
    double yeastWeight = (yeastT / 48) * 5;
    double saltWeight = (saltT / 48) * 10;
    double saltYeast = flourWeight / 20;
    double maltWeight = yeastWeight;

    // condition for the salt + yeast to be 1/20th the weight of flour
    if (Math.abs((saltWeight + yeastWeight) - saltYeast) > 0.001) {
      throw new IllegalArgumentException(
          "weight of the salt + yeast should be 1/20th the weight of the flour");
    }

    this.flour = flourWeight;
    this.water = flourWeight;
    this.yeast = yeastWeight;
    this.salt = saltWeight;
    this.malt = maltWeight;

  }

  // returns whether the same ingredients have the same weights within 0.001 ounces
  boolean sameRecipe(BagelRecipe other) {
    // uses absolute value to see if the different is less than 0.001
    return Math.abs(this.flour - other.flour) < 0.001
        && Math.abs(this.water - other.water) < 0.001
        && Math.abs(this.yeast - other.yeast) < 0.001
        && Math.abs(this.salt - other.salt) < 0.001
        && Math.abs(this.malt - other.malt) < 0.001;

    /* MEHTOD TEMPLATE:
     * everything in the class template, plus:
     fields of other:
     * other.flour ...    -- double
     * other.water ...    -- double
     * other.yeast ...    -- double
     * other.salt ...     -- double
     * other.malt ...     -- double
     Methods:
     * other.sameRecipe(BagelRecipe) ...    -- boolean
     Methods for fields of other:
     * N/A
     */
  }
}

class ExamplesRecipes {
  // checks for sameRecipe method
  boolean testSameRecipe(Tester t) {
    BagelRecipe recipe1 = new BagelRecipe(20.0, 20.0, 0.3, 0.7, 0.3);
    BagelRecipe recipe2 = new BagelRecipe(20.0, 0.3);
    BagelRecipe recipe3 = new BagelRecipe(20.0, 20.0, 0.302, 0.698, 0.302);

    // checks if the two recipes are the same, true
    return t.checkExpect(recipe1.sameRecipe(recipe2), true)
        // checks the two recipes which aren't the same, false
        && t.checkExpect(recipe2.sameRecipe(recipe3), false);
  }

  // tests constructor exception for flour and water
  boolean testFlourWaterException(Tester t) {
    return t.checkConstructorException(
        new IllegalArgumentException(
            "the weight of the flour should be equal to the weight of the water"),
        "BagelRecipe", 20.0, 19.0, 0.3, 0.7, 0.3);
  }

  // tests constructor exception for yeast and malt
  boolean testYeastMaltException(Tester t) {
    return t.checkConstructorException(
        new IllegalArgumentException(
            "the weight of the yeast should be equal the weight of the malt"),
        "BagelRecipe", 20.0, 20.0, 0.3, 0.7, 0.4);
  }

  // tests constructor exception for salt and yeast
  boolean testSaltYeastException(Tester t) {
    return t.checkConstructorException(
        new IllegalArgumentException(
            "the weight of the salt + yeast should be 1/20th the weight of the flour"),
        "BagelRecipe", 20.0, 20.0, 0.4, 0.8, 0.4);
  }
  
  // tests constructor exception for the volume constructor
  boolean testVolumeException(Tester t) {
    return t.checkConstructorException(
        new IllegalArgumentException(
            "weight of the salt + yeast should be 1/20th the weight of the flour"),
        "BagelRecipe", 5.0, 0.0, 3.0);
  }
}