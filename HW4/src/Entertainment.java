import tester.*;

// to represent a type of entertainment
interface IEntertainment {
  
  //compute the total price of this Entertainment
  double totalPrice();

  //computes the minutes of entertainment of this IEntertainment
  int duration();

  //produce a String that shows the name and price of this IEntertainment
  String format();

  //is this IEntertainment the same as that one?
  boolean sameEntertainment(IEntertainment that);

  // is this IEntertainment the same as the given Magazine?
  boolean sameEntertainmentMagazine(Magazine m);
  
  // is this IEntertainment the same as the given TVSeries?
  boolean sameEntertainmentTVSeries(TVSeries t);
  
  // is this IEntertainment the same as the given Podcast?
  boolean sameEntertainmentPodcast(Podcast p);
}

// represents an abstract entertainment type
abstract class AEntertainment implements IEntertainment {
  String name;
  double price;
  int installments;

  AEntertainment(String name, double price, int installments) {
    this.name = name;
    this.price = price;
    this.installments = installments;
  }

  /*  TEMPLATE 
  FIELDS:
  ... this.name ...           -- String
  ... this.price ...          -- double
  ... this.installments ...   -- int
  METHODS:
  ... this.totalPrice() ...         -- double
  ... this.duration() ...           -- int
  ... this.sameEntertainment() ...  -- boolean
  ... this.format() ...             -- String
  ... this.sameEntertainmentMagazine(Magazine m)  -- boolean
  ... this.sameEntertainmentTVSeries(TVSeries t)  -- boolean
  ... this.sameEntertainmentPodcast(Podcast p)    -- boolean
   */

  // computes the price of a yearly subscription to an AEntertainment
  public double totalPrice() {
    return this.price * this.installments;    
  }

  // computes the minutes of entertainment of this AEntertainment, (includes all installments)
  public int duration() {
    return this.installments * 50;
  }

  // produce a String that shows the name and price of this Magazine
  public String format() {
    return this.name + ", " + this.price + ".";
  }

  // is this AEntertainment the same as that IEntertainment?
  public boolean sameEntertainment(IEntertainment that) {
    return false;

    /* Method Template:
     * everything in the class template, plus:
     * FIELDS OF THAT:
     * ... that.name ...    -- String
     * ... that.price ...   -- double
     * ... that.installments ...  -- int
     * ... that.genre ...       -- String
     * ... that.pages ...       -- int
     * ... that.corporation ... -- String
     * METHODS:
     * ... this.sameEntertainment(IEntertainment that) ...  -- boolean
     * METHODS ON FIELDS FOR THAT:
     * ... that.price.totalPrice() ...        -- double
     * ... that.installments.duration() ...   -- int
     * ... that.pages.duration() ...          -- int
     */
  }

  // is this IEntertainment the same as the given Magazine?
  public boolean sameEntertainmentMagazine(Magazine m) {
    return false;

    /* Method Template:
     * everything in the class template, plus:
     * FIELDS OF THAT:
     * ... that.name ...    -- String
     * ... that.price ...   -- double
     * ... that.installments ...  -- int
     * ... that.genre ...       -- String
     * ... that.pages ...       -- int
     * ... that.corporation ... -- String
     * METHODS:
     * ... this.sameEntertainmentMagazine(Magazine m) ...  -- boolean
     * METHODS ON FIELDS:
     * ... that.price.totalPrice() ...        -- double
     * ... that.installments.duration() ...   -- int
     * ... that.pages.duration() ...          -- int
     */
  }

  // is this IEntertainment the same as the given TVSeries?
  public boolean sameEntertainmentTVSeries(TVSeries t) {
    return false;

    /* Method Template:
     * everything in the class template, plus:
     * FIELDS OF THAT:
     * ... that.name ...    -- String
     * ... that.price ...   -- double
     * ... that.installments ...  -- int
     * ... that.genre ...       -- String
     * ... that.pages ...       -- int
     * ... that.corporation ... -- String
     * METHODS:
     * ... this.sameEntertainmentTVSeries(TVSeries t) ...  -- boolean
     * METHODS ON FIELDS:
     * ... that.price.totalPrice() ...        -- double
     * ... that.installments.duration() ...   -- int
     * ... that.pages.duration() ...          -- int
     */
  }
  
  // is this IEntertainment the same as the given Podcast?
  public boolean sameEntertainmentPodcast(Podcast p) {
    return false;

    /* Method Template:
     * everything in the class template, plus:
     * FIELDS OF THAT:
     * ... that.name ...    -- String
     * ... that.price ...   -- double
     * ... that.installments ...  -- int
     * ... that.genre ...       -- String
     * ... that.pages ...       -- int
     * ... that.corporation ... -- String
     * METHODS:
     * ... this.sameEntertainmentPodcast(Podcast p) ...  -- boolean
     * METHODS ON FIELDS:
     * ... that.price.totalPrice() ...        -- double
     * ... that.installments.duration() ...   -- int
     * ... that.pages.duration() ...          -- int
     */
  }
}

// represents a magazine
class Magazine extends AEntertainment {
  String genre;
  int pages;

