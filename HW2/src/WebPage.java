import tester.Tester;

// represents a list of items
interface IItem {
  
  // counts the total image size
  int totalImageSize();
  
  // counts the total amount of characters
  int textLength();
  
  // concatenates file names of images as one string 
  String images();
}

// a class representing text
class Text implements IItem {
  String contents;
  
  Text(String contents) {
    this.contents = contents;
  }
  
  /* TEMPLATE
  FIELDS:
  ... this.contents ...       -- String
  METHODS
  ... this.totalImageSize() ...    -- int
  ... this.textLength() ...        -- int
  ... this.images() ...            -- String
*/

  // counts the total image size
  public int totalImageSize() {
    return 0;
  }

  // counts the total amount of characters
  public int textLength() {
    return this.contents.length();
  }

  // concatenates file names of images as one string 
  public String images() {
    return "";
  }
}

// a class representing an image
class Image implements IItem {
  String fileName;
  int size;
  String fileType;
  
  Image(String fileName, int size, String fileType) {
    this.fileName = fileName;
    this.size = size;
    this.fileType = fileType;
  }

  /* TEMPLATE
  FIELDS:
  ... this.fileName ...            -- String
  ... this.size ...                -- int
  ... this.fileType ...            -- String
  METHODS
  ... this.totalImageSize() ...    -- int
  ... this.textLength() ...        -- int
  ... this.images() ...            -- String
*/
  
  // counts the total image size
  public int totalImageSize() {
    return this.size;
  }

  // counts the total amount of characters
  public int textLength() {
    return this.fileName.length() + this.fileType.length();
  }

  // concatenates file names of images as one string 
  public String images() {
    return this.fileName + "." + this.fileType;
  }
}

// a class representing a link
class Link implements IItem {
  String name;
  WebPage page;
  
  Link(String name, WebPage page) {
    this.name = name;
    this.page = page;
  }
  
  /* TEMPLATE
  FIELDS:
  ... this.name ...                -- String
  ... this.page ...                -- WebPage
  METHODS
  ... this.totalImageSize() ...    -- int
  ... this.textLength() ...        -- int
  ... this.images() ...            -- String
*/

  // counts the total image size
  public int totalImageSize() {
    return this.page.totalImageSize();
  }

  // counts the total amount of characters
  public int textLength() {
    return this.name.length() + this.page.textLength();
  }

  // concatenates file names of images as one string 
  public String images() {
    return this.page.images();
  }
}

// represents a ListOf Items
interface ILoItem {
  
  // counts the total image size
  int totalImageSize();
  
  // counts the total amount of characters
  int textLength();
  
  // concatenates file names of images as one string 
  String images();
}

// a class representing a WebPage
class WebPage implements ILoItem {
  String title;
  String url;
  ILoItem items;
  
  WebPage(String title, String url, ILoItem items) {
    this.title = title;
    this.url = url;
    this.items = items;
  }
  
  /* TEMPLATE
  FIELDS:
  ... this.title ...               -- String
  ... this.url ...                 -- String
  ... this.items ...               -- ILoItem
  METHODS
  ... this.totalImageSize() ...    -- int
  ... this.textLength() ...        -- int
  ... this.images() ...            -- String
*/

  // counts the total image size
  public int totalImageSize() {
    return this.items.totalImageSize();
  }

  // counts the total amount of characters
  public int textLength() {
    return this.title.length() + this.items.textLength();
  }

  // concatenates file names of images as one string 
  public String images() {
    return this.items.images();
  }
}

// represents an empty list of items
class MtLoItem implements ILoItem {
  MtLoItem() { }
  
  /* TEMPLATE
  FIELDS:
  ... N/A ...
  METHODS
  ... this.totalImageSize() ...    -- int
  ... this.textLength() ...        -- int
  ... this.images() ...            -- String
*/

  // counts the total image size
  public int totalImageSize() {
    return 0;
  }

  // counts the total amount of characters
  public int textLength() {
    return 0;
  }

  // concatenates file names of images as one string 
  public String images() {
    return "";
  }
  
}

// represents a non-empty list of items
class ConsLoItem implements ILoItem {
  IItem first;
  ILoItem rest;
  
  ConsLoItem(IItem first, ILoItem rest) {
    this.first = first;
    this.rest = rest;
  }
  
