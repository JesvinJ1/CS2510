import java.util.function.BiFunction;
import java.util.function.Function;
import java.util.function.Predicate;

import tester.Tester;


//represents a generic list
interface IList<T> {
  //filter this list using the given predicate
  IList<T> filter(Predicate<T> pred);

  //map a given function onto every member of this list and return a list of the results
  <U> IList<U> map(Function<T, U> converter);

  //combine the items in this list using the given function
  <U> U fold(BiFunction<T, U, U> converter, U initial);  
}

//represents a generic empty list
class MtList<T> implements IList<T> {

  MtList() {}

  /* CLASS TEMPLATE
  FIELDS:
  ... N/A ...
  METHODS:
  ... this.filter(Predicate<T> pred) ...      --IList<T>
  ... this.map(Function<T, U> converter) ...  --<U> IList<U>
  ... this.fold(BiFunction<T, U, U> converter, U initial) ...   --<U> U
  METHODS OF FIELDS:
  ... N/A ...
   */

  //filter this empty list using the given predicate
  public IList<T> filter(Predicate<T> pred) {
    return new MtList<T>();
  }

  //map a given function onto every member of this list and return a list of the results
  public <U> IList<U> map(Function<T, U> converter) {
    return new MtList<U>();
  }

  //combine the items in this list using the given function
  public <U> U fold(BiFunction<T, U, U> converter, U initial) {
    return initial;
  }
}

//represents a generic non-empty list
class ConsList<T> implements IList<T> {
  T first;
  IList<T> rest;

  ConsList(T first, IList<T> rest) {
    this.first = first;
    this.rest = rest;
  }

  /* CLASS TEMPLATE
  FIELDS:
  ... this.first ...    -- T
  ... this.rest ...     -- IList<T>
  METHODS:
  ... this.filter(Predicate<T> pred) ...      --IList<T>
  ... this.map(Function<T, U> converter) ...  --<U> IList<U>
  ... this.fold(BiFunction<T, U, U> converter, U initial) ...   --<U> U
  METHODS OF FIELDS:
  ... this.first.filter(Predicate<T> pred) ...      --IList<T>
  ... this.first.map(Function<T, U> converter) ...  --<U> IList<U>
  ... this.first.fold(BiFunction<T, U, U> converter, U initial) ...   --<U> U
  ... this.rest.filter(Predicate<T> pred) ...      --IList<T>
  ... this.rest.map(Function<T, U> converter) ...  --<U> IList<U>
  ... this.rest.fold(BiFunction<T, U, U> converter, U initial) ...   --<U> U
   */

  //filter this non-empty list using the given predicate
  public IList<T> filter(Predicate<T> pred) {
    if (pred.test(this.first)) {
      return new ConsList<T>(this.first, this.rest.filter(pred));
    }
    else {
      return this.rest.filter(pred);
    }
  }

  //map a given function onto every member of this list and return a list of the results
  public <U> IList<U> map(Function<T, U> converter) {
    return new ConsList<U>(converter.apply(this.first), this.rest.map(converter));
  }

  //combine the items in this list using the given function
  public <U> U fold(BiFunction<T, U, U> converter, U initial) {
    return converter.apply(this.first, this.rest.fold(converter,initial));
  }
}

// class that helps to check if starts with "J"
class JStart implements Predicate<String> {
  public boolean test(String s) {
    return s.startsWith("J");
  }
  
  /* CLASS TEMPLATE
  FIELDS:
  ... N/A ...
  METHODS:
  ... this.test(String s) ...     -- boolean
  METHODS OF FIELDS:
  ... N/A ...
   */
}

// class that helps to check if ends with "er"
class EndsWithER implements Predicate<String> {
  public boolean test(String s) {
    return s.endsWith("er");
  }
  /* CLASS TEMPLATE
  FIELDS:
  ... N/A ...
  METHODS:
  ... this.test(String s) ...     -- boolean
  METHODS OF FIELDS:
  ... N/A ...
   */
}

// class that helps count the amount of months that met condition (end w/ "er")
class countHelper implements BiFunction<String, Integer, Integer> {
  public Integer apply(String s, Integer counter) {
    return counter + 1;
  }
  /* CLASS TEMPLATE
  FIELDS:
  ... N/A ...
  METHODS:
  ... this.apply(String s, Integer counter) ...     -- Integer
  METHODS OF FIELDS:
  ... N/A ...
   */
}

// class that helps abbreviate to the first 3 characters
class abbreviate implements Function<String, String> {
  public String apply(String s) {
    return s.substring(0, 3);
  }
  /* CLASS TEMPLATE
  FIELDS:
  ... N/A ...
  METHODS:
  ... this.apply(String s) ...     -- String
  METHODS OF FIELDS:
  ... N/A ...
   */
}

// Examples class
class ExamplesLists{

  ExamplesLists() {}

  // list of all months
  IList<String> months = new ConsList<>("January",
      new ConsList<>("February",
          new ConsList<>("March",
              new ConsList<>("April",
                  new ConsList<>("May",
                      new ConsList<>("June",
                          new ConsList<>("July",
                              new ConsList<>("August",
                                  new ConsList<>("September",
                                      new ConsList<>("October",
                                          new ConsList<>("November",
                                              new ConsList<>("December",
                                                  new MtList<>()
                                                  ))))))))))));
  
  // empty list of months
  IList<String> emptyMonths = new MtList<>();

  // months that start with "J"
  IList<String> jMonths = new ConsList<>("January",
      new ConsList<>("June",
          new ConsList<>("July",
              new MtList<>())));

  // months ending in "er"
  IList<String> erMonths = new ConsList<>("September",
      new ConsList<>("October",
          new ConsList<>("November",
              new ConsList<>("December",
                  new MtList<>()))));
  
  // first three letters of a month
  IList<String> threeLetterMonths = new ConsList<>("Jan",
      new ConsList<>("Feb",
          new ConsList<>("Mar",
              new ConsList<>("Apr",
                  new ConsList<>("May",
                      new ConsList<>("Jun",
                          new ConsList<>("Jul",
                              new ConsList<>("Aug",
                                  new ConsList<>("Sep",
                                      new ConsList<>("Oct",
                                          new ConsList<>("Nov",
                                              new ConsList<>("Dec",
                                                  new MtList<>()))))))))))));

  // tests for finding months that start with J using filter
  boolean testFindMonthsWithLetterJ(Tester t) {
    return t.checkExpect(this.months.filter(new JStart()), this.jMonths)
        && t.checkExpect(this.emptyMonths.filter(new JStart()), this.emptyMonths);
  }

  // tests for counting the amount of months that end in "er"
  boolean testEndInER(Tester t) {
    return t.checkExpect(this.months.filter(new EndsWithER()).fold(new countHelper(), 0), 4)
        && t.checkExpect(this.emptyMonths.filter(new EndsWithER()).fold(new countHelper(), 0), 0);
  }
  
  // tests for abbreviating the months to the first 3 letters of the month
  boolean testThreeLetterAbreviation(Tester t) {
    return t.checkExpect(this.months.map(new abbreviate()), threeLetterMonths)
        && t.checkExpect(this.emptyMonths.map(new abbreviate()), emptyMonths);
  }
}