  Magazine(String name, double price, String genre, int pages, int installments) {
    super(name, price, installments);
    this.genre = genre;
    this.pages = pages;
  }

  /*  TEMPLATE 
    FIELDS:
    ... this.name ...           -- String
    ... this.price ...          -- double
    ... this.installments ...   -- int
    ... this.genre ...          -- String
    ... this.pages ...          -- int
    METHODS:
    ... this.totalPrice() ...         -- double
    ... this.duration() ...           -- int
    ... this.sameEntertainment() ...  -- boolean
    ... this.format() ...             -- String
    ... this.sameEntertainmentMagazine(Magazine m) ...  -- boolean
    METHODS ON FIELDS:
    ... this.pages.duration() ...   -- int
   */

  // computes the minutes of entertainment
  public int duration() {
    return this.pages * 5 * this.installments;
  }

  // is this IEntertainment the same as that one?
  public boolean sameEntertainment(IEntertainment that) {
    return that.sameEntertainmentMagazine(this);
    
    /* Method Template:
     * everything in the class template, plus:
     * FIELDS OF THAT:
     * ... that.name ...    -- String
     * ... that.price ...   -- double
     * ... that.installments ...  -- int
     * ... that.genre ...       -- String
     * ... that.pages ...       -- int
     * ... that.corporation ... -- String
     * METHODS:
     * ... this.sameEntertainment(IEntertainment that) ...  -- boolean
     * METHODS ON FIELDS:
     * ... that.price.totalPrice() ...        -- double
     * ... that.installments.duration() ...   -- int
     * ... that.pages.duration() ...          -- int
     */
  }

  // is this IEntertainment the same as the given Magazine?
  public boolean sameEntertainmentMagazine(Magazine m) {
    return this.name.equals(m.name)
        && Math.abs(this.price - m.price) < 0.001
        && this.installments == m.installments
        && this.pages == m.pages
        && this.genre.equals(m.genre);
    
    /* Method Template:
     * everything in the class template, plus:
     * FIELDS OF THAT:
     * ... that.name ...    -- String
     * ... that.price ...   -- double
     * ... that.installments ...  -- int
     * ... that.genre ...       -- String
     * ... that.pages ...       -- int
     * ... that.corporation ... -- String
     * METHODS:
     * ... this.sameEntertainmentMagazine(Magazine m) ...  -- boolean
     * METHODS ON FIELDS:
     * ... that.price.totalPrice() ...        -- double
     * ... that.installments.duration() ...   -- int
     * ... that.pages.duration() ...          -- int
     */
  }
}

// represents a TV Series
class TVSeries extends AEntertainment {
  String corporation;

  TVSeries(String name, double price, int installments, String corporation) {
    super(name, price, installments);
    this.corporation = corporation;
  }

  /*  TEMPLATE 
    FIELDS:
    ... this.name ...           -- String
    ... this.price ...          -- double
    ... this.installments ...   -- int
    ... this.corporation ...    -- String
    METHODS:
    ... this.totalPrice() ...         -- double
    ... this.duration() ...           -- int
    ... this.sameEntertainment() ...  -- boolean
    ... this.format() ...             -- String
    ... this.sameEntertainmentTVSeries(TVSeries t)  -- boolean
   */

  // is this IEntertainment the same as that one?
  public boolean sameEntertainment(IEntertainment that) {
    return that.sameEntertainmentTVSeries(this);
    
    /* Method Template:
     * everything in the class template, plus:
     * FIELDS OF THAT:
     * ... that.name ...    -- String
     * ... that.price ...   -- double
     * ... that.installments ...  -- int
     * ... that.genre ...       -- String
     * ... that.pages ...       -- int
     * ... that.corporation ... -- String
     * METHODS:
     * ... this.sameEntertainment(IEntertainment that) ...  -- boolean
     * METHODS ON FIELDS:
     * ... that.price.totalPrice() ...        -- double
     * ... that.installments.duration() ...   -- int
     * ... that.pages.duration() ...          -- int
     */
  }


  // is this IEntertainment the same as the given TVSeries?
  public boolean sameEntertainmentTVSeries(TVSeries t) {
    return this.name.equals(t.name)
        && Math.abs(this.price - t.price) < 0.001
        && this.installments == t.installments
        && this.corporation.equals(t.corporation);
    
    /* Method Template:
     * everything in the class template, plus:
     * FIELDS OF THAT:
     * ... that.name ...    -- String
     * ... that.price ...   -- double
     * ... that.installments ...  -- int
     * ... that.genre ...       -- String
     * ... that.pages ...       -- int
     * ... that.corporation ... -- String
     * METHODS:
     * ... this.sameEntertainmentTVSeries(TVSeries t) ...  -- boolean
     * METHODS ON FIELDS:
     * ... that.price.totalPrice() ...        -- double
     * ... that.installments.duration() ...   -- int
     * ... that.pages.duration() ...          -- int
     */
  }
}

// represents a podcast
class Podcast extends AEntertainment {

  Podcast(String name, double price, int installments) {
    super(name, price, installments);
  }