  /* TEMPLATE
  FIELDS:
  ... this.first ...               -- IItem
  ... this.rest ...                -- ILoItem
  METHODS:
  ... this.totalImageSize() ...    -- int
  ... this.textLength() ...        -- int
  ... this.images() ...            -- String
  METHODS OF FIELDS:
  ... this.first.totalImageSize() ...   -- int
  ... this.first.textLength() ...       -- int
  ... this.first.images() ...           -- String
  ... this.rest.totalImageSize ...      -- int
  ... this.rest.textLength() ...        -- int
  ... this.rest.images() ...            -- String
*/

  // counts the total image size
  public int totalImageSize() {
    return this.first.totalImageSize() + this.rest.totalImageSize();
  }

  // counts the total amount of characters
  public int textLength() {
    return this.first.textLength() + this.rest.textLength();
  }

  // concatenates file names of images as one string 
  public String images() {
    if (this.first.images().isEmpty()) {
      return this.rest.images();
    }
    if (this.rest.images().isEmpty()) {
      return this.first.images();
    }
    return this.first.images() + ", " + this.rest.images();
  }
}

// Examples
class ExamplesWebPage {
  // Example 1 (Fundies II)
  IItem text1 = new Text("Home sweet home");
  IItem image1 = new Image("wvh-lab", 400, "png");
  IItem text2 = new Text("The staff");
  IItem image2 = new Image("profs", 240, "jpeg");
  
  ILoItem emptyList = new MtLoItem();
 
  // Example 2 (HtDP)
  IItem text3 = new Text("How to Design Programs");
  IItem image3 = new Image("htdp", 4300, "tiff");
  
  ILoItem htdpItems = new ConsLoItem(text3, new ConsLoItem(image3, emptyList));
  
  WebPage HtDP = new WebPage("HtDP", "htdp.org", htdpItems);
  
  //Example 3 (OOD)
  IItem text4 = new Text("Stay classy, Java");
  IItem link3 = new Link("Back to the Future", HtDP);
  
  ILoItem oodItems = new ConsLoItem(text4, new ConsLoItem(link3, emptyList));
  
  WebPage OOD = new WebPage("OOD", "ccs.neu.edu/OOD", oodItems);
  
  // Example 1 Link
  IItem link1 = new Link("A Look Back", HtDP);
  IItem link2 = new Link("A Look Ahead", OOD);
  
  // Example 1
  ILoItem fundiesItems = new ConsLoItem((text1), 
      new ConsLoItem(image1, new ConsLoItem(text2, 
          new ConsLoItem(image2, new ConsLoItem(link1, new ConsLoItem(link2, emptyList))))));
  
  WebPage fundiesWP = new WebPage("Fundies II", "ccs.neu.edu/Fundies2", fundiesItems);
  
  WebPage emWP = new WebPage("", "", emptyList);
  
  
  // tests for totalImageSize
  boolean testerTotalImageSize(Tester t) {
    // test is adding the size of the images present for all images including links
    return t.checkExpect(fundiesWP.totalImageSize(), 9240)
        // test is adding the size of all images on HtDP including links
        && t.checkExpect(HtDP.totalImageSize(), 4300)
        // test is adding the size of all images on OOD including links
        && t.checkExpect(OOD.totalImageSize(), 4300)
        // tests is for an empty case
        && t.checkExpect(emWP.totalImageSize(), 0);
        
  }
  
  // tests for textLength
  boolean testerTextLength(Tester t) {
    // test is adding all the characters on Fundies II and its links
    return t.checkExpect(fundiesWP.textLength(), 182)
        // test is adding all the characters on HtDP and its links
        && t.checkExpect(HtDP.textLength(), 34)
        // test is adding all the characters on OOD and its links
        && t.checkExpect(OOD.textLength(), 72)
        // test is for an empty case
        && t.checkExpect(emWP.textLength(), 0);
  }
  
  // test is producing a string representing all image names and types
  boolean testerImages(Tester t) {
    // test is adding all image names from Fundies II and its links
    return t.checkExpect(fundiesWP.images(), "wvh-lab.png, profs.jpeg, htdp.tiff, htdp.tiff")
        // test is adding all image names from HtDP and its links
        && t.checkExpect(HtDP.images(), "htdp.tiff")
        // test is adding all image names from OOD and its links
        && t.checkExpect(OOD.images(), "htdp.tiff")
        // test is for an empty case
        && t.checkExpect(emWP.images(), "");
  }
}

// htdp.tiff appears twice because there are links that reference other web pages which would
// then reference the image again. This same duplication idea occurs for the other methods 
// as well because totalImageSize and textLength are also doing the same thing where they 
// reference other links which in turn leads to another web page

