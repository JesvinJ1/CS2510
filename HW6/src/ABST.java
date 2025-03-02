import java.util.Comparator;

import tester.*;

// represents a BST
abstract class ABST<T> {

  Comparator<T> order;

  ABST(Comparator<T> order) {
    this.order = order;
  }

  // produces a new BST with the given item inserted
  abstract ABST<T> insert(T item);

  // checks if item is present in BST
  abstract boolean present(T item);

  // helps to recursively find the left most item
  abstract T getLeftMostHelper(T data);

  // returns the leftmost item contained in a BST
  abstract T getLeftmost();

  // helps to recurse through the list
  abstract ABST<T> getRightHelper(T small);

  // returns BST containing all but the leftmost item
  abstract ABST<T> getRight();

  // helper compares two nodes to see if they are equal
  abstract boolean sameNode(Node<T> node);

  // checks if BST is the same as an empty one, a leaf
  abstract boolean sameLeaf(ABST<T> tree);

  // returns if BST is the same as the given BST
  abstract boolean sameTree(ABST<T> tree);

  // helper compares if two nodes contain the same data
  abstract boolean sameDataHelper(ABST<T> tree);

  // checks if BST contains the same data in the same order as the given BST
  abstract boolean sameData(ABST<T> tree);

  // represents a BST by producing a list of items in the BST, sorted
  abstract IList<T> buildList();

}

// represents a leaf in a BST
class Leaf<T> extends ABST<T> {

  Leaf(Comparator<T> order) {
    super(order);
  }

  // produces a new BST with the given item inserted
  ABST<T> insert(T item) {
    return new Node<T>(this.order, item, new Leaf<T>(this.order), new Leaf<T>(this.order));
  }

  // checks if item is present in a BST leaf
  boolean present(T item) {
    return false;
  }

  // helps to recursively find the left most item
  T getLeftMostHelper(T data) {
    return data;
  }

  // returns the leftmost item contained in a BST
  T getLeftmost() {
    throw new RuntimeException("No leftmost item of an empty tree");
  }

  // helps to recurse through the list
  ABST<T> getRightHelper(T small) {
    return this;
  }

  // returns BST containing all but the leftmost item
  ABST<T> getRight() {
    throw new RuntimeException("No right of an empty tree");
  }

  // helper compares two nodes to see if they are equal
  public boolean sameNode(Node<T> node) {
    return false;
  }

  // checks if BST is the same as an empty one, a leaf
  boolean sameLeaf(ABST<T> tree) {
    return true;
  }

  // returns if BST is the same as the given BST
  boolean sameTree(ABST<T> tree) {
    return tree.sameLeaf(this);
  }

  // helper compares if two nodes contain the same data
  boolean sameDataHelper(ABST<T> tree) {
    return true;
  }

  // checks if BST contains the same data in the same order as the given BST
  boolean sameData(ABST<T> tree) {
    return tree.sameDataHelper(this);
  }

  // represents a BST by producing a list of items in the BST, sorted
  IList<T> buildList() {
    return new MtList<T>();
  }

}

// represents a node in a BST
class Node<T> extends ABST<T> {

  T data;
  ABST<T> left;
  ABST<T> right;

  Node(Comparator<T> order, T data, ABST<T> left, ABST<T> right) {
    super(order);
    this.data = data;
    this.left = left;
    this.right = right;
  }

  // produces a new BST with the given item inserted
  ABST<T> insert(T item) {
    if (this.order.compare(item, this.data) < 0) {
      return new Node<T>(this.order, this.data, this.left.insert(item), this.right);
    }
    else {
      return new Node<T>(this.order, this.data, this.left, this.right.insert(item));
    }
  }

  // checks if item is present in BST
  boolean present(T item) {
    return this.order.compare(this.data, item) == 0
        || this.left.present(item)
        || this.right.present(item);
  }

  // helps to recursively find the left most item
  T getLeftMostHelper(T data) {
    return this.left.getLeftMostHelper(this.data);
  }

  // returns the leftmost item contained in a BST
  T getLeftmost() {
    return this.getLeftMostHelper(this.data);
  }

  // helps to recurse through the list
  ABST<T> getRightHelper(T small) {
    if (order.compare(this.data, small) == 0) {
      return this.right;
    }
    else {
      return new Node<T>(this.order, this.data, 
          this.left.getRightHelper(small), this.right.getRightHelper(small));
    }
  }

  // returns BST containing all but the leftmost item
  ABST<T> getRight() {
    return this.getRightHelper(this.getLeftmost());
  }

  // helper compares two nodes to see if they are equal
  boolean sameNode(Node<T> node) {
    return this.order.compare(this.data, node.data) == 0
        && this.left.sameTree(node.left)
        && this.right.sameTree(node.right);
  }

  // checks if BST is the same as an empty one, a leaf
  boolean sameLeaf(ABST<T> tree) {
    return false;
  }

  // returns if BST is the same as the given BST
  boolean sameTree(ABST<T> tree) {
    return tree.sameNode(this);
  }