  /*  TEMPLATE 
    FIELDS:
    ... this.name ...           -- String
    ... this.price ...          -- double
    ... this.installments ...   -- int
    METHODS:
    ... this.totalPrice() ...         -- double
    ... this.duration() ...           -- int
    ... this.sameEntertainment() ...  -- boolean
    ... this.format() ...             -- String
    ... this.sameEntertainmentPodcast(Podcast p)  -- boolean
   */

  // is this IEntertainment the same as that one?
  public boolean sameEntertainment(IEntertainment that) {
    return that.sameEntertainmentPodcast(this);
    
    /* Method Template:
     * everything in the class template, plus:
     * FIELDS OF THAT:
     * ... that.name ...    -- String
     * ... that.price ...   -- double
     * ... that.installments ...  -- int
     * ... that.genre ...       -- String
     * ... that.pages ...       -- int
     * ... that.corporation ... -- String
     * METHODS:
     * ... this.sameEntertainment(IEntertainment that) ...  -- boolean
     * METHODS ON FIELDS:
     * ... that.price.totalPrice() ...        -- double
     * ... that.installments.duration() ...   -- int
     * ... that.pages.duration() ...          -- int
     */
  }

  // is this IEntertainment the same as the given Podcast?
  public boolean sameEntertainmentPodcast(Podcast p) {
    return this.name.equals(p.name)
        && Math.abs(this.price - p.price) < 0.001
        && this.installments == p.installments;
    
    /* Method Template:
     * everything in the class template, plus:
     * FIELDS OF THAT:
     * ... that.name ...    -- String
     * ... that.price ...   -- double
     * ... that.installments ...  -- int
     * ... that.genre ...       -- String
     * ... that.pages ...       -- int
     * ... that.corporation ... -- String
     * METHODS:
     * ... this.sameEntertainmentPodcast(Podcast p) ...  -- boolean
     * METHODS ON FIELDS:
     * ... that.price.totalPrice() ...        -- double
     * ... that.installments.duration() ...   -- int
     * ... that.pages.duration() ...          -- int
     */
  }
}

// examples class
class ExamplesEntertainment {
  IEntertainment rollingStone = new Magazine("Rolling Stone", 2.55, "Music", 60, 12);
  IEntertainment houseOfCards = new TVSeries("House of Cards", 5.25, 13, "Netflix");
  IEntertainment serial = new Podcast("Serial", 0.0, 8);

  //testing total price method
  boolean testTotalPrice(Tester t) {
    return t.checkInexact(this.rollingStone.totalPrice(), 2.55 * 12, .0001) 
        && t.checkInexact(this.houseOfCards.totalPrice(), 5.25 * 13, .0001)
        && t.checkInexact(this.serial.totalPrice(), 0.0, .0001);
  } 
  
  // tests for duration
  boolean testDuration(Tester t ) {
    // tests the duration of a magazine
    return t.checkExpect(rollingStone.duration(), 3600)
        // tests the duration of a TVSeries
        && t.checkExpect(houseOfCards.duration(), 650)
        // tests the duration of a Podcast
        && t.checkExpect(serial.duration(), 400);
  }
  
  // tests for format
  boolean testFormat(Tester t) {
    // tests the format of a magazine
    return t.checkExpect(rollingStone.format(), "Rolling Stone, 2.55.")
        // tests the format of a TVSeries
        && t.checkExpect(houseOfCards.format(), "House of Cards, 5.25.")
        // tests the format of a Podcast
        && t.checkExpect(serial.format(), "Serial, 0.0.");
  }
  
  // tests for sameEntertainment
  boolean testEntertainment(Tester t) {
    IEntertainment rollingStone2 = new Magazine("Rolling Stone", 2.55, "Music", 60, 12);
    IEntertainment fakeRollingStone = new Magazine("Rolling", 2.55, "Music", 60, 12);
    IEntertainment houseOfCards2 = new TVSeries("House of Cards", 5.25, 13, "Netflix");
    IEntertainment fakeHouseOfCards = new TVSeries("House of Cards", 5.25, 14, "Netflix");
    IEntertainment serial2 = new Podcast("Serial", 0.0, 8);
    IEntertainment fakeSerial = new Podcast("Serial", 0.1, 8);
    
    // compares two different magazine objects with same information
    return t.checkExpect(this.rollingStone.sameEntertainment(rollingStone2), true)
        // compares two different magazine objects but different information
        && t.checkExpect(this.rollingStone.sameEntertainment(fakeRollingStone), false)
        // compares two different TVSeries objects with same information
        && t.checkExpect(this.houseOfCards.sameEntertainment(houseOfCards2), true)
        // compares two different TVSeries objects but different information
        && t.checkExpect(this.houseOfCards.sameEntertainment(fakeHouseOfCards), false)
        // compares two different Podcast objects with same information
        && t.checkExpect(this.serial.sameEntertainment(serial2), true)
        // compares two different Podcast objects but different information
        && t.checkExpect(this.serial.sameEntertainment(fakeSerial), false);
  }
}