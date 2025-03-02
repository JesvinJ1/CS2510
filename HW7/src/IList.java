// represents a generic list
interface IList<T> {

}

// represents an empty generic list
class MtList<T> implements IList<T> {

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

// TODO
interface IListVisitor {
  
}