  // helper compares if two nodes contain the same data
  boolean sameDataHelper(ABST<T> tree) {
    return tree.present(this.data)
        && this.left.sameDataHelper(tree)
        && this.right.sameDataHelper(tree);
  }

  // checks if BST contains the same data in the same order as the given BST
  boolean sameData(ABST<T> tree) {
    return tree.sameDataHelper(this) && this.sameDataHelper(tree);
  }

  // represents a BST by producing a list of items in the BST, sorted
  IList<T> buildList() {
    return new ConsList<T>(this.getLeftmost(), this.getRight().buildList());
  }

}

// represents a generic list
interface IList<T> {

}

// represents a generic empty list
class MtList<T> implements IList<T> {

  MtList() {}

}

// represents a generic non-empty list
class ConsList<T> implements IList<T> {
  T first;
  IList<T> rest;

  ConsList(T first, IList<T> rest) {
    this.first = first;
    this.rest = rest;
  }

}

// represents books
class Book {

  String title;
  String author;
  int price;

  Book(String title, String author, int price) {
    this.title = title;
    this.author = author;
    this.price = price;
  }

}

// book compared by title
class BooksByTitle implements Comparator<Book> {

  // compares book titles
  public int compare(Book book1, Book book2) {
    return book1.title.compareTo(book2.title);
  }

}

// book compared by author
class BooksByAuthor implements Comparator<Book> {

  // compares book authors
  public int compare(Book book1, Book book2) {
    return book1.author.compareTo(book2.author);
  }

}

// book compared by price
class BooksByPrice implements Comparator<Book> {

  // compares book prices
  public int compare(Book book1, Book book2) {
    return book1.price - book2.price;
  }
}

// examples class
class ExamplesBST {
  // examples of books
  Book book1 = new Book("Book 1", "Author 1", 10);
  Book book2 = new Book("Book 22", "Author 2", 17);
  Book book3 = new Book("Book 333", "Author 3", 20);
  Book book4 = new Book("Book 4444", "Author 4", 22);
  Book book5 = new Book("Book 55555", "Author 5", 21);
  Book book6 = new Book("Book 666666", "Author 6", 8);

  // comparator examples
  Comparator<Book> compareTitle = new BooksByTitle();
  Comparator<Book> compareAuthor = new BooksByAuthor();
  Comparator<Book> comparePrice = new BooksByPrice();

  // nodes using compareTitle
  ABST<Book> tLeaf = new Leaf<Book>(this.compareTitle);
  ABST<Book> t1Node = new Node<Book>(this.compareTitle, this.book2, this.tLeaf, this.tLeaf);
  ABST<Book> t2Node = new Node<Book>(this.compareTitle, this.book3, this.tLeaf, this.tLeaf);
  ABST<Book> tree1 = new Node<Book>(this.compareTitle, this.book1, this.t1Node, this.t2Node);

  // nodes using compareAuthor
  ABST<Book> aLeaf = new Leaf<Book>(this.compareAuthor);
  ABST<Book> a1Node = new Node<Book>(this.compareAuthor, this.book2, this.aLeaf, this.aLeaf);
  ABST<Book> a2Node = new Node<Book>(this.compareAuthor, this.book3, this.aLeaf, this.aLeaf);
  ABST<Book> tree2 = new Node<Book>(this.compareAuthor, this.book1, this.a1Node, this.a2Node);

  // nodes using comparePrice
  ABST<Book> pLeaf = new Leaf<Book>(this.comparePrice);
  ABST<Book> p1Node = new Node<Book>(this.comparePrice, this.book1, this.pLeaf, this.pLeaf);
  ABST<Book> p2Node = new Node<Book>(this.comparePrice, this.book2, this.pLeaf, this.pLeaf);
  ABST<Book> p3Node = new Node<Book>(this.comparePrice, this.book6, this.pLeaf, this.pLeaf);
  ABST<Book> p4Node = new Node<Book>(this.comparePrice, this.book1, this.p3Node, this.p2Node);
  ABST<Book> p5Node = new Node<Book>(this.comparePrice, this.book4, this.pLeaf, this.pLeaf);
  ABST<Book> p6Node = new Node<Book>(this.comparePrice, this.book5, this.pLeaf, this.p5Node);
  ABST<Book> p7Node = new Node<Book>(this.comparePrice, this.book3, this.pLeaf, this.pLeaf);
  ABST<Book> tree3 = new Node<Book>(this.comparePrice, this.book1, this.pLeaf, this.p2Node);
  ABST<Book> tree4 = new Node<Book>(this.comparePrice, this.book2, this.p1Node, this.pLeaf);
  ABST<Book> tree5 = new Node<Book>(this.comparePrice, this.book2, this.p1Node, this.p7Node);
  ABST<Book> tree6 = new Node<Book>(this.comparePrice, this.book3, this.p4Node, this.p6Node);

  // examples of lists
  IList<Book> emptyList = new MtList<Book>();
  IList<Book> tree1List = new ConsList<Book>(this.book1,
      new ConsList<Book>(this.book2,
          this.emptyList));
  IList<Book> tree2List = new ConsList<Book>(this.book6,
      new ConsList<Book>(this.book1,
          new ConsList<Book>(this.book2,
              new ConsList<Book>(this.book3,
                  new ConsList<Book>(this.book5,
                      new ConsList<Book>(this.book4,
                          this.emptyList))))));

  // tests for books
  boolean testCompare(Tester t) {
    return t.checkExpect(this.compareTitle.compare(this.book1, this.book2), -1)
        && t.checkExpect(this.compareTitle.compare(this.book1, this.book1), 0)
        && t.checkExpect(this.compareTitle.compare(this.book2, this.book4), -2)
        && t.checkExpect(this.compareAuthor.compare(this.book1, this.book2), -1)
        && t.checkExpect(this.compareAuthor.compare(this.book2, this.book1), 1)
        && t.checkExpect(this.compareAuthor.compare(this.book5, this.book6), -1)
        && t.checkExpect(this.compareAuthor.compare(this.book2, this.book2), 0)
        && t.checkExpect(this.comparePrice.compare(this.book2, this.book1), 7)
        && t.checkExpect(this.comparePrice.compare(this.book5, this.book6), 13)
        && t.checkExpect(this.comparePrice.compare(this.book6, this.book5), -13)
        && t.checkExpect(this.comparePrice.compare(this.book5, this.book5), 0);
  }

  // tests for insert method
  boolean testInsert(Tester t) {
    return t.checkExpect(this.p1Node.insert(this.book2), this.tree3)
        && t.checkExpect(this.tree4.insert(this.book3), this.tree5)
        && t.checkExpect(this.pLeaf.insert(this.book1), this.p1Node);
  }

  // tests for the present method
  boolean testPresent(Tester t) {
    return t.checkExpect(this.tree1.present(this.book1), true)
        && t.checkExpect(this.p1Node.present(this.book1), true)
        && t.checkExpect(this.p1Node.present(this.book2), false)
        && t.checkExpect(this.tree5.present(this.book2), true)
        && t.checkExpect(this.aLeaf.present(this.book1), false);
  }

  // tests for the getLeftmost method
  boolean testGetLeftMost(Tester t) {
    return t.checkExpect(this.tree2.getLeftmost(), this.book2)
        && t.checkExpect(this.tree4.getLeftmost(), this.book1)
        // testing the helper
        && t.checkExpect(this.tree2.getLeftMostHelper(this.book1), this.book2)
        && t.checkExpect(this.tree1.getLeftMostHelper(this.book2), this.book2)
        && t.checkException(new RuntimeException("No leftmost item of an empty tree"),
            aLeaf, "getLeftmost");
  }

  // tests for the getRight method
  boolean testGetRight(Tester t) {
    return t.checkExpect(this.tree3.getRight(), this.p2Node)
        && t.checkExpect(this.tree4.getRight(), this.p2Node)
        // testing the helper
        && t.checkExpect(this.tree3.getRightHelper(this.book1), this.p2Node)
        && t.checkException(new RuntimeException("No right of an empty tree"), aLeaf, "getRight");
  }
  
  // tests for sameNode method
  boolean testSameNode(Tester t) {
    return t.checkExpect(this.aLeaf.sameNode((Node<Book>) this.tree3), false)
        && t.checkExpect(this.tree2.sameNode((Node<Book>)this.tree2), true);
  }
  
  //tests for sameLeaf method
  boolean testSameLeaf(Tester t) {
    return t.checkExpect(this.aLeaf.sameLeaf((Leaf<Book>) this.aLeaf), true)
        && t.checkExpect(this.tLeaf.sameLeaf((Leaf<Book>)this.tLeaf), true)
        && t.checkExpect(this.tree5.sameLeaf((Leaf<Book>) this.aLeaf), false);
  }

  // tests for sameTree method
  boolean testSameTree(Tester t) {
    return t.checkExpect(this.tree3.sameTree(this.tree3), true)
        && t.checkExpect(this.tree2.sameTree(this.tree3), false)
        && t.checkExpect(this.pLeaf.sameTree(this.pLeaf), true);
  }

  // tests for sameData method
  boolean testSameData(Tester t) {
    return t.checkExpect(this.tree1.sameData(this.tree1), true)
        && t.checkExpect(this.tree4.sameData(this.tree1), false)
        && t.checkExpect(this.tree1.sameData(this.tree2), true)
        && t.checkExpect(this.pLeaf.sameData(this.tree1), false)
        && t.checkExpect(this.aLeaf.sameData(this.tree1), false)
        // testing the helper
        && t.checkExpect(this.aLeaf.sameDataHelper(this.aLeaf), true)
        && t.checkExpect(this.tree1.sameDataHelper(this.tree1), true)
        && t.checkExpect(this.tree4.sameDataHelper(this.aLeaf), false);
  }
  
  // tests for buildList method
  boolean testBuildList(Tester t) {
    return t.checkExpect(this.tree3.buildList(), this.tree1List)
        && t.checkExpect(this.tree6.buildList(), this.tree2List)
        && t.checkExpect(this.aLeaf.buildList(), this.emptyList);
  }

